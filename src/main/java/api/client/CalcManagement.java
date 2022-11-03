package api.client;

import io.restassured.response.ValidatableResponse;

import static util.Constants.CALC_MAN_SHAPES_ID_EP;
import static util.Constants.CALC_MAN_SHAPES_SET_NAME_EP;

public class CalcManagement {

    public static <T> ValidatableResponse shapeRename(T input, int id) {
        return  BaseRequests.putRequest(input,id, CALC_MAN_SHAPES_SET_NAME_EP);
    }

public static <T> ValidatableResponse getShapeData (int id) {
    return  BaseRequests.getRequest(id, CALC_MAN_SHAPES_ID_EP);
}

}
