package soma.edupi.db.classroomAccount.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import soma.edupi.db.classroomAccount.domain.ClassroomAccount;
import soma.edupi.db.classroomAccount.models.CreateClassroomAccountRequest;
import soma.edupi.db.classroomAccount.service.ClassroomAccountService;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/v1/classroom-account")
public class ClassroomAccountController {

    private final ClassroomAccountService classroomAccountService;

    @PostMapping
    public ResponseEntity<ClassroomAccount> create(
        @RequestBody CreateClassroomAccountRequest createClassroomAccountRequest) {
        ClassroomAccount follower = classroomAccountService.createFollower(createClassroomAccountRequest);

        return ResponseEntity.ok().body(follower);
    }

}

