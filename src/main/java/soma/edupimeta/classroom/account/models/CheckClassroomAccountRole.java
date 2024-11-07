package soma.edupimeta.classroom.account.models;

import com.fasterxml.jackson.annotation.JsonProperty;

public class CheckClassroomAccountRole {

    @JsonProperty(value = "isAccess")
    private final boolean isAccess;
    @JsonProperty(value = "isHost")
    private final boolean isHost;

    public CheckClassroomAccountRole(boolean isAccess, boolean isHost) {
        this.isAccess = isAccess;
        this.isHost = isHost;
    }
}
