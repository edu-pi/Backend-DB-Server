package soma.edupimeta.classroom.account.service.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import soma.edupimeta.classroom.account.service.domain.ClassroomAccount;

@Repository
public interface ClassroomAccountRepository extends JpaRepository<ClassroomAccount, Long> {

    boolean existsByAccountIdAndClassroomId(Long accountId, Long classroomId);

}
