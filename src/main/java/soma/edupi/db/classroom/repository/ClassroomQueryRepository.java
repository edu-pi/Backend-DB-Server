package soma.edupi.db.classroom.repository;

import org.springframework.stereotype.Repository;

@Repository
public interface ClassroomQueryRepository {

    public boolean existsLeaderClassroomByAccountIdAndName(Long accountId, String name);
}
