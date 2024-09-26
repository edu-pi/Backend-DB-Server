package soma.edupimeta.classroom.account.service.repository;

import soma.edupimeta.classroom.account.service.domain.ActionStatus;

public interface ClassroomAccountQueryRepository {

    Boolean updateActionStatusForClassroom(Long classroomId);

    Boolean updateActionStatusByClassroomIdAndAccountId(Long classroomId, Long accountId, ActionStatus actionStatus);
}
