package com.project.balmer.dnd.ui.shop;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.project.balmer.dnd.Model.ShopInfo;
import com.project.balmer.dnd.repository.DataManager;



public class HomeShopSharedViewModel extends ViewModel {

    private MutableLiveData<ShopInfo> shopInfo = new MutableLiveData<>();

    public LiveData<ShopInfo> getShop() {
        return shopInfo;
    }
}
