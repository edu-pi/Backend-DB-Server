package soma.edupi.db.classroom.repository;

import org.springframework.stereotype.Repository;

@Repository
public interface ClassroomQueryRepository {

    boolean existsHostClassroomByAccountIdAndName(Long accountId, String name);
}
