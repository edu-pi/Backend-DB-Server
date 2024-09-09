package soma.haeya.edupi_db.classroom.service;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import soma.haeya.edupi_db.classroom.model.request.CreateClassroomRequest;
import soma.haeya.edupi_db.classroom.repository.ClassroomRepository;
import soma.haeya.edupi_db.common.exception.DbServerException;

@Service
@RequiredArgsConstructor
public class ClassroomService {

    private final ClassroomRepository groupRepository;

    public void createClassroom(CreateClassroomRequest createClassroomRequest) {
        ThrowIfDuplicate(createClassroomRequest);

        groupRepository.save(createClassroomRequest.toEntity());
    }

    private void ThrowIfDuplicate(CreateClassroomRequest createClassroomRequest) {
        if (groupRepository
            .findByUserIdAndName(createClassroomRequest.getUserId(), createClassroomRequest.getName())
            .isPresent()) {
            throw new DbServerException(HttpStatus.CONFLICT, "그룹 이름이 이미 존재합니다.");
        }
    }

}
