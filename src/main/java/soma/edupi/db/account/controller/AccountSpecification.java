package soma.edupi.db.account.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import soma.edupi.db.account.models.request.LoginRequest;
import soma.edupi.db.account.models.request.SignupRequest;
import soma.edupi.db.account.models.response.LoginResponse;
import soma.edupi.db.account.models.response.SignupResponse;

@Tag(name = "Member", description = "Member API")
public interface AccountSpecification {

    @Operation(summary = "회원가입", description = "사용자가 입력한 회원가입 정보를 DB에 저장하는 API")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "회원가입이 완료되었습니다.", content = @Content(mediaType = "application/json")),
        @ApiResponse(responseCode = "400", description = "중복된 이메일입니다.", content = @Content(mediaType = "application/json")),
        @ApiResponse(responseCode = "500", description = "DB 무결성 위반 오류", content = @Content(mediaType = "application/json")),
    })
    ResponseEntity<SignupResponse> signup(@Valid @RequestBody SignupRequest signupRequest);


    @Operation(summary = "유저 토큰 조회", description = "존재하는 토큰인지 조회하는 API")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "토큰 정보 조회 성공", content = @Content(mediaType = "application/json")),
        @ApiResponse(responseCode = "400", description = "이메일 혹은 비밀번호가 일치하지 않습니다.", content = @Content(mediaType = "application/json")),
    })
    ResponseEntity<LoginResponse> login(@Valid @RequestBody LoginRequest loginRequest);
}
