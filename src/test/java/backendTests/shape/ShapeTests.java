package backendTests.shape;

import api.client.CalcManagement;
import api.dto.shape.RenameShape;
import io.restassured.response.ValidatableResponse;
import org.junit.jupiter.api.Test;



public class ShapeTests {
    private final int testShapeId = 166;

    @Test
    void shapeRename()  {
        RenameShape renameShape = new RenameShape();
        ValidatableResponse response = CalcManagement.shapeRename(renameShape, testShapeId);
        response.statusCode(200);
    }
}
