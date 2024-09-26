package soma.edupimeta.classroom.exception;

import soma.edupimeta.web.exception.DbServerException;
import soma.edupimeta.web.exception.ErrorEnum;

public class ClassroomException extends DbServerException {

    public ClassroomException(ErrorEnum errorEnum) {
        super(errorEnum);
    }
}
