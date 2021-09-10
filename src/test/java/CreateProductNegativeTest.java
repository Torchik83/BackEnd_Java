
import com.github.javafaker.Faker;
import dto.Product;
import lombok.SneakyThrows;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EnumSource;
import retrofit2.Response;
import enums.*;
import static enums.AssertMethod.negativeAssert400;

public class CreateProductNegativeTest extends BaseTest {

    Faker faker = new Faker();


    @SneakyThrows
    @ParameterizedTest
    @EnumSource(value = NumericField.class, names = {"ID_CATEGORY","MAX_INT", "NEGATIVE_NUMBER", "ZERO", "EMPTY",
            "SPACE_BETWEEN_NUMBER", "STRING_MEANING", "NAME_CATEGORY", "CHAR_MEANING"})
    void createProductIdTest(NumericField numericField) {
        Response<Product> response = productService
                .createProduct(new Product()
                        .withId(numericField.getIntMeaning())
                        .withTitle(faker.food().dish())
                        .withCategoryTitle(Category.FOOD.getTitle())
                        .withPrice(1))
                .execute();
        negativeAssert400(response);
    }

    @SneakyThrows
    @Test
    void createProductTitleTest() {
        Response<Product> response = productService
                .createProduct(new Product()
                        .withTitle(TextField.BIG_TITLE.getStrTitle())
                        .withCategoryTitle(Category.FOOD.getTitle())
                        .withPrice(1))
                .execute();
        negativeAssert400(response);
    }

    @SneakyThrows
    @ParameterizedTest
    @EnumSource(value = TextField.class, names = {"RUSSIAN_TITLE", "EMPTY", "SPACE_FRONT_TITLE", "SPACE_TITLE"
            ,"CHAR_TITLE", "INT_TITLE", "ZERO", "SPACE_BETWEEN_NUMBER", "NULL_ID", "BOOL_ID"})
    void createProductStringCategoryTitleTest(TextField textField) {
        Response<Product> response = productService
                .createProduct(new Product()
                        .withTitle(faker.food().ingredient())
                        .withCategoryTitle(textField.getStrTitle())
                        .withPrice(1))
                .execute();
        negativeAssert400(response);
    }

    @SneakyThrows
    @Test
    void createProductWithoutCategoryTitleTest() {
        Response<Product> response = productService
                .createProduct(new Product()
                        .withTitle(faker.food().ingredient())
                        .withPrice(1))
                .execute();
        negativeAssert400(response);
    }
}

