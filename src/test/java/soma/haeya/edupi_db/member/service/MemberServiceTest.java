package soma.haeya.edupi_db.member.service;

import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

import java.util.Optional;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.crypto.password.PasswordEncoder;
import soma.haeya.edupi_db.member.domain.Member;
import soma.haeya.edupi_db.member.dto.request.LoginRequest;
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
            .isInstanceOf(UserFriendlyException.class)
            .hasMessage("이메일 혹은 비밀번호가 일치하지 않습니다.");

    }

    @Test
    @DisplayName("db에 저장된 member와 다른 비밀번호를 사용해 로그인")
    void findMemberByEmailAndPasswordFailPassword() {

        LoginRequest loginRequest = new LoginRequest("asdf@naver.com", "asdf1234");

        when(memberRepository.findMemberByEmail(anyString())).thenReturn(
            Optional.of(
                Member.builder()
                    .email("asdf@naver.com")
                    .password("differentPassword")
                    .role("ROLE_USER")
                    .name("홍길동")
                    .build())
        );
        when(passwordEncoder.matches(anyString(), anyString())).thenThrow(
            new UserFriendlyException("이메일 혹은 비밀번호가 일치하지 않습니다.")
        );

        Assertions.assertThatThrownBy(() -> memberService.findMemberByEmailAndPassword(loginRequest))
            .isInstanceOf(UserFriendlyException.class)
            .hasMessage("이메일 혹은 비밀번호가 일치하지 않습니다.");

    }

    @Test
    @DisplayName("유효한 이메일과 비밀번호를 사용해 로그인")
    void findMemberByEmailAndPasswordSuccess() {

        LoginRequest loginRequest = new LoginRequest("asdf@naver.com", "asdf1234");
        LoginResponse expected = new LoginResponse("asdf@naver.com", "홍길동", "ROLE_USER");

        when(memberRepository.findMemberByEmail(anyString()))
            .thenReturn(
                Optional.of(Member.builder()
                    .email("asdf@naver.com")
                    .password("differentPassword")
                    .role("ROLE_USER")
                    .name("홍길동")
                    .build()
                ));
        when(passwordEncoder.matches(anyString(), anyString())).thenReturn(true);

        LoginResponse result = memberService.findMemberByEmailAndPassword(loginRequest);

        Assertions.assertThat(result).isEqualTo(expected);
    }
}