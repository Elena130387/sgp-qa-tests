package api.dto.polygon;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class PolygonInput {
    private List<PointDto> coordinates;
    private String name;
    private boolean forceProcessing;
    private boolean testDataGenerating;
}
