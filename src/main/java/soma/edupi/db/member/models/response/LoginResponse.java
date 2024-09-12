package soma.edupi.db.member.models.response;

import lombok.Builder;
import soma.edupi.db.member.domain.Member;

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
