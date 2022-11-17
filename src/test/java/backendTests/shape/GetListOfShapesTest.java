package backendTests.shape;

import api.client.CalcManagement;
import api.dto.shape.ExistingShape;
import api.helper.JsonHelper;
import com.fasterxml.jackson.core.JsonProcessingException;
import io.restassured.response.ValidatableResponse;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class GetListOfShapesTest {

    @Test
    void getLimitNumberOfShapes() throws JsonProcessingException {
        int limit = 3;
        ValidatableResponse responseShapesList = CalcManagement.getLimitNumberOfShapes(limit);
        List<ExistingShape> shapesList = JsonHelper.<ExistingShape>getObjectsListFromJson(responseShapesList);
        assertEquals(limit,
                shapesList.size(), "Количество областей в ответе не совпадает с заданным");
    }
}
