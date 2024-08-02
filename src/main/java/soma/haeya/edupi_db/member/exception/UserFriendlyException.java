package soma.haeya.edupi_db.member.exception;

import lombok.Getter;

/**
 * 사용자에게 에러의 원인을 보여줘야 할 때 사용하는 Exception
 */
@Getter
public class UserFriendlyException extends RuntimeException {

    private String errorCode;

    public UserFriendlyException(String message) {
        super(message);
    }

    public UserFriendlyException(String message, String errorCode) {
        super(message);
        this.errorCode = errorCode;
    }
}
