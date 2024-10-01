package soma.edupimeta.classroom.account.models;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import soma.edupimeta.classroom.account.service.domain.ClassroomAccountRole;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class CreateClassroomAccountRequest {

    @NotNull
    private String email;
    @NotNull
    private Long classroomId;
    @NotNull
    private ClassroomAccountRole role;

}
