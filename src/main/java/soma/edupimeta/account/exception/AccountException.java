package soma.edupimeta.account.exception;

import soma.edupimeta.web.exception.DbServerException;
import soma.edupimeta.web.exception.ErrorEnum;

public class AccountException extends DbServerException {

    public AccountException(ErrorEnum errorEnum) {
        super(errorEnum);
    }
}
