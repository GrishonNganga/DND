package com.project.balmer.dnd.repository;

import com.project.balmer.dnd.Model.GoodInfo;
import com.project.balmer.dnd.Model.ShopInfo;

import java.util.List;


import io.reactivex.Observable;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface FirebaseApi {

    @GET("shop.json")
    Call<List<ShopInfo>> getShops();

    @GET("good/.json")
    Call<List<GoodInfo>> getGoods();
}
