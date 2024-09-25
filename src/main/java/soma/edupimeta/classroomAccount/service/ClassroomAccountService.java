package soma.edupimeta.classroomAccount.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import soma.edupimeta.classroom.exception.ClassroomErrorCode;
import soma.edupimeta.classroom.exception.ClassroomException;
import soma.edupimeta.classroom.service.repository.ClassroomRepository;
import soma.edupimeta.classroomAccount.exception.ClassroomAccountErrorCode;
import soma.edupimeta.classroomAccount.exception.ClassroomAccountException;
import soma.edupimeta.classroomAccount.service.domain.ClassroomAccount;
import soma.edupimeta.classroomAccount.service.domain.ClassroomAccountRole;
import soma.edupimeta.classroomAccount.service.repository.ClassroomAccountRepository;

@Service
@RequiredArgsConstructor
public class ClassroomAccountService {

    private final ClassroomAccountRepository classroomAccountRepository;
    private final ClassroomRepository classroomRepository;

    public ClassroomAccount registerGuest(Long accountId, Long classroomId) {
        if (isExistsClassroom(classroomId)) {
            throw new ClassroomException(ClassroomErrorCode.CLASSROOM_NOT_FOUND);
        }

        if (isDuplicate(accountId, classroomId)) {
            throw new ClassroomAccountException(ClassroomAccountErrorCode.ALREADY_REGISTER);
        }

        ClassroomAccount classroomAccount = ClassroomAccount.builder()
            .accountId(accountId)
            .classroomId(classroomId)
            .role(ClassroomAccountRole.HOST)
            .build();

        return addClassroomAccount(classroomAccount);
    }

    private boolean isExistsClassroom(Long classroomId) {
        return !classroomRepository.existsById(classroomId);
    }

    private boolean isDuplicate(Long accountId, Long classroomId) {
        return !classroomAccountRepository.existsByAccountIdAndClassroomId(accountId, classroomId);
    }

    private ClassroomAccount addClassroomAccount(ClassroomAccount classroomAccount) {
        return classroomAccountRepository.save(classroomAccount);
    }

}
