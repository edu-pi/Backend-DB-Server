package soma.edupimeta.classroom.models;

import java.util.List;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class MyClassroomsResponse {

    private List<MyClassroomResponse> host;
    private List<MyClassroomResponse> guest;

    public MyClassroomsResponse(List<MyClassroomResponse> host, List<MyClassroomResponse> guest) {
        this.host = host;
        this.guest = guest;
    }
}
