package testdata;

import models.CreateUserRequest;
import models.LoginRequest;
import models.UpdateDataRequest;

import static endpoints.Endpoints.*;

public class TestData {

    public static final String UPDATE_USER_PATH = USERS + "2",
            USERS_PAGE_PATH = USERS_PAGE + "2",
            EMAIL = "michael.lawson@reqres.in",
            NAME = "Mike",
            JOB = "manager",
            MISSING_PASSWORD_ERROR_MESSAGE = "Missing password",
            COLOUR_NAME = "cerulean",
            SUPPORT_URL = "https://reqres.in/#support-heading";

    public static CreateUserRequest createUserRequest() {
        CreateUserRequest request = new CreateUserRequest();
        request.setJob("manager");
        request.setName(NAME);
        return request;
    }

    public static UpdateDataRequest updateDataRequest() {
        UpdateDataRequest request = new UpdateDataRequest();
        request.setJob("manager");
        request.setName(NAME);
        return request;
    }

    public static LoginRequest sendLoginWithoutPasswordRequest() {
        LoginRequest request = new LoginRequest();
        request.setEmail(EMAIL);
        return request;
    }
}
