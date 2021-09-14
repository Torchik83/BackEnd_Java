import com.github.javafaker.Faker;
import db.model.Products;
import db.model.ProductsExample;
import dto.Product;
import enums.Category;
import enums.NumericField;
import enums.TextField;
import lombok.SneakyThrows;
import okhttp3.ResponseBody;
import org.hamcrest.CoreMatchers;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EnumSource;
import retrofit2.Response;

import java.util.Objects;

import static enums.AssertMethod.positiveCreateAssert;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

public class CreateProductPositiveTest extends BaseTest {
    Product product;
    Faker faker = new Faker();
    int id;

    @BeforeEach
    void setUp(){
        product = new Product()
                .withTitle(faker.food().ingredient())
                .withCategoryTitle(testCategory.getTitle())
                .withPrice((int) (Math.random() * 10000));
    }

    @Test
    @SneakyThrows
    void createProductInFoodCategoryTest() {
        Response<Product> response = productService.createProduct(product).execute();
        id = Objects.requireNonNull(response.body()).getId();
        assertThat(response.isSuccessful(), CoreMatchers.is(true));
        assertThat(response.body().getTitle(), equalTo(product.getTitle()));
        ProductsExample example = new ProductsExample();
        example.createCriteria().andCategory_idEqualTo(Long.valueOf(testCategory.getId()));
        assertThat(response.body().getTitle(), equalTo(product.getTitle()));

    }

    @SneakyThrows
    @ParameterizedTest
    @EnumSource(value = TextField.class, names = {"RUSSIAN_TITLE", "EMPTY", "SPACE_FRONT_TITLE", "SPACE_TITLE",
            "CHAR_TITLE", "INT_TITLE", "ZERO", "SPACE_BETWEEN_NUMBER", "NULL"})
    void createProductTitleTest(TextField textField) {
        Response<Product> response = productService
                .createProduct(new Product()
                        .withTitle(textField.getStrTitle())
                        .withCategoryTitle(Category.FOOD.getTitle())
                        .withPrice(1))
                .execute();
        id = Objects.requireNonNull(response.body()).getId();
        positiveCreateAssert(response);
        assertThat(response.body().getTitle(), equalTo(textField.getStrTitle()));
        assertThat(response.body().getCategoryTitle(), equalTo(Category.FOOD.getTitle()));
    }

    @SneakyThrows
    @Test
    void createProductWithoutTitleTest() {
        Response<Product> response = productService
                .createProduct(new Product()
                        .withCategoryTitle(Category.FOOD.getTitle())
                        .withPrice(1))
                .execute();
        id = Objects.requireNonNull(response.body()).getId();
        positiveCreateAssert(response);
        assertThat(response.body().getCategoryTitle(), equalTo(Category.FOOD.getTitle()));
        ProductsExample example = new ProductsExample();
        example.createCriteria().andCategory_idEqualTo(Long.valueOf(testCategory.getId())).andTitleEqualTo(null);
        Products productFromDb = productsMapper.selectByExample(example).get(1);
        assertThat(productFromDb.getTitle()).isEqualTo(product.getTitle());
    }

    @SneakyThrows
    @ParameterizedTest
    @EnumSource(value = NumericField.class, names = {"PRICE", "ZERO_FRONT_PRICE", "MAX_INT", "NEGATIVE_NUMBER",
            "ZERO", "SPACE_FRONT_PRICE", "SPACE_BETWEEN_NUMBER", "EMPTY", "SPACE_MEANING", "STRING_MEANING", "CHAR_MEANING"})
    void createProductPriceTest(NumericField numericField) {
        Response<Product> response = productService
                .createProduct(product = new Product()
                        .withTitle(faker.food().ingredient())
                        .withCategoryTitle(Category.FOOD.getTitle())
                        .withPrice(numericField.getIntMeaning()))
                .execute();
        id = Objects.requireNonNull(response.body()).getId();
        positiveCreateAssert(response);
        assertThat(response.body().getTitle(), equalTo(product.getTitle()));
        assertThat(response.body().getCategoryTitle(), equalTo(Category.FOOD.getTitle()));
        assertThat(response.body().getPrice(), equalTo(numericField.getIntMeaning()));
        ProductsExample example = new ProductsExample();
        example.createCriteria().andCategory_idEqualTo(Long.valueOf(testCategory.getId())).andPriceEqualTo(1000);
        Products productFromDb = productsMapper.selectByExample(example).get(0);
        assertThat(productFromDb.getPrice()).isEqualTo(product.getPrice());
    }

