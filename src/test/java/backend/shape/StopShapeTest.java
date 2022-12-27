package backend.shape;

import backend.interfaces.GetRelatedDataByIdControllerTest;

import static util.Constants.*;

public class StopShapeTest implements GetRelatedDataByIdControllerTest {

    @Override
    public String getUrl() {
        return CALC_MAN_SHAPES_STOP_EP;
    }

    @Override
    public String getErrorMsg() {
        return "stopShape.shapeId:";
    }

    @Override
    public int getCorrectId() {
        return CORRECT_SHAPE_ID;
    }

    @Override
    public int getDeletedId() {
        return DELETED_SHAPE_ID;
    }
}
