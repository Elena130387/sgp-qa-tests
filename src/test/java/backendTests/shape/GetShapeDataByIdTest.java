package backendTests.shape;

import api.dto.shape.Shape;
import backendTests.interfaces.GetByIdControllerTest;
import io.restassured.mapper.ObjectMapperType;
import org.junit.jupiter.api.Test;

import static api.client.CalcManagement.getShapeDataById;
import static api.helper.JsonHelper.getDataFromJsonFile;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static util.Constants.*;

public class GetShapeDataByIdTest implements GetByIdControllerTest {
    private static final String TEST_SHAPE_FILE = "./src/test/resources/shapes/Orlando,Florida(15km).json";

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

    @Test
    void checkResponseObjectStructure() {
        Shape expShapeStructure = (Shape) getDataFromJsonFile(TEST_SHAPE_FILE, Shape.class);

        Shape actShapeStructure = getShapeDataById(CORRECT_SHAPE_ID).extract()
                .as(Shape.class, ObjectMapperType.GSON);

        assertEquals(expShapeStructure,
                actShapeStructure, "Актуальные данные области отличаются от ожидаемых");
    }
}
