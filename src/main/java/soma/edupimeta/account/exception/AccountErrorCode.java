package soma.edupimeta.account.exception;

import org.springframework.http.HttpStatus;
import soma.edupimeta.web.exception.ErrorCode;

public enum
AccountErrorCode implements ErrorCode {
    // 400
    EMAIL_NOT_MATCH(HttpStatus.BAD_REQUEST, "DB-400001", "존재하지 않는 회원입니다."),
    EMAIL_OR_PASSWORD_NOT_MATCH(HttpStatus.BAD_REQUEST, "DB-400002", "아이디 혹은 비밀번호가 일치하지 않습니다."),

    // 409
    EMAIL_DUPLICATE(HttpStatus.CONFLICT, "DB-409001", "이메일 중복입니다."),

    ;

    private final HttpStatus httpStatus;
    private final String code;
    private final String detail;

    AccountErrorCode(HttpStatus httpStatus, String code, String details) {
        this.httpStatus = httpStatus;
        this.code = code;
        this.detail = details;
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
        return detail;
    }

}
