package soma.edupi.db.account.models.response;

import lombok.Getter;

@Getter
public class SignupResponse {

    String message;

    public SignupResponse() {
        this.message = "회원가입이 완료되었습니다.";
    }
}
