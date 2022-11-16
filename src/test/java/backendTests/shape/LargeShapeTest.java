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
import java.util.List;
import java.util.concurrent.TimeoutException;

import static api.client.CalcManagement.*;
import static api.client.Estimator.*;
import static api.dto.StatusesList.DELETED;
import static api.dto.StatusesList.STOPPED;
import static api.helper.CalculationHelper.waitForCalculationStarting;
import static api.helper.CalculationHelper.waitForCalculationStop;
import static api.helper.JsonHelper.*;
import static api.helper.PolygonHelper.verifyPolygonNumberAndCoordinates;
import static java.lang.Thread.sleep;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static util.Constants.CALCULATION_TIMEOUT_SEC;

public class LargeShapeTest {
    private String currentShapeName;
    private int shapeId;
    private ValidatableResponse responseCreateShape;
    private NewShape newShape;
    public static final int DURATION_SEC = 1;
    private static final String SMALL_SHAPE_WITH_ONE_POLYGON_FILE = "./src/test/resources/largeShapeWithTwoPolygons.json";

    @BeforeEach
    public void createTestShape() throws IOException, InterruptedException, TimeoutException {
        try {
            newShape = (NewShape) getDataFromJsonFile(SMALL_SHAPE_WITH_ONE_POLYGON_FILE, NewShape.class);
        } catch (JsonPathException exception) {
            throw new RuntimeException("Не удалось создать область из файла  " + SMALL_SHAPE_WITH_ONE_POLYGON_FILE);
        }
        newShape.AddDateToShapeName();
        responseCreateShape = createNewShape(newShape);
        responseCreateShape.statusCode(200);
        shapeId = getIntFromJson(responseCreateShape, "id");
        System.out.println(shapeId);
        waitForCalculationStarting(shapeId, CALCULATION_TIMEOUT_SEC, DURATION_SEC);
    }

    @Test
    void createLargeShape() {
        currentShapeName = getStringFromJson(responseCreateShape, "name");
        assertEquals(currentShapeName,
                newShape.getName(), "У созданной области название не совпадает с заданным");
        assertTrue(shapeId > 0, "ID созданной области должно быть > 0");

        verifyPolygonNumberAndCoordinates(newShape.getPolygons(), responseCreateShape.extract().as(NewShape.class, ObjectMapperType.GSON).getPolygons());
    }

    @Test
    void renameCalculatingShape() {
        RenameShape renameShape = new RenameShape();
        ValidatableResponse responseRenameShape = CalcManagement.shapeRename(renameShape, shapeId);
        responseRenameShape.statusCode(200);

        ValidatableResponse responseGetNewShapeData = getShapeDataById(shapeId);
        currentShapeName = getStringFromJson(responseGetNewShapeData, "name");
        assertEquals(currentShapeName,
                renameShape.getValue(), "Изменение наименования области не выполнено");
    }

    @Test
    void deleteCalculatingShape() {
        ValidatableResponse responseDeleteShape = deleteShapeDataById(shapeId);
        responseDeleteShape.statusCode(200);

        ValidatableResponse responseGetNewShapeData = getShapeDataById(shapeId);
        assertEquals(DELETED.getStatusName(),
                getStringFromJson(responseGetNewShapeData, "status"), "Область не была удалена");
    }

    @Test
    void stopCalculatingShape() throws InterruptedException, TimeoutException {
        List<Integer> jobExecutionIds = getJobExecutionIds(shapeId, 10, 0);
        for (Integer jobExecutionId : jobExecutionIds) {
            stopJobExecutionById(jobExecutionId);
        }

        waitForCalculationStop(shapeId, 10, DURATION_SEC);

        ValidatableResponse responseGetNewShapeData = getShapeDataById(shapeId);
        assertEquals(STOPPED.getStatusName(),
                getStringFromJson(responseGetNewShapeData, "status"), "Расчет области не был остановлен");
    }


    @AfterEach
    public void deleteTestShape() throws InterruptedException {
        String testShapeStatus = getStringFromJson(getShapeDataById(shapeId), "status");
        if (!testShapeStatus.equals(DELETED.getStatusName())) {
            ValidatableResponse responseDeleteShape = deleteShapeDataById(shapeId);
        }

        List<Integer> jobExecutionIds = getJobExecutionIds(shapeId, 2, 0);
        sleep(12000);
        ValidatableResponse responseDeleteShape = deleteJobExecutionById(jobExecutionIds);
    }
}

