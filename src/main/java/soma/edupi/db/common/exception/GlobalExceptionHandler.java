package soma.edupi.db.common.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import soma.edupi.db.common.models.response.DefaultErrorResponse;
import soma.edupi.db.member.exception.InvalidInputException;
import soma.edupi.db.member.exception.ServerException;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(InvalidInputException.class)
    public ResponseEntity<DefaultErrorResponse> handleInvalidInputException(InvalidInputException ex) {
        return ResponseEntity
            .status(HttpStatus.BAD_REQUEST)
            .body(new DefaultErrorResponse(ex.getMessage()));
    }

    @ExceptionHandler(ServerException.class)
    public ResponseEntity<DefaultErrorResponse> handleServerException(ServerException ex) {
        return ResponseEntity
            .status(HttpStatus.INTERNAL_SERVER_ERROR)
            .body(new DefaultErrorResponse(ex.getMessage()));
    }
}
