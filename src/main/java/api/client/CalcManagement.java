package api.client;

import io.restassured.response.ValidatableResponse;

import static util.Constants.*;

public class CalcManagement {

    public static <T> ValidatableResponse shapeRename(T input, int id) {
        return BaseRequests.putRequest(input, id, CALC_MAN_SHAPES_SET_NAME_EP);
    }

    public static ValidatableResponse getShapeDataById(int id) {
        return BaseRequests.getRequestWithId(id, CALC_MAN_SHAPES_ID_EP);
    }

    public static <T> ValidatableResponse createNewShape(T input) {
        return BaseRequests.postRequestWithBody(input, CALC_MAN_SHAPES_EP);
    }

    public static ValidatableResponse deleteShapeDataById(int id) {
        return BaseRequests.deleteRequestWithId(id, CALC_MAN_SHAPES_ID_EP);
    }

    public static ValidatableResponse getLimitNumberOfShapes(int limit) {
        return BaseRequests.getRequestWithParam(limit, CALC_MAN_SHAPES_EP);
    }
}
