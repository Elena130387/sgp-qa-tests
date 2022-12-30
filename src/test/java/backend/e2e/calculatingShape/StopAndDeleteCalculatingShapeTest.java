package backend.e2e.calculatingShape;

import api.dto.shape.ShapeInput;
import io.restassured.response.ValidatableResponse;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeoutException;

import static api.client.CalcManagement.*;
import static api.client.Estimator.*;
import static api.dto.StatusesList.STOPPED;
import static api.helper.CalculationHelper.waitForCalculationStarting;
import static api.helper.CalculationHelper.waitForCalculationStop;
import static api.helper.JsonHelper.*;
import static api.helper.ShapeHelper.createShapeFromJson;
import static java.lang.Thread.sleep;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static util.Constants.CALCULATION_TIMEOUT_SEC;

public class StopAndDeleteCalculatingShapeTest {
    private int shapeId;
    private List<Integer> jobExecutionIds = new ArrayList<>();
    public static final int DURATION_SEC = 1;
    private static final String LARGE_SHAPE_WITH_TWO_POLYGONS_FILE = "./src/test/resources/shapeInput/shapeWithTwoPolygons.json";

    @BeforeEach
    public void createTestShape() throws TimeoutException {
        ShapeInput newShape = (ShapeInput) getDataFromJsonFile(LARGE_SHAPE_WITH_TWO_POLYGONS_FILE, ShapeInput.class);
        newShape.AddDateToShapeName();
        ValidatableResponse responseCreateShape = createShapeFromJson(newShape);
        shapeId = getIntFromJson(responseCreateShape, "id");
        waitForCalculationStarting(shapeId, CALCULATION_TIMEOUT_SEC, DURATION_SEC);
        jobExecutionIds = getJobExecutionIds(shapeId, 2, 0);
    }

    @Test
    void deleteCalculatingShape() {
        ValidatableResponse responseDeleteShape = deleteShapeById(shapeId);
        responseDeleteShape.statusCode(200);
        assertEquals(204,
                getShapeDataById(shapeId).extract().statusCode(), "Область не была удалена");
    }

    @Test
    void stopJobExecutionId() throws TimeoutException {
        for (Integer jobExecutionId : jobExecutionIds) {
            stopJobExecutionById(jobExecutionId);
        }

        waitForCalculationStop(shapeId, 10, DURATION_SEC);

        ValidatableResponse responseGetNewShapeData = getShapeDataById(shapeId);
        assertEquals(STOPPED.getStatusName(),
                getStringFromJson(responseGetNewShapeData, "status"), "Расчет области не был остановлен");
    }

    @Test
    void stopCalculatingShapeById() throws TimeoutException {
        stopShapeCalculationById(shapeId);

        waitForCalculationStop(shapeId, 10, DURATION_SEC);

        ValidatableResponse responseGetNewShapeData = getShapeDataById(shapeId);
        assertEquals(STOPPED.getStatusName(),
                getStringFromJson(responseGetNewShapeData, "status"), "Расчет области не был остановлен");
    }

    @AfterEach
    public void deleteTestShape() {
        System.out.println(shapeId);
        if (!(getShapeDataById(shapeId).extract().statusCode() == 204)) {
            deleteShapeByIdWithoutResponseReturn(shapeId);
        }

        try {
            sleep(12000);
        } catch (InterruptedException exception) {
            System.out.println("Прервано ожидание обновления статуса области в базе данных");
        }
        deleteJobExecutionsByIds(jobExecutionIds);
    }
}
