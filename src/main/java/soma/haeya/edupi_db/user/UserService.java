package soma.haeya.edupi_db.user;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import soma.haeya.edupi_db.user.domain.Member;

import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    @Transactional
    public void saveUser(Member member){
        userRepository.save(member);
    }

    public List<Member> findUsers(){
        return userRepository.findAll();
    }

    public Member findUserById(Long id) {
        return userRepository.findById(id).orElse(null);
    }


}
