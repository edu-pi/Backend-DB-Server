package soma.edupi.db.member.repository;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import soma.edupi.db.member.domain.Member;

public interface MemberRepository extends JpaRepository<Member, Long> {

    boolean existsByEmail(String email);

    Optional<Member> findMemberByEmail(String email);
}



