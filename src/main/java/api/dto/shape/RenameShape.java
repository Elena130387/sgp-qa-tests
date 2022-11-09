package api.dto.shape;

import lombok.Data;

import static util.Util.getTimestampNowAsString;

@Data
public class RenameShape {
    String value;

    public RenameShape() {
        value = String.format("NEW_NAME_%s", getTimestampNowAsString());
    }

}
