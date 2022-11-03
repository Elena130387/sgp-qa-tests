package api.client;

import io.restassured.response.ValidatableResponse;

import static util.Constants.*;

public class CalcManagement {

    public static <T> ValidatableResponse shapeRename(T input, int id) {
        return BaseRequests.putRequest(input, id, CALC_MAN_SHAPES_SET_NAME_EP);
    }

    public static ValidatableResponse getShapeData(int id) {
        return BaseRequests.getRequest(id, CALC_MAN_SHAPES_ID_EP);
    }

    public static <T> ValidatableResponse addNewShape(T input) {
        return BaseRequests.postRequestWithBody(input, CALC_MANAGEMENT_URL + CALC_MAN_SHAPES_EP);
    }
}
