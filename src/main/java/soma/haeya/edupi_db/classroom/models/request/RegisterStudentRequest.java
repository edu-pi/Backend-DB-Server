package soma.haeya.edupi_db.classroom.models.request;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import soma.haeya.edupi_db.classroom.domain.Student;

@Getter
@RequiredArgsConstructor
public class RegisterStudentRequest {

    private final Long classroomId;
    private final Long userId;

    public Student toEntity() {
        return Student.builder()
            .classroomId(classroomId)
            .userId(userId)
            .build();
    }

}
