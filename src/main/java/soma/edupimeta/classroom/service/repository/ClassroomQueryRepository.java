package soma.edupimeta.classroom.service.repository;

import java.util.List;
import org.springframework.stereotype.Repository;
import soma.edupimeta.classroom.models.MyClassroomResponse;
import soma.edupimeta.classroomAccount.service.domain.ClassroomAccountRole;

@Repository
public interface ClassroomQueryRepository {

    boolean existsHostClassroomByAccountIdAndName(Long accountId, String name);

    List<MyClassroomResponse> findMyClassroomByClassroomAccountRole(Long accountId, ClassroomAccountRole role);

}
