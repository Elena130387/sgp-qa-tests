package backendTests.integration;

import api.client.CalcManagement;
import api.dto.shape.ShapeInput;
import api.dto.shape.ShapeRename;
import io.restassured.mapper.ObjectMapperType;
import io.restassured.response.ValidatableResponse;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.concurrent.TimeoutException;

import static api.client.CalcManagement.deleteShapeDataById;
import static api.client.CalcManagement.getShapeDataById;
import static api.dto.StatusesList.DELETED;
import static api.helper.JsonHelper.*;
import static api.helper.PolygonHelper.verifyPolygonNumberAndCoordinates;
import static api.helper.ShapeHelper.createShapeFromJson;
import static api.helper.ShapeHelper.waitForShapeStatusCompleted;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static util.Constants.CALCULATION_TIMEOUT_SEC;


public class SmallShapeWithOnePolygonTest {
    private String currentShapeName;
    private int shapeId;
    private ValidatableResponse responseCreateShape;
    private ShapeInput newShape;
    public static final int DURATION_SEC = 1;
    private static final String SMALL_SHAPE_WITH_ONE_POLYGON_FILE = "./src/test/resources/shapeInput/smallShapeWithOnePolygon.json";

    @BeforeEach
    public void createTestShape() throws TimeoutException {
        newShape = (ShapeInput) getDataFromJsonFile(SMALL_SHAPE_WITH_ONE_POLYGON_FILE, ShapeInput.class);
        newShape.AddDateToShapeName();
        responseCreateShape = createShapeFromJson(newShape);
        shapeId = getIntFromJson(responseCreateShape, "id");
        waitForShapeStatusCompleted(shapeId, CALCULATION_TIMEOUT_SEC, DURATION_SEC);
    }

    @Test
    void createSmallShape() {
        currentShapeName = getStringFromJson(responseCreateShape, "name");
        assertEquals(currentShapeName,
                newShape.getName(), "У созданной области название не совпадает с заданным");
        assertTrue(shapeId > 0, "ID созданной области должно быть > 0");

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

    @Test
    void deleteCompletedShape() {
        ValidatableResponse responseDeleteShape = deleteShapeDataById(shapeId);
        responseDeleteShape.statusCode(200);

        ValidatableResponse responseGetNewShapesData = getShapeDataById(shapeId);
        assertEquals(DELETED.getStatusName(),
                getStringFromJson(responseGetNewShapesData, "status"), "Область не была удалена");
    }


    @AfterEach
    public void deleteTestShape() {
        System.out.println(shapeId);

        String testShapeStatus = getStringFromJson(getShapeDataById(shapeId), "status");
        if (!testShapeStatus.equals(DELETED.getStatusName())) {
            ValidatableResponse responseDeleteShape = deleteShapeDataById(shapeId);
        }
    }
}
