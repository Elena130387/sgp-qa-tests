package api.dto.shape;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

@Data
public class Models {
    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    private Progress footprint;

    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    private Progress objectDetection;

    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    private Progress largeTiles;

    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    private Progress defaultTiles;

    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    private Progress height;

    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    private Progress landUse;

    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    private Progress population;
}
