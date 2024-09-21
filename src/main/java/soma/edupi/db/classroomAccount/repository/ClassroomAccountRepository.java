package soma.edupi.db.classroomAccount.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import soma.edupi.db.classroomAccount.domain.ClassroomAccount;

@Repository
public interface ClassroomAccountRepository extends JpaRepository<ClassroomAccount, Long> {

}
