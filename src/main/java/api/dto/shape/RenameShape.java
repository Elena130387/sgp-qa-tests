package api.dto.shape;

import lombok.Builder;
import lombok.Data;


import static util.Util.getTimestampNowAsString;

@Data
@Builder
public class RenameShape {
    String value;

    public RenameShape() {
        value = String.format("NEW_NAME_%s",getTimestampNowAsString());
    }
}
