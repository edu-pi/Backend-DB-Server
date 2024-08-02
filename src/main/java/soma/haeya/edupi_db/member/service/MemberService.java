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
import soma.haeya.edupi_db.member.exception.UserFriendlyException;
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
            throw new UserFriendlyException("중복된 이메일입니다. 다른 이메일을 사용해주세요.");
        }

        // 저장
        try {
            Member member = signupRequest.toEntity();
            // 비밀번호 암호화
            member.encodePassword(passwordEncoder);
            memberRepository.save(member);
        } catch (DataIntegrityViolationException e) {
            // 사용자에게 반환할 에러 메시지
            throw new UserFriendlyException("데이터베이스 제약 조건 위반: " + e.getMessage());
        }
    }

    public LoginResponse findMemberByEmailAndPassword(LoginRequest loginRequest) {
        Member findMember = memberRepository.findMemberByEmail(loginRequest.getEmail());

        if (passwordEncoder.matches(findMember.getPassword(), loginRequest.getPassword())) {
            throw new UserFriendlyException("비밀번호가 일치하지 않습니다.");
        }

        return LoginResponse.of(findMember);
    }


}
