package soma.edupimeta.account.service;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Optional;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.transaction.annotation.Transactional;
import soma.edupimeta.account.exception.InvalidInputException;
import soma.edupimeta.account.models.EmailRequest;
import soma.edupimeta.account.models.LoginRequest;
import soma.edupimeta.account.models.SignupRequest;
import soma.edupimeta.account.service.domain.Account;
import soma.edupimeta.account.service.domain.AccountRole;
import soma.edupimeta.account.service.repository.AccountRepository;

@ExtendWith(MockitoExtension.class)
class AccountServiceTest {

    @Mock
    private AccountRepository accountRepository;

    @Mock
    private PasswordEncoder passwordEncoder;

    @InjectMocks
    private AccountService accountService;

    @Test
    @DisplayName("db에 저장되지 않은 이메일을 사용해 로그인")
    void loginFailEmail() {

        LoginRequest loginRequest = new LoginRequest("asdf@naver.com", "asdf1234");

        when(accountRepository.findAccountByEmail(anyString())).thenThrow(
            new InvalidInputException("이메일 혹은 비밀번호가 일치하지 않습니다."));

        Assertions.assertThatThrownBy(() -> accountService.login(loginRequest.getEmail(), loginRequest.getPassword()))
            .isInstanceOf(InvalidInputException.class).hasMessage("이메일 혹은 비밀번호가 일치하지 않습니다.");

    }

    @Test
    @DisplayName("db에 저장된 account와 다른 비밀번호를 사용해 로그인")
    void loginFailPassword() {

        LoginRequest loginRequest = new LoginRequest("asdf@naver.com", "differentPassword");

        when(accountRepository.findAccountByEmail(anyString())).thenReturn(Optional.of(
            Account.builder().email("asdf@naver.com").password("asdf1234!").role(AccountRole.USER).name("홍길동")
                .build()));
        when(passwordEncoder.matches(anyString(), anyString())).thenThrow(
            new InvalidInputException("이메일 혹은 비밀번호가 일치하지 않습니다."));

        Assertions.assertThatThrownBy(() -> accountService.login(loginRequest.getEmail(), loginRequest.getPassword()))
            .isInstanceOf(InvalidInputException.class).hasMessage("이메일 혹은 비밀번호가 일치하지 않습니다.");

    }

    @Test
    @DisplayName("저장된 계정 활성화")
    void activateAccountSuccess() {
        // given
        EmailRequest emailRequest = new EmailRequest("test@naver.com");
        Account mockMember = mock(Account.class);

        when(accountRepository.findAccountByEmail(anyString()))
            .thenReturn(Optional.of(mockMember));

        // When
        accountService.verifyAccountByEmail(emailRequest.getEmail());

        // Then
        verify(mockMember).activate();  // activate 메소드 호출 검증
    }

    @Test
    @DisplayName("저장되지 않은 계정 활성화")
    void activateAccountFailNotExist() {
        // given
        EmailRequest emailRequest = new EmailRequest("test@naver.com");

        when(accountRepository.findAccountByEmail(anyString()))
            .thenReturn(Optional.empty());

        // When & Then
        Assertions.assertThatThrownBy(() -> accountService.verifyAccountByEmail(emailRequest.getEmail()))
            .isInstanceOf(InvalidInputException.class).hasMessage("존재하지 않는 회원입니다.");
    }

    @Test
    @DisplayName("유효한 이메일과 비밀번호를 사용해 로그인")
    void loginSuccess() {

        LoginRequest loginRequest = new LoginRequest("asdf@naver.com", "asdf1234!");
        Account account = Account.builder()
            .email("asdf@naver.com")
            .password("asdf1234!")
            .name("홍길동")
            .build();

        when(accountRepository.findAccountByEmail(anyString())).thenReturn(Optional.of(
            Account.builder().email("asdf@naver.com").password("asdf1234!").role(AccountRole.USER).name("홍길동")
                .build()));
        when(passwordEncoder.matches(anyString(), anyString())).thenReturn(true);

        Account result = accountService.login(loginRequest.getEmail(), loginRequest.getPassword());

        verify(accountRepository).findAccountByEmail(anyString());
        verify(passwordEncoder).matches(anyString(), anyString());
    }

    @Test
    @DisplayName("중복된 이메일로 회원가입 시도")
    void saveAccountFailEmail() {
        // Given
        SignupRequest signupRequest = SignupRequest.builder()
            .email("invalid-email")  // 유효하지 않은 이메일
            .name("김미미")
            .password("qpwoeiruty00@")
            .build();

        when(accountRepository.existsByEmail(anyString())).thenReturn(true);

        // Then & When
        Assertions.assertThatThrownBy(() -> accountService.signup(signupRequest))
            .isInstanceOf(InvalidInputException.class)
            .hasMessage("중복된 이메일입니다. 다른 이메일을 사용해주세요.");

    }

    @Test
    @DisplayName("회원 정보 저장 중 DB 오류")
    void saveAccountFailDb() {
        // Given
        SignupRequest signupRequest = SignupRequest.builder()
            .email("asdf@naver.com")
            .name("김미미")
            .password("qpwoeiruty00@")
            .build();

        when(accountRepository.save(any(Account.class))).thenThrow(mock(DataIntegrityViolationException.class));

        // Then & When
        Assertions.assertThatThrownBy(() -> accountService.signup(signupRequest))
            .isInstanceOf(DataIntegrityViolationException.class);

    }

    @Test
    @Transactional
    @DisplayName("회원가입 성공")
    void saveAccountSuccess() {
        // Given
        SignupRequest signupRequest = SignupRequest.builder()
            .email("jakdiv@naver.com")  // 유효하지 않은 이메일
            .name("김미미")
            .password("addaqcww@")
            .build();

        Account account = signupRequest.toEntity();

        // `save` 메서드 호출 시 반환할 객체 설정
        when(accountRepository.save(any(Account.class))).thenReturn(account);

        // When
        accountService.signup(signupRequest);

        // Then
        verify(accountRepository).save(any(Account.class));   // 호출 되었는지 검증


    }
}