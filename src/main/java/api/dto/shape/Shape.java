package api.dto.shape;

import api.dto.polygon.Polygon;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class Shape {
    private int id;
    private String name;
    private String createTime;
    private String status;
    private long orderIndex;
    private Progress progress;
    private Models models;
    private List<Polygon> polygons = new ArrayList<>();
    private List<Polygon> initialPolygons;
    private double areaSize;
}
