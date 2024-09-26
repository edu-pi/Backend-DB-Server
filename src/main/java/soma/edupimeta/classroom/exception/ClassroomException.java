package soma.edupimeta.classroom.exception;

import soma.edupimeta.web.exception.BaseException;
import soma.edupimeta.web.exception.ErrorEnum;

public class ClassroomException extends BaseException {

    public ClassroomException(ErrorEnum errorEnum) {
        super(errorEnum);
    }
}
