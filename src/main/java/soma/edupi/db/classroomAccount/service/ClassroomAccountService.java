package soma.edupi.db.classroomAccount.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import soma.edupi.db.classroom.repository.ClassroomRepository;
import soma.edupi.db.classroomAccount.domain.ClassroomAccount;
import soma.edupi.db.classroomAccount.domain.ClassroomAccountRole;
import soma.edupi.db.classroomAccount.models.CreateClassroomAccountRequest;
import soma.edupi.db.classroomAccount.repository.ClassroomAccountRepository;
import soma.edupi.db.common.exception.AlreadyExistsException;

@Service
@RequiredArgsConstructor
public class ClassroomAccountService {

    private final ClassroomAccountRepository classroomAccountRepository;
    private final ClassroomRepository classroomRepository;

    public ClassroomAccount registerGuest(CreateClassroomAccountRequest createClassroomAccountRequest) {
        if (!classroomRepository.existsById(createClassroomAccountRequest.getClassroomId())) {
            throw new AlreadyExistsException("이미 등록된 계정입니다.");
        }

        ClassroomAccount classroomAccount = createClassroomAccountRequest.toEntity(ClassroomAccountRole.ROLE_GUEST);

        return classroomAccountRepository.save(classroomAccount);
    }

}
