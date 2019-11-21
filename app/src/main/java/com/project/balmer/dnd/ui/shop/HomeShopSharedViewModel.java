package com.project.balmer.dnd.ui.shop;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.project.balmer.dnd.Model.ShopInfo;
import com.project.balmer.dnd.repository.DataManager;

//This class was intended to be used in place of safe=args.
//Either way it is intended to share data between fragments.

public class HomeShopSharedViewModel extends ViewModel {

    private MutableLiveData<ShopInfo> shopInfo = new MutableLiveData<>();

    public LiveData<ShopInfo> getShop() {
        return shopInfo;
    }
}
