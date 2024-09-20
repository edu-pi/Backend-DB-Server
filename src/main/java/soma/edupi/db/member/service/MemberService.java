package soma.edupi.db.member.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import soma.edupi.db.member.domain.Member;
import soma.edupi.db.member.exception.InvalidInputException;
import soma.edupi.db.member.exception.ServerException;
import soma.edupi.db.member.models.request.EmailRequest;
import soma.edupi.db.member.models.request.LoginRequest;
import soma.edupi.db.member.models.request.SignupRequest;
import soma.edupi.db.member.models.response.LoginResponse;
import soma.edupi.db.member.repository.MemberRepository;

@Slf4j
@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class MemberService {

    private final MemberRepository memberRepository;
    private final PasswordEncoder passwordEncoder;

    public void saveMember(SignupRequest signupRequest) {
        // 이메일 중복 체크
        if (memberRepository.existsByEmail(signupRequest.getEmail())) {
            throw new InvalidInputException("중복된 이메일입니다. 다른 이메일을 사용해주세요.");
        }
        try {
            Member member = signupRequest.toEntity();
            member.encodePassword(passwordEncoder); // 비밀번호 암호화

            memberRepository.save(member);
        } catch (DataIntegrityViolationException e) {
            throw new ServerException("DB 저장 실패");
        } catch (Exception e) {
            throw new ServerException("알 수 없는 오류: " + e.getMessage());
        }
    }

    public LoginResponse findMemberByEmailAndPassword(LoginRequest loginRequest) {
        Member findMember = memberRepository.findMemberByEmail(loginRequest.getEmail()).orElseThrow(
            // 이메일이 일치하지 않는 경우
            () -> new InvalidInputException("이메일 혹은 비밀번호가 일치하지 않습니다.")
        );

        // 비밀번호가 일치하지 않는 경우
        if (!passwordEncoder.matches(loginRequest.getPassword(), findMember.getPassword())) {
            throw new InvalidInputException("이메일 혹은 비밀번호가 일치하지 않습니다.");
        }

        return LoginResponse.of(findMember);
    }

    @Transactional
    public void activateAccount(EmailRequest emailRequest) {
        // 이메일이 일치하는 유저 찾기
        Member findMember = memberRepository.findMemberByEmail(emailRequest.getEmail())
            .orElseThrow(() -> {
                    log.info("이메일 인증 실패, 함수 = activateAccount, 이메일 = {} ", emailRequest.getEmail());
                    return new InvalidInputException("존재하지 않는 회원입니다.");
                }
            );
        // 계정 활성
        findMember.activate();
        memberRepository.save(findMember);

    }


}
