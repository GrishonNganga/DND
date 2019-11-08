package com.project.balmer.dnd.ui.order;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.project.balmer.dnd.Model.OrderInfo;
import com.project.balmer.dnd.repository.DataManager;

import java.util.List;

import static com.project.balmer.dnd.repository.DataManager.getdm;

public class OrderViewModel extends ViewModel {

    private static MutableLiveData<List<OrderInfo>> orders;
    private DataManager dataManager;
    private List<OrderInfo> ordersUpdated;

    public static LiveData<List<OrderInfo>> getOrders(){
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
    public void addOrder(OrderInfo orderInfo){
        ordersUpdated = orders.getValue();
        ordersUpdated.add(orderInfo);
        orders.setValue(ordersUpdated);

    }

    public void removeOrder(OrderInfo orderInfo){
        ordersUpdated = orders.getValue();
        ordersUpdated.remove(orderInfo);
        orders.setValue(ordersUpdated);

    }


}