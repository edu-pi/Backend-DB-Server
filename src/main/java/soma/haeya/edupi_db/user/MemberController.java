package soma.haeya.edupi_db.user;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import soma.haeya.edupi_db.user.dto.request.SignupRequest;
import soma.haeya.edupi_db.user.dto.response.SuccessResponse;

@Slf4j
@RestController
@RequestMapping("/user/*")
@RequiredArgsConstructor
public class MemberController {

    private final MemberService memberService;

    @RequestMapping(value = "save/signup", method = RequestMethod.POST)
    public ResponseEntity<SuccessResponse> createPost(@RequestBody SignupRequest signupRequest) {
        memberService.saveMember(signupRequest);

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(new SuccessResponse("회원가입 성공"));

    }
}
