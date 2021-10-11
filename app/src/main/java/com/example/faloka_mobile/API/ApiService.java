package com.example.faloka_mobile.API;

import com.example.faloka_mobile.Login.LoginResponse;
import com.example.faloka_mobile.Model.Address;
import com.example.faloka_mobile.Model.Category;
import com.example.faloka_mobile.Model.Courier;
import com.example.faloka_mobile.Model.Logout;
import com.example.faloka_mobile.Model.Message;
import com.example.faloka_mobile.Model.Product;
import com.example.faloka_mobile.Model.Profile;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface ApiService {
    @FormUrlEncoded
    @POST("auth/login")
    Call<LoginResponse> getSession(@Field("email") String email, @Field("password") String password);

    @GET("home")
    Call<List<Category>> getCategories();

    @GET("products")
    Call<List<Product>> getProductSubCategories(
            @Query("category") String slugCategory,
            @Query("subcategory") String slugSubCategory);

    @GET("products/{slug}/related")
    Call<List<Product>> getRelatedProducts(
        @Path(value = "slug", encoded = true) String slug
    );

    @POST("auth/logout")
    Call<Message> getLogoutMessage(@Header("Authorization") String auth);

    @GET("auth/profile")
    Call<Profile> getProfile(@Header("Authorization") String auth);

    @GET("expeditions")
    Call<List<Courier>> getExpeditions();

    @PUT("auth/address/{address_id}")
    Call<Message> putAddress(@Path("address_id") int addressID, @Body Address address);

    @DELETE("auth/address/{address_id}")
    Call<Message> deleteAddress(@Path("address_id") int addressID);
}

