package soma.haeya.edupi_db.member.service;

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
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.client.HttpClientErrorException;
import soma.haeya.edupi_db.member.domain.Member;
import soma.haeya.edupi_db.member.dto.request.LoginRequest;
import soma.haeya.edupi_db.member.dto.request.SignupRequest;
import soma.haeya.edupi_db.member.dto.response.LoginResponse;
import soma.haeya.edupi_db.member.exception.UserFriendlyException;
import soma.haeya.edupi_db.member.repository.MemberRepository;

@ExtendWith(MockitoExtension.class)
class MemberServiceTest {

    @Mock
    private MemberRepository memberRepository;

    @Mock
    private PasswordEncoder passwordEncoder;

    @InjectMocks
    private MemberService memberService;

    @Test
    @DisplayName("db에 저장되지 않은 이메일을 사용해 로그인")
    void findMemberByEmailAndPasswordFailEmail() {

        LoginRequest loginRequest = new LoginRequest("asdf@naver.com", "asdf1234");

        when(memberRepository.findMemberByEmail(anyString())).thenThrow(
            new UserFriendlyException("이메일 혹은 비밀번호가 일치하지 않습니다."));

        Assertions.assertThatThrownBy(() -> memberService.findMemberByEmailAndPassword(loginRequest))
            .isInstanceOf(UserFriendlyException.class).hasMessage("이메일 혹은 비밀번호가 일치하지 않습니다.");

    }

    @Test
    @DisplayName("db에 저장된 member와 다른 비밀번호를 사용해 로그인")
    void findMemberByEmailAndPasswordFailPassword() {

        LoginRequest loginRequest = new LoginRequest("asdf@naver.com", "differentPassword");

        when(memberRepository.findMemberByEmail(anyString())).thenReturn(Optional.of(
            Member.builder().email("asdf@naver.com").password("asdf1234!").role("ROLE_USER").name("홍길동").build()));
        when(passwordEncoder.matches(anyString(), anyString())).thenThrow(
            new UserFriendlyException("이메일 혹은 비밀번호가 일치하지 않습니다."));

        Assertions.assertThatThrownBy(() -> memberService.findMemberByEmailAndPassword(loginRequest))
            .isInstanceOf(UserFriendlyException.class).hasMessage("이메일 혹은 비밀번호가 일치하지 않습니다.");

    }

    @Test
    @DisplayName("유효한 이메일과 비밀번호를 사용해 로그인")
    void findMemberByEmailAndPasswordSuccess() {

        LoginRequest loginRequest = new LoginRequest("asdf@naver.com", "asdf1234!");
        LoginResponse expected = new LoginResponse("asdf@naver.com", "홍길동", "ROLE_USER");

        when(memberRepository.findMemberByEmail(anyString())).thenReturn(Optional.of(
            Member.builder().email("asdf@naver.com").password("asdf1234!").role("ROLE_USER").name("홍길동").build()));
        when(passwordEncoder.matches(anyString(), anyString())).thenReturn(true);

        LoginResponse result = memberService.findMemberByEmailAndPassword(loginRequest);

        Assertions.assertThat(result).isEqualTo(expected);
    }

    @Test
    @DisplayName("중복된 이메일로 회원가입 시도")
    void saveMemberFailEmail(){
        // Given
        SignupRequest signupRequest = SignupRequest.builder()
            .email("invalid-email")  // 유효하지 않은 이메일
            .name("김미미")
            .password("qpwoeiruty00@")
            .build();

        when(memberRepository.existsByEmail(anyString())).thenReturn(true);

        // Then & When
        Assertions.assertThatThrownBy(() -> memberService.saveMember(signupRequest))
            .isInstanceOf(UserFriendlyException.class)
            .hasMessage("중복된 이메일입니다. 다른 이메일을 사용해주세요.");

    }

    @Test
    @DisplayName("회원 정보 저장 중 DB 오류")
    void saveMemberFailDb(){
        // Given
        SignupRequest signupRequest = SignupRequest.builder()
            .email("asdf@naver.com")
            .name("김미미")
            .password("qpwoeiruty00@")
            .build();

        // 중복 이메일 아님
        when(memberRepository.existsByEmail(anyString())).thenReturn(false);

        DataIntegrityViolationException exception = mock(DataIntegrityViolationException.class);
        when(memberRepository.save(any(Member.class))).thenThrow(exception);

        // Then & When
        Assertions.assertThatThrownBy(() -> memberService.saveMember(signupRequest))
            .isInstanceOf(UserFriendlyException.class)
            .hasMessage("데이터베이스 제약 조건 위반");

    }

    @Test
    @DisplayName("회원가입 성공")
    void saveMemberSuccess(){
        // Given
        SignupRequest signupRequest = SignupRequest.builder()
            .email("jakdiv@naver.com")  // 유효하지 않은 이메일
            .name("김미미")
            .password("addaqcww@")
            .build();

        when(memberRepository.existsByEmail(signupRequest.getEmail())).thenReturn(false);

        // When
        memberService.saveMember(signupRequest);

        // Then
        verify(memberRepository).save(any(Member.class));   // 호출 되었는지 검증

    }
}