package com.project.balmer.dnd.ui.home;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.project.balmer.dnd.Model.ShopInfo;
import com.project.balmer.dnd.repository.DataManager;

import java.util.List;

import static com.project.balmer.dnd.repository.DataManager.getdm;

public class HomeViewModel extends ViewModel {

    private MutableLiveData<List<ShopInfo>> shops;
    private DataManager dataManager;

       public void init(){
        if (shops != null){
            return;
        }
        dataManager = getdm();
        shops = new MutableLiveData<>();
        shops = dataManager.getShops();
    }

    public LiveData<List<ShopInfo>>  getShops(){

        return shops;
    }
}