package soma.edupimeta.classroom.service;

import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import soma.edupimeta.account.exception.AccountErrorEnum;
import soma.edupimeta.account.exception.AccountException;
import soma.edupimeta.classroom.account.service.domain.ClassroomAccount;
import soma.edupimeta.classroom.account.service.domain.ClassroomAccountRole;
import soma.edupimeta.classroom.account.service.repository.ClassroomAccountRepository;
import soma.edupimeta.classroom.exception.ClassroomErrorEnum;
import soma.edupimeta.classroom.exception.ClassroomException;
import soma.edupimeta.classroom.models.ClassroomActionInfo;
import soma.edupimeta.classroom.models.ClassroomInfoResponse;
import soma.edupimeta.classroom.models.CreateClassroomRequest;
import soma.edupimeta.classroom.models.MyClassroomResponse;
import soma.edupimeta.classroom.service.domain.Classroom;
import soma.edupimeta.classroom.service.repository.ClassroomRepository;

@Service
@Transactional
@RequiredArgsConstructor
public class ClassroomService {

    private final ClassroomRepository classroomRepository;
    private final ClassroomAccountRepository classroomAccountRepository;

    public Classroom createClassroom(CreateClassroomRequest createClassroomRequest) {
        if (isDuplicatedClassroomName(createClassroomRequest)) {
            throw new ClassroomException(ClassroomErrorEnum.CLASSROOM_NAME_DUPLICATE);
        }

        Classroom savedClassroom = addClassroom(createClassroomRequest);

        ClassroomAccount classroomAccount = ClassroomAccount.builder()
            .accountId(createClassroomRequest.getAccountId())
            .classroomId(savedClassroom.getId())
            .role(ClassroomAccountRole.HOST)
            .build();

        addClassroomAccount(classroomAccount);

        return savedClassroom;
    }

    @Transactional(readOnly = true)
    public List<MyClassroomResponse> getMyClassrooms(Long accountId) {
        if (accountId == null) {
            throw new AccountException(AccountErrorEnum.EMAIL_NOT_MATCH);
        }

        return findMyClassroomsBy(accountId);
    }

    public void deleteClassroom(Long classroomId) {
        if (isNotExistClassroom(classroomId)) {
            throw new ClassroomException(ClassroomErrorEnum.CLASSROOM_NOT_FOUND);
        }
        deleteAllClassroomAccount(classroomId);

        deleteClassroomBy(classroomId);
    }

    public Long initAllActionStatusBy(Long classroomId) {
        if (isNotExistClassroom(classroomId)) {
            throw new ClassroomException(ClassroomErrorEnum.CLASSROOM_NOT_FOUND);
        }

        return initActionStatusBy(classroomId);
    }

    @Transactional(readOnly = true)
    public ClassroomInfoResponse getClassroomInfo(Long classroomId) {

        Classroom classroom = classroomRepository.findClassroomById(classroomId).orElseThrow(
            () -> new ClassroomException(ClassroomErrorEnum.CLASSROOM_NOT_FOUND)
        );

        List<ClassroomActionInfo> classroomActionInfos = classroomRepository.findClassroomInfo(classroomId);

        return new ClassroomInfoResponse(classroom.getName(), classroomActionInfos);
    }

    private Boolean isDuplicatedClassroomName(CreateClassroomRequest createClassroomRequest) {
        return classroomRepository.existsHostClassroomByAccountIdAndName(
            createClassroomRequest.getAccountId(),
            createClassroomRequest.getName()
        );
    }

    private Classroom addClassroom(CreateClassroomRequest createClassroomRequest) {
        return classroomRepository.save(createClassroomRequest.toEntity());
    }

    private void addClassroomAccount(ClassroomAccount classroomAccount) {
        classroomAccountRepository.save(classroomAccount);
    }

    private List<MyClassroomResponse> findMyClassroomsBy(Long accountId) {
        return classroomRepository.findMyClassrooms(accountId);
    }

    private boolean isNotExistClassroom(Long classroomId) {
        return !classroomRepository.existsById(classroomId);
    }

    private void deleteAllClassroomAccount(Long classroomId) {
        classroomAccountRepository.deleteByClassroomId(classroomId);
    }

    private void deleteClassroomBy(Long classroomId) {
        classroomRepository.deleteById(classroomId);
    }

    private Long initActionStatusBy(Long classroomId) {
        return classroomAccountRepository.updateActionStatusForClassroom(classroomId);
    }

}


