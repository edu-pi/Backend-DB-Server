package soma.edupimeta.classroom.account.service.repository;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import soma.edupimeta.classroom.account.service.domain.ClassroomAccount;

@Repository
public interface ClassroomAccountRepository
    extends JpaRepository<ClassroomAccount, Long>, ClassroomAccountQueryRepository {

    boolean existsByAccountIdAndClassroomId(Long accountId, Long classroomId);

    Optional<ClassroomAccount> findByClassroomIdAndAccountId(Long classroomId, Long accountId);

    void deleteByClassroomId(Long classroomId);
}
