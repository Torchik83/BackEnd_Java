import com.github.javafaker.Faker;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import retrofit2.Retrofit;
import db.dao.CategoriesMapper;
import db.dao.ProductsMapper;
import db.model.Categories;
import db.model.CategoriesExample;
import db.model.ProductsExample;
import service.CategoryService;
import service.ProductService;
import utils.DbUtils;
import utils.RetrofitUtils;

public class BaseTest {
    static Retrofit retrofit;
    static CategoryService categoryService;
    static ProductService productService;
    static Faker faker;
    static ProductsMapper productsMapper;
    static CategoriesMapper categoriesMapper;
    static Categories testCategory;

    @BeforeAll
    static void beforeAll() {
        retrofit = RetrofitUtils.getRetrofit();
        categoryService = retrofit.create(CategoryService.class);
        productService = retrofit.create(ProductService.class);
        faker = new Faker();
        productsMapper = DbUtils.getProductsMapper();
        categoriesMapper = DbUtils.getCategoriesMapper();

        String quote = faker.backToTheFuture().quote();
        //создаем новую категорию
        testCategory = DbUtils.getNewTestCategory(quote);
    }

    @AfterAll
    static void afterAll() {
        DbUtils.deleteAllProductsWithTheCategory(testCategory.getId());
        categoriesMapper.deleteByPrimaryKey(testCategory.getId());
    }
}