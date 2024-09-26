package soma.edupimeta.classroom.account.exception;

import org.springframework.http.HttpStatus;
import soma.edupimeta.web.exception.ErrorEnum;

public enum ClassroomAccountErrorEnum implements ErrorEnum {
    // 400
    ALREADY_REGISTER(HttpStatus.BAD_REQUEST, "DB-400201", "클래스룸에 이미 참여되어 있습니다."),
    CAN_NOT_UPDATE_ACTION_STATUS(HttpStatus.BAD_REQUEST, "DB-400202", "상태를 반영할 수 없습니다.");

    private final HttpStatus httpStatus;
    private final String code;
    private final String details;

    ClassroomAccountErrorEnum(HttpStatus httpStatus, String code, String details) {
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
