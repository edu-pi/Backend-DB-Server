package soma.edupimeta.classroomAccount;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import soma.edupimeta.classroomAccount.service.domain.ClassroomAccount;
import soma.edupimeta.classroomAccount.models.CreateClassroomAccountRequest;
import soma.edupimeta.classroomAccount.service.ClassroomAccountService;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/v1/classroom-account")
public class ClassroomAccountController {

    private final ClassroomAccountService classroomAccountService;

    @PostMapping
    public ResponseEntity<ClassroomAccount> registerGuest(
        @RequestBody CreateClassroomAccountRequest createClassroomAccountRequest
    ) {
        ClassroomAccount guest = classroomAccountService.registerGuest(createClassroomAccountRequest);

        return ResponseEntity.ok().body(guest);
    }

}

