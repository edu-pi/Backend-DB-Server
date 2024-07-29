package soma.haeya.edupi_db.user;

import org.springframework.data.jpa.repository.JpaRepository;
import soma.haeya.edupi_db.user.domain.Member;

public interface UserRepository extends JpaRepository<Member, Long> {
}



