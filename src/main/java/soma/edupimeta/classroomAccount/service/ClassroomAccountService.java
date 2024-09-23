package soma.edupimeta.classroomAccount.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import soma.edupimeta.classroom.service.repository.ClassroomRepository;
import soma.edupimeta.classroomAccount.service.domain.ClassroomAccount;
import soma.edupimeta.classroomAccount.service.domain.ClassroomAccountRole;
import soma.edupimeta.classroomAccount.models.CreateClassroomAccountRequest;
import soma.edupimeta.classroomAccount.service.repository.ClassroomAccountRepository;
import soma.edupimeta.common.exception.AlreadyExistsException;

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
