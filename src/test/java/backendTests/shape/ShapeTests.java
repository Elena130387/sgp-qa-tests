package backendTests.shape;

import api.client.CalcManagement;
import api.dto.shape.RenameShape;
import io.restassured.response.ValidatableResponse;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;


public class ShapeTests {
    private final int testShapeId = 166;
    private String currentShapeName;

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
}
