package soma.haeya.edupi_db.student.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import soma.haeya.edupi_db.student.models.request.RegisterStudentRequest;
import soma.haeya.edupi_db.student.service.StudentService;

@RestController
@RequiredArgsConstructor
@RequestMapping("/v1/student")
public class StudentController {

    private final StudentService studentService;

    @PostMapping
    public ResponseEntity<Void> registerStudent(@RequestBody RegisterStudentRequest registerStudentRequest) {
        studentService.registerStudent(registerStudentRequest);

        return ResponseEntity.ok().build();
    }

}
