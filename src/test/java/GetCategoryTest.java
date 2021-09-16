
import enums.*;
import service.CategoryService;
import utils.RetrofitUtils;
import lombok.SneakyThrows;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EnumSource;
import retrofit2.Response;
import static enums.AssertMethod.*;

public class GetCategoryTest {
    static CategoryService categoryService;

    @BeforeAll
    static void beforeAll(){
        categoryService = RetrofitUtils.getRetrofit().create(CategoryService.class);
    }

    @SneakyThrows
    @ParameterizedTest
    @EnumSource(value = NumericField.class, names = {"MAX_INT", "NEGATIVE_NUMBER", "ZERO"})
    void getCategoryIntIdNegativeTest(NumericField numericField){
        Response<dto.Category> response = categoryService.getCategory(numericField.getIntMeaning()).execute();
        negativeAssert404(response);
    }

    @SneakyThrows
    @ParameterizedTest
    @EnumSource(value = NumericField.class, names = {"EMPTY", "SPACE_BETWEEN_NUMBER"})
    void getCategoryStrIdTest(NumericField numericField){
        Response<dto.Category> response = categoryService.getCategory(numericField.getStrMeaning()).execute();
        negativeAssert404(response);
    }

    @SneakyThrows
    @ParameterizedTest
    @EnumSource(value = NumericField.class, names = {"STRING_MEANING", "NAME_CATEGORY", "CHAR_MEANING"/*, "SPACE_MEANING"*/})
    void getCategoryStringTest(NumericField numericField){
        Response<dto.Category> response = categoryService.getCategory(numericField.getStrMeaning()).execute();
        negativeAssert400(response);
    }

    @SneakyThrows
    @Test
    void getCategoryDoubleIdTest(){
        Response<dto.Category> response = categoryService.getCategory(NumericField.DOUBLE_MEANING.getDoubleMeaning()).execute();
        negativeAssert400(response);
    }

    @SneakyThrows
    @Test
    void getCategoryBooleanIdTest(){
        Response<dto.Category> response = categoryService.getCategory(NumericField.BOOL_MEANING.isBoolMeaning()).execute();
        negativeAssert400(response);
    }

    @SneakyThrows
    @Test
    void getCategoryBoolIDTest(){
        Response<dto.Category> response = categoryService.getCategory(NumericField.LONG_MEANING.getLongMeaning()).execute();
        negativeAssert404(response);
    }
}