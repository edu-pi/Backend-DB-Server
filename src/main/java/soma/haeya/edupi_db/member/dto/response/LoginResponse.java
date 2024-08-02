package soma.haeya.edupi_db.member.dto.response;

import lombok.Builder;
import soma.haeya.edupi_db.member.domain.Member;

@Builder
public record LoginResponse(String email, String name, String role) {

    public static LoginResponse of(Member member) {
        return LoginResponse.builder()
            .email(member.getEmail())
            .name(member.getName())
            .role(member.getRole())
            .build();
    }
}
