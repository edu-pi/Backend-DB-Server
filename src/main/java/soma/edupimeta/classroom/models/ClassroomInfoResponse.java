package soma.edupimeta.classroom.models;

import java.util.List;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class ClassroomInfoResponse {

    private String className;
    private List<ClassroomActionInfo> totalActionInfo;

    public ClassroomInfoResponse(String className, List<ClassroomActionInfo> totalActionInfo) {
        this.className = className;
        this.totalActionInfo = totalActionInfo;
    }
}
