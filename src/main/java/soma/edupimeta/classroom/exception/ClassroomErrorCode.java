package soma.edupimeta.classroom.exception;

import org.springframework.http.HttpStatus;
import soma.edupimeta.web.exception.ErrorCode;

public enum ClassroomErrorCode implements ErrorCode {
    //400
    CLASSROOM_NOT_FOUND(HttpStatus.BAD_REQUEST, "DB-400101", "클래스룸이 존재하지 않습니다."),

    //409,
    CLASSROOM_NAME_DUPLICATE(HttpStatus.CONFLICT, "DB-409101", "해당 클래스룸 이름이 이미 있습니다.");

    private final HttpStatus httpStatus;
    private final String code;
    private final String details;

    ClassroomErrorCode(HttpStatus httpStatus, String code, String details) {
        this.httpStatus = httpStatus;
        this.code = code;
        this.details = details;
    }

    @Override
    public HttpStatus getHttpStatus() {
        return httpStatus;
    }

    @Override
    public String getCode() {
        return code;
    }

    @Override
    public String getDetail() {
        return details;
    }

}
