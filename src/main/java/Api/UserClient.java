package Api;


import io.restassured.response.ValidatableResponse;

import static Api.RestAssuredClient.getBaseSpec;
import static io.restassured.RestAssured.given;

public class UserClient {

    private static final String USER_PATH = "/api/auth/";

       // Создание пользователя
    public ValidatableResponse createUser (User user) {
        return given()
                .spec(getBaseSpec())
                .body(user)
                .when()
                .post(USER_PATH + "register")
                .then();
    }

      // логин пользователя
    public ValidatableResponse loginUser(UserCredentials credentials) {
        return given()
                .spec(getBaseSpec())
                .body(credentials)
                .when()
                .post(USER_PATH + "login")
                .then();
    }

     // удаление пользователя
    public ValidatableResponse deleteUser(String accessToken) {
        return given()
                .header("Authorization", accessToken)
                .spec(getBaseSpec())
                .when()
                .delete(USER_PATH + "user")
                .then();
    }
}