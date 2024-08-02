package soma.haeya.edupi_db.member.dto.request;

import lombok.Data;

@Data
public class LoginRequest {

    private String email;
    private String password;
}
