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
    private static String amount;
    private DataManager dataManager;
    private List<OrderInfo> ordersUpdated;
    private static MutableLiveData<String> cash;

    public static LiveData<List<OrderInfo>> getOrders(){
        return orders;
    }

    public void init(){
        if (orders != null){
            return;
        }
        dataManager = getdm();
        orders = new MutableLiveData<>();
        cash = new MutableLiveData<>();
        orders = dataManager.getOrders();
        amount = "0";
        cash.setValue(amount);
    }

    public void addOrder(OrderInfo orderInfo){
        ordersUpdated = orders.getValue();
        int total = Integer.parseInt(cash.getValue());
        ordersUpdated.add(orderInfo);
        orders.setValue(ordersUpdated);

            String price = orderInfo.getGoodInfo().getPrice();
            int i = Integer.parseInt(price);
            total += i * Integer.parseInt(orderInfo.getQuantity());
        amount = String.valueOf(total);
       cash.setValue(amount);
    }

    public void removeOrder(OrderInfo orderInfo){
        int total = Integer.parseInt(cash.getValue());
        ordersUpdated = orders.getValue();
        ordersUpdated.remove(orderInfo);
        orders.setValue(ordersUpdated);

            String price = orderInfo.getGoodInfo().getPrice();
            int i = Integer.parseInt(price);
            total -= i;
        amount = String.valueOf(total);
        cash.setValue(amount);

    }
    public LiveData<String> totalAmount(){
        return cash;
    }


}