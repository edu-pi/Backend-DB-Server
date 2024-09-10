package soma.haeya.edupi_db.common.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public class DbServerException extends RuntimeException {

    private final HttpStatus httpStatus;

    public DbServerException(HttpStatus httpStatus, String message) {
        super(message);
        this.httpStatus = httpStatus;
    }
}
