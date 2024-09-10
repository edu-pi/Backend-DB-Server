package soma.haeya.edupi_db.classroom.service;

import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import soma.haeya.edupi_db.classroom.models.request.CreateClassroomRequest;
import soma.haeya.edupi_db.classroom.models.response.ClassroomResponse;
import soma.haeya.edupi_db.classroom.models.response.MyClassroomWithCountResponse;
import soma.haeya.edupi_db.classroom.repository.ClassroomRepository;
import soma.haeya.edupi_db.common.exception.DbServerException;

@Service
@Transactional
@RequiredArgsConstructor
public class ClassroomService {

    private final ClassroomRepository classroomRepository;

    public void createClassroom(CreateClassroomRequest createClassroomRequest) {

        if (classroomRepository.existsByTeacherIdAndName(
            createClassroomRequest.getUserId(),
            createClassroomRequest.getName()
        )) {
            throw new DbServerException(HttpStatus.CONFLICT, "클래스룸 이름이 이미 존재합니다.");
        }

        classroomRepository.save(createClassroomRequest.toEntity());
    }

    @Transactional(readOnly = true)
    public MyClassroomWithCountResponse getMyClassrooms(Long userId) {
        if (userId == null) {
            throw new DbServerException(HttpStatus.BAD_REQUEST, "로그인이 필요합니다.");
        }

        List<ClassroomResponse> classrooms = classroomRepository.findAllByTeacherIdWithStudentCount(userId);

        return new MyClassroomWithCountResponse(classrooms.size(), classrooms);
    }

}
