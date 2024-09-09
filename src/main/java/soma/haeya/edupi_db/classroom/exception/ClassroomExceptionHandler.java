package soma.haeya.edupi_db.classroom.exception;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
import soma.haeya.edupi_db.common.model.response.DefaultErrorResponse;

@Slf4j
@RestControllerAdvice
public class ClassroomExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(NameDuplicateException.class)
    public ResponseEntity<DefaultErrorResponse> handleDuplicateException(NameDuplicateException exception) {
        printErrorLog(exception);

        return ResponseEntity
            .status(exception.getHttpStatus())
            .body(new DefaultErrorResponse("중복된 이름의 클래스룸이 있습니다."));
    }

    private void printErrorLog(NameDuplicateException exception) {
        StackTraceElement[] stackTrace = exception.getStackTrace();
        String className = stackTrace[0].getClassName();
        String methodName = stackTrace[0].getMethodName();

        String exceptionMessage = exception.getMessage();

        log.info("Exception occurred in class = {}, method = {}, message = {}, exception class = {}",
            className, methodName, exceptionMessage, exception.getClass().getCanonicalName());
    }

}