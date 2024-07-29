package soma.haeya.edupi_db.user.dto.response;

import org.springframework.http.HttpStatus;

public record ErrorResponse(String message, HttpStatus status) {
}
