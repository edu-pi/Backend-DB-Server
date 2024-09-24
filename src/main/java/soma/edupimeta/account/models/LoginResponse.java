package soma.edupimeta.account.models;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import soma.edupimeta.account.service.domain.Account;
import soma.edupimeta.account.service.domain.AccountRole;

@Getter
@NoArgsConstructor
public class LoginResponse {

    private Long id;
    private String email;
    private String name;
    private AccountRole accountRole;

    @Builder
    public LoginResponse(Long id, String email, String name, AccountRole role) {
        this.id = id;
        this.email = email;
        this.name = name;
        this.accountRole = role;
    }

    public static LoginResponse of(Account account) {
        return LoginResponse.builder()
            .id(account.getId())
            .email(account.getEmail())
            .name(account.getName())
            .role(account.getRole())
            .build();
    }
}
