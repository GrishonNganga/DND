package com.project.balmer.dnd.repository;

import android.app.Activity;
import android.util.Log;

import androidx.lifecycle.MutableLiveData;

import com.google.firebase.auth.FirebaseAuth;
import com.project.balmer.dnd.Model.CustomerInfo;
import com.project.balmer.dnd.Model.GoodInfo;
import com.project.balmer.dnd.Model.OrderInfo;
import com.project.balmer.dnd.Model.ShopInfo;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

import static com.project.balmer.dnd.SignUpActivity.firebaseAuth;

public class DataManager {
    private static DataManager dataManager;
    private static FirebaseApi firebaseApi;
    private static Retrofit retrofit;


    public static DataManager getdm(Activity activity){
        if (dataManager == null) {
            dataManager = new DataManager();
            retrofit = RetrofitClient.getInstance();
            firebaseApi = retrofit.create(FirebaseApi.class);

        }
            return dataManager;
    }

    private ArrayList<OrderInfo> orders = new ArrayList<>();

    public void createUserOnDb(CustomerInfo customerInfo, CreateUser createUser){
        Call<CustomerInfo> userOnDb = firebaseApi.createUserOnDb(firebaseAuth.getUid(), customerInfo);
        userOnDb.enqueue(new Callback<CustomerInfo>() {
            @Override
            public void onResponse(Call<CustomerInfo> call, Response<CustomerInfo> response) {
                if (!response.isSuccessful()){
                    Log.e("Create user error", String.valueOf(response.code()));
                    createUser.onSuccess(true);
                }
                else {
                    Log.e("Create user success", String.valueOf(response.code()));
                    createUser.onSuccess(true);
                }
            }
            @Override
            public void onFailure(Call<CustomerInfo> call, Throwable t) {

            }
        });

    }

    public interface CreateUser{
        void onSuccess(boolean done);
    }

   //Method pulls data from the API.
    //Should always be used.
    public void getShops(final ShopCallBack shopCallBack){
        if (FirebaseAuth.getInstance().getCurrentUser() == null){
            Log.e("User null error", FirebaseAuth.getInstance().getCurrentUser().getEmail());
        }
        Call<List<ShopInfo>> shops = firebaseApi.getShops();
        shops.enqueue(new Callback<List<ShopInfo>>() {
            @Override
            public void onResponse(Call<List<ShopInfo>> call, Response<List<ShopInfo>> response) {
                if (!response.isSuccessful()) {
                    Log.e("Get shops Error code", String.valueOf(response.code()));
                } else {
                    Log.e("Get shops success code", String.valueOf(response.body().size()));
                    //setData(response.body());
                    shopCallBack.onSuccess(response.body());

                }
            }
            @Override
            public void onFailure(Call<List<ShopInfo>> call, Throwable t) {
                //shops1.setValue(null);
                Log.e("Get Shops Error", t.getMessage());
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

    public void makeOrder(OrderInfo orderInfo){
        String UID = UUID.randomUUID().toString();
        Call<OrderInfo> orders = firebaseApi.makeOrder(UID, orderInfo);
        orders.enqueue(new Callback <OrderInfo>() {
            @Override
            public void onResponse(Call<OrderInfo> call, Response<OrderInfo> response) {
                if (!response.isSuccessful()) {
                    Log.e("Make Order Error code", String.valueOf(response.code()));
                }else {
                    Log.e("Make Order Success code", String.valueOf(response.code()));
                    Log.e("Order code", String.valueOf(response.body()));
                    // orderCallback.onSuccess(response.body());

                    Call<CustomerInfo> customerInfoCall = firebaseApi.updateOrder(firebaseAuth.getUid(),UID, UID);
                    customerInfoCall.enqueue(new Callback<CustomerInfo>() {
                        @Override
                        public void onResponse(Call<CustomerInfo> call, Response<CustomerInfo> response) {
                            if (!response.isSuccessful()){
                                Log.e("Update order Error", String.valueOf(response.code()));
                            }else {
                                Log.e("Update order success", String.valueOf(response.body()));
                            }

                        }

                        @Override
                        public void onFailure(Call<CustomerInfo> call, Throwable t) {

                        }
                    });
                }



            }

            @Override
            public void onFailure(Call<OrderInfo> call, Throwable t) {
                Log.e("Make Order failure", t.getMessage());
            }
        });
    }

}
class RetrofitClient {
    private static Retrofit ourInstance;

    public static Retrofit getInstance(){
        if (ourInstance == null){
            ourInstance = new Retrofit.Builder()
                    .baseUrl("https://dndfirebaseproject-313ab.firebaseio.com/")
                    .addConverterFactory(GsonConverterFactory.create())
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                    .build();

        }
        return ourInstance;
    }
    public RetrofitClient(){}
}


