package soma.haeya.edupi_db.member.dto.response;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class ErrorResponse {

    String message;
    public ErrorResponse(String message) {
        this.message = message;
    }
}
