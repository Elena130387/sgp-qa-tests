package api.dto.shape;

import api.dto.polygon.PolygonInput;
import lombok.Builder;
import lombok.Data;

import java.util.List;

import static util.Util.getTimestampNowAsString;

@Data
@Builder
public class NewShape {
    String name;
    List<PolygonInput> polygons;
    int shapeGroupId;
    String pipelineType;

    public void AddDateToShapeName() {
        name = name + "_" + getTimestampNowAsString();
    }
}
