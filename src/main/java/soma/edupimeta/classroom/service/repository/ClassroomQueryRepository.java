package soma.edupimeta.classroom.service.repository;

import java.util.List;
import org.springframework.stereotype.Repository;
import soma.edupimeta.classroom.account.service.domain.ClassroomAccountRole;
import soma.edupimeta.classroom.models.MyClassroomResponse;

@Repository
public interface ClassroomQueryRepository {

    boolean existsHostClassroomByAccountIdAndName(Long accountId, String name);

    List<MyClassroomResponse> findMyClassroomByClassroomAccountRole(Long accountId, ClassroomAccountRole role);

}
