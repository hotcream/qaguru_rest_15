package tests;

import models.ColourResponse;
import models.CreateUserResponse;
import models.UnsuccessfulLoginResponse;
import models.UpdateDataResponse;
import models.UserResponse;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static endpoints.Endpoints.LOGIN;
import static endpoints.Endpoints.UNKNOWN;
import static endpoints.Endpoints.USERS;
import static io.qameta.allure.Allure.step;
import static io.restassured.RestAssured.given;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.notNullValue;
import static specs.RequestSpecs.requestSpecification;
import static specs.ResponseSpecs.responseSpec;
import static testdata.TestData.*;

public class ReqresTest extends BaseTest {

    @Test
    @DisplayName("Проверка получения списка пользователей")
    void getUsersListTest() {

        UserResponse response =
                step("Отправка запроса на получение списка пользователей", () ->
                        given(requestSpecification)
                                .get(USERS_PAGE_PATH)
                                .then()
                                .spec(responseSpec(200))
                                .extract().as(UserResponse.class));

        step("Проверка email", () ->
                assertThat(response.getData().get(0).getEmail(), is(EMAIL)));
    }

    @Test
    @DisplayName("Проверка создания пользователя")
    void postCreateUserTest() {

        CreateUserResponse response =
                step("Отправка запроса на создание пользователя", () ->
                        given(requestSpecification)
                                .body(createUserRequest())
                                .when()
                                .post(USERS)
                                .then()
                                .spec(responseSpec(201))
                                .extract().as(CreateUserResponse.class));

        step("Проверка имени", () ->
                assertThat(response.getName(), is(NAME)));
        step("Проверка должности", () ->
                assertThat(response.getJob(), is(JOB)));
        step("Проверка времени создания", () ->
                assertThat(response.getCreatedAt(), is(notNullValue())));

    }

    @Test
    @DisplayName("Проверка обновления данных пользователя")
    void updateUserDataTest() {

        UpdateDataResponse response =
                step("Отправка запроса на обновление данных пользователя", () ->
                        given(requestSpecification)
                                .body(updateDataRequest())
                                .when()
                                .put(UPDATE_USER_PATH)
                                .then()
                                .spec(responseSpec(200))
                                .extract().as(UpdateDataResponse.class));

        step("Проверка места работы", () ->
                assertThat(response.getJob(), is(JOB)));
    }

    @Test
    @DisplayName("Проверка авторизации без пароля")
    void checkMissingPasswordTest() {

        UnsuccessfulLoginResponse response =
                step("Отправка запроса на авторизацию без пароля", () ->
                        given(requestSpecification)
                                .body(sendLoginWithoutPasswordRequest())
                                .when()
                                .post(LOGIN)
                                .then()
                                .spec(responseSpec(400))
                                .extract().as(UnsuccessfulLoginResponse.class));

        step("Проверка ошибки отсутствия пароля", () ->
                assertThat(response.getError(), is(MISSING_PASSWORD_ERROR_MESSAGE)));
    }

    @Test
    @DisplayName("Проверка получения цветов")
    void getColourTest() {

        ColourResponse response =
                step("Отправка запроса на получение цветов", () ->
                        given(requestSpecification)
                                .get(UNKNOWN)
                                .then()
                                .log().all()
                                .statusCode(200)
                                .extract().as(ColourResponse.class));

        step("Проверка названия цвета", () ->
                assertThat(response.getData().get(0).getName(), is(COLOUR_NAME)));
        step("Проверка ссылки", () ->
                assertThat(response.getSupport().getUrl(), is(SUPPORT_URL)));
    }
}