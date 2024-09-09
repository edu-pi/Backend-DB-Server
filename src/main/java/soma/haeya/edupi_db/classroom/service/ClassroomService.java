package soma.haeya.edupi_db.classroom.service;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import soma.haeya.edupi_db.classroom.models.request.CreateClassroomRequest;
import soma.haeya.edupi_db.classroom.repository.ClassroomRepository;
import soma.haeya.edupi_db.common.exception.DbServerException;

@Service
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

}
