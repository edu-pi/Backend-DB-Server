package soma.edupimeta.account;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import soma.edupimeta.account.models.AccountActivateResponse;
import soma.edupimeta.account.models.EmailRequest;
import soma.edupimeta.account.models.LoginRequest;
import soma.edupimeta.account.models.LoginResponse;
import soma.edupimeta.account.models.SignupOauthRequest;
import soma.edupimeta.account.models.SignupRequest;
import soma.edupimeta.account.models.SignupResponse;


@Tag(name = "Member", description = "Member API")
public interface AccountOpenApi {

    @Operation(summary = "회원가입", description = "사용자가 입력한 회원가입 정보를 DB에 저장하는 API")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "회원가입이 완료되었습니다.", content = @Content(mediaType = "application/json")),
        @ApiResponse(responseCode = "400", description = "중복된 이메일입니다.", content = @Content(mediaType = "application/json")),
        @ApiResponse(responseCode = "500", description = "DB 무결성 위반 오류", content = @Content(mediaType = "application/json")),
    })
    ResponseEntity<SignupResponse> saveAccount(@Valid @RequestBody SignupRequest signupRequest);

    @Operation(summary = "oauth 회원가입", description = "사용자가 입력한 회원가입 정보를 DB에 저장하는 API")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "회원가입이 완료되었습니다.", content = @Content(mediaType = "application/json")),
        @ApiResponse(responseCode = "400", description = "중복된 이메일입니다.", content = @Content(mediaType = "application/json")),
        @ApiResponse(responseCode = "500", description = "DB 무결성 위반 오류", content = @Content(mediaType = "application/json")),
    })
    ResponseEntity<SignupResponse> saveAccountWithOauth(
        @Valid @RequestBody SignupOauthRequest signupOauthRequest);


    @Operation(summary = "이메일 중복 확인", description = "해당 이메일로 가입된 회원이 있는지 확인")
    ResponseEntity<Boolean> isExistsEmail(@RequestParam("email") String email);

    @Operation(summary = "이메일 중복 확인", description = "해당 이메일로 가입된 회원이 있는지 확인")
    ResponseEntity<Boolean> isExistsEmailByProvider(@RequestParam("email") String email,
        @PathVariable(value = "provider") String provider);

    @Operation(summary = "유저 토큰 조회", description = "존재하는 토큰인지 조회하는 API")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "토큰 정보 조회 성공", content = @Content(mediaType = "application/json")),
        @ApiResponse(responseCode = "400", description = "이메일 혹은 비밀번호가 일치하지 않습니다.", content = @Content(mediaType = "application/json")),
    })
    ResponseEntity<LoginResponse> login(@Valid @RequestBody LoginRequest loginRequest);

    @Operation(summary = "oauth 회원 로그인", description = "존재하는 회원인지 조회하는 API")
    ResponseEntity<LoginResponse> login(@Valid @RequestBody EmailRequest emailRequest);

    @Operation(summary = "계정 활성화", description = "사용자가 이메일 인증 후 계정을 활성화 하는 API")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "계정 활성화 성공", content = @Content(mediaType = "application/json")),
        @ApiResponse(responseCode = "400", description = "존재하지 않는 이메일", content = @Content(mediaType = "application/json")),
    })
    ResponseEntity<AccountActivateResponse> activateAccount(@Valid @RequestBody EmailRequest emailRequest);
}
