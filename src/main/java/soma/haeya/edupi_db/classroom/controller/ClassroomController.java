package soma.haeya.edupi_db.classroom.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import soma.haeya.edupi_db.classroom.models.request.CreateClassroomRequest;
import soma.haeya.edupi_db.classroom.models.request.RegisterStudentRequest;
import soma.haeya.edupi_db.classroom.service.ClassroomService;

@RestController
@RequiredArgsConstructor
@RequestMapping("/v1/classroom")
public class ClassroomController {

    private final ClassroomService classroomService;

    @PostMapping
    public ResponseEntity<Void> createClassroom(
        @RequestBody CreateClassroomRequest createClassroomRequest
    ) {
        classroomService.createClassroom(createClassroomRequest);

        return ResponseEntity.ok().build();
    }

    @PostMapping("/student")
    public ResponseEntity<Void> registerStudent(@RequestBody RegisterStudentRequest registerStudentRequest) {
        classroomService.registerStudent(registerStudentRequest);

        return ResponseEntity.ok().build();
    }
}
