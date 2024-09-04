package soma.haeya.edupi_db.member.dto.response;

import lombok.Getter;

@Getter
public class ErrorResponse {
    String message;

    public ErrorResponse(String message) {
        this.message = message;
    }
}
