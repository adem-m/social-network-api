package com.esgi.fixtures;

import com.esgi.modules.user.application.CreateUser;
import com.esgi.modules.user.domain.Email;

import io.restassured.http.ContentType;
import io.restassured.response.Response;

import static io.restassured.RestAssured.given;

public class UserTestFixtures {
    public static Response insertTestUser() {
        final var request = new CreateUser("lastname", "firstname", new Email("test@example.com"), "azertyUIOP123$");
        return given()
            .contentType(ContentType.JSON)
            .body(request)
            .when()
            .post("/users");
    }

    public static Response deleteUser(String token) {
        return given()
            .header("Authorization", "Bearer " + token)
            .when()
            .delete("/users");
    }
}
