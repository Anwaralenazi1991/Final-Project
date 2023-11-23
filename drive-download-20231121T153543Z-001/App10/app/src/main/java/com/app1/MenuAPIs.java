package com.app1;

import java.util.ArrayList;
import java.util.List;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

/**
 * Created by Mohamed Fadel
 * Date: 3/27/2020.
 * email: mohamedfadel91@gmail.com.
 */
public interface MenuAPIs {
    @GET("api/resturants.php")
    Call<List<Model>> getRestaurants();

    @GET("api/menu.php")
    Call<List<MenuModel>> getItems(@Query("id") long id);

    @POST("api/AddOrder.php")
    Call<Order> addOrder(@Query("name") String name, @Query("address") String address,
                                @Query("total") double total,
                                @Query("appName") String appName,
                                @Body ArrayList<MenuModel> items);
}
