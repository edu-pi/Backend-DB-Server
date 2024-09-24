package soma.edupimeta.classroomAccount.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import soma.edupimeta.classroom.service.repository.ClassroomRepository;
import soma.edupimeta.classroomAccount.service.domain.ClassroomAccount;
import soma.edupimeta.classroomAccount.service.domain.ClassroomAccountRole;
import soma.edupimeta.classroomAccount.service.repository.ClassroomAccountRepository;
import soma.edupimeta.web.exception.AlreadyExistsException;

@Service
@RequiredArgsConstructor
public class ClassroomAccountService {

    private final ClassroomAccountRepository classroomAccountRepository;
    private final ClassroomRepository classroomRepository;

    public ClassroomAccount registerGuest(Long accountId, Long classroomId) {
        if (isExistsClassroom(classroomId)) {
            throw new IllegalArgumentException("해당 클래스룸이 존재하지 않습니다.");
        }

        if (isDuplicate(accountId, classroomId)) {
            throw new AlreadyExistsException("이미 등록된 계정입니다.");
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
