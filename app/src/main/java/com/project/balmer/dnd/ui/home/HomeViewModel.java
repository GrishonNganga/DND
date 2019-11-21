package com.project.balmer.dnd.ui.home;

import android.app.Activity;
import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.project.balmer.dnd.Model.ShopInfo;
import com.project.balmer.dnd.repository.DataManager;

import java.util.List;

import static com.project.balmer.dnd.repository.DataManager.getdm;

public class HomeViewModel extends ViewModel {

    private MutableLiveData<List<ShopInfo>> shopList;
    private List<ShopInfo> shops;
    private boolean isLoggedIn;
    private DataManager dataManager;

    public void init(Activity activity) {
        if (shopList != null) {
            return;
        }
        dataManager = getdm(activity);
        shopList = new MutableLiveData<>();
        dataManager.getShops(new DataManager.ShopCallBack() {
            @Override
            public void onSuccess(List<ShopInfo> shopInfos) {
                shopList.setValue(shopInfos);
                Log.e("Shop on VModel", String.valueOf(shopList.getValue().size()));
            }
        });
    }
    public MutableLiveData<Boolean> getLogInStatus() {
        MutableLiveData<Boolean> status = new MutableLiveData<>();
        status.setValue(isLoggedIn);
        return status;
    }

    public LiveData<List<ShopInfo>> getShops() {
        return shopList;
    }
}