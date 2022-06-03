package com.esgi.fixtures;

import io.restassured.http.ContentType;
import io.restassured.response.Response;

import static io.restassured.RestAssured.given;

public class UserTestFixtures {
    public static Response insertTestUser(int port) {
        final var request = new UserTestFixtures.Data("lastname", "firstname", "test@example.com", "azertyUIOP123$");
        return given()
                .port(port)
                .contentType(ContentType.JSON)
                .with().body(request)
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

    static class Data {
        final public String lastname;
        final public String firstname;
        final public String email;
        final public String password;

        public Data(String lastname, String firstname, String email, String password) {
            this.lastname = lastname;
            this.firstname = firstname;
            this.email = email;
            this.password = password;
        }
    }
}
