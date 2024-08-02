package soma.haeya.edupi_db.member.exception;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import soma.haeya.edupi_db.member.dto.response.ErrorResponse;

@Slf4j
@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(UserFriendlyException.class)
    public ResponseEntity<ErrorResponse> handleUserFriendlyException(UserFriendlyException ex) {
        ErrorResponse errorResponse = new ErrorResponse(ex.getMessage());
        log.error("[GlobalExceptionHandler] UserFriendlyException 예외 발생 : {}", ex.getMessage());

        return ResponseEntity
            .status(HttpStatus.BAD_REQUEST)
            .body(errorResponse);
    }
}
