package soma.edupimeta.account.exception;

import soma.edupimeta.web.exception.BaseException;
import soma.edupimeta.web.exception.ErrorEnum;

public class AccountException extends BaseException {

    public AccountException(ErrorEnum errorEnum) {
        super(errorEnum);
    }
}
