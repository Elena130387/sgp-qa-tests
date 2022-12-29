package backend.shape;

import backend.interfaces.GetRelatedDataByIdControllerTest;
import io.restassured.response.ValidatableResponse;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static api.client.BaseRequests.getRequestWithId;
import static api.client.CalcManagement.deleteShapeByIdWithoutResponseReturn;
import static api.client.CalcManagement.getShapeDataById;
import static api.dto.StatusesList.STOPPED;
import static api.helper.JsonHelper.getStringFromJson;
import static api.helper.ShapeHelper.createShapeFromFileAndGetID;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static util.Constants.*;

public class StopShapeTest implements GetRelatedDataByIdControllerTest {
    private static int shapeId;
    private static final String SHAPE_WITH_TWO_POLYGONS_FILE = "./src/test/resources/shapeInput/shapeWithTwoPolygons.json";

    @BeforeAll
    public static void createTestShape() {
        shapeId = createShapeFromFileAndGetID(SHAPE_WITH_TWO_POLYGONS_FILE);
    }

    @Override
    public String getUrl() {
        return CALC_MAN_SHAPES_STOP_EP;
    }

    @Override
    public String getErrorMsg() {
        return "stopShape.shapeId:";
    }

    @Override
    public int getCorrectId() {
        return shapeId;
    }

    @Override
    public int getDeletedId() {
        return DELETED_SHAPE_ID;
    }

    @Test
    void  stopUnCalculatedShape_Expect200ok(){
        ValidatableResponse response = getRequestWithId(CORRECT_SHAPE_ID, getUrl());
        response.statusCode(200);

        ValidatableResponse responseGetShapeData = getShapeDataById(shapeId);
        assertEquals(STOPPED.getStatusName(),
                getStringFromJson(responseGetShapeData, "status"), "Статус области не соответвтует ожиданию");
    }

    @AfterAll
    public static void deleteTestShape() {
        if (!(getShapeDataById(shapeId).extract().statusCode() == 204)) {
            deleteShapeByIdWithoutResponseReturn(shapeId);
        }
    }
}
