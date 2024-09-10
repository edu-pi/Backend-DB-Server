package soma.haeya.edupi_db.student.service;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import soma.haeya.edupi_db.classroom.repository.ClassroomRepository;
import soma.haeya.edupi_db.common.exception.DbServerException;
import soma.haeya.edupi_db.student.models.request.RegisterStudentRequest;
import soma.haeya.edupi_db.student.repository.StudentRepository;

@Service
@Transactional
@RequiredArgsConstructor
public class StudentService {

    private final ClassroomRepository classroomRepository;
    private final StudentRepository studentRepository;

    public void registerStudent(RegisterStudentRequest registerStudentRequest) {

        if (!classroomRepository.existsById(registerStudentRequest.getClassroomId())) {
            throw new DbServerException(HttpStatus.BAD_REQUEST, "요청에 대한 클래스룸이 없습니다.");
        }

        if (studentRepository.existsByClassroomIdAndUserId(
            registerStudentRequest.getClassroomId(),
            registerStudentRequest.getUserId()
        )) {
            throw new DbServerException(HttpStatus.CONFLICT, "이미 가입된 클래스룸입니다.");
        }

        studentRepository.save(registerStudentRequest.toEntity());

    }
}
