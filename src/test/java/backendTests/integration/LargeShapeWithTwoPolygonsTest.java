package backendTests.integration;

import api.client.CalcManagement;
import api.dto.shape.ShapeInput;
import api.dto.shape.ShapeRename;
import io.restassured.mapper.ObjectMapperType;
import io.restassured.response.ValidatableResponse;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeoutException;

import static api.client.CalcManagement.deleteShapeDataById;
import static api.client.CalcManagement.getShapeDataById;
import static api.client.Estimator.*;
import static api.dto.StatusesList.DELETED;
import static api.dto.StatusesList.STOPPED;
import static api.helper.CalculationHelper.waitForCalculationStarting;
import static api.helper.CalculationHelper.waitForCalculationStop;
import static api.helper.JsonHelper.*;
import static api.helper.PolygonHelper.verifyPolygonNumberAndCoordinates;
import static api.helper.ShapeHelper.createShapeFromJson;
import static java.lang.Thread.sleep;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static util.Constants.CALCULATION_TIMEOUT_SEC;

public class LargeShapeWithTwoPolygonsTest {
    private String currentShapeName;
    private int shapeId;
    private ValidatableResponse responseCreateShape;
    private ShapeInput newShape;
    private List<Integer> jobExecutionIds = new ArrayList<>();
    public static final int DURATION_SEC = 1;
    private static final String LARGE_SHAPE_WITH_TWO_POLYGONS_FILE = "./src/test/resources/shapeInput/largeShapeWithTwoPolygons.json";

    @BeforeEach
    public void createTestShape() throws TimeoutException {
        newShape = (ShapeInput) getDataFromJsonFile(LARGE_SHAPE_WITH_TWO_POLYGONS_FILE, ShapeInput.class);
        newShape.AddDateToShapeName();
        responseCreateShape = createShapeFromJson(newShape);
        shapeId = getIntFromJson(responseCreateShape, "id");
        waitForCalculationStarting(shapeId, CALCULATION_TIMEOUT_SEC, DURATION_SEC);
        jobExecutionIds = getJobExecutionIds(shapeId, 2, 0);
    }

    @Test
    void createLargeShape() {
        currentShapeName = getStringFromJson(responseCreateShape, "name");
        assertEquals(currentShapeName,
                newShape.getName(), "У созданной области название не совпадает с заданным");
        assertTrue(shapeId > 0, "ID созданной области должно быть > 0");

        verifyPolygonNumberAndCoordinates(newShape.getPolygons(), responseCreateShape.extract().as(ShapeInput.class, ObjectMapperType.GSON).getPolygons());
    }

    @Test
    void renameCalculatingShape() {
        ShapeRename renameShape = new ShapeRename();
        ValidatableResponse responseRenameShape = CalcManagement.shapeRename(renameShape, shapeId);
        responseRenameShape.statusCode(200);

        ValidatableResponse responseGetNewShapesData = getShapeDataById(shapeId);
        currentShapeName = getStringFromJson(responseGetNewShapesData, "name");
        assertEquals(currentShapeName,
                renameShape.getValue(), "Изменение наименования области не выполнено");
    }

    @Test
    void deleteCalculatingShape() {
        ValidatableResponse responseDeleteShape = deleteShapeDataById(shapeId);
        responseDeleteShape.statusCode(200);

        ValidatableResponse responseGetNewShapesData = getShapeDataById(shapeId);
        assertEquals(DELETED.getStatusName(),
                getStringFromJson(responseGetNewShapesData, "status"), "Область не была удалена");
    }

    @Test
    void stopCalculatingShape() throws TimeoutException {
        for (Integer jobExecutionId : jobExecutionIds) {
            stopJobExecutionById(jobExecutionId);
        }

        waitForCalculationStop(shapeId, 10, DURATION_SEC);

        ValidatableResponse responseGetNewShapeData = getShapeDataById(shapeId);
        assertEquals(STOPPED.getStatusName(),
                getStringFromJson(responseGetNewShapeData, "status"), "Расчет области не был остановлен");
    }


    @AfterEach
    public void deleteTestShape() {
        System.out.println(shapeId);

        String testShapeStatus = getStringFromJson(getShapeDataById(shapeId), "status");
        if (!testShapeStatus.equals(DELETED.getStatusName())) {
            ValidatableResponse responseDeleteShape = deleteShapeDataById(shapeId);
        }

        try {
            sleep(12000);
        } catch (InterruptedException exception) {
            System.out.println("Прервано ожидание обновления статуса области в базе данных");
        }

        ValidatableResponse responseDeleteJobExecutions = deleteJobExecutionsByIds(jobExecutionIds);
    }
}
