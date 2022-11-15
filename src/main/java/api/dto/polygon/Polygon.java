package api.dto.polygon;


import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class Polygon {
    private int id;
    private long estimatorJobId;
    private String name;
    private List<PointDto> coordinates;
    private String startTime;
    private String endTime;
    private String status;
    private String processingPhase;
    private int shapeId;
    private double areaSize;
    private List<GeoInfo> geoInfo;
}
