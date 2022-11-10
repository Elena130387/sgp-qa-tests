package backendTests.shape;

import api.client.CalcManagement;
import api.dto.shape.NewShape;
import api.dto.shape.RenameShape;
import io.restassured.mapper.ObjectMapperType;
import io.restassured.path.json.exception.JsonPathException;
import io.restassured.response.ValidatableResponse;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

import static api.client.CalcManagement.*;
import static api.client.ShapeHelper.waitForShapeStatusCompleted;
import static api.dto.shape.ShapeStatus.DELETED;
import static java.lang.Thread.sleep;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static util.BaseResponseUtil.verifyPolygonNumberAndCoordinates;
import static util.JsonUtil.*;


public class SmallShapeTests {
    private static String currentShapeName;
    private static int shapeId;
    private static ValidatableResponse responseCreateShape;
    private static NewShape newShape;
    private static final int CALCULATION_TIMEOUT_SEC = 60;
    private static final int DURATION_SEC = 15;
    private static final String SMALL_SHAPE_WITH_ONE_POLYGON_FILE = "./src/test/resources/smallShapeWithOnePolygon.json";

    @BeforeEach
    public void createTestShape() throws IOException, InterruptedException {
        try {
            newShape = (NewShape) getDataFromJsonFile(SMALL_SHAPE_WITH_ONE_POLYGON_FILE, NewShape.class);
        } catch (JsonPathException exception) {
            throw new RuntimeException("Не удалось создать область из файла  " + SMALL_SHAPE_WITH_ONE_POLYGON_FILE);
        }
        newShape.AddDateToShapeName();
        responseCreateShape = createNewShape(newShape);
        responseCreateShape.statusCode(200);
        shapeId = getIntFromJson(responseCreateShape, "id");
        sleep(800);
    }

    @Test
    void createSmallShape() {
        currentShapeName = getStringFromJson(responseCreateShape, "name");
        assertEquals(currentShapeName,
                newShape.getName(), "У созданной области название не совпадает с заданным");
        assertTrue(shapeId > 0, "ID созданной области должно быть > 0");

        verifyPolygonNumberAndCoordinates(newShape.getPolygons(), responseCreateShape.extract().as(NewShape.class, ObjectMapperType.GSON).getPolygons());
    }

    @Test
    void renameCompletedShape() throws InterruptedException, TimeoutException {
        waitForShapeStatusCompleted(shapeId, CALCULATION_TIMEOUT_SEC, DURATION_SEC);

        RenameShape renameShape = new RenameShape();
        ValidatableResponse responseRenameShape = CalcManagement.shapeRename(renameShape, shapeId);
        responseRenameShape.statusCode(200);

        ValidatableResponse responseGetNewShapeData = getShapeDataById(shapeId);
        currentShapeName = getStringFromJson(responseGetNewShapeData, "name");
        assertEquals(currentShapeName,
                renameShape.getValue(), "Изменение наименования области не выполнено");
    }

    @Test
    void deleteCompletedShape() throws InterruptedException, TimeoutException {
        waitForShapeStatusCompleted(shapeId, CALCULATION_TIMEOUT_SEC, DURATION_SEC);

        ValidatableResponse responseDeleteShape = deleteShapeDataById(shapeId);
        responseDeleteShape.statusCode(200);

        ValidatableResponse responseGetNewShapeData = getShapeDataById(shapeId);
        assertEquals(DELETED.getStatusName(),
                getStringFromJson(responseGetNewShapeData, "status"), "Область не была удалена");
    }


    @AfterEach
    public void deleteTestShape() {
        String testShapeStatus = getStringFromJson(getShapeDataById(shapeId), "status");
        if (!testShapeStatus.equals(DELETED.getStatusName())) {
            ValidatableResponse responseDeleteShape = deleteShapeDataById(shapeId);
        }
    }
}
