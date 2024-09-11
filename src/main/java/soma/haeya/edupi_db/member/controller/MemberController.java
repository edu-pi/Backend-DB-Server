package soma.haeya.edupi_db.member.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import soma.haeya.edupi_db.member.dto.request.LoginRequest;
import soma.haeya.edupi_db.member.dto.request.SignupRequest;
import soma.haeya.edupi_db.member.dto.response.LoginResponse;
import soma.haeya.edupi_db.member.dto.response.SignupResponse;
import soma.haeya.edupi_db.member.service.MemberService;

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
}
