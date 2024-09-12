package soma.edupi.db.classroomAccount.models;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import soma.edupi.db.classroomAccount.domain.ClassroomAccount;
import soma.edupi.db.classroomAccount.domain.ClassroomAccountRole;

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
