package soma.edupi.db.classroom.repository;

import java.util.List;
import org.springframework.stereotype.Repository;
import soma.edupi.db.classroom.models.response.ClassroomResponse;

@Repository
public interface ClassroomQueryRepository {

    boolean existsHostClassroomByAccountIdAndName(Long accountId, String name);

    public List<ClassroomResponse> findAllByAccountIdWithGuestCount(Long accountId);

}
