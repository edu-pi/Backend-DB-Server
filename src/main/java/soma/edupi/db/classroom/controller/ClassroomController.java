package soma.edupi.db.classroom.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import soma.edupi.db.classroom.domain.Classroom;
import soma.edupi.db.classroom.models.request.CreateClassroomRequest;
import soma.edupi.db.classroom.service.ClassroomService;

@RestController
@RequiredArgsConstructor
@RequestMapping("/v1/classroom")
public class ClassroomController implements ClassroomSpecification {

    private final ClassroomService groupService;

    @Override
    @PostMapping
    public ResponseEntity<Classroom> createClassroom(
        @RequestBody CreateClassroomRequest createClassroomRequest
    ) {
        Classroom classroom = groupService.createClassroom(createClassroomRequest);

        return ResponseEntity.ok(classroom);
    }
}

