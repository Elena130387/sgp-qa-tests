package api.dto;

import lombok.Getter;

@Getter
public enum StatusesList {
    COMPLETED("completed"),
    STARTING("starting"),
    STARTED("started"),
    STOPPED("stopped"),
    FAILED("failed"),
    DELETED("deleted");

    private final String statusName;

    StatusesList(String name) {
        this.statusName = name;
    }
}
