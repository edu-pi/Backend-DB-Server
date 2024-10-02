package soma.edupimeta.classroom.account.service;

import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import soma.edupimeta.account.exception.AccountErrorEnum;
import soma.edupimeta.account.exception.AccountException;
import soma.edupimeta.account.service.domain.Account;
import soma.edupimeta.account.service.repository.AccountRepository;
import soma.edupimeta.classroom.account.exception.ClassroomAccountErrorEnum;
import soma.edupimeta.classroom.account.exception.ClassroomAccountException;
import soma.edupimeta.classroom.account.models.ClassroomAccountResponse;
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

    private final AccountRepository accountRepository;
    private final ClassroomAccountRepository classroomAccountRepository;
    private final ClassroomRepository classroomRepository;

    public ClassroomAccountResponse registerClassroomAccount(
        String email,
        Long classroomId,
        ClassroomAccountRole role
    ) {
        if (isNotExistClassroom(classroomId)) {
            throw new ClassroomException(ClassroomErrorEnum.CLASSROOM_NOT_FOUND);
        }

        Account account = accountRepository.findAccountByEmail(email).orElseThrow(
            () -> new AccountException(AccountErrorEnum.EMAIL_NOT_MATCH)
        );

        if (isDuplicate(account.getId(), classroomId)) {
            throw new ClassroomAccountException(ClassroomAccountErrorEnum.ALREADY_REGISTER);
        }

        ClassroomAccount classroomAccount = ClassroomAccount.builder()
            .accountId(account.getId())
            .classroomId(classroomId)
            .actionStatus(ActionStatus.DEFAULT.getValue())
            .role(role)
            .build();

        addClassroomAccount(classroomAccount);

        return new ClassroomAccountResponse(account, classroomAccount);
    }

    public List<ClassroomAccountResponse> getAllClassroomAccountsBy(Long classroomId) {
        return classroomAccountRepository.findByClassroomId(classroomId);
    }

    public void deleteClassroomAccount(Long classroomAccountId) {
        if (isNotExistClassroomAccount(classroomAccountId)) {
            throw new ClassroomAccountException(ClassroomAccountErrorEnum.CLASSROOM_ACCOUNT_NOT_FOUND);
        }

        classroomAccountRepository.deleteById(classroomAccountId);
    }

    public Long initAllActionStatusBy(Long classroomId) {
        if (isNotExistClassroom(classroomId)) {
            throw new ClassroomException(ClassroomErrorEnum.CLASSROOM_NOT_FOUND);
        }

        return initActionStatusBy(classroomId);
    }

    public ActionStatus changeActionStatusBy(
        Long classroomId,
        Long accountId,
        ActionStatus actionStatus
    ) {
        if (isNotExistClassroom(classroomId)) {
            throw new ClassroomException(ClassroomErrorEnum.CLASSROOM_NOT_FOUND);
        }

        ClassroomAccount classroomAccount = getClassroomAccount(classroomId, accountId);

        if (classroomAccount.getRole() == ClassroomAccountRole.HOST) {
            throw new ClassroomAccountException(ClassroomAccountErrorEnum.HOST_CAN_NOT_UPDATE_ACTION_STATUS);
        }

        classroomAccount.updateActionStatus(actionStatus);

        return classroomAccount.getActionStatus();
    }

    private boolean isNotExistClassroom(Long classroomId) {
        return !classroomRepository.existsById(classroomId);
    }

    private boolean isDuplicate(Long accountId, Long classroomId) {
        return classroomAccountRepository.existsByAccountIdAndClassroomId(accountId, classroomId);
    }

    private void addClassroomAccount(ClassroomAccount classroomAccount) {
        classroomAccountRepository.save(classroomAccount);
    }

    private Long initActionStatusBy(Long classroomId) {
        return classroomAccountRepository.updateActionStatusForClassroom(classroomId);
    }

    private ClassroomAccount getClassroomAccount(Long classroomId, Long accountId) {
        return classroomAccountRepository.findByClassroomIdAndAccountId(classroomId, accountId)
            .orElseThrow(() -> new ClassroomAccountException(ClassroomAccountErrorEnum.CLASSROOM_ACCOUNT_NOT_FOUND));
    }

    private boolean isNotExistClassroomAccount(Long classroomAccountId) {
        return !classroomAccountRepository.existsById(classroomAccountId);
    }

}
