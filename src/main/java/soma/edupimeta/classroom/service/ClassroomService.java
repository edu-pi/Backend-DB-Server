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
        // AccountId가 같고 Leader로 참여한 classroom 중에 name이 일치하는 것이 있으면
        if (isDuplicatedClassroomName(createClassroomRequest)) {
            throw new ClassroomException(ClassroomErrorEnum.CLASSROOM_NAME_DUPLICATE);
        }

        // 클래스룸 생성
        Classroom savedClassroom = classroomRepository.save(createClassroomRequest.toEntity());

        // 호스트 생성
        ClassroomAccount classroomAccount = ClassroomAccount.builder()
            .accountId(createClassroomRequest.getAccountId())
            .classroomId(savedClassroom.getId())
            .role(ClassroomAccountRole.HOST)
            .build();

        classroomAccountRepository.save(classroomAccount);

        return savedClassroom;
    }

    @Transactional(readOnly = true)
    public List<MyClassroomResponse> getMyClassrooms(Long accountId) {
        if (accountId == null) {
            throw new AccountException(AccountErrorEnum.EMAIL_NOT_MATCH);
        }

        return classroomRepository.findMyClassrooms(accountId);
    }

    private Boolean isDuplicatedClassroomName(CreateClassroomRequest createClassroomRequest) {
        return classroomRepository.existsHostClassroomByAccountIdAndName(
            createClassroomRequest.getAccountId(),
            createClassroomRequest.getName()
        );
    }

}


