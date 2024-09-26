package soma.edupimeta.classroomAccount.exception;

import soma.edupimeta.web.exception.BaseException;
import soma.edupimeta.web.exception.ErrorEnum;

public class ClassroomAccountException extends BaseException {

    public ClassroomAccountException(ErrorEnum errorEnum) {
        super(errorEnum);
    }
}
