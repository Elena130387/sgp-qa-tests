package backend.interfaces;

import io.restassured.RestAssured;
import io.restassured.response.ValidatableResponse;
import org.junit.jupiter.api.Test;

import static api.client.BaseRequests.getRequestWithId;

public interface GetByIdControllerTest {
    String getUrl();

    int getCorrectId();

    int getDeletedId();

    @Test
    default void getEntity_withIncorrectId_expect400Error() {
        String id = "text";
        ValidatableResponse response = RestAssured
                .given()
                .when()
                .get(getUrl(), id)
                .then();
        response.statusCode(400);
    }

    @Test
    default void getEntity_withNonexistentId_expect204noContent() {
        int id = 100000000;
        ValidatableResponse response = getRequestWithId(id, getUrl());
        response.statusCode(204);
    }

    @Test
    default void getEntity_withDeletedId_expect204noContent() {
        ValidatableResponse response = getRequestWithId(getCorrectId(), getUrl());
        response.statusCode(204);
    }

    @Test
    default void getEntity_withCorrectId_expect200ok() {
        ValidatableResponse response = getRequestWithId(getDeletedId(), getUrl());
        response.statusCode(200);
    }
}
