package backend.shape;

import io.restassured.response.ValidatableResponse;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static api.client.CalcManagement.deleteShapeByIdWithoutResponseReturn;
import static api.client.CalcManagement.getListOfRunningShapes;
import static api.client.Estimator.deleteJobExecutionsByIds;
import static api.client.Estimator.getJobExecutionIds;
import static api.helper.CalculationHelper.waitForCalculationStarting;
import static api.helper.ShapeHelper.createShapeFromFileAndGetID;
import static api.helper.ShapeHelper.getListOfShapesIdsFromResponse;
import static java.lang.Thread.sleep;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static util.Constants.CALCULATION_TIMEOUT_SEC;

public class GetListOfRunningShapes {
    private static int firstShapeId;
    private static int secondShapeId;
    public static final int DURATION_SEC = 1;
    private static final String LARGE_SHAPE_WITH_TWO_POLYGONS_FILE = "./src/test/resources/shapeInput/shapeWithTwoPolygons.json";

    @BeforeEach
    public void beforeTest() throws Exception {
        firstShapeId = createShapeFromFileAndGetID(LARGE_SHAPE_WITH_TWO_POLYGONS_FILE);
        waitForCalculationStarting(firstShapeId, CALCULATION_TIMEOUT_SEC, DURATION_SEC);

        secondShapeId = createShapeFromFileAndGetID(LARGE_SHAPE_WITH_TWO_POLYGONS_FILE);
        waitForCalculationStarting(secondShapeId, CALCULATION_TIMEOUT_SEC, DURATION_SEC);
    }

    @Test
    void checkGetListOfRunningShapes() {
        ValidatableResponse responseShapesList = getListOfRunningShapes();
        List<Integer> listShapeIds = getListOfShapesIdsFromResponse(responseShapesList);
        assertAll(
                () -> assertTrue(listShapeIds.contains(firstShapeId), "В списке отсутствует id " + firstShapeId),
                () -> assertTrue(listShapeIds.contains(secondShapeId), "В списке отсутствует id " + secondShapeId)
        );
    }

    @AfterEach
    public void afterTestExecution() {
        deleteShapeByIdWithoutResponseReturn(firstShapeId);
        deleteShapeByIdWithoutResponseReturn(secondShapeId);

        // TODO код ниже удалить после реализации задачи https://dev.azure.com/Syncretis/SmartGeoPlatform-Ecomonitoring/_boards/board/t/SmartGeoPlatform-Ecomonitoring%20Team/Stories/?workitem=24686
        try {
            sleep(12000);
        } catch (InterruptedException exception) {
            System.out.println("Прервано ожидание обновления статуса области в базе данных");
        }
        deleteJobExecutionsByIds(getJobExecutionIds(firstShapeId, 2, 0));
        deleteJobExecutionsByIds(getJobExecutionIds(secondShapeId, 2, 0));
    }
}
