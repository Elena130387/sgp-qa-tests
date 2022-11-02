package api.dto.shape;

import lombok.Builder;
import lombok.Data;

import java.util.List;

import static util.Util.getTimestampNowAsString;

@Data
@Builder
public class RenameShape {
    String name;
    List<PolygonInput> polygons;

    public RenameShape() {
        name = String.format("NEW_NAME_%s",getTimestampNowAsString());
    }
}
