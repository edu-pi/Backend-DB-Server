package soma.haeya.edupi_db.classroom.models.response;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class ClassroomResponse {

    private final String name;
    private final Long studentCount;

}
