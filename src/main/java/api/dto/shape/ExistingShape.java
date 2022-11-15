package api.dto.shape;

import api.dto.polygon.Polygon;
import lombok.Data;

import java.util.List;

@Data
public class ExistingShape {
    private int id;
    private String name;
    private String createTime;
    private String status;
    private long orderIndex;
    private Progress progress;
    private List<Polygon> polygons;
    private double areaSize;
}
