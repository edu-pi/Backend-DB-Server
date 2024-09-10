package soma.haeya.edupi_db.member.dto.response;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class ErrorResponse extends Response{

    public ErrorResponse(String message) {
        super(message);
    }
}
