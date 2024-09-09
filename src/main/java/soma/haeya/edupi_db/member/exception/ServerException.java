package soma.haeya.edupi_db.member.exception;

/**
 * 서버에서 처리 실패에 대한 Exception
 */
public class ServerException extends RuntimeException{
    public ServerException(String message) {
        super(message);
    }
}
