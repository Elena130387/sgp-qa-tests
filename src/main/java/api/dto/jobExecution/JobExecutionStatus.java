package api.dto.jobExecution;

import lombok.Getter;

@Getter
public enum JobExecutionStatus {
    COMPLETED("completed"),
    STARTING("starting"),
    STARTED("started"),
    STOPPED("stopped"),
    FAILED("failed");

    private final String statusName;

    JobExecutionStatus(String name) {
        this.statusName = name;
    }
}
