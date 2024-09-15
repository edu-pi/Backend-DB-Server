package soma.edupi.db.classroom.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import soma.edupi.db.classroom.domain.Classroom;
import soma.edupi.db.classroom.models.request.CreateClassroomRequest;
import soma.edupi.db.classroom.repository.ClassroomRepository;
import soma.edupi.db.classroomAccount.domain.ClassroomAccount;
import soma.edupi.db.classroomAccount.domain.ClassroomAccountRole;
import soma.edupi.db.classroomAccount.repository.ClassroomAccountRepository;
import soma.edupi.db.common.exception.AlreadyExistsException;

@Service
@Transactional
@RequiredArgsConstructor
public class ClassroomService {

    private final ClassroomRepository classroomRepository;

    private final ClassroomAccountRepository classroomAccountRepository;

    public Classroom createClassroom(CreateClassroomRequest createClassroomRequest) {
        // AccountId가 같고 Leader로 참여한 classroom 중에 name이 일치하는 것이 있으면
        if (classroomRepository.existsHostClassroomByAccountIdAndName(
            createClassroomRequest.getAccountId(), createClassroomRequest.getName())
        ) {
            throw new AlreadyExistsException("이미 해당 이름의 클래스룸이 존재합니다.");
        }

        // 클래스룸 생성
        Classroom savedClassroom = classroomRepository.save(createClassroomRequest.toEntity());

        // 호스트 생성
        ClassroomAccount classroomAccount = ClassroomAccount.builder()
            .accountId(createClassroomRequest.getAccountId())
            .classroomId(savedClassroom.getId())
            .role(ClassroomAccountRole.ROLE_HOST)
            .build();

        classroomAccountRepository.save(classroomAccount);

        return savedClassroom;
    }

}
