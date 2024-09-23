package soma.edupimeta.classroomAccount.service.domain;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum ClassroomAccountRole {

    ROLE_HOST(1),
    ROLE_GUEST(2);

    private final int code;

}
