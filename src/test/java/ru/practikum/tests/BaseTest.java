package ru.practikum.tests;

import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.config.LogConfig;
import io.restassured.http.ContentType;
import io.restassured.parsing.Parser;
import org.junit.Before;
import ru.practikum.util.Constants;
import java.io.IOException;

public class BaseTest {
    @Before
    public void startUp() throws IOException {
        RestAssured.defaultParser = Parser.JSON;
        RestAssured.requestSpecification = new RequestSpecBuilder().setBaseUri(Constants.HOST)
                .setContentType(ContentType.JSON)
                .build();
        RestAssured.config = RestAssured.config()
                .logConfig(LogConfig.logConfig().enableLoggingOfRequestAndResponseIfValidationFails());
    }
}