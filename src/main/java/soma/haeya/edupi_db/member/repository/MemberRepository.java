package soma.haeya.edupi_db.member.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import soma.haeya.edupi_db.member.domain.Member;

public interface MemberRepository extends JpaRepository<Member, Long> {

    boolean existsByEmail(String email);

    Member findMemberByEmail(String email);
}



