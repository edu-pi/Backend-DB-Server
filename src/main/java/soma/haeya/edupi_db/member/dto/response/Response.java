package soma.haeya.edupi_db.member.dto.response;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class Response {

    String message;

    public Response(String message) {
        this.message = message;
    }
}
