package soma.edupimeta.classroom.models;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class ClassroomResponse {

    private Long id;
    private String name;
    private Long guestCount;

    public ClassroomResponse(Long id, String name, Long guestCount) {
        this.id = id;
        this.name = name;
        this.guestCount = guestCount;
    }
}
