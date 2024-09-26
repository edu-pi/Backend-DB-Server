package soma.edupimeta.classroom.account;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import soma.edupimeta.classroom.account.models.ActionChangeRequest;
import soma.edupimeta.classroom.account.models.ActionInitializeRequest;
import soma.edupimeta.classroom.account.models.CreateClassroomAccountRequest;
import soma.edupimeta.classroom.account.service.domain.ClassroomAccount;

@Tag(name = "ClassroomAccount", description = "ClassroomAccount API")
public interface ClassroomAccountOpenApi {

    @Operation(summary = "클래스룸 계정 등록", description = "클래스룸에 계정을 등록하는 API")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "클래스룸 계정 등록에 성공했습니다.", content = @Content(mediaType = "application/json")),
        @ApiResponse(responseCode = "400", description = "이미 등록된 계정입니다.", content = @Content(mediaType = "application/json")),
    })
    ResponseEntity<ClassroomAccount> registerClassroomAccount(
        @RequestBody CreateClassroomAccountRequest createClassroomAccountRequest
    );

    @Operation(summary = "클래스룸에 포함된 계정의 진척도 상태 초기화", description = "클래스룸에 포함된 계정의 진척도 상태 진행중으로 모두 초기화")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "초기화에 성공했습니다.", content = @Content(mediaType = "application/json")),
        @ApiResponse(responseCode = "400", description = "초기화하는데 실패했습니다.", content = @Content(mediaType = "application/json")),
    })
    ResponseEntity<Void> initialization(@RequestBody ActionInitializeRequest actionInitializeRequest);

    @Operation(summary = "클래스룸 계정의 진척도 상태 변경", description = "클래스룸 계정의 진척도 상태를 완료, 도움으로 변환")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "상태 변환에 성공했습니다.", content = @Content(mediaType = "application/json")),
        @ApiResponse(responseCode = "400", description = "상태를 변경할 수 없습니다.", content = @Content(mediaType = "application/json")),
    })
    ResponseEntity<Void> changeClassroomAccountActionStatus(@RequestBody ActionChangeRequest actionChangeRequest);

}
