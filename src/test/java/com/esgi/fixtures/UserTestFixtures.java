package com.esgi.fixtures;

import com.esgi.modules.user.application.CreateUser;
import com.esgi.modules.user.domain.Email;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

import static io.restassured.RestAssured.given;

public class UserTestFixtures {
    public static Response insertTestUser(int port) {
        final var request = new CreateUser("lastname", "firstname", new Email("test@example.com"), "azertyUIOP123$");
        return given()
                .port(port)
                .contentType(ContentType.JSON)
                .body(request)
                .when()
                .post("/users/register");
    }

    public static Response deleteUser(String token, int port) {
        return given()
                .port(port)
                .header("Authorization", "Bearer " + token)
                .when()
                .delete("/users");
    }
}
