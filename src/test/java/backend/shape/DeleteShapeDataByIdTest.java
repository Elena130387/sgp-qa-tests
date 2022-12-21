package backend.shape;

import backend.interfaces.DeleteByIdControllerTest;
import io.restassured.response.ValidatableResponse;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;

import java.util.concurrent.TimeoutException;

import static api.client.CalcManagement.deleteShapeDataById;
import static api.client.CalcManagement.getShapeDataById;
import static api.dto.StatusesList.DELETED;
import static api.helper.JsonHelper.getStringFromJson;
import static api.helper.ShapeHelper.createShapeFromFileAndGetID;
import static api.helper.ShapeHelper.waitForShapeStatusCompleted;
import static util.Constants.*;

public class DeleteShapeDataByIdTest implements DeleteByIdControllerTest {
    private static int shapeId;
    private static final String SMALL_SHAPE_WITH_ONE_POLYGON_FILE = "./src/test/resources/shapeInput/smallShapeWithOnePolygon.json";
    public static final int DURATION_SEC = 1;

    @BeforeAll
    public static void createTestShape() throws TimeoutException {
        shapeId = createShapeFromFileAndGetID(SMALL_SHAPE_WITH_ONE_POLYGON_FILE);
        waitForShapeStatusCompleted(shapeId, CALCULATION_TIMEOUT_SEC, DURATION_SEC);
    }

    @Override
    public String getUrl() {
        return CALC_MAN_SHAPES_ID_EP;
    }

    @Override
    public int getCorrectId() {
        return shapeId;
    }

    @Override
    public int getDeletedId() {
        return DELETED_SHAPE_ID;
    }

    @AfterAll
    public static void deleteTestShape() {
        if (!(getShapeDataById(shapeId).extract().statusCode() == 204)) {
            ValidatableResponse responseDeleteShape = deleteShapeDataById(shapeId);
        }
    }
}
