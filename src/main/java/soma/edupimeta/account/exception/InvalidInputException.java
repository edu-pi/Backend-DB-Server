package soma.edupimeta.account.exception;

import lombok.Getter;

/**
 * 사용자의 유효하지 않는 입력에 대한 Exception
 */
@Getter
public class InvalidInputException extends RuntimeException {

    public InvalidInputException(String message) {
        super(message);
    }

}
