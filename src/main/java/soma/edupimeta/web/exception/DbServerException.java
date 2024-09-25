package soma.edupimeta.web.exception;

import lombok.Getter;

@Getter
public class DbServerException extends RuntimeException {

    private final ErrorCode errorCode;

    public DbServerException(ErrorCode errorCode) {
        super(errorCode.getDetail());
        this.errorCode = errorCode;
    }
}
