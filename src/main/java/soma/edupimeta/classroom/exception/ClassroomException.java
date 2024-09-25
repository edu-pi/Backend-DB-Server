package soma.edupimeta.classroom.exception;

import soma.edupimeta.web.exception.DbServerException;
import soma.edupimeta.web.exception.ErrorCode;

public class ClassroomException extends DbServerException {

    public ClassroomException(ErrorCode errorCode) {
        super(errorCode);
    }
}
