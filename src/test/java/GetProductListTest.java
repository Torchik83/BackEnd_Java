import lombok.SneakyThrows;
import okhttp3.ResponseBody;
import org.junit.jupiter.api.Test;
import retrofit2.Response;

import static enums.AssertMethod.positiveAssert;

public class GetProductListTest extends BaseTest {

    @SneakyThrows
    @Test
    void getProductPositiveTest(){
        Response<ResponseBody> response = productService.getProduct().execute();
        positiveAssert(response);
    }
}
