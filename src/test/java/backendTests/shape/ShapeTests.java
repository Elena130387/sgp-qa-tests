package backendTests.shape;

import api.client.BaseRequests;
import api.client.CalcManagement;
import api.dto.shape.NewShape;
import api.dto.shape.RenameShape;
import io.restassured.response.ValidatableResponse;
import org.junit.jupiter.api.Test;
import util.JsonUtil;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;


public class ShapeTests {
    private final int testShapeId = 166;
    private String currentShapeName;
    private static final String SMALL_SHAPE_WITH_ONE_POLYGON_FILE = "./src/test/resources/smallShapeWithOnePolygon.json";

    @Test
    void shapeRename()  {
        RenameShape renameShape = new RenameShape();
        ValidatableResponse response = CalcManagement.shapeRename(renameShape, testShapeId);
        response.statusCode(200);

        ValidatableResponse responseGetShapeData = CalcManagement.getShapeData(testShapeId);
        currentShapeName = responseGetShapeData.extract().body().jsonPath().getString("name");
        assertEquals(currentShapeName,
                renameShape.getValue(), "Изменение наименования области не выполнено");
    }

    @Test
    void shapeCreate() throws IOException {
        NewShape newShape = (NewShape) JsonUtil.getDataFromJsonFile(SMALL_SHAPE_WITH_ONE_POLYGON_FILE, NewShape.class);
        newShape.AddDateToShapeName();
    }
}
