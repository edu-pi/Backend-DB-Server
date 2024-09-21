package soma.edupi.db.classroom.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import soma.edupi.db.classroom.domain.Classroom;
import soma.edupi.db.classroom.models.request.CreateClassroomRequest;

@Tag(name = "Classroom", description = "Classroom API")
public interface ClassroomSpecification {

    @Operation(summary = "클래스룸 생성", description = "사용자가 입력한 클래스룸 정보를 DB에 저장하는 API")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "클래스룸 생성이 완료되었습니다.", content = @Content(mediaType = "application/json")),
    })
    ResponseEntity<Classroom> createClassroom(@RequestBody CreateClassroomRequest createClassroomRequest);
}
