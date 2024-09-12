package soma.edupi.db.classroom.models.request;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import soma.edupi.db.classroom.domain.Classroom;

@Getter
@RequiredArgsConstructor
public class CreateClassroomRequest {

    private final Long accountId;
    private final String name;
    private final String inviteLink;

    public Classroom toEntity() {
        return Classroom.builder()
            .name(name)
            .inviteLink(inviteLink)
            .build();
    }

}
