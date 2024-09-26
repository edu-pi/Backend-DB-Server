package soma.edupimeta.web.exception;

import org.springframework.http.HttpStatus;

public interface ErrorEnum {

    HttpStatus getHttpStatus();

    String getCode();

    String getDetail();
}
