package api.client;

import io.restassured.response.ValidatableResponse;

import static util.Constants.CALC_MAN_SHAPES_ID_EP;

public class CalcManagement {

    public static <T> ValidatableResponse shapeRename(T input, int id) {
        return  BaseRequests.putRequest(input,id, CALC_MAN_SHAPES_ID_EP);
    }
}
