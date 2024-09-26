package soma.edupimeta.classroomAccount.exception;

import soma.edupimeta.web.exception.DbServerException;
import soma.edupimeta.web.exception.ErrorEnum;

public class ClassroomAccountException extends DbServerException {

    public ClassroomAccountException(ErrorEnum errorEnum) {
        super(errorEnum);
    }
}
