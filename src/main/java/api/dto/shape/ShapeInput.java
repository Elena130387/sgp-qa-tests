package api.dto.shape;

import api.dto.polygon.PolygonInput;
import lombok.Builder;
import lombok.Data;

import java.util.List;

import static util.Util.getTimestampNowAsString;

@Data
@Builder
public class ShapeInput {
    private String name;
    private List<PolygonInput> polygons;

    public void AddDateToShapeName() {
        name = name + "_" + getTimestampNowAsString();
    }
}
