package soma.haeya.edupi_db.classroom.service;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import soma.haeya.edupi_db.classroom.models.request.CreateClassroomRequest;
import soma.haeya.edupi_db.classroom.models.request.RegisterStudentRequest;
import soma.haeya.edupi_db.classroom.repository.ClassroomRepository;
import soma.haeya.edupi_db.classroom.repository.StudentRepository;
import soma.haeya.edupi_db.common.exception.DbServerException;

@Service
@RequiredArgsConstructor
public class ClassroomService {

    private final ClassroomRepository classroomRepository;
    private final StudentRepository studentRepository;

    public void createClassroom(CreateClassroomRequest createClassroomRequest) {

        if (classroomRepository.existsByTeacherIdAndName(
            createClassroomRequest.getUserId(),
            createClassroomRequest.getName()
        )) {
            throw new DbServerException(HttpStatus.CONFLICT, "클래스룸 이름이 이미 존재합니다.");
        }

        classroomRepository.save(createClassroomRequest.toEntity());
    }

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
