package ru.geekbrains.tests;

import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.filter.log.LogDetail;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import org.hamcrest.CoreMatchers;
import org.junit.jupiter.api.BeforeAll;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.notNullValue;

public abstract class BaseTest {
    static Properties properties;
    static String host;
    static String username;
    static String token;
    static Integer userId;

    static ResponseSpecification positiveResponseSpecification;
    static ResponseSpecification imageSmallUploadResponseSpecification;
    static ResponseSpecification imageMediumUploadResponseSpecification;
    static ResponseSpecification imageBigUploadResponseSpecification;
    static ResponseSpecification imageBmpUploadResponseSpecification;
    static ResponseSpecification imageTiffUploadResponseSpecification;
    static ResponseSpecification imagePngUploadResponseSpecification;
    static ResponseSpecification image1x1pclUploadResponseSpecification;
    static ResponseSpecification imageNoImageUploadResponseSpecification;
    static ResponseSpecification videoSmallUploadResponseSpecification;
    static ResponseSpecification videoMediumUploadResponseSpecification;
    static ResponseSpecification videoBigUploadResponseSpecification;
    static ResponseSpecification videoMovUploadResponseSpecification;

    static RequestSpecification requestSpecification;
    static RequestSpecification imageSmallUploadRequestSpecification;
    static RequestSpecification imageMediumUploadRequestSpecification;
    static RequestSpecification imageBigUploadRequestSpecification;
    static RequestSpecification videoSmallUploadRequestSpecification;
    static RequestSpecification videoMediumUploadRequestSpecification;
    static RequestSpecification videoBigUploadRequestSpecification;

