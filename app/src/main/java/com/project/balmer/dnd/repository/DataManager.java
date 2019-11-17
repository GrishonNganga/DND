package com.project.balmer.dnd.repository;

import android.util.Log;

import androidx.lifecycle.MutableLiveData;

import com.project.balmer.dnd.Model.CustomerInfo;
import com.project.balmer.dnd.Model.GoodInfo;
import com.project.balmer.dnd.Model.OrderInfo;
import com.project.balmer.dnd.Model.ShopInfo;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class DataManager {
    private static DataManager dataManager;

    public static DataManager getdm(){
        if (dataManager == null) {
            dataManager = new DataManager();
        }
            return dataManager;
    }
    
    private ArrayList<OrderInfo> orders = new ArrayList<>();
    private Retrofit retrofit;
    
   //Method pulls data from the API.
    //Should always be used.
    public void getShops(final ShopCallBack shopCallBack){
        MutableLiveData <List<ShopInfo>> finalShopList = new MutableLiveData<>();
        retrofit = RetrofitClient.getInstance();
        FirebaseApi firebaseApi = retrofit.create(FirebaseApi.class);
        Call<List<ShopInfo>> shops = firebaseApi.getShops();
        shops.enqueue(new Callback<List<ShopInfo>>() {
            @Override
            public void onResponse(Call<List<ShopInfo>> call, Response<List<ShopInfo>> response) {
                if (!response.isSuccessful()) {
                    Log.e("Error code", String.valueOf(response.code()));
                }
                Log.e("Success", String.valueOf(response.code()));

                Log.e("Shops shop number code", String.valueOf(response.body().size()));
                //setData(response.body());
                shopCallBack.onSuccess(response.body());

            }

            @Override
            public void onFailure(Call<List<ShopInfo>> call, Throwable t) {
                //shops1.setValue(null);
                Log.e("Throwable Error", t.getMessage());
            }
        });
            //Log.e("Shops shop number code", String.valueOf(shopData.getValue().size()));
    }
    public interface ShopCallBack {
        void onSuccess(List<ShopInfo> shopInfos);

    }
    
    public void getGoodss(GoodCallBack goodCallBack){
        MutableLiveData <List<GoodInfo>> finalGoodList = new MutableLiveData<>();
        retrofit = RetrofitClient.getInstance();
        FirebaseApi firebaseApi = retrofit.create(FirebaseApi.class);
        Call<List<GoodInfo>> goods = firebaseApi.getGoods();
        goods.enqueue(new Callback<List<GoodInfo>>() {
            @Override
            public void onResponse(Call<List<GoodInfo>> call, Response<List<GoodInfo>> response) {
                if (!response.isSuccessful()) {
                    Log.e("Good Error code", String.valueOf(response.code()));
                }
                Log.e("Success", String.valueOf(response.code()));

                Log.e("Shops shop number code", String.valueOf(response.body().size()));
                //setData(response.body());
                goodCallBack.onSuccess(response.body());
            }

            @Override
            public void onFailure(Call<List<GoodInfo>> call, Throwable t) {
                Log.e("Good failure error", t.getMessage());
            }
        });
    }
    
    public interface GoodCallBack{
        void onSuccess(List<GoodInfo> goodInfos);
    }

    //Yet to be implemented
    public MutableLiveData<CustomerInfo> getCustomer() {
        MutableLiveData<CustomerInfo> customer = new MutableLiveData<>();
        return customer;
    }
    
    public MutableLiveData<List<OrderInfo>> getOrders(){
        orders.clear();
        MutableLiveData<List<OrderInfo>> ordersData = new MutableLiveData<>();
        ordersData.setValue(orders);
        return ordersData;
    }
}
class RetrofitClient {
    private static Retrofit ourInstance;

    public static Retrofit getInstance(){
        if (ourInstance == null){
            ourInstance = new Retrofit.Builder()
                    .baseUrl("https://dndfirebaseproject-313ab.firebaseio.com/" )
                    .addConverterFactory(GsonConverterFactory.create())
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                    .build();

        }
        return ourInstance;
    }
    public RetrofitClient(){}
}


