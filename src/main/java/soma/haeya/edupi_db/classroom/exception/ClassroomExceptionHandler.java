package soma.haeya.edupi_db.classroom.exception;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
import soma.haeya.edupi_db.common.exception.DbServerException;
import soma.haeya.edupi_db.common.model.response.DefaultErrorResponse;

@Slf4j
@RestControllerAdvice
public class ClassroomExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(DbServerException.class)
    public ResponseEntity<DefaultErrorResponse> handleDuplicateException(DbServerException exception) {
        printErrorLog(exception);

        return ResponseEntity
            .status(exception.getHttpStatus())
            .body(new DefaultErrorResponse(exception.getMessage()));
    }

    private void printErrorLog(Exception exception) {
        StackTraceElement[] stackTrace = exception.getStackTrace();
        String className = stackTrace[0].getClassName();
        String methodName = stackTrace[0].getMethodName();

        String exceptionMessage = exception.getMessage();

        log.info("Exception occurred in class = {}, method = {}, message = {}, exception class = {}",
            className, methodName, exceptionMessage, exception.getClass().getCanonicalName());
    }

}
