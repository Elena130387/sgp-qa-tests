package api.client;

import api.dto.shape.NewShape;
import io.restassured.path.json.exception.JsonPathException;

import java.io.IOException;

import static util.JsonUtil.getDataFromJsonFile;

public class ShapeHelper {

    public static int createShapeAndGetId(String newShapeFile) throws IOException {
        try {
            NewShape newShape = (NewShape) getDataFromJsonFile(newShapeFile, NewShape.class);
            return CalcManagement.createShapeAndGetId(newShape);
        } catch (JsonPathException exception) {
            throw new RuntimeException("Не удалось создать область из файла  " + newShapeFile);
        }
    }
}
