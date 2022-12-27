package backend.interfaces;

import io.restassured.RestAssured;
import io.restassured.response.ValidatableResponse;
import org.junit.jupiter.api.Test;

import static api.client.BaseRequests.getRequestWithId;
import static api.helper.JsonHelper.getStringFromJson;
import static org.junit.jupiter.api.Assertions.assertEquals;

public interface GetRelatedDataByIdControllerTest {
    String getUrl();

    String getErrorMsg();

    int getCorrectId();

    int getDeletedId();

    @Test
    default void getEntity_withIncorrectStringId_expect400Error() {
        String id = "text";
        ValidatableResponse response = RestAssured
                .given()
                .when()
                .get(getUrl(), id)
                .then();
        response.statusCode(400);
    }

    @Test
    default void getEntity_withIncorrectDoubleId_expect400Error() {
        double id = 2.7;
        ValidatableResponse response = RestAssured
                .given()
                .when()
                .get(getUrl(), id)
                .then();
        response.statusCode(400);
    }

    @Test
    default void getEntity_withNonexistentId_expect400Error() {
        int id = 100000000;
        ValidatableResponse response = getRequestWithId(id, getUrl());
        response.statusCode(400);
    }

    @Test
    default void getEntity_withZeroId_expect400Error() {
        int id = 0;
        ValidatableResponse response = getRequestWithId(id, getUrl());
        response.statusCode(400);
    }

    @Test
    default void getEntity_withDeletedId_expect400Error() {
        ValidatableResponse response = getRequestWithId(getDeletedId(), getUrl());
        response.statusCode(400);
    }

    @Test
    default void getEntity_withDeletedI_expectErrorMessage() {
        ValidatableResponse response = getRequestWithId(getDeletedId(), getUrl());
        String message = getStringFromJson(response, "message");
        assertEquals(getErrorMsg() + " Введен некорректный ID области",
                message, "Сообщение об ошибке не соответвтует ожидаемому");
    }

    @Test
    default void getEntity_withCorrectId_expect200ok() {
        ValidatableResponse response = getRequestWithId(getCorrectId(), getUrl());
        response.statusCode(200);
    }
}
