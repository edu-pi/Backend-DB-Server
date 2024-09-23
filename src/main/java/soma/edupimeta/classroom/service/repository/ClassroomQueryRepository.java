package soma.edupimeta.classroom.service.repository;

import java.util.List;
import org.springframework.stereotype.Repository;
import soma.edupimeta.classroom.models.ClassroomResponse;

@Repository
public interface ClassroomQueryRepository {

    boolean existsHostClassroomByAccountIdAndName(Long accountId, String name);

    public List<ClassroomResponse> findAllByAccountIdWithGuestCount(Long accountId);

}
