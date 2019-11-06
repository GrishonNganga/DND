package com.project.balmer.dnd.ui.shop;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.project.balmer.dnd.Model.CustomerInfo;
import com.project.balmer.dnd.Model.GoodInfo;
import com.project.balmer.dnd.Model.ShopInfo;
import com.project.balmer.dnd.repository.DataManager;

import java.util.ArrayList;
import java.util.List;

import static com.project.balmer.dnd.repository.DataManager.getdm;


public class ShopViewModel extends ViewModel {

    private MutableLiveData<List<GoodInfo>> goods;
    private DataManager dataManager;
    private MutableLiveData<CustomerInfo> customerInfo;
    private MutableLiveData<String>  quantity;
    private MutableLiveData<String> shopImage;

    public LiveData<List<GoodInfo>> getGoods(){

        return goods;
    }

    public void init() {
        if (goods != null) {
            return;
        }
        dataManager = getdm();
        goods = new MutableLiveData<>();
        goods = dataManager.getGoods();
        customerInfo = dataManager.getCustomer();
    }

    public LiveData<CustomerInfo> getCustomer(){
        customerInfo = new MutableLiveData<>();
        return customerInfo;
    }

    public LiveData<String> getQuantity(){
        quantity = new MutableLiveData<>();
        quantity.setValue("0");
        return quantity;
    }
    public void addQuantity(){
        String quant = quantity.getValue();
        int hold = Integer.parseInt(quant);
        hold = hold +1;

        quantity.setValue(String.valueOf(hold));

    }
    public void removeQuantity(){
        String quant = quantity.getValue();
        int hold = Integer.parseInt(quant);
            if (hold > 0){
                hold = hold - 1;
            }

        quantity.setValue(String.valueOf(hold));

    }
    public LiveData<String> getImage(int i){
        MutableLiveData<List<ShopInfo>> shops = dataManager.getShops();
        List<ShopInfo> shop = shops.getValue();
        ShopInfo shopInfo = shop.get(i);
        shopImage = new MutableLiveData<>();
        shopImage.setValue(shopInfo.getImage());
        return shopImage;
    }

}
