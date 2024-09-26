package soma.edupimeta.classroom.account.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
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

    public void initializeClassroomAccountActionStatus(Long classroomId) {
        boolean isUpdated = classroomAccountRepository.updateActionStatusForClassroom(classroomId);

        if (!isUpdated) {
            throw new ClassroomAccountException(ClassroomAccountErrorEnum.CAN_NOT_UPDATE_ACTION_STATUS);
        }
    }

    public void changeClassroomAccountActionStatus(Long classroomId, Long accountId, ActionStatus actionStatus) {
        boolean isUpdated = classroomAccountRepository.updateActionStatusByClassroomIdAndAccountId(
            classroomId,
            accountId,
            actionStatus
        );

        if (!isUpdated) {
            throw new ClassroomAccountException(ClassroomAccountErrorEnum.CAN_NOT_UPDATE_ACTION_STATUS);
        }
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

}
