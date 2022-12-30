package api.client;

import api.dto.shape.ShapeInput;
import api.dto.shape.ShapeRename;
import io.restassured.response.ValidatableResponse;

import static util.Constants.*;

public class CalcManagement {

    public static ValidatableResponse shapeRename(ShapeRename input, int id) {
        return BaseRequests.putRequestWithId(input, id, CALC_MAN_SHAPES_SET_NAME_EP);
    }

    public static ValidatableResponse getShapeDataById(int id) {
        return BaseRequests.getRequestWithId(id, CALC_MAN_SHAPES_ID_EP);
    }

    public static ValidatableResponse createNewShape(ShapeInput input) {
        return BaseRequests.postRequestWithBody(input, CALC_MAN_SHAPES_EP);
    }

    public static ValidatableResponse deleteShapeById(int id) {
        return BaseRequests.deleteRequestWithId(id, CALC_MAN_SHAPES_ID_EP);
    }

    public static void deleteShapeByIdWithoutResponseReturn(int id) {
        BaseRequests.deleteRequestWithId(id, CALC_MAN_SHAPES_ID_EP);
    }

    public static void stopShapeCalculationById(int id) {
        BaseRequests.getRequestWithId(id, CALC_MAN_SHAPES_STOP_EP);
    }

    public static ValidatableResponse getLimitNumberOfShapes(int limit) {
        return BaseRequests.getRequestWithParam(limit, CALC_MAN_SHAPES_EP);
    }

    public static ValidatableResponse getListOfAllShapes() {
        return BaseRequests.getRequest(CALC_MAN_SHAPES_EP);
    }

    public static ValidatableResponse getSortedListOfShapes(String sort) {
        return BaseRequests.getRequestWithParam(sort, CALC_MAN_SHAPES_EP);
    }

    public static ValidatableResponse getListOfRunningShapes() {
        return BaseRequests.getRequest(CALC_MAN_RUNNING_SHAPES_EP);
    }
}
