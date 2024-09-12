package soma.edupi.db.classroomAccount.domain;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum ClassroomAccountRole {

    ROLE_LEADER(1),
    ROLE_FOLLOWER(2);

    private final int tag;

}
