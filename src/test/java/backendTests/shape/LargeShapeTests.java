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

import static api.client.CalcManagement.*;
import static api.dto.shape.ShapeStatus.COMPLETED;
import static api.dto.shape.ShapeStatus.DELETED;
import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static util.BaseResponseUtil.verifyPolygonNumberAndCoordinates;
import static util.JsonUtil.*;
import static util.JsonUtil.getStringFromJson;

public class LargeShapeTests {
    private static String currentShapeName;
    private static int currentShapeId;
    private static ValidatableResponse responseCreateShape;
    private static NewShape newShape;
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
        currentShapeId = getIntFromJson(responseCreateShape, "id");
        Thread.sleep(800);
    }

    @Test
    void createShape() {
        currentShapeName = getStringFromJson(responseCreateShape, "name");
        assertEquals(currentShapeName,
                newShape.getName(), "У созданной области название не совпадает с заданным");
        assertTrue(currentShapeId > 0, "ID созданной области должно быть > 0");

        verifyPolygonNumberAndCoordinates(newShape.getPolygons(), responseCreateShape.extract().as(NewShape.class, ObjectMapperType.GSON).getPolygons());
    }

    @Test
    void deleteJustCreatedShapeWithStatusCompleted() {
        ValidatableResponse responseGetShapeData = getShapeDataById(currentShapeId);
        assertEquals(COMPLETED.getStatusName(),
                getStringFromJson(responseGetShapeData, "status"), "Расчет калькуляции данных области не выполнен");

        ValidatableResponse responseDeleteShape = deleteShapeDataById(currentShapeId);
        responseDeleteShape.statusCode(200);

        ValidatableResponse responseGetNewShapeData = getShapeDataById(currentShapeId);
        assertEquals(DELETED.getStatusName(),
                getStringFromJson(responseGetNewShapeData, "status"), "Область не была удалена");
    }

    @Test
    void deleteChangedShape() {
        RenameShape renameShape = new RenameShape();
        ValidatableResponse responseRenameShape = CalcManagement.shapeRename(renameShape, currentShapeId);
        responseRenameShape.statusCode(200);

        ValidatableResponse responseGetShapeData = getShapeDataById(currentShapeId);
        assertNotEquals(DELETED.getStatusName(),
                getStringFromJson(responseGetShapeData, "status"), "Для теста нельзя использовать область со статусом УДАЛЕНА");

        ValidatableResponse responseDeleteShape = deleteShapeDataById(currentShapeId);
        responseDeleteShape.statusCode(200);

        ValidatableResponse responseGetNewShapeData = getShapeDataById(currentShapeId);
        assertEquals(DELETED.getStatusName(),
                getStringFromJson(responseGetNewShapeData, "status"), "Область не была удалена");
    }


    @AfterEach
    public void deleteTestShape() {
        String testShapeStatus = getStringFromJson(getShapeDataById(currentShapeId), "status");
        if (!testShapeStatus.equals(DELETED.getStatusName())) {
            ValidatableResponse responseDeleteShape = deleteShapeDataById(currentShapeId);
        }
    }
}

