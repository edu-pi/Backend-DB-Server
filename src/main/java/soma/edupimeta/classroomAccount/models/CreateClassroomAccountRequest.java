package soma.edupimeta.classroomAccount.models;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import soma.edupimeta.classroomAccount.service.domain.ClassroomAccount;
import soma.edupimeta.classroomAccount.service.domain.ClassroomAccountRole;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class CreateClassroomAccountRequest {

    @NotNull
    private Long accountId;
    @NotNull
    private Long classroomId;

    public ClassroomAccount toEntity(ClassroomAccountRole role) {
        return ClassroomAccount.builder()
            .accountId(accountId)
            .classroomId(classroomId)
            .role(role)
            .build();
    }

}
