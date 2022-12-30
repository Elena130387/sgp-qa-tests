package backend.e2e.completedShape;

import api.client.CalcManagement;
import api.dto.shape.ShapeInput;
import api.dto.shape.ShapeRename;
import io.restassured.mapper.ObjectMapperType;
import io.restassured.response.ValidatableResponse;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.concurrent.TimeoutException;

import static api.client.CalcManagement.deleteShapeByIdWithoutResponseReturn;
import static api.client.CalcManagement.getShapeDataById;
import static api.helper.JsonHelper.*;
import static api.helper.PolygonHelper.verifyPolygonNumberAndCoordinates;
import static api.helper.ShapeHelper.createShapeFromJson;
import static api.helper.ShapeHelper.waitForShapeStatusCompleted;
import static org.junit.jupiter.api.Assertions.*;
import static util.Constants.CALCULATION_TIMEOUT_SEC;


public class ShapeWithOnePolygonTest {
    private String currentShapeName;
    private static int shapeId;
    private static ValidatableResponse responseCreateShape;
    private static ShapeInput newShape;
    public static final int DURATION_SEC = 1;
    private static final String SMALL_SHAPE_WITH_ONE_POLYGON_FILE = "./src/test/resources/shapeInput/smallShapeWithOnePolygon.json";

    @BeforeAll
    public static void createTestShape() throws TimeoutException {
        newShape = (ShapeInput) getDataFromJsonFile(SMALL_SHAPE_WITH_ONE_POLYGON_FILE, ShapeInput.class);
        newShape.AddDateToShapeName();
        responseCreateShape = createShapeFromJson(newShape);
        shapeId = getIntFromJson(responseCreateShape, "id");
        waitForShapeStatusCompleted(shapeId, CALCULATION_TIMEOUT_SEC, DURATION_SEC);
    }

    @Test
    void checkSmallShapeCreation() {
        currentShapeName = getStringFromJson(responseCreateShape, "name");

        assertAll(
                () -> assertEquals(currentShapeName,
                        newShape.getName(), "У созданной области название не совпадает с заданным"),
                () -> assertTrue(shapeId > 0, "ID созданной области должно быть > 0")
        );

        verifyPolygonNumberAndCoordinates(newShape.getPolygons(), responseCreateShape.extract().as(ShapeInput.class, ObjectMapperType.GSON).getPolygons());
    }

    @Test
    void renameCompletedShape() {
        ShapeRename renameShape = new ShapeRename();
        ValidatableResponse responseRenameShape = CalcManagement.shapeRename(renameShape, shapeId);
        responseRenameShape.statusCode(200);

        ValidatableResponse responseGetNewShapesData = getShapeDataById(shapeId);
        currentShapeName = getStringFromJson(responseGetNewShapesData, "name");
        assertEquals(currentShapeName,
                renameShape.getValue(), "Изменение наименования области не выполнено");
    }

    @AfterAll
    public static void deleteTestShape() {
        System.out.println(shapeId);

        if (!(getShapeDataById(shapeId).extract().statusCode() == 204)) {
            deleteShapeByIdWithoutResponseReturn(shapeId);
        }
    }
}
