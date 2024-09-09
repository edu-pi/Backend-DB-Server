package soma.haeya.edupi_db.classroom.models.request;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import soma.haeya.edupi_db.classroom.domain.Classroom;

@Getter
@RequiredArgsConstructor
public class CreateClassroomRequest {

    private final Long userId;
    private final String name;
    private final String inviteLink;

    public Classroom toEntity() {
        return Classroom.builder()
            .teacherId(userId)
            .name(name)
            .inviteLink(inviteLink)
            .build();
    }

}
