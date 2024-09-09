package soma.haeya.edupi_db;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import soma.haeya.edupi_db.common.model.response.DefaultErrorResponse;
import soma.haeya.edupi_db.member.exception.InvalidInputException;
import soma.haeya.edupi_db.member.exception.ServerException;

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
