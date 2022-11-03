package api.dto.shape;


import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class Polygon {
    int id;
    int estimatorJobId;
    String name;
    List<PointDto> coordinates;
    String startTime;
    String endTime;
    String status;
    String processingPhase;
    int shapeId;
    double areaSize;
    List<GeoInfo> geoInfo;
}