    @BeforeAll
    static void beforeAll() throws IOException {


        positiveResponseSpecification = new ResponseSpecBuilder()
                .expectStatusCode(200)
                .expectBody("success", CoreMatchers.is(true))
                .expectBody("status", equalTo(200))
                .build();

        imageSmallUploadResponseSpecification = new ResponseSpecBuilder()
                .expectStatusCode(200)
                .expectBody("success", CoreMatchers.is(true))
                .expectBody("status", equalTo(200))
                .expectBody("data.width", equalTo(1160))
                .expectBody("data.type",equalTo("image/jpeg") )
                .expectBody("data.account_id", notNullValue())
                .expectBody("data.size",notNullValue())
                .build();

        imageMediumUploadResponseSpecification = new ResponseSpecBuilder()
                .expectStatusCode(200)
                .expectBody("success", CoreMatchers.is(true))
                .expectBody("status", equalTo(200))
                .expectBody("data.width", equalTo(3376))
                .expectBody("data.type",equalTo("image/jpeg") )
                .expectBody("data.account_id", notNullValue())
                .expectBody("data.size",notNullValue())
                .build();

        imageBigUploadResponseSpecification = new ResponseSpecBuilder()
                .expectStatusCode(400)
                .expectBody("success", CoreMatchers.is(false))
                .expectBody("data.error", equalTo("File is over the size limit"))
                .build();

        imageBmpUploadResponseSpecification = new ResponseSpecBuilder()
                .expectStatusCode(200)
                .expectBody("success", CoreMatchers.is(true))
                .expectBody("status", equalTo(200))
                .expectBody("data.width", equalTo(1275))
                .expectBody("data.type",equalTo("image/bmp") )
                .expectBody("data.account_id", notNullValue())
                .expectBody("data.size",notNullValue())
                .build();

        imageTiffUploadResponseSpecification = new ResponseSpecBuilder()
                .expectStatusCode(200)
                .expectBody("success", CoreMatchers.is(true))
                .expectBody("status", equalTo(200))
                .expectBody("data.width", equalTo(1280))
                .expectBody("data.type",equalTo("image/tiff") )
                .expectBody("data.account_id", notNullValue())
                .expectBody("data.size",notNullValue())
                .build();

        imagePngUploadResponseSpecification = new ResponseSpecBuilder()
                .expectStatusCode(200)
                .expectBody("success", CoreMatchers.is(true))
                .expectBody("status", equalTo(200))
                .expectBody("data.width", equalTo(1489))
                .expectBody("data.type",equalTo("image/png") )
                .expectBody("data.account_id", notNullValue())
                .expectBody("data.size",notNullValue())
                .build();

        image1x1pclUploadResponseSpecification = new ResponseSpecBuilder()
                .expectStatusCode(200)
                .expectBody("success", CoreMatchers.is(true))
                .expectBody("status", equalTo(200))
                .expectBody("data.width", equalTo(1))
                .expectBody("data.type",equalTo("image/jpeg") )
                .expectBody("data.account_id", notNullValue())
                .expectBody("data.size",notNullValue())
                .build();

        imageNoImageUploadResponseSpecification = new ResponseSpecBuilder()
                .expectStatusCode(400)
                .expectBody("success", CoreMatchers.is(false))
                .expectBody("status", equalTo(400))
                .expectBody("data.error.code", equalTo(1003))
                .expectBody("data.error.message",equalTo("File type invalid (1)") )
                .build();

        videoSmallUploadResponseSpecification = new ResponseSpecBuilder()
                .expectStatusCode(200)
                .expectBody("success", CoreMatchers.is(true))
                .expectBody("status", equalTo(200))
                .expectBody("data.width", equalTo(1))
                .expectBody("data.type",equalTo("image/jpeg") )
                .expectBody("data.account_id", notNullValue())
                .expectBody("data.size",notNullValue())
                .build();

        videoMediumUploadResponseSpecification = new ResponseSpecBuilder()
                .expectStatusCode(200)
                .expectBody("success", CoreMatchers.is(true))
                .expectBody("status", equalTo(200))
                .expectBody("data.width", equalTo(1))
                .expectBody("data.type",equalTo("image/jpeg") )
                .expectBody("data.account_id", notNullValue())
                .expectBody("data.size",notNullValue())
                .build();

        videoBigUploadResponseSpecification = new ResponseSpecBuilder()
                .expectStatusCode(400)
                .expectBody("success", CoreMatchers.is(false))
                .expectBody("status", equalTo(400))
                .expectBody("data.error.message",equalTo("File is over the size limit") )
                .build();

        videoMovUploadResponseSpecification = new ResponseSpecBuilder()
                .expectStatusCode(200)
                .expectBody("success", CoreMatchers.is(true))
                .expectBody("status", equalTo(200))
                .expectBody("data.width", equalTo(1))
                .expectBody("data.type",equalTo("image/jpeg") )
                .expectBody("data.account_id", notNullValue())
                .expectBody("data.size",notNullValue())
                .build();


        properties = new Properties();
        properties.load(new FileInputStream("src/test/resources/application.properties"));
        host = properties.getProperty("host");
        username = properties.getProperty("username", "testprogmath");
        token = properties.getProperty("token");
//        token = properties.getProperty("auth.token");
        userId = Integer.valueOf(properties.getProperty("user.id"));



        RestAssured.baseURI = host;
        RestAssured.enableLoggingOfRequestAndResponseIfValidationFails();

        requestSpecification = new RequestSpecBuilder()
                .addHeader("Authorization", token)
                .log(LogDetail.URI)
                .log(LogDetail.METHOD)
                .build();

        imageBigUploadRequestSpecification = new RequestSpecBuilder()
                .addHeader("Authorization", token)
//                .addMultiPart("image")
                .build();

        videoSmallUploadRequestSpecification = new RequestSpecBuilder()
                .addRequestSpecification(requestSpecification)
                .addFormParam("type", "mp4")
                .addFormParam("title", "video")
                .build();

        videoMediumUploadRequestSpecification = new RequestSpecBuilder()
                .addRequestSpecification(requestSpecification)
                .addFormParam("type", "mp4")
                .addFormParam("title", "video")
                .build();

        videoBigUploadRequestSpecification = new RequestSpecBuilder()
                .addRequestSpecification(requestSpecification)
                .addFormParam("type", "mp4")
                .addFormParam("title", "video")
                .build();

        RestAssured.responseSpecification = positiveResponseSpecification;
        RestAssured.requestSpecification = requestSpecification;



    }
}
