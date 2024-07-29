package soma.haeya.edupi_db.user;

import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import soma.haeya.edupi_db.user.domain.Member;
import soma.haeya.edupi_db.user.dto.request.SignupRequest;
import soma.haeya.edupi_db.user.exception.UserFriendlyException;

import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class MemberService {

    private final MemberRepository memberRepository;

    @Transactional
    public void saveUser(SignupRequest signupRequest){
        // 이메일 중복 체크
        if (memberRepository.existsByEmail(signupRequest.getEmail())) {
            throw new UserFriendlyException("중복된 이메일입니다. 다른 이메일을 사용해주세요.");
        }

        Member member = createMember(signupRequest);

        // 저장
        try {
            memberRepository.save(member);
        } catch (DataIntegrityViolationException e) {
            // 사용자에게 반환할 에러 메시지
            throw new UserFriendlyException("데이터베이스 제약 조건 위반: " + e.getMessage());
        }
    }

    private Member createMember (SignupRequest signupRequest) {
        return new Member(signupRequest.getEmail(), signupRequest.getPassword(), signupRequest.getName(), signupRequest.getPhoneNumber());
    }

    public List<Member> findMembers(){
        return memberRepository.findAll();
    }

    public Member findMemberById(Long id) {
        return memberRepository.findById(id).orElse(null);
    }


}
