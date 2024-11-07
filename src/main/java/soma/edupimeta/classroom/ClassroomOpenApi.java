package soma.edupimeta.classroom;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import java.util.List;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import soma.edupimeta.classroom.models.ClassroomInfoResponse;
import soma.edupimeta.classroom.models.CreateClassroomRequest;
import soma.edupimeta.classroom.models.MyClassroomResponse;
import soma.edupimeta.classroom.service.domain.Classroom;

@Tag(name = "Classroom", description = "Classroom API")
public interface ClassroomOpenApi {

    @Operation(summary = "클래스룸 생성", description = "사용자가 입력한 클래스룸 정보를 DB에 저장하는 API")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "클래스룸 생성이 완료되었습니다.", content = @Content(mediaType = "application/json")),
    })
    ResponseEntity<Classroom> createClassroom(@RequestBody CreateClassroomRequest createClassroomRequest);

    @Operation(summary = "클래스룸 조회", description = "사용자 계정에 연관된 클래스룸 조회")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "클래스룸 조회 성공", content = @Content(mediaType = "application/json")),
    })
    ResponseEntity<List<MyClassroomResponse>> getMyClassrooms(Long accountId);

    @Operation(summary = "클래스룸 삭제", description = "클래스룸과 포함된 계정 모두 삭제")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "클래스룸 삭제 성공", content = @Content(mediaType = "application/json")),
        @ApiResponse(responseCode = "400", description = "해당 클래스룸이 없습니다.", content = @Content(mediaType = "application/json")),
    })
    ResponseEntity<Void> deleteClassroom(@RequestParam Long classroomId);

    @Operation(summary = "클래스룸 이름과 포함된 계정의 action 정보 반환", description = "클래스룸 이름과 포함된 계정의 현재 action 정보 반환")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "정보를 불러오는데 성공헀습니다.", content = @Content(mediaType = "application/json")),
        @ApiResponse(responseCode = "400", description = "해당 클래스룸이 없습니다.", content = @Content(mediaType = "application/json")),
    })
    ResponseEntity<ClassroomInfoResponse> getInfo(@RequestParam Long classroomId);
}
