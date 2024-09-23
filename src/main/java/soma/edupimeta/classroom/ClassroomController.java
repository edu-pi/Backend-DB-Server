package soma.edupimeta.classroom;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import soma.edupimeta.classroom.service.domain.Classroom;
import soma.edupimeta.classroom.models.CreateClassroomRequest;
import soma.edupimeta.classroom.models.MyClassroomsResponse;
import soma.edupimeta.classroom.service.ClassroomService;

@RestController
@RequiredArgsConstructor
@RequestMapping("/v1/classroom")
public class ClassroomController implements ClassroomOpenApi {

    private final ClassroomService classroomService;

    @Override
    @PostMapping
    public ResponseEntity<Classroom> createClassroom(@RequestBody CreateClassroomRequest createClassroomRequest) {
        Classroom classroom = classroomService.createClassroom(createClassroomRequest);

        return ResponseEntity.ok(classroom);
    }

    @GetMapping
    public ResponseEntity<MyClassroomsResponse> getMyClassrooms(Long userId) {
        MyClassroomsResponse myClassroomsResponse = classroomService.getMyClassrooms(userId);

        return ResponseEntity.ok(myClassroomsResponse);
    }
}

