package com.project.balmer.dnd.repository;

import com.project.balmer.dnd.Model.CustomerInfo;
import com.project.balmer.dnd.Model.GoodInfo;
import com.project.balmer.dnd.Model.OrderInfo;
import com.project.balmer.dnd.Model.ShopInfo;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public interface FirebaseApi {

    @GET("shop.json")
    Call<List<ShopInfo>> getShops();

    @GET("good/.json")
    Call<List<GoodInfo>> getGoods();

    @PUT("order/{id}.json")
    Call<OrderInfo>makeOrder(@Path("id") String uuid,
            @Body OrderInfo orderInfo);

    @PUT("customer/{id}.json")
    Call<CustomerInfo>  createUserOnDb(@Path("id")String id,
            @Body CustomerInfo customerInfo);

    @PUT("customer/{id}/orders/{uid}.json")
    Call<CustomerInfo> updateOrder(@Path("id") String id,
            @Path("uid") String uid,
            @Body String orderNo);
}
