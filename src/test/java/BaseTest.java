
import service.ProductService;
import utils.RetrofitUtils;
import org.junit.jupiter.api.BeforeAll;

public class BaseTest {
    static ProductService productService;

    @BeforeAll
    static void beforeAll(){
        productService = RetrofitUtils.getRetrofit().create(ProductService.class);
    }
}
