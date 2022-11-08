package api.client;

import io.restassured.response.ValidatableResponse;

import static util.Constants.*;
import static util.JsonUtil.getIntFromJson;

public class CalcManagement {

    public static <T> ValidatableResponse shapeRename(T input, int id) {
        return BaseRequests.putRequest(input, id, CALC_MAN_SHAPES_SET_NAME_EP);
    }

    public static ValidatableResponse getShapeDataById(int id) {
        return BaseRequests.getRequest(id, CALC_MAN_SHAPES_ID_EP);
    }

    public static <T> ValidatableResponse createNewShape(T input) {
        return BaseRequests.postRequestWithBody(input, CALC_MANAGEMENT_URL + CALC_MAN_SHAPES_EP);
    }

    public static <T> int createShapeAndGetId(T input) {
        ValidatableResponse response = createNewShape(input);
        return getIntFromJson(response, "id");
    }

    public static ValidatableResponse deleteShapeDataById(int id) {
        return BaseRequests.deleteRequest(id, CALC_MAN_SHAPES_ID_EP);
    }
}
