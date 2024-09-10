package soma.haeya.edupi_db.member.dto.response;

import lombok.Getter;

@Getter
public class SignupResponse extends Response {

    public SignupResponse() {
        super("회원가입이 완료되었습니다.");
    }
}
