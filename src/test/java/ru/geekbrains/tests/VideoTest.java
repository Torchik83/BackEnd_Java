package ru.geekbrains.tests;

import org.junit.jupiter.api.Test;

import java.io.File;

import static base.Endpoints.UPLOAD_IMAGE;
import static io.restassured.RestAssured.given;

public class VideoTest extends BaseTest {

    static final String SMALL_VIDEO_FILE = "src/test/resources/Video 1_1.2Мб.mp4";
    static final String MEDIUM_VIDEO_FILE = "src/test/resources/Video 2_7Мб.mp4";
    static final String BIG_VIDEO_FILE = "src/test/resources/Video 3_14Мб.mp4";
    static final String MOV_VIDEO_FILE = "src/test/resources/Video 4_mov.MOV";
    static final String IMAGE_TO_VIDEO = "src/test/resources/Mikey1_2_image_to_video.jpg";

    @Test
    void uploadSmallVideoTest() {
        given()
                .spec(videoSmallUploadRequestSpecification)
                .multiPart("video", new File(SMALL_VIDEO_FILE))
                .when()
                .post(UPLOAD_IMAGE)
                .prettyPeek()
                .then();
    }

    @Test
    void uploadMediumVideoTest() {
          given()
                .spec(videoMediumUploadRequestSpecification)
                .multiPart("video", new File(MEDIUM_VIDEO_FILE))
                .when()
                .post(UPLOAD_IMAGE)
                .prettyPeek()
                .then();
    }

    @Test
    void uploadBigVideoTest() {
         given()
                .spec(videoBigUploadRequestSpecification)
                .multiPart("video", new File(BIG_VIDEO_FILE))
                .when()
                .post(UPLOAD_IMAGE)
                .prettyPeek()
                .then();
    }

    @Test
    void uploadMovVideoTest() {
         given()
                .spec(requestSpecification)
                .multiPart("video", new File(MOV_VIDEO_FILE))
                .when()
                .post(UPLOAD_IMAGE)
                .prettyPeek()
                .then();
    }

    @Test
    void uploadImageToVideoTest() {
         given()
                .spec(requestSpecification)
                .multiPart("video", new File(IMAGE_TO_VIDEO))
                .when()
                .post(UPLOAD_IMAGE)
                .prettyPeek()
                .then();
    }
}
