package soma.edupi.db.account.models.response;

import lombok.Getter;

@Getter
public class AccountActivateResponse {

    private String email;

    public AccountActivateResponse(String email) {
        this.email = email;
    }
}
