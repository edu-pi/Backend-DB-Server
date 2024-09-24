package soma.edupimeta.classroom.models;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class MyClassroomResponse {

    private Long id;
    private String name;
    private Long totalPeople;

    public MyClassroomResponse(Long id, String name, Long totalPeople) {
        this.id = id;
        this.name = name;
        this.totalPeople = totalPeople;
    }
}
