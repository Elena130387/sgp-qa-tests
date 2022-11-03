package api.dto.shape;

import lombok.Data;

import java.util.List;

@Data
public class Shape {
    int id;
    String name;
    String createTime;
    String status;
    long orderIndex;
    Progress progress;
    List<Polygon> polygons;
    double areaSize;
}
