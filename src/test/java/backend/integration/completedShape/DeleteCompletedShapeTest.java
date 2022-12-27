package backend.integration.completedShape;

import api.dto.shape.ShapeInput;
import io.restassured.response.ValidatableResponse;
import org.junit.jupiter.api.Test;

import java.util.concurrent.TimeoutException;

import static api.client.CalcManagement.deleteShapeById;
import static api.client.CalcManagement.getShapeDataById;
import static api.helper.JsonHelper.getDataFromJsonFile;
import static api.helper.JsonHelper.getIntFromJson;
import static api.helper.ShapeHelper.createShapeFromJson;
import static api.helper.ShapeHelper.waitForShapeStatusCompleted;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static util.Constants.CALCULATION_TIMEOUT_SEC;

public class DeleteCompletedShapeTest {
    public static final int DURATION_SEC = 1;
    private static final String SMALL_SHAPE_WITH_ONE_POLYGON_FILE = "./src/test/resources/shapeInput/smallShapeWithOnePolygon.json";

    @Test
    void deleteCompletedShape() throws TimeoutException {
        ShapeInput newShape = (ShapeInput) getDataFromJsonFile(SMALL_SHAPE_WITH_ONE_POLYGON_FILE, ShapeInput.class);
        newShape.AddDateToShapeName();
        ValidatableResponse responseCreateShape = createShapeFromJson(newShape);
        int shapeId = getIntFromJson(responseCreateShape, "id");
        waitForShapeStatusCompleted(shapeId, CALCULATION_TIMEOUT_SEC, DURATION_SEC);

        ValidatableResponse responseDeleteShape = deleteShapeById(shapeId);
        responseDeleteShape.statusCode(200);
        assertEquals(204,
                getShapeDataById(shapeId).extract().statusCode(), "Область не была удалена");
    }
}
