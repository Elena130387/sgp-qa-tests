package api.dto.shape;

import lombok.Getter;

@Getter
public enum ShapeStatus {
    COMPLETED("completed"),
    STARTING("starting"),
    STOPPED("stopped"),
    FAILED("failed"),
    DELETED("deleted");

    private final String statusName;

    ShapeStatus(String name) {
        this.statusName = name;
    }
}
