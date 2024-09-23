package soma.edupimeta.classroom;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import soma.edupimeta.classroom.models.CreateClassroomRequest;
import soma.edupimeta.classroom.models.MyClassroomsResponse;
import soma.edupimeta.classroom.service.ClassroomService;
import soma.edupimeta.classroom.service.domain.Classroom;

@RestController
@RequiredArgsConstructor
@RequestMapping("/v1/classroom")
public class ClassroomController implements ClassroomOpenApi {

    private final ClassroomService classroomService;

    @Override
    @PostMapping
    public ResponseEntity<Classroom> createClassroom(@RequestBody CreateClassroomRequest createClassroomRequest) {
        Classroom classroom = classroomService.createClassroom(createClassroomRequest);

        return ResponseEntity
            .status(HttpStatus.OK)
            .body(classroom);
    }

    @Override
    @GetMapping
    public ResponseEntity<MyClassroomsResponse> getMyClassrooms(Long accountId) {
        MyClassroomsResponse myClassroomsResponse = classroomService.getMyClassrooms(accountId);

        return ResponseEntity
            .status(HttpStatus.OK)
            .body(myClassroomsResponse);
    }
}

