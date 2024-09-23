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
        // TODO: 조인
        if (isDuplicate(classroomId)) {
            throw new AlreadyExistsException("이미 등록된 계정입니다.");
        }

        ClassroomAccount classroomAccount = ClassroomAccount.builder()
            .accountId(accountId)
            .classroomId(classroomId)
            .role(ClassroomAccountRole.ROLE_HOST)
            .build();

        return addClassroomAccount(classroomAccount);
    }

    private boolean isDuplicate(Long classroomId) {
        return !classroomRepository.existsById(classroomId);
    }

    private ClassroomAccount addClassroomAccount(ClassroomAccount classroomAccount) {
        return classroomAccountRepository.save(classroomAccount);
    }

}
