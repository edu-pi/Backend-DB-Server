package soma.edupimeta.classroom.account.exception;

import org.springframework.http.HttpStatus;
import soma.edupimeta.web.exception.ErrorEnum;

public enum ClassroomAccountErrorEnum implements ErrorEnum {
    // 400
    ALREADY_REGISTER(HttpStatus.BAD_REQUEST, "DB-400201", "클래스룸에 이미 참여되어 있습니다."),
    ALREADY_UPDATE_ACTION_STATUS(HttpStatus.BAD_REQUEST, "DB-400202", "이미 동일한 행동입니다."),
    HOST_CAN_NOT_UPDATE_ACTION_STATUS(HttpStatus.BAD_REQUEST, "DB-400203", "클래스룸을 생성한 사람은 행동을 수정할 수 없습니다."),
    INVALID_VALUE(HttpStatus.BAD_REQUEST, "DB-400204", "잘못된 값이 들어왔습니다."),

    //404
    CLASSROOM_ACCOUNT_NOT_FOUND(HttpStatus.NOT_FOUND, "DB-404200", "해당 클래스룸 계정을 찾을 수 없습니다.");

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