    @SneakyThrows
    @Test
    void createProductWithoutPriceTest() {
        Response<Product> response = productService
                .createProduct(product = new Product()
                        .withTitle(faker.food().ingredient())
                        .withCategoryTitle(Category.FOOD.getTitle()))
                .execute();
        id = Objects.requireNonNull(response.body()).getId();
        positiveCreateAssert(response);
        assertThat(response.body().getTitle(), equalTo(product.getTitle()));
        assertThat(response.body().getCategoryTitle(), equalTo(Category.FOOD.getTitle()));
        ProductsExample example = new ProductsExample();
        example.createCriteria().andCategory_idEqualTo(Long.valueOf(testCategory.getId())).andPriceEqualTo(null);
        Products productFromDb = productsMapper.selectByExample(example).get(0);
        assertThat(productFromDb.getPrice()).isEqualTo(product.getPrice());
    }

    @SneakyThrows
    @Test
    void createProductBoolPriceTest() {
        Response<Product> response = productService
                .createProduct(product = new Product()
                        .withTitle(faker.food().ingredient())
                        .withCategoryTitle(Category.FOOD.getTitle())
                        .withPrice(NumericField.BOOL_MEANING.getIntMeaning()))
                .execute();
        id = Objects.requireNonNull(response.body()).getId();
        positiveCreateAssert(response);
        assertThat(response.body().getTitle(), equalTo(product.getTitle()));
        assertThat(response.body().getCategoryTitle(), equalTo(Category.FOOD.getTitle()));
        assertThat(response.body().getPrice(), equalTo(NumericField.BOOL_MEANING.getIntMeaning()));
    }

    @SneakyThrows
    @Test
    void createProductDoublePriceTest() {
        Response<Product> response = productService
                .createProduct(product = new Product()
                        .withTitle(faker.food().ingredient())
                        .withCategoryTitle(Category.FOOD.getTitle())
                        .withPrice(NumericField.DOUBLE_MEANING.getIntMeaning()))
                .execute();
        id = Objects.requireNonNull(response.body()).getId();
        positiveCreateAssert(response);
        assertThat(response.body().getTitle(), equalTo(product.getTitle()));
        assertThat(response.body().getCategoryTitle(), equalTo(Category.FOOD.getTitle()));
        assertThat(response.body().getPrice(), equalTo(NumericField.DOUBLE_MEANING.getIntMeaning()));
    }

    @SneakyThrows
    @Test
    void createProductLongPriceTest() {
        Response<Product> response = productService
                .createProduct(product = new Product()
                        .withTitle(faker.food().ingredient())
                        .withCategoryTitle(Category.FOOD.getTitle())
                        .withPrice(NumericField.LONG_MEANING.getIntMeaning()))
                .execute();
        id = Objects.requireNonNull(response.body()).getId();
        positiveCreateAssert(response);
        assertThat(response.body().getTitle(), equalTo(product.getTitle()));
        assertThat(response.body().getCategoryTitle(), equalTo(Category.FOOD.getTitle()));
        assertThat(response.body().getPrice(), equalTo(NumericField.LONG_MEANING.getIntMeaning()));
    }

    @SneakyThrows
    @AfterEach
    void tearDown() {
        Response<ResponseBody> response = productService.deleteIntIdProduct(id).execute();
        assertThat(response.isSuccessful(), CoreMatchers.is(true));
    }
}
