package soma.edupimeta.classroom;

import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import soma.edupimeta.classroom.models.ClassroomInfoResponse;
import soma.edupimeta.classroom.models.CreateClassroomRequest;
import soma.edupimeta.classroom.models.MyClassroomResponse;
import soma.edupimeta.classroom.service.ClassroomService;
import soma.edupimeta.classroom.service.domain.Classroom;

@Slf4j
@RestController
@RequiredArgsConstructor
public class ClassroomController implements ClassroomOpenApi {

    private final ClassroomService classroomService;

    @Override
    @PostMapping("/v1/classroom")
    public ResponseEntity<Classroom> createClassroom(@RequestBody CreateClassroomRequest createClassroomRequest) {
        Classroom classroom = classroomService.createClassroom(createClassroomRequest);

        return ResponseEntity
            .status(HttpStatus.OK)
            .body(classroom);
    }

    @Override
    @GetMapping("/v1/classroom")
    public ResponseEntity<List<MyClassroomResponse>> getMyClassrooms(Long accountId) {
        List<MyClassroomResponse> myClassroomsResponse = classroomService.getMyClassrooms(accountId);

        return ResponseEntity
            .status(HttpStatus.OK)
            .body(myClassroomsResponse);
    }

    @Override
    @DeleteMapping("/v1/classroom")
    public ResponseEntity<Void> deleteClassroom(@RequestParam Long classroomId) {
        classroomService.deleteClassroom(classroomId);

        return ResponseEntity
            .status(HttpStatus.OK)
            .build();
    }

    @Override
    @GetMapping("/v1/classroom/info")
    public ResponseEntity<ClassroomInfoResponse> getInfo(@RequestParam Long classroomId) {
        ClassroomInfoResponse classroomInfo = classroomService.getClassroomInfo(classroomId);

        return ResponseEntity
            .status(HttpStatus.OK)
            .body(classroomInfo);
    }

}

