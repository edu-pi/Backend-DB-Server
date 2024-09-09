package soma.haeya.edupi_db.member.service;

import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import soma.haeya.edupi_db.member.domain.Member;
import soma.haeya.edupi_db.member.dto.request.LoginRequest;
import soma.haeya.edupi_db.member.dto.request.SignupRequest;
import soma.haeya.edupi_db.member.dto.response.LoginResponse;
import soma.haeya.edupi_db.member.exception.InvalidInputException;
import soma.haeya.edupi_db.member.exception.ServerException;
import soma.haeya.edupi_db.member.repository.MemberRepository;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class MemberService {

    private final MemberRepository memberRepository;
    private final PasswordEncoder passwordEncoder;

    @Transactional
    public void saveMember(SignupRequest signupRequest) {
        // 이메일 중복 체크
        if (memberRepository.existsByEmail(signupRequest.getEmail())) {
            throw new InvalidInputException("중복된 이메일입니다. 다른 이메일을 사용해주세요.");
        }
        try {
            Member member = signupRequest.toEntity();
            member.encodePassword(passwordEncoder); // 비밀번호 암호화

            memberRepository.save(member);
        }
        catch (DataIntegrityViolationException e) {
            throw new ServerException("DB 무결성 위반 오류: " + e.getMessage());
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


}
