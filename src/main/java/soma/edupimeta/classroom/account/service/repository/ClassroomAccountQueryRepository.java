package soma.edupimeta.classroom.account.service.repository;

import java.util.List;
import soma.edupimeta.classroom.account.models.ClassroomAccountResponse;

public interface ClassroomAccountQueryRepository {

    Long updateActionStatusForClassroom(Long classroomId);

    List<ClassroomAccountResponse> findByClassroomId(Long classroomId);
}
