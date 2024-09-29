package soma.edupimeta.classroom.account.service.domain;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

public enum ClassroomAccountRole {
    HOST(1), GUEST(2);

    private final int value;

    ClassroomAccountRole(int value) {
        this.value = value;
    }

    @JsonCreator
    public static ClassroomAccountRole fromValue(int value) {
        for (ClassroomAccountRole role : ClassroomAccountRole.values()) {
            if (role.getValue() == value) {
                return role;
            }
        }
        throw new IllegalArgumentException("Invalid value for ClassroomAccountRole: " + value);
    }

    @JsonValue
    public int getValue() {
        return value;
    }
}
