package soma.edupimeta.classroom.service.repository;

import java.util.List;
import org.springframework.stereotype.Repository;
import soma.edupimeta.classroom.models.ClassroomActionInfo;
import soma.edupimeta.classroom.models.MyClassroomResponse;

@Repository
public interface ClassroomQueryRepository {

    boolean existsHostClassroomByAccountIdAndName(Long accountId, String name);

    List<MyClassroomResponse> findMyClassrooms(Long accountId);

    List<ClassroomActionInfo> findClassroomInfo(Long classroomId);
}
