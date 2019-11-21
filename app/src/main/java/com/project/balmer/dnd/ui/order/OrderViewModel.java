package com.project.balmer.dnd.ui.order;

import android.app.Activity;
import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.project.balmer.dnd.Model.CustomerInfo;
import com.project.balmer.dnd.Model.GoodInfo;
import com.project.balmer.dnd.Model.OrderInfo;
import com.project.balmer.dnd.repository.DataManager;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import static com.project.balmer.dnd.SignUpActivity.firebaseAuth;
import static com.project.balmer.dnd.repository.DataManager.getdm;

public class OrderViewModel extends ViewModel {

    private static MutableLiveData<List<OrderInfo>> orders;
    private static String amount;
    private static DataManager dataManager;
    private List<OrderInfo> ordersUpdated;
    private static MutableLiveData<String> cash;
    private static OrderInfo order;

    public static LiveData<List<OrderInfo>> getOrders(){
        return orders;
    }

    public void init(Activity activity){
        if (orders != null){
            return;
        }
        dataManager = getdm(activity);
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
    public void removeAllOrders(){
        int total = Integer.parseInt(cash.getValue());
        ordersUpdated = orders.getValue();
        ordersUpdated.clear();
        orders.setValue(ordersUpdated);

        total = 0;
        amount = String.valueOf(total);
        cash.setValue(amount);

    }
    public LiveData<String> totalAmount(){
        return cash;
    }

    public void makeOrder() {
        List<GoodInfo> goods = new ArrayList<>();
        int price = 0;
        for (OrderInfo orderInfo : orders.getValue()) {
            goods.add(orderInfo.getGoodInfo());
            int unitPrice = Integer.parseInt(orderInfo.getGoodInfo().getPrice());
            int quantity = Integer.parseInt(orderInfo.getQuantity());
            price = price + (unitPrice * quantity);
        }
        //Come back here to add quantity
        order = new OrderInfo(firebaseAuth.getUid(), goods, String.valueOf(price));
        Log.e("Goods are", String.valueOf(order.getGoods().size()));
        Log.e("Price is ", order.getPrice());

        dataManager.makeOrder(order);
    }
}