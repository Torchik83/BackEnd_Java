package service;

import dto.Category;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface CategoryService {

    @GET("categories/{id}")
    Call<Category> getCategory(@Path("id") int id);
    @GET("categories/{id}")
    Call<Category> getCategory(@Path("id") String id);
    @GET("categories/{id}")
    Call<Category> getCategory(@Path("id") long id);
    @GET("categories/{id}")
    Call<Category> getCategory(@Path("id") char id);
    @GET("categories/{id}")
    Call<Category> getCategory(@Path("id") double id);
    @GET("categories/{id}")
    Call<Category> getCategory(@Path("id") boolean id);
}
