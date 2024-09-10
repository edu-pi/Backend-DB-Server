package soma.haeya.edupi_db.member.dto.response;

import lombok.Getter;

@Getter
public class SignupResponse {

    String message;

    public SignupResponse() {
        this.message = "회원가입이 완료되었습니다.";
    }
}
