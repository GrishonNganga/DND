package com.project.balmer.dnd.ui.order;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.project.balmer.dnd.Model.OrderInfo;
import com.project.balmer.dnd.repository.DataManager;

import java.util.List;

import static com.project.balmer.dnd.repository.DataManager.getdm;

public class OrderViewModel extends ViewModel {

    private MutableLiveData<List<OrderInfo>> orders;
    private DataManager dataManager;

    public LiveData<List<OrderInfo>> getOrders(){
        return orders;
    }

    public void init(){
        if (orders != null){
            return;
        }
        dataManager = getdm();
        orders = new MutableLiveData<>();
        orders = dataManager.getOrders();
    }


}