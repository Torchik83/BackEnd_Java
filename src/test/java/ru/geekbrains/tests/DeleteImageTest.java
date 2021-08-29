package ru.geekbrains.tests;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.io.File;
import static io.restassured.RestAssured.given;

public class DeleteImageTest extends BaseTest {
String imageDeleteHash;
    @BeforeEach
    void setUp () {
         imageDeleteHash =  given()
                .header("Authorization", token)
                .body(new File("src/test/resources/Screen 1_100kb.jpg"))
                .when()
                .post("/image")
                .jsonPath()
                .get("data.deletehash");
    }
    @Test
    void deleteExistentImageTest() {
        given()
                .header("Authorization", token)
                .when()
                .delete("image/{imageHash}", imageDeleteHash)
                .prettyPeek()
                .then()
                .statusCode(200);
    }
}
