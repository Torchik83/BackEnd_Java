package ru.geekbrains.tests;

import dao.ImageResponse;
import io.restassured.specification.RequestSpecification;
import org.apache.commons.io.FileUtils;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.IOException;
import java.util.Base64;

import static base.Endpoints.UPLOAD_IMAGE;
import static base.Images.IMAGE_URL;
import static io.restassured.RestAssured.given;

public class ImageTest extends BaseTest {

    static final String IMAGE_FILE = "src/test/resources/Screen 1_100kb.jpg";
    static final String MEDIUM_IMAGE_FILE = "src/test/resources/Screen 2_7Mb.jpg";
    static final String BIG_IMAGE_FILE = "src/test/resources/Screen 3_11Mb.jpg";
    static final String BMP_IMAGE_FILE = "src/test/resources/Screen 5_bmp.bmp";
    static final String TIFF_IMAGE_FILE = "src/test/resources/Screen 6_tiff.tiff";
    static final String PNG_IMAGE_FILE = "src/test/resources/Screen 7_png.png";
    static final String PATH_TO_1X1PCL_IMAGE = "src/test/resources/Screen 8_1x1pcl.jpg";
    static final String NO_IMAGE_FILE = "src/test/resources/Screen 9_no image.html";

    RequestSpecification multiPartReqSpec;
    String base64Image;
    RequestSpecification imageRequestSpecification;

    @BeforeEach
    void setUp() throws IOException {
        byte[] imageBytesArray = FileUtils.readFileToByteArray(new File(IMAGE_FILE));
        base64Image = Base64.getEncoder().encodeToString(imageBytesArray);
    }

    @Test
    void uploadImageFileTest() {
          given()
                .spec(requestSpecification)
                .multiPart("image", new File(IMAGE_FILE))
                .expect()
                .spec(positiveResponseSpecification)
                .when()
                .post(UPLOAD_IMAGE)
                .prettyPeek()
                .then()
                .extract()
                .as(ImageResponse.class);

    }

    @Test
    void uploadImageSmallFileTest() {
         given()
                .spec(requestSpecification)
                .multiPart("image", new File(IMAGE_FILE))
                .expect()
                .spec(imageSmallUploadResponseSpecification)
                .when()
                .post(UPLOAD_IMAGE)
                .prettyPeek()
                .then()
                .extract()
                .as(ImageResponse.class);
    }

    @Test
    void uploadImageMediumFileTest() {
         given()
                .spec(requestSpecification)
                .multiPart("image", new File(MEDIUM_IMAGE_FILE))
                .expect()
                .spec(imageMediumUploadResponseSpecification)
                .when()
                .post(UPLOAD_IMAGE)
                .prettyPeek()
                .then()
                .extract()
                .as(ImageResponse.class);
    }

    @Test
    void uploadImageBigFileTest() {
        given()
                .spec(imageBigUploadRequestSpecification)
                .multiPart("image", new File(BIG_IMAGE_FILE))
                .expect()
                .spec(imageBigUploadResponseSpecification)
                .when()
                .post(UPLOAD_IMAGE)
                .prettyPeek()
                .then()
                .extract()
                .as(ImageResponse.class);
    }

    @Test
    void uploadImageBmpFileTest() {
        given()
                .spec(requestSpecification)
                .multiPart("image", new File(BMP_IMAGE_FILE))
                .expect()
                .spec(imageBmpUploadResponseSpecification)
                .when()
                .post(UPLOAD_IMAGE)
                .prettyPeek()
                .then()
                .extract()
                .as(ImageResponse.class);
    }

    @Test
    void uploadImageTiffFileTest() {
        given()
                .spec(requestSpecification)
                .multiPart("image", new File(TIFF_IMAGE_FILE))
                .expect()
                .spec(imageTiffUploadResponseSpecification)
                .when()
                .post(UPLOAD_IMAGE)
                .prettyPeek()
                .then()
                .extract()
                .as(ImageResponse.class);
    }

    @Test
    void uploadImagePngFileTest() {
        given()
                .spec(requestSpecification)
                .multiPart("image", new File(PNG_IMAGE_FILE))
                .expect()
                .spec(imagePngUploadResponseSpecification)
                .when()
                .post(UPLOAD_IMAGE)
                .prettyPeek()
                .then()
                .extract()
                .as(ImageResponse.class);
    }

    @Test
    void upload1x1pclFileTest() {
        given()
                .spec(requestSpecification)
                .multiPart("image", new File(PATH_TO_1X1PCL_IMAGE))
                .expect()
                .spec(image1x1pclUploadResponseSpecification)
                .when()
                .post(UPLOAD_IMAGE)
                .prettyPeek()
                .then()
                .extract()
                .as(ImageResponse.class);
    }

    @Test
    void uploadNoImageFileTest() {
        given()
                .spec(requestSpecification)
                .multiPart("image", new File(NO_IMAGE_FILE))
                .expect()
                .spec(imageNoImageUploadResponseSpecification)
                .when()
                .post(UPLOAD_IMAGE)
                .prettyPeek()
                .then()
                .extract()
                .as(ImageResponse.class);
    }

    @Test
    void uploadFileWithLinkTest() {
        given()
                .spec(requestSpecification)
                .multiPart("image", IMAGE_URL.getPath())
                .when()
                .post(UPLOAD_IMAGE)
                .prettyPeek()
                .then()
                .extract()
                .as(ImageResponse.class);
    }

    @Test
    void uploadBase64FileTest() {
        given()
                .spec(requestSpecification)
                .multiPart("image", base64Image)
                .when()
                .post(UPLOAD_IMAGE)
                .prettyPeek()
                .then()
                .extract()
                .as(ImageResponse.class);
    }
}