package api.dto.polygon;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class PolygonInput {
    String name;
    String description;
    List<PointDto> coordinates;
    boolean forceProcessing;
    boolean testDataGenerating;
}
