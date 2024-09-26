package soma.edupimeta.web.exception;

import lombok.Getter;

@Getter
public class DbServerException extends RuntimeException {

    private final ErrorEnum errorCode;

    public DbServerException(ErrorEnum errorCode) {
        super(errorCode.getDetail());
        this.errorCode = errorCode;
    }
}
