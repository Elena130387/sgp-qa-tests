package api.dto.shape;

import lombok.Data;

import static util.Util.getTimestampNowAsString;

@Data
public class ShapeRename {
    private String value;

    public ShapeRename() {
        value = String.format("NEW_NAME_%s", getTimestampNowAsString());
    }

}
