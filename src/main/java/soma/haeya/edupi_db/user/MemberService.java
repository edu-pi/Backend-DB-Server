package soma.haeya.edupi_db.user;

import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import soma.haeya.edupi_db.user.domain.Member;
import soma.haeya.edupi_db.user.domain.request.SignUpDTO;
import soma.haeya.edupi_db.user.exception.UserFriendlyException;

import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class MemberService {

    private final MemberRepository memberRepository;

    @Transactional
    public void saveUser(SignUpDTO signUpDTO){
        // 이메일 중복 체크
        if (memberRepository.existsByEmail(signUpDTO.getEmail())) {
            throw new UserFriendlyException("중복된 이메일입니다. 다른 이메일을 사용해주세요.");
        }

        Member member = new Member();
        member.setEmail(signUpDTO.getEmail());
        member.setPassword(signUpDTO.getPassword());
        member.setName(signUpDTO.getName());
        member.setPhoneNumber(signUpDTO.getPhoneNumber());
        member.setIs_enabled(true);

        // 저장
        try {
            memberRepository.save(member);
        } catch (DataIntegrityViolationException e) {
            // 사용자에게 반환할 에러 메시지
            throw new UserFriendlyException("데이터베이스 제약 조건 위반: " + e.getMessage());
        }
    }

    public List<Member> findUsers(){
        return memberRepository.findAll();
    }

    public Member findUserById(Long id) {
        return memberRepository.findById(id).orElse(null);
    }


}
