package soma.edupimeta.account.models;

import lombok.Getter;

@Getter
public class AccountActivateResponse {

    private String email;

    public AccountActivateResponse(String email) {
        this.email = email;
    }
}
