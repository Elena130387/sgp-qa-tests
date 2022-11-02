package backendTests.shape;

import api.client.CalcManagement;
import api.dto.shape.RenameShape;
import io.restassured.response.ValidatableResponse;
import org.junit.jupiter.api.Test;
import util.JsonUtil;

import java.io.IOException;


public class ShapeTests {
    private static final String EXECUTION_FILE = "./src/test/resources/renameShape.json";
    private final int testShapeId = 166;

    @Test
    void shapeRename() throws IOException {
        RenameShape renameShape = (RenameShape) JsonUtil.getDataFromJsonFile(EXECUTION_FILE, RenameShape.class);
        ValidatableResponse response = CalcManagement.shapeRename(renameShape, testShapeId);
        response.statusCode(200);
    }
}
