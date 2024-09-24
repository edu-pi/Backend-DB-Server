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
import soma.edupimeta.account.models.AccountActivateResponse;
import soma.edupimeta.account.models.EmailRequest;
import soma.edupimeta.account.models.LoginRequest;
import soma.edupimeta.account.models.LoginResponse;
import soma.edupimeta.account.models.SignupRequest;
import soma.edupimeta.account.models.SignupResponse;
import soma.edupimeta.account.service.AccountService;
import soma.edupimeta.account.service.domain.Account;


@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/v1/account")
public class AccountController implements AccountOpenApi {

    private final AccountService accountService;

    @Override
    @PostMapping("/signup")
    public ResponseEntity<SignupResponse> saveAccount(@Valid @RequestBody SignupRequest signupRequest) {
        Account account = accountService.signup(signupRequest);

        return ResponseEntity
            .status(HttpStatus.OK)
            .body(new SignupResponse(account));
    }

    @Override
    @PostMapping("/login")
    public ResponseEntity<LoginResponse> login(@Valid @RequestBody LoginRequest loginRequest) {
        Account account = accountService.login(loginRequest.getEmail(), loginRequest.getPassword());

        return ResponseEntity
            .status(HttpStatus.OK)
            .body(LoginResponse.of(account));
    }

    @PostMapping("/activate")
    public ResponseEntity<AccountActivateResponse> activateAccount(@Valid @RequestBody EmailRequest emailRequest) {
        log.info("계정 활성화 요청 = {} ", emailRequest.getEmail());

        String email = accountService.verifyAccountByEmail(emailRequest.getEmail());

        return ResponseEntity
            .status(HttpStatus.OK)
            .body(new AccountActivateResponse(email));
    }
}
