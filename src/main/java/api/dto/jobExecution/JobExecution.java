package api.dto.jobExecution;

import api.dto.polygon.PolygonInput;
import lombok.Data;

@Data
public class JobExecution {
    private long id;
    private String createdTime;
    private String startTime;
    private String endTime;
    private String exitCode;
    private String status;
    private String processingPhase;
    private PolygonInput polygon;
}
