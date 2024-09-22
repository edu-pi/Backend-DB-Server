package soma.edupi.db.account.models.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class EmailRequest {

    private String email;

    public EmailRequest(@JsonProperty("email") String email) {
        this.email = email;
    }

}
