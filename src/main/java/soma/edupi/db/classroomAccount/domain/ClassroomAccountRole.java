package soma.edupi.db.classroomAccount.domain;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum ClassroomAccountRole {

    ROLE_HOST(1, "호스트"),
    ROLE_GUEST(2, "게스트");

    private final int code;
    private final String name;

}
