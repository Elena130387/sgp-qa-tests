package api.dto.shape;

import api.dto.polygon.Polygon;
import lombok.Data;

import java.util.List;

@Data
public class ExistingShape {
    int id;
    String name;
    String createTime;
    String status;
    long orderIndex;
    Progress progress;
    List<Polygon> polygons;
    double areaSize;
}
