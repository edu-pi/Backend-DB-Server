package soma.edupimeta.account;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import soma.edupimeta.account.models.EmailRequest;
import soma.edupimeta.account.models.LoginRequest;
import soma.edupimeta.account.models.SignupRequest;
import soma.edupimeta.account.models.AccountActivateResponse;
import soma.edupimeta.account.models.LoginResponse;
import soma.edupimeta.account.models.SignupResponse;
import soma.edupimeta.account.service.AccountService;


@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/v1/account")
public class AccountController implements AccountOpenApi {

    private final AccountService accountService;

    @Override
    @PostMapping("/signup")
    public ResponseEntity<SignupResponse> saveAccount(@Valid @RequestBody SignupRequest signupRequest) {
        accountService.saveAccount(signupRequest);

        return ResponseEntity
            .status(HttpStatus.OK)
            .body(new SignupResponse());
    }

    @Override
    @PostMapping("/login")
    public ResponseEntity<LoginResponse> findAccountByEmail(@Valid @RequestBody LoginRequest loginRequest) {
        LoginResponse loginResponse = accountService.findAccountByEmail(loginRequest);

        return ResponseEntity
            .status(HttpStatus.OK)
            .body(loginResponse);
    }


    @PostMapping("/activate")
    public ResponseEntity<AccountActivateResponse> activateAccount(@Valid @RequestBody EmailRequest emailRequest) {
        String email = accountService.activateAccount(emailRequest);
        log.info("이메일 인증 완료, 이메일 = {} ", emailRequest.getEmail());

        return ResponseEntity
            .status(HttpStatus.OK)
            .body(new AccountActivateResponse(email));
    }
}
