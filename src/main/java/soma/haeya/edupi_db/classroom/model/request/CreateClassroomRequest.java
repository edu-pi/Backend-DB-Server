package soma.haeya.edupi_db.classroom.model.request;

import lombok.Getter;
import lombok.NoArgsConstructor;
import soma.haeya.edupi_db.classroom.domain.Classroom;

@Getter
@NoArgsConstructor
public class CreateClassroomRequest {

    private Long userId;
    private String name;
    private String inviteLink;

    public Classroom toEntity() {
        return Classroom.builder()
            .userId(userId)
            .name(name)
            .inviteLink(inviteLink)
            .build();
    }

}
