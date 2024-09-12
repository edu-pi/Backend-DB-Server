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

@Service
@Transactional
@RequiredArgsConstructor
public class ClassroomService {

    private final ClassroomRepository classroomRepository;

    private final ClassroomAccountRepository classroomAccountRepository;

    public Classroom createClassroom(CreateClassroomRequest createClassroomRequest) {
        ThrowIfDuplicateClassroom(createClassroomRequest);

        Classroom savedClassroom = classroomRepository.save(createClassroomRequest.toEntity());

        ClassroomAccount classroomAccount = new ClassroomAccount(
            createClassroomRequest.getAccountId(),
            savedClassroom.getId(),
            ClassroomAccountRole.ROLE_LEADER
        );

        classroomAccountRepository.save(classroomAccount);

        return savedClassroom;
    }

    private void ThrowIfDuplicateClassroom(CreateClassroomRequest createClassroomRequest) {
        // AccountId가 같고 Leader로 참여한 classroom 중에 name이 일치하는 것이 있으면
        if (classroomRepository.existsLeaderClassroomByAccountIdAndName(
            createClassroomRequest.getAccountId(),
            createClassroomRequest.getName())
        ) {
            throw new IllegalArgumentException();
        }

    }

}
