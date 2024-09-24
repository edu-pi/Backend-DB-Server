package soma.edupimeta.account.models;

import lombok.Getter;
import soma.edupimeta.account.service.domain.Account;
import soma.edupimeta.account.service.domain.AccountRole;

@Getter
public class SignupResponse {

    Long id;
    String email;
    String name;
    AccountRole role;
    String phoneNumber;
    boolean isEnabled;

    public SignupResponse(Account account) {
        this.id = account.getId();
        this.email = account.getEmail();
        this.name = account.getName();
        this.role = account.getRole();
        this.phoneNumber = account.getPhoneNumber();
        this.isEnabled = account.getIsEnabled();
    }
}
