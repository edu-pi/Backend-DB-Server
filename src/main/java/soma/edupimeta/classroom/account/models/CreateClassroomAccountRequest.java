package soma.edupimeta.classroom.account.models;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import soma.edupimeta.classroom.account.service.domain.ClassroomAccount;
import soma.edupimeta.classroom.account.service.domain.ClassroomAccountRole;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class CreateClassroomAccountRequest {

    @NotNull
    private Long accountId;
    @NotNull
    private Long classroomId;
    @NotNull
    private ClassroomAccountRole role;

    public ClassroomAccount toEntity(ClassroomAccountRole role) {
        return ClassroomAccount.builder()
            .accountId(accountId)
            .classroomId(classroomId)
            .role(role)
            .build();
    }

}
