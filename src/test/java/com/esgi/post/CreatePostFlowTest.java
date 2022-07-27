package com.esgi.post;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.esgi.fixtures.LoginTestFixtures;
import com.esgi.fixtures.UserTestFixtures;
import com.esgi.modules.post.exposition.PostRequest;

import io.restassured.RestAssured;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.http.ContentType;

import static io.restassured.RestAssured.given;
import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(
    webEnvironment = WebEnvironment.RANDOM_PORT
)
public class CreatePostFlowTest {
  @LocalServerPort
  int port;

  @Before
  public void setup() {
      RestAssured.port = port;
      RestAssured.filters(new RequestLoggingFilter(), new ResponseLoggingFilter());
  }

  @Test
  public void should_add_a_post() {
    UserTestFixtures.insertTestUser(port)
      .then()
      .statusCode(201);
    
    var token = LoginTestFixtures.loginAsTestUser(port);

    PostRequest body = new PostRequest();
    body.content = "some content";
    body.code = null;

    given()
        .port(port)
        .header("Authorization", "Bearer " + token)
        .contentType(ContentType.JSON)
        .with().body(body)
        .when()
        .post("/posts")
        .then()
        .statusCode(201);

    UserTestFixtures.deleteUser(token, port);
  }
}
