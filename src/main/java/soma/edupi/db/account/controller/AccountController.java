package soma.edupi.db.account.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import soma.edupi.db.account.models.request.LoginRequest;
import soma.edupi.db.account.models.request.SignupRequest;
import soma.edupi.db.account.models.response.LoginResponse;
import soma.edupi.db.account.models.response.SignupResponse;
import soma.edupi.db.account.service.AccountService;

@RestController
@RequiredArgsConstructor
@RequestMapping("/v1/account")
public class AccountController implements AccountSpecification {

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
}
