package api.dto.shape;


import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class PolygonInput {
    String name;
    String description;
    List<PointDto> coordinates;
    int shapeId;
    boolean forceProcessing;
    boolean testDataGenerating;
}
