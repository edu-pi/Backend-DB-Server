package soma.edupimeta.classroom.service;

import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import soma.edupimeta.classroom.models.CreateClassroomRequest;
import soma.edupimeta.classroom.models.MyClassroomResponse;
import soma.edupimeta.classroom.models.MyClassroomsResponse;
import soma.edupimeta.classroom.service.domain.Classroom;
import soma.edupimeta.classroom.service.repository.ClassroomRepository;
import soma.edupimeta.classroomAccount.service.domain.ClassroomAccount;
import soma.edupimeta.classroomAccount.service.domain.ClassroomAccountRole;
import soma.edupimeta.classroomAccount.service.repository.ClassroomAccountRepository;
import soma.edupimeta.web.exception.AlreadyExistsException;
import soma.edupimeta.web.exception.DbServerException;

@Service
@Transactional
@RequiredArgsConstructor
public class ClassroomService {

    private final ClassroomRepository classroomRepository;

    private final ClassroomAccountRepository classroomAccountRepository;

    public Classroom createClassroom(CreateClassroomRequest createClassroomRequest) {
        // AccountId가 같고 Leader로 참여한 classroom 중에 name이 일치하는 것이 있으면
        if (isDuplicatedClassroomName(createClassroomRequest)) {
            throw new AlreadyExistsException("이미 해당 이름의 클래스룸이 존재합니다.");
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
    public MyClassroomsResponse getMyClassrooms(Long accountId) {
        if (accountId == null) {
            throw new DbServerException(HttpStatus.BAD_REQUEST, "로그인이 필요합니다.");
        }

        List<MyClassroomResponse> hosts = classroomRepository.findMyClassroomByClassroomAccountRole(
            accountId,
            ClassroomAccountRole.HOST
        );

        List<MyClassroomResponse> guests = classroomRepository.findMyClassroomByClassroomAccountRole(
            accountId,
            ClassroomAccountRole.GUEST
        );

        return new MyClassroomsResponse(hosts, guests);
    }

    private Boolean isDuplicatedClassroomName(CreateClassroomRequest createClassroomRequest) {
        return classroomRepository.existsHostClassroomByAccountIdAndName(
            createClassroomRequest.getAccountId(),
            createClassroomRequest.getName()
        );
    }

}


