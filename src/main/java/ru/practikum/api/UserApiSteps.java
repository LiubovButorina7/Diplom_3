package ru.practikum.api;

import io.qameta.allure.Step;
import io.restassured.response.ValidatableResponse;
import ru.practikum.models.User;
import ru.practikum.util.Constants;

import static io.restassured.RestAssured.given;

public class UserApiSteps {
    User user;

    @Step("Set registration data api")
    public void setRegistrationDataApi(User user, String email, String password, String name) {
        user.setEmail(email + "@test.ru");
        user.setPassword(password);
        user.setName(name);
    }

    @Step("Set user access token api")
    public void setAccessTokenApi(User user, ValidatableResponse response) {
        String userAccessToken = response.extract().body().path("accessToken");
        user.setAccessToken(userAccessToken);
    }
    @Step("Register a new user ")
    public ValidatableResponse registerUserApi(User user) {
        return given()
                .body(user)
                .when()
                .post(Constants.REGISTER_USER)
                .then();
    }

    @Step("Send POST request to /api/auth/login")
    public ValidatableResponse loginUserApi(User user) {
        return given()
                .body(user)
                .when()
                .post(Constants.LOGIN_USER)
                .then();
    }

    @Step("Delete a user")
    public void deleteUserApi(User user) {
        given()
                .header("Authorization", user.getAccessToken())
                .when()
                .post(Constants.DELETE_USER)
                .then();
    }
}
