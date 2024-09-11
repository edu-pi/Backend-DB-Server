package soma.haeya.edupi_db.classroom.models.response;

import java.util.List;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class MyClassroomsResponse {

    private final int classroomCount;
    private final List<ClassroomResponse> classrooms;

}
