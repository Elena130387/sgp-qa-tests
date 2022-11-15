package api.dto.jobExecution;

import lombok.Data;

@Data
public class JobExecution {
    private long id;
    private String startTime;
    private String endTime;
    private String exitCode;
    private String status;
    private String processingPhase;
}
