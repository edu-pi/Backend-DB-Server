package soma.haeya.edupi_db.member.dto.response;

import lombok.Getter;

@Getter
public class SignUpResponse {

    String message;
    Long memberId;

    public SignUpResponse(Long memberId) {
        this.message = "회원가입이 완료되었습니다.";
        this.memberId = memberId;
    }
}
