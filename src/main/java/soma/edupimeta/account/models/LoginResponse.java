package soma.edupimeta.account.models;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import soma.edupimeta.account.service.domain.Account;

@Getter
@NoArgsConstructor
public class LoginResponse {

    private String email;
    private String name;
    private String role;

    @Builder
    public LoginResponse(String email, String name, String role) {
        this.email = email;
        this.name = name;
        this.role = role;
    }

    public static LoginResponse of(Account member) {
        return LoginResponse.builder()
            .email(member.getEmail())
            .name(member.getName())
            .role(member.getRole())
            .build();
    }
}
