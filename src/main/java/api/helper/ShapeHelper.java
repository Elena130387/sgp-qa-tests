package api.helper;

import api.dto.shape.ShapeInput;
import io.restassured.response.ValidatableResponse;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

import static api.client.CalcManagement.createNewShape;
import static api.client.CalcManagement.getShapeDataById;
import static api.dto.StatusesList.COMPLETED;
import static api.helper.JsonHelper.*;
import static util.Util.timeoutIsReached;

public class ShapeHelper {

    public static int createShapeFromFileAndGetID(String newShapeFile) {
        ShapeInput newShape = (ShapeInput) getDataFromJsonFile(newShapeFile, ShapeInput.class);
        return createShapeFromJsonAndGetID(newShape);
    }

    public static ValidatableResponse createShapeFromFile(String newShapeFile) {
        ShapeInput newShape = (ShapeInput) getDataFromJsonFile(newShapeFile, ShapeInput.class);
        return createShapeFromJson(newShape);
    }

    public static <T> ValidatableResponse createShapeFromJson(T inputJson) {
        ValidatableResponse response = createNewShape(inputJson);
        response.statusCode(200);
        return response;
    }

    public static <T> int createShapeFromJsonAndGetID(T inputJson) {
        ValidatableResponse response = createNewShape(inputJson);
        response.statusCode(200);
        return getIntFromJson(response, "id");
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
