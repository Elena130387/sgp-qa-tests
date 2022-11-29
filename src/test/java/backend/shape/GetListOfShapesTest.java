package backend.shape;

import api.client.CalcManagement;
import api.dto.shape.Shape;
import api.helper.JsonHelper;
import io.restassured.response.ValidatableResponse;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class GetListOfShapesTest {

    @Test
    void getLimitNumberOfShapes() {
        int limit = 3;
        ValidatableResponse responseShapesList = CalcManagement.getLimitNumberOfShapes(limit);
        List<Shape> shapesList = JsonHelper.<Shape>getObjectsListFromJson(responseShapesList);
        assertEquals(limit,
                shapesList.size(), "Количество областей в ответе не совпадает с заданным");
    }
}
