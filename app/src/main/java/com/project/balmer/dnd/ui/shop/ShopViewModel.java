package com.project.balmer.dnd.ui.shop;

import android.app.Activity;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.project.balmer.dnd.Model.CustomerInfo;
import com.project.balmer.dnd.Model.GoodInfo;
import com.project.balmer.dnd.Model.OrderInfo;
import com.project.balmer.dnd.Model.ShopInfo;
import com.project.balmer.dnd.repository.DataManager;

import java.util.ArrayList;
import java.util.List;

import static com.project.balmer.dnd.repository.DataManager.getdm;


public class ShopViewModel extends ViewModel {

    private MutableLiveData<List<GoodInfo>> goods;
    private DataManager dataManager;
    private MutableLiveData<CustomerInfo> customerInfo;
    private MutableLiveData<String>  quantity = new MutableLiveData<>();

    public void init(Activity activity) {
        if (goods != null) {
            return;
        }
        dataManager = getdm(activity);
        goods = new MutableLiveData<>();
        dataManager.getGoodss(new DataManager.GoodCallBack() {
            @Override
            public void onSuccess(List<GoodInfo> goodInfos) {
                goods.setValue(goodInfos);
            }
        });
        customerInfo = dataManager.getCustomer();
    }

    public LiveData<List<GoodInfo>> getGoodss(){
        return goods;
    }

    //Used in ShopOrder
    public LiveData<CustomerInfo> getCustomer(){
        customerInfo = new MutableLiveData<>();
        return customerInfo;
    }

    //Used in ShopOrder
    public void initQuantity(){
        quantity.setValue("0");
    }

    public LiveData<String> getQuantity(){
        //quantity.setValue("0");
        return quantity;
    }

    //Used in ShopOrder
    public void addQuantity(){
        String quant = quantity.getValue();
        int hold = Integer.parseInt(quant);
        hold = hold +1;

        quantity.setValue(String.valueOf(hold));

    }

    //Used in ShopOPder
    public void removeQuantity(){
        String quant = quantity.getValue();
        int hold = Integer.parseInt(quant);
            if (hold > 0){
                hold = hold - 1;
            }

        quantity.setValue(String.valueOf(hold));

    }

}
