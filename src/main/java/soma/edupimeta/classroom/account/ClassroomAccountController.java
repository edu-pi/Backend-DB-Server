package soma.edupimeta.classroom.account;

import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import soma.edupimeta.classroom.account.models.ActionChangeRequest;
import soma.edupimeta.classroom.account.models.ClassroomAccountResponse;
import soma.edupimeta.classroom.account.models.CreateClassroomAccountRequest;
import soma.edupimeta.classroom.account.service.ClassroomAccountService;
import soma.edupimeta.classroom.account.service.domain.ActionStatus;

@Slf4j
@RestController
@RequiredArgsConstructor
public class ClassroomAccountController implements ClassroomAccountOpenApi {

    private final ClassroomAccountService classroomAccountService;

    @Override
    @PostMapping("/v1/classroom/account")
    public ResponseEntity<ClassroomAccountResponse> registerClassroomAccount(
        @RequestBody CreateClassroomAccountRequest createClassroomAccountRequest
    ) {
        ClassroomAccountResponse classroomAccount = classroomAccountService.registerClassroomAccount(
            createClassroomAccountRequest.getEmail(),
            createClassroomAccountRequest.getClassroomId(),
            createClassroomAccountRequest.getRole()
        );

        return ResponseEntity
            .status(HttpStatus.OK)
            .body(classroomAccount);
    }

    @Override
    @GetMapping("/v1/classroom/account")
    public ResponseEntity<List<ClassroomAccountResponse>> getClassroomAccountsBy(@RequestParam Long classroomId) {
        List<ClassroomAccountResponse> classroomAccounts = classroomAccountService.getAllClassroomAccountsBy(
            classroomId);

        return ResponseEntity
            .status(HttpStatus.OK)
            .body(classroomAccounts);
    }

    @Override
    @DeleteMapping("/v1/classroom/account")
    public ResponseEntity<Void> deleteClassroomAccount(@RequestParam Long classroomAccountId) {
        classroomAccountService.deleteClassroomAccount(classroomAccountId);

        return ResponseEntity
            .status(HttpStatus.OK)
            .build();
    }

    @Override
    @PostMapping("/v1/classroom/account/action")
    public ResponseEntity<ActionStatus> changeActionStatus(
        @RequestBody ActionChangeRequest actionChangeRequest
    ) {
        ActionStatus actionStatus = classroomAccountService.changeActionStatusBy(
            actionChangeRequest.getClassroomId(),
            actionChangeRequest.getAccountId(),
            actionChangeRequest.getAction()
        );

        return ResponseEntity
            .status(HttpStatus.OK)
            .body(actionStatus);
    }

}

