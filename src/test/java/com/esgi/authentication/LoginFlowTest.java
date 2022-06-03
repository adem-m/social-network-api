package com.esgi.authentication;

//import com.esgi.config.UserTestConfig;
import com.esgi.fixtures.UserTestFixtures;
import com.esgi.modules.authentication.exposition.LoginResponse;

import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.runner.RunWith;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import io.restassured.RestAssured;

import static io.restassured.RestAssured.when;
import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(
    webEnvironment = WebEnvironment.RANDOM_PORT
)
@AutoConfigureMockMvc
@ActiveProfiles("test")
public class LoginFlowTest {
    @LocalServerPort
    int port;

    @BeforeEach
    public void setup() {
        RestAssured.port = port;
    }

    @Test
    public void should_return_jwt() {
        UserTestFixtures.insertTestUser();

        var res = when()
            .post("/login")
            .then()
            .statusCode(201)
            .extract()
            .body().jsonPath().getObject(".", LoginResponse.class);
        
        assertThat(res.token()).isInstanceOf(String.class);
        assertThat(res.userId()).isInstanceOf(String.class);

        UserTestFixtures.deleteUser(res.token());
    }
}
