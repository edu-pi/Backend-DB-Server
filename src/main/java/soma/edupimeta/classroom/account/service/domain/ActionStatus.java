package soma.edupimeta.classroom.account.service.domain;

import lombok.Getter;

@Getter
public enum ActionStatus {
    ING(0), HELP(1), COMPLETE(2);

    private final int type;

    ActionStatus(int type) {
        this.type = type;
    }
}
