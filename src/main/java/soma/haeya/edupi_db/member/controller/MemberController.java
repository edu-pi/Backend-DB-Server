package soma.haeya.edupi_db.member.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import soma.haeya.edupi_db.member.dto.request.LoginRequest;
import soma.haeya.edupi_db.member.dto.request.SignupRequest;
import soma.haeya.edupi_db.member.dto.response.LoginResponse;
import soma.haeya.edupi_db.member.dto.response.SignUpResponse;
import soma.haeya.edupi_db.member.service.MemberService;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/member")
public class MemberController {

    private final MemberService memberService;

    @PostMapping("/signup")
    public ResponseEntity<SignUpResponse> saveMember(@Valid @RequestBody SignupRequest signupRequest) {
        Long memberId = memberService.saveMember(signupRequest);

        return ResponseEntity
            .status(HttpStatus.OK)
            .body(new SignUpResponse(memberId));
    }

    @PostMapping("/findByEmailAndPassword")
    public ResponseEntity<LoginResponse> findMemberByEmailAndPassword(@Valid @RequestBody LoginRequest loginRequest) {
        LoginResponse loginResponse = memberService.findMemberByEmailAndPassword(loginRequest);

        return ResponseEntity
            .status(HttpStatus.OK)
            .body(loginResponse);
    }
}
