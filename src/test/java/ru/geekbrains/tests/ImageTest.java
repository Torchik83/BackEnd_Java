package ru.geekbrains.tests;

import org.hamcrest.CoreMatchers;
import org.junit.jupiter.api.Test;
import java.io.File;
import static io.restassured.RestAssured.given;

public class ImageTest extends BaseTest {

    String imageDeleteHash;
    String Upload_ID;

    @Test
    void uploadImageFileTest() {
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
    @Test
    void uploadMediumSizeImageFileTest() {
        imageDeleteHash = given()
                .header("Authorization", token)
                .body(new File("src/test/resources/Screen 2_7Mb.jpg"))
                .expect()
                .statusCode(200)
                .body("success", CoreMatchers.is(true))
                .body("data.type", CoreMatchers.is("image/jpeg"))
                .body("data.width", CoreMatchers.is(3376))
                .body("data.size", CoreMatchers.not(0))
                .when()
                .post("/image")
                .prettyPeek()
                .jsonPath()
                .get("data.deletehash");
    }
    @Test
    void uploadBigSizeImageFileTest()  {
        given()
                .header("Authorization", token)
                .body(new File("src/test/resources/Screen 3_11Mb.jpg"))
                .when()
                .post("/image")
                .prettyPeek()
                .then()
                .statusCode(400)
                .body("success", CoreMatchers.is(false))
                .body("data.error", CoreMatchers.is("File is over the size limit"));
    }
    @Test
    void uploadBmpFileTest() {
        imageDeleteHash = given()
                .header("Authorization", token)
                .body(new File("src/test/resources/Screen 5_bmp.bmp"))
                .expect()
                .statusCode(200)
                .body("success", CoreMatchers.is(true))
                .body("data.type", CoreMatchers.is("image/bmp"))
                .body("data.width", CoreMatchers.is(1275))
                .body("data.size", CoreMatchers.not(0))
                .when()
                .post("/image")
                .prettyPeek()
                .jsonPath()
                .get("data.deletehash");
    }
    @Test
    void uploadTiffFileTest() {
        imageDeleteHash = given()
                .header("Authorization", token)
                .body(new File("src/test/resources/Screen 6_tiff.tiff"))
                .expect()
                .statusCode(200)
                .body("success", CoreMatchers.is(true))
                .body("data.type", CoreMatchers.is("image/tiff"))
                .body("data.width", CoreMatchers.is(1280))
                .body("data.size", CoreMatchers.not(0))
                .when()
                .post("/image")
                .prettyPeek()
                .jsonPath()
                .get("data.deletehash");
    }
    @Test
    void uploadPngFileTest() {
        imageDeleteHash = given()
                .header("Authorization", token)
                .body(new File("src/test/resources/Screen 7_png.png"))
                .expect()
                .statusCode(200)
                .body("success", CoreMatchers.is(true))
                .body("data.type", CoreMatchers.is("image/png"))
                .body("data.width", CoreMatchers.is(1489))
                .body("data.size", CoreMatchers.not(0))
                .when()
                .post("/image")
                .prettyPeek()
                .jsonPath()
                .get("data.deletehash");
    }
    @Test
    void upload1x1pclImageFileTest()  {
       imageDeleteHash = given()
               .header("Authorization", token)
               .body(new File("src/test/resources/Screen 8_1x1pcl.jpg"))
               .expect()
               .statusCode(200)
               .body("success", CoreMatchers.is(true))
               .body("data.type", CoreMatchers.is("image/jpeg"))
               .body("data.width", CoreMatchers.is(1))
               .body("data.size", CoreMatchers.not(0))
               .when()
               .post("/image")
               .prettyPeek()
               .jsonPath()
               .get("data.deletehash");
    }
    @Test
    void uploadNoImageFileTest() {
        given()
                .header("Authorization", token)
                .body(new File("src/test/resources/Screen 9_no image.html"))
                .when()
                .post("/image")
                .prettyPeek()
                .then()
                .statusCode(400)
                .body("success", CoreMatchers.is(false));
    }
    @Test
    void uploadUrlImageFileTest() {
        Upload_ID = given()
                .header("Authorization", token)
                .multiPart("image", "https://images.wallpaperscraft.ru/image/doroga_gory_tuman_219202_1024x768.jpg")
                .params("type","url")
                .expect()
                .statusCode(200)
                .body("success", CoreMatchers.is(true))
                .body("data.type", CoreMatchers.is("image/jpeg"))
                .body("data.width", CoreMatchers.is(1024))
                .body("data.size", CoreMatchers.not(0))
                .when()
                .post("/image")
                .prettyPeek()
                .then()
                .extract()
                .response()
                .jsonPath()
                .getString("data.id");
    }

//    @AfterEach
//   void tearDown() {
//        given()
//               .header("Authorization", token)
//               .when()
//               .delete("image/{imageHash}", imageDeleteHash)
//               .then()
//               .statusCode(200);
//   }
}