package soma.edupimeta.classroom.models;

import lombok.Getter;
import lombok.NoArgsConstructor;
import soma.edupimeta.classroom.account.service.domain.ActionStatus;

@Getter
@NoArgsConstructor
public class ClassroomActionInfo {

    private String name;
    private Long count;

    public ClassroomActionInfo(int actionValue, Long count) {
        this.name = ActionStatus.fromValue(actionValue).name();
        this.count = count;
    }
}
