package backendTests.shape;

import interfaces.GetByIdControllerTest;

import static util.Constants.*;

public class GetShapeDataTest implements GetByIdControllerTest {
    @Override
    public String getUrl() {
        return CALC_MAN_SHAPES_ID_EP;
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
