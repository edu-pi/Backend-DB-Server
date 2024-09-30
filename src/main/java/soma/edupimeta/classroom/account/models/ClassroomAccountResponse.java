package soma.edupimeta.classroom.account.models;

import lombok.Getter;
import lombok.NoArgsConstructor;
import soma.edupimeta.classroom.account.service.domain.ActionStatus;
import soma.edupimeta.classroom.account.service.domain.ClassroomAccountRole;

@Getter
@NoArgsConstructor
public class ClassroomAccountResponse {

    private Long id;
    private String email;
    private String name;
    private ActionStatus status;
    private ClassroomAccountRole role;

    public ClassroomAccountResponse(
        Long id,
        String email,
        String name,
        int status,
        ClassroomAccountRole role
    ) {
        this.id = id;
        this.email = email;
        this.name = name;
        this.status = ActionStatus.fromValue(status);
        this.role = role;
    }
}
