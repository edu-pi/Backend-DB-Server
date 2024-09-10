package soma.haeya.edupi_db.member.dto.response;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class SuccessResponse extends Response{

    public SuccessResponse(String message) {
        super(message);
    }
}
