package soma.edupimeta.account.exception;

import soma.edupimeta.web.exception.DbServerException;
import soma.edupimeta.web.exception.ErrorCode;

public class AccountException extends DbServerException {

    public AccountException(ErrorCode errorCode) {
        super(errorCode);
    }
}
