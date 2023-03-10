package backend.e2e.calculatingShape;

import api.client.CalcManagement;
import api.dto.shape.ShapeInput;
import api.dto.shape.ShapeRename;
import io.restassured.mapper.ObjectMapperType;
import io.restassured.response.ValidatableResponse;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeoutException;

import static api.client.CalcManagement.deleteShapeByIdWithoutResponseReturn;
import static api.client.CalcManagement.getShapeDataById;
import static api.client.Estimator.deleteJobExecutionsByIds;
import static api.client.Estimator.getJobExecutionIds;
import static api.helper.CalculationHelper.waitForCalculationStarting;
import static api.helper.JsonHelper.*;
import static api.helper.PolygonHelper.verifyPolygonNumberAndCoordinates;
import static api.helper.ShapeHelper.createShapeFromJson;
import static java.lang.Thread.sleep;
import static org.junit.jupiter.api.Assertions.*;
import static util.Constants.CALCULATION_TIMEOUT_SEC;

public class ShapeWithTwoPolygonsTest {
    private String currentShapeName;
    private static int shapeId;
    private static ValidatableResponse responseCreateShape;
    private static ShapeInput newShape;
    private static List<Integer> jobExecutionIds = new ArrayList<>();
    public static final int DURATION_SEC = 1;
    private static final String LARGE_SHAPE_WITH_TWO_POLYGONS_FILE = "./src/test/resources/shapeInput/shapeWithTwoPolygons.json";

    @BeforeAll
    public static void createTestShape() throws TimeoutException {
        newShape = (ShapeInput) getDataFromJsonFile(LARGE_SHAPE_WITH_TWO_POLYGONS_FILE, ShapeInput.class);
        newShape.AddDateToShapeName();
        responseCreateShape = createShapeFromJson(newShape);
        shapeId = getIntFromJson(responseCreateShape, "id");
        waitForCalculationStarting(shapeId, CALCULATION_TIMEOUT_SEC, DURATION_SEC);
        jobExecutionIds = getJobExecutionIds(shapeId, 2, 0);
    }

    @Test
    void checkTwoPolygonsShapeCreation() {
        currentShapeName = getStringFromJson(responseCreateShape, "name");

        assertAll(
                () -> assertEquals(currentShapeName,
                        newShape.getName(), "?? ?????????????????? ?????????????? ???????????????? ???? ?????????????????? ?? ????????????????"),
                () -> assertTrue(shapeId > 0, "ID ?????????????????? ?????????????? ???????????? ???????? > 0")
        );

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
                renameShape.getValue(), "?????????????????? ???????????????????????? ?????????????? ???? ??????????????????");
    }

    @AfterAll
    public static void deleteTestShape() {
        System.out.println(shapeId);
        if (!(getShapeDataById(shapeId).extract().statusCode() == 204)) {
            deleteShapeByIdWithoutResponseReturn(shapeId);
        }

        // TODO ?????? ???????? ?????????????? ?????????? ???????????????????? ???????????? https://dev.azure.com/Syncretis/SmartGeoPlatform-Ecomonitoring/_boards/board/t/SmartGeoPlatform-Ecomonitoring%20Team/Stories/?workitem=24686
        try {
            sleep(12000);
        } catch (InterruptedException exception) {
            System.out.println("???????????????? ???????????????? ???????????????????? ?????????????? ?????????????? ?? ???????? ????????????");
        }
        deleteJobExecutionsByIds(jobExecutionIds);
    }
}
