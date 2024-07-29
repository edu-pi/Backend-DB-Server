package soma.haeya.edupi_db.user;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import soma.haeya.edupi_db.user.domain.Member;
import soma.haeya.edupi_db.user.domain.request.SignUpDTO;

import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class MemberService {

    private final MemberRepository memberRepository;

    @Transactional
    public void saveUser(SignUpDTO signUpDTO){
        Member member = new Member();
        member.setEmail(signUpDTO.getEmail());
        member.setPassword(signUpDTO.getPassword());
        member.setName(signUpDTO.getName());
        member.setPhoneNumber(signUpDTO.getPhoneNumber());
        member.setIs_enabled(true);

        memberRepository.save(member);
    }

    public List<Member> findUsers(){
        return memberRepository.findAll();
    }

    public Member findUserById(Long id) {
        return memberRepository.findById(id).orElse(null);
    }


}
