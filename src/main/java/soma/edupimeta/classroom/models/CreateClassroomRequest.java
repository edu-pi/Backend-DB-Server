package soma.edupimeta.classroom.models;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import soma.edupimeta.classroom.service.domain.Classroom;

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
