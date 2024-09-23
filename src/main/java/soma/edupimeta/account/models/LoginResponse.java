package soma.edupimeta.account.models;

import lombok.Builder;
import soma.edupimeta.account.service.domain.Account;

@Builder
public record LoginResponse(String email, String name, String role) {

    public static LoginResponse of(Account member) {
        return LoginResponse.builder()
            .email(member.getEmail())
            .name(member.getName())
            .role(member.getRole())
            .build();
    }
}
