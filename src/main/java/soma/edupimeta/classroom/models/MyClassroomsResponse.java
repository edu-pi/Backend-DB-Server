package soma.edupimeta.classroom.models;

import java.util.List;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class MyClassroomsResponse {

    private List<MyClassroomResponse> myClassrooms;

    public MyClassroomsResponse(List<MyClassroomResponse> myClassrooms) {
        this.myClassrooms = myClassrooms;
    }
}
