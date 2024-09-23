package soma.edupimeta.common.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import soma.edupimeta.account.exception.InvalidInputException;
import soma.edupimeta.account.exception.ServerException;
import soma.edupimeta.common.models.DefaultErrorResponse;

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
