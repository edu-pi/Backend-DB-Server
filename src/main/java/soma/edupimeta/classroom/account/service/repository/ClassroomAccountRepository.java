package soma.edupimeta.classroom.account.service.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import soma.edupimeta.classroom.account.service.domain.ClassroomAccount;
import soma.edupimeta.classroom.account.service.domain.ClassroomAccountRole;

@Repository
public interface ClassroomAccountRepository
    extends JpaRepository<ClassroomAccount, Long>, ClassroomAccountQueryRepository {

    boolean existsByAccountIdAndClassroomId(Long accountId, Long classroomId);

    boolean existsByAccountIdAndClassroomIdAndRole(Long accountId, Long classroomId, ClassroomAccountRole role);
}
