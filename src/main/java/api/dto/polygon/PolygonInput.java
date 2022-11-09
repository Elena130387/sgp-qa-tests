package api.dto.polygon;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class PolygonInput {
    List<PointDto> coordinates;
    String name;
    boolean forceProcessing;
    boolean testDataGenerating;
}
