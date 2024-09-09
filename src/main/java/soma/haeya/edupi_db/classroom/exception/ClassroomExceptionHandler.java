package soma.haeya.edupi_db.classroom.exception;

import jakarta.validation.ConstraintViolationException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
import soma.haeya.edupi_db.common.exception.DbServerException;
import soma.haeya.edupi_db.common.exception.ExceptionLogger;
import soma.haeya.edupi_db.common.model.response.DefaultErrorResponse;

@Slf4j
@RestControllerAdvice(basePackages = {"soma.haeya.edupi_db.classroom"})
public class ClassroomExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(DbServerException.class)
    public ResponseEntity<DefaultErrorResponse> handleDuplicateException(DbServerException exception) {
        ExceptionLogger.printErrorLog(exception);

        return ResponseEntity
            .status(exception.getHttpStatus())
            .body(new DefaultErrorResponse(exception.getMessage()));
    }

    // Valid 에러 핸들링
    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<DefaultErrorResponse> validationError(ConstraintViolationException exception) {
        ExceptionLogger.printErrorLog(exception);

        return ResponseEntity
            .status(HttpStatus.BAD_REQUEST)
            .body(new DefaultErrorResponse("잘못된 요청입니다."));
    }


}
