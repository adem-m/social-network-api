package com.esgi.fixtures;

import io.restassured.http.ContentType;

import static io.restassured.RestAssured.given;

import com.esgi.modules.authentication.exposition.LoginRequest;
import com.esgi.modules.authentication.exposition.LoginResponse;

public class LoginTestFixtures {
  public static String loginAsTestUser(int port) {
    LoginRequest body = new LoginRequest();
    body.email = "test@example.com";
    body.password = "azertyUIOP123$";
    
    return given()
      .port(port)
      .contentType(ContentType.JSON)
      .with().body(body)
      .when()
      .post("/login")
      .then()
      .statusCode(201)
      .extract()
      .body().jsonPath().getObject(".", LoginResponse.class)
      .token();
  }
}
