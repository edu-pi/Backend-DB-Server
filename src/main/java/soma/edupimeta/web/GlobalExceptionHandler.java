package soma.edupimeta.web;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import soma.edupimeta.account.exception.AccountException;
import soma.edupimeta.web.exception.BaseException;
import soma.edupimeta.web.exception.ErrorEnum;
import soma.edupimeta.web.models.ErrorResponse;

@Slf4j
@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(BaseException.class)
    public ResponseEntity<ErrorResponse> handleServerException(BaseException exception) {
        printErrorLog(exception);

        ErrorEnum errorEnum = exception.getErrorCode();

        return ResponseEntity
            .status(errorEnum.getHttpStatus())
            .body(new ErrorResponse(errorEnum.getCode(), errorEnum.getDetail()));
    }

    @ExceptionHandler(AccountException.class)
    public ResponseEntity<ErrorResponse> handleServerException(AccountException exception) {
        printErrorLog(exception);

        ErrorEnum errorEnum = exception.getErrorCode();

        return ResponseEntity
            .status(errorEnum.getHttpStatus())
            .body(new ErrorResponse(errorEnum.getCode(), errorEnum.getDetail()));
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponse> handleException(Exception exception) {
        printErrorLog(exception);

        return ResponseEntity
            .status(HttpStatus.BAD_REQUEST)
            .body(new ErrorResponse("DB-400999", "예상하지 못한 에러가 발생했습니다."));
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
