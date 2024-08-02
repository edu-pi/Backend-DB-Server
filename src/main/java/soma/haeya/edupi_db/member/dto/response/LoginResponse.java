package soma.haeya.edupi_db.member.dto.response;

import lombok.Builder;

@Builder
public record LoginResponse(String email, String name, String role) {

}
