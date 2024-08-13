package tests;

import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static io.restassured.http.ContentType.JSON;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.notNullValue;

public class ReqresTest extends BaseTest {

    @Test
    void getUsersListTest() {
        given()
                .log()
                .all()
                .get("/users?page=2")
                .then().log().body()
                .statusCode(200)
                .body("data[0].email", is("michael.lawson@reqres.in"));
    }

    @Test
    void postCreateUserTest() {

        String authData = "{\"name\": \"Mike\", \"job\": \"manager\"}";

        given()
                .body(authData)
                .contentType(JSON)
                .log().uri()
                .when()
                .post("/users")
                .then()
                .log().status()
                .log().body()
                .statusCode(201)
                .body("name", is("Mike"))
                .body("job", is("manager"))
                .body("id", is(notNullValue()));
    }

    @Test
    void updateUserDataTest() {

        String updateData = "{\"name\": \"morpheus\", \"job\": \"manager\"}";

        given()
                .body(updateData)
                .log().all()
                .contentType(JSON)
                .when()
                .put("/users/2")
                .then()
                .log().body()
                .statusCode(200)
                .body("job", is("manager"));
    }

    @Test
    void checkMissingPasswordTest() {

        String authData = "{\"email\": \"peter@klaven\"}";

        given()
                .body(authData)
                .contentType(JSON)
                .log().uri()
                .when()
                .post("/login")
                .then()
                .log().status()
                .log().body()
                .statusCode(400)
                .body("error", is("Missing password"));
    }

    @Test
    void getColourTest() {

        given()
                .log()
                .all()
                .get("/unknown/9")
                .then()
                .log().all()
                .statusCode(200)
                .body("data.name", is("blue iris"))
                .body("support.url", is("https://reqres.in/#support-heading"));
    }
}