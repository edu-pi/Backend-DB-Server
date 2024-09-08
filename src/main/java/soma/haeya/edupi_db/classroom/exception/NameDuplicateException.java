package soma.haeya.edupi_db.classroom.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public class NameDuplicateException extends RuntimeException {

    private final HttpStatus httpStatus;

    public NameDuplicateException(HttpStatus httpStatus, String message) {
        super(message);
        this.httpStatus = httpStatus;
    }
}
