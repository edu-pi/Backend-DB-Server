package soma.edupimeta.common.exception;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class ExceptionLogger {

    public static void printErrorLog(Exception exception) {
        StackTraceElement[] stackTrace = exception.getStackTrace();
        String className = stackTrace[0].getClassName();
        String methodName = stackTrace[0].getMethodName();

        String exceptionMessage = exception.getMessage();

        log.info("Exception occurred in class = {}, method = {}, message = {}, exception class = {}",
            className, methodName, exceptionMessage, exception.getClass().getCanonicalName());
    }

}
