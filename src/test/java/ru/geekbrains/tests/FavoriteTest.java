package ru.geekbrains.tests;

import org.hamcrest.CoreMatchers;
import org.junit.jupiter.api.Test;
import java.io.File;
import static io.restassured.RestAssured.given;


public class FavoriteTest extends BaseTest {

    String imageDeleteHash;
    String fileID;

    @Test
    void uploadFileTest() {
        imageDeleteHash = given()
                .header("Authorization", token)
                .body(new File("src/test/resources/Screen 1_100kb.jpg"))
                .expect()
                .statusCode(200)
                .body("success", CoreMatchers.is(true))
                .body("data.type", CoreMatchers.is("image/jpeg"))
                .body("data.width", CoreMatchers.is(1160))
                .body("data.size", CoreMatchers.not(0))
                .when()
                .post("/image")
                .prettyPeek()
                .jsonPath()
                .get("data.deletehash");
    }
//    @Test
//    void postImageFavourite(){
 //        given()
//                .headers("Authorization", token)
//                .log()
//                .method()
//                .expect()
//                .statusCode(200)
//                .body("success", CoreMatchers.is(true))
//                .body("data", CoreMatchers.is("favorite"))
 //               .when()
//                .post("/image/{imageHash}/favorite", fileID)
//                .prettyPeek();
 //   }
}