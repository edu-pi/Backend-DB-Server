package soma.edupimeta.classroom.models;

import java.util.List;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class MyClassroomsResponse {

    private int classroomCount;
    private List<ClassroomResponse> classrooms;

    public MyClassroomsResponse(int classroomCount, List<ClassroomResponse> classrooms) {
        this.classroomCount = classroomCount;
        this.classrooms = classrooms;
    }
}
