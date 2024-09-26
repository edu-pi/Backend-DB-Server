package soma.edupimeta.classroom.account.models;

import lombok.Getter;
import lombok.NoArgsConstructor;
import soma.edupimeta.classroom.account.service.domain.ActionStatus;

@Getter
@NoArgsConstructor
public class ActionChangeRequest {

    private Long classroomId;
    private Long accountId;
    private ActionStatus action;
}
