package soma.haeya.edupi_db.common.models.response;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class DefaultErrorResponse {

    private final String message;
}
