package api.helper;

import api.client.CalcManagement;
import api.dto.shape.NewShape;
import io.restassured.path.json.exception.JsonPathException;
import io.restassured.response.ValidatableResponse;

import java.io.IOException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

import static api.client.CalcManagement.getShapeDataById;
import static api.dto.StatusesList.COMPLETED;
import static api.helper.JsonHelper.getDataFromJsonFile;
import static api.helper.JsonHelper.getStringFromJson;
import static util.Util.timeoutIsReached;

public class ShapeHelper {

    public static int createShapeAndGetId(String newShapeFile) throws IOException {
        try {
            NewShape newShape = (NewShape) getDataFromJsonFile(newShapeFile, NewShape.class);
            return CalcManagement.createShapeAndGetId(newShape);
        } catch (JsonPathException exception) {
            throw new RuntimeException("Не удалось создать область из файла  " + newShapeFile);
        }
    }

    public static void waitForShapeStatusCompleted(int shapeId, int timeoutInSeconds, int durationInSeconds) throws InterruptedException, TimeoutException {
        long start = System.currentTimeMillis();
        while (!timeoutIsReached(start, timeoutInSeconds, durationInSeconds)) {
            ValidatableResponse responseGetShapeData = getShapeDataById(shapeId);
            String status = getStringFromJson(responseGetShapeData, "status");
            if (status.equals(COMPLETED.getStatusName())) {
                return;
            } else {
                Thread.sleep(TimeUnit.SECONDS.toMillis(durationInSeconds));
            }
        }
        throw new TimeoutException("Калькуляция не выполнена за ожидаемое время: " + timeoutInSeconds + " секунд");
    }
}
