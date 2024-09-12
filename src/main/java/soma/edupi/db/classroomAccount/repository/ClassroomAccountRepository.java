package soma.edupi.db.classroomAccount.repository;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import soma.edupi.db.classroomAccount.domain.ClassroomAccount;
import soma.edupi.db.classroomAccount.domain.ClassroomAccountRole;

@Repository
public interface ClassroomAccountRepository extends JpaRepository<ClassroomAccount, Long> {

    Optional<ClassroomAccount> findByAccountIdAndRole(Long classroomId, ClassroomAccountRole role);

}
