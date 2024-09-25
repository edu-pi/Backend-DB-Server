package soma.edupimeta.classroomAccount.exception;

import org.springframework.http.HttpStatus;
import soma.edupimeta.web.exception.ErrorCode;

public enum ClassroomAccountErrorCode implements ErrorCode {
    // 400
    ALREADY_REGISTER(HttpStatus.BAD_REQUEST, "DB-400201", "클래스룸에 이미 참여되어 있습니다."),

    ;

    private final HttpStatus httpStatus;
    private final String code;
    private final String details;

    ClassroomAccountErrorCode(HttpStatus httpStatus, String code, String details) {
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