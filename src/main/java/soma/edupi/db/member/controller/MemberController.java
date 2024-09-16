package soma.edupi.db.member.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import soma.edupi.db.member.models.request.EmailRequest;
import soma.edupi.db.member.models.request.LoginRequest;
import soma.edupi.db.member.models.request.SignupRequest;
import soma.edupi.db.member.models.response.LoginResponse;
import soma.edupi.db.member.models.response.SignupResponse;
import soma.edupi.db.member.service.MemberService;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/v1/member")
public class MemberController implements MemberSpecification {

    private final MemberService memberService;

    @PostMapping("/signup")
    public ResponseEntity<SignupResponse> saveMember(@Valid @RequestBody SignupRequest signupRequest) {
        memberService.saveMember(signupRequest);

        return ResponseEntity
            .status(HttpStatus.OK)
            .body(new SignupResponse());
    }

    @PostMapping("/findByEmailAndPassword")
    public ResponseEntity<LoginResponse> findMemberByEmailAndPassword(@Valid @RequestBody LoginRequest loginRequest) {
        LoginResponse loginResponse = memberService.findMemberByEmailAndPassword(loginRequest);

        return ResponseEntity
            .status(HttpStatus.OK)
            .body(loginResponse);
    }

    @PostMapping("/activate")
    public ResponseEntity<Void> activateAccount(@Valid @RequestBody EmailRequest emailRequest) {
        memberService.activateAccount(emailRequest);
        log.info("이메일 인증 완료, 이메일 = {} ", emailRequest.getEmail());

        return ResponseEntity
            .status(HttpStatus.OK).build();
    }
}
