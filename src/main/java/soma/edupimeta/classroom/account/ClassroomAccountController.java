package soma.edupimeta.classroom.account;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import soma.edupimeta.classroom.account.models.ActionChangeRequest;
import soma.edupimeta.classroom.account.models.ActionInitializeRequest;
import soma.edupimeta.classroom.account.models.CreateClassroomAccountRequest;
import soma.edupimeta.classroom.account.service.ClassroomAccountService;
import soma.edupimeta.classroom.account.service.domain.ClassroomAccount;

@Slf4j
@RestController
@RequiredArgsConstructor
public class ClassroomAccountController implements ClassroomAccountOpenApi {

    private final ClassroomAccountService classroomAccountService;

    @Override
    @PostMapping("/v1/classroom/account")
    public ResponseEntity<ClassroomAccount> registerClassroomAccount(
        @RequestBody CreateClassroomAccountRequest createClassroomAccountRequest
    ) {
        ClassroomAccount classroomAccount = classroomAccountService.registerClassroomAccount(
            createClassroomAccountRequest.getAccountId(),
            createClassroomAccountRequest.getClassroomId(),
            createClassroomAccountRequest.getRole()
        );

        return ResponseEntity
            .status(HttpStatus.OK)
            .body(classroomAccount);
    }

    @Override
    @PostMapping("/v1/classroom/action/initialization")
    public ResponseEntity<Long> initialization(@RequestBody ActionInitializeRequest actionInitializeRequest) {
        log.info("Initial classroom id: {}", actionInitializeRequest.getClassroomId());
        long updateCount = classroomAccountService.initializeClassroomAccountActionStatus(
            actionInitializeRequest.getClassroomId()
        );

        return ResponseEntity
            .status(HttpStatus.OK)
            .body(updateCount);
    }

    @Override
    @PostMapping("/v1/classroom/account/action")
    public ResponseEntity<Void> changeClassroomAccountActionStatus(
        @RequestBody ActionChangeRequest actionChangeRequest
    ) {
        classroomAccountService.changeClassroomAccountActionStatus(
            actionChangeRequest.getClassroomId(),
            actionChangeRequest.getAccountId(),
            actionChangeRequest.getAction()
        );

        return ResponseEntity
            .status(HttpStatus.OK)
            .build();
    }


}

