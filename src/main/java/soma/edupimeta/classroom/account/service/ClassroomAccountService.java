package soma.edupimeta.classroom.account.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import soma.edupimeta.classroom.account.exception.ClassroomAccountErrorEnum;
import soma.edupimeta.classroom.account.exception.ClassroomAccountException;
import soma.edupimeta.classroom.account.service.domain.ActionStatus;
import soma.edupimeta.classroom.account.service.domain.ClassroomAccount;
import soma.edupimeta.classroom.account.service.domain.ClassroomAccountRole;
import soma.edupimeta.classroom.account.service.repository.ClassroomAccountRepository;
import soma.edupimeta.classroom.exception.ClassroomErrorEnum;
import soma.edupimeta.classroom.exception.ClassroomException;
import soma.edupimeta.classroom.service.repository.ClassroomRepository;

@Service
@Transactional
@RequiredArgsConstructor
public class ClassroomAccountService {

    private final ClassroomAccountRepository classroomAccountRepository;
    private final ClassroomRepository classroomRepository;

    public ClassroomAccount registerClassroomAccount(Long accountId, Long classroomId, ClassroomAccountRole role) {
        if (isExistsClassroom(classroomId)) {
            throw new ClassroomException(ClassroomErrorEnum.CLASSROOM_NOT_FOUND);
        }

        if (isDuplicate(accountId, classroomId)) {
            throw new ClassroomAccountException(ClassroomAccountErrorEnum.ALREADY_REGISTER);
        }

        ClassroomAccount classroomAccount = ClassroomAccount.builder()
            .accountId(accountId)
            .classroomId(classroomId)
            .actionStatus(ActionStatus.ING.getType())
            .role(role)
            .build();

        return addClassroomAccount(classroomAccount);
    }

    public Long initializeClassroomAccountActionStatus(Long classroomId) {
        if (isExistsClassroom(classroomId)) {
            throw new ClassroomException(ClassroomErrorEnum.CLASSROOM_NOT_FOUND);
        }

        return classroomAccountRepository.updateActionStatusForClassroom(classroomId);
    }

    public void changeClassroomAccountActionStatus(Long classroomId, Long accountId, ActionStatus actionStatus) {
        if (isExistsClassroom(classroomId)) {
            throw new ClassroomException(ClassroomErrorEnum.CLASSROOM_NOT_FOUND);
        }

        ClassroomAccount classroomAccount = getClassroomAccount(classroomId, accountId);

        if (classroomAccount.getRole() == ClassroomAccountRole.HOST) {
            throw new ClassroomAccountException(ClassroomAccountErrorEnum.HOST_CAN_NOT_UPDATE_ACTION_STATUS);
        }
        
        classroomAccount.updateActionStatus(actionStatus);
    }

    private boolean isExistsClassroom(Long classroomId) {
        return !classroomRepository.existsById(classroomId);
    }

    private boolean isDuplicate(Long accountId, Long classroomId) {
        return classroomAccountRepository.existsByAccountIdAndClassroomId(accountId, classroomId);
    }

    private ClassroomAccount addClassroomAccount(ClassroomAccount classroomAccount) {
        return classroomAccountRepository.save(classroomAccount);
    }

    private ClassroomAccount getClassroomAccount(Long classroomId, Long accountId) {
        return classroomAccountRepository.findByClassroomIdAndAccountId(classroomId, accountId)
            .orElseThrow(() -> new ClassroomAccountException(ClassroomAccountErrorEnum.CLASSROOM_ACCOUNT_NOT_FOUND));
    }

}
