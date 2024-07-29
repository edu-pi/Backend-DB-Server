package soma.haeya.edupi_db.user.domain.response;

import org.springframework.http.HttpStatus;

public record ErrorResponse(String message, HttpStatus status) {
}
