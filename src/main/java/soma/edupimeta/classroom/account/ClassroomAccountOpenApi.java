package soma.edupimeta.classroom.account;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import soma.edupimeta.classroom.account.models.CreateClassroomAccountRequest;
import soma.edupimeta.classroom.account.service.domain.ClassroomAccount;

@Tag(name = "ClassroomAccount", description = "ClassroomAccount API")
public interface ClassroomAccountOpenApi {

    @Operation(summary = "게스트 등록", description = "클래스룸에 게스트를 등록하는 API")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "게스트 등록에 성공했습니다.", content = @Content(mediaType = "application/json")),
        @ApiResponse(responseCode = "400", description = "이미 등록된 계정입니다.", content = @Content(mediaType = "application/json")),
    })
    ResponseEntity<ClassroomAccount> registerClassroomAccount(
        @RequestBody CreateClassroomAccountRequest createClassroomAccountRequest
    );
}
