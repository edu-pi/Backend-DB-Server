package soma.haeya.edupi_db.member.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import soma.haeya.edupi_db.member.dto.request.EmailRequest;
import soma.haeya.edupi_db.member.dto.request.LoginRequest;
import soma.haeya.edupi_db.member.dto.request.SignupRequest;
import soma.haeya.edupi_db.member.dto.response.ErrorResponse;
import soma.haeya.edupi_db.member.dto.response.LoginResponse;
import soma.haeya.edupi_db.member.dto.response.Response;
import soma.haeya.edupi_db.member.dto.response.SignupResponse;
import soma.haeya.edupi_db.member.dto.response.SuccessResponse;
import soma.haeya.edupi_db.member.service.MemberService;

@RestController
@RequiredArgsConstructor
@RequestMapping("/member")
public class MemberController {

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

    @PostMapping("/check-email")
    public ResponseEntity<Response> isEmailDuplicated(@Valid @RequestBody EmailRequest emailRequest){
        boolean isDuplicated =  memberService.isEmailDuplicated(emailRequest.getEmail());

        if (isDuplicated) {
            return ResponseEntity.status(HttpStatus.CONFLICT)
                .body(new ErrorResponse("중복 이메일입니다."));

        } else {
            return ResponseEntity.ok()
                .body(new SuccessResponse("사용 가능합니다."));

        }
    }
}
