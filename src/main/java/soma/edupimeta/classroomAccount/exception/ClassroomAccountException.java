package soma.edupimeta.classroomAccount.exception;

import soma.edupimeta.web.exception.DbServerException;
import soma.edupimeta.web.exception.ErrorCode;

public class ClassroomAccountException extends DbServerException {

    public ClassroomAccountException(ErrorCode errorCode) {
        super(errorCode);
    }
}
