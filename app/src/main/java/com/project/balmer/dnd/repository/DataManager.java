package com.project.balmer.dnd.repository;
import androidx.lifecycle.MutableLiveData;

import com.project.balmer.dnd.Model.CustomerInfo;
import com.project.balmer.dnd.Model.GoodInfo;
import com.project.balmer.dnd.Model.OrderInfo;
import com.project.balmer.dnd.Model.ShopInfo;
import java.util.ArrayList;
import java.util.List;

public class DataManager {
    private static DataManager dataManager;

    public static DataManager getdm(){
        if (dataManager == null)
            dataManager = new DataManager();
            return dataManager;

    }
    CustomerInfo customerInfo = new CustomerInfo("001", "Grishon Gikima", "0729400426", null);
    ShopInfo shopInfo = new ShopInfo("0001", "Safari Park", "Kasarani", null);
    GoodInfo goodInfo = new GoodInfo("0001","Pizza", "The best pizza you have ever tasted", shopInfo, "1500");
    private FirebaseUtil firebaseUtil;

    private ArrayList<ShopInfo> shops = new ArrayList<>();
    private ArrayList<GoodInfo> goods = new ArrayList<>();
    private ArrayList<OrderInfo> orders = new ArrayList<>();

    public MutableLiveData<List<ShopInfo>> getShops(){
        shops.clear();
        setShops();

        MutableLiveData<List<ShopInfo>> shopsData = new MutableLiveData<>();
        shopsData.setValue(shops);

        return shopsData;
    }
    public MutableLiveData<List<GoodInfo>> getGoods(){
        goods.clear();
        setGoods();
        MutableLiveData<List<GoodInfo>> goodsData  = new MutableLiveData<>();
        goodsData.setValue(goods);

        return goodsData;
    }

    public MutableLiveData<CustomerInfo> getCustomer() {
        MutableLiveData<CustomerInfo> customer = new MutableLiveData<>();
        customer.setValue(customerInfo);
        return customer;
    }
    public MutableLiveData<List<OrderInfo>> getOrders(){
        orders.clear();
        setOrders();
        MutableLiveData<List<OrderInfo>> ordersData = new MutableLiveData<>();
        ordersData.setValue(orders);
        return ordersData;
    }

    private void setGoods() {
        goods.add(new GoodInfo("001", "Cassava", "Nduma imewekwa kwa mafuta", null, "1200"));

        goods.add(new GoodInfo("002", "Chips", "Best chips you will ever taste my friend", null, "1000"));

        goods.add(new GoodInfo("003", "Tea", "Chai moto ya maziwa", null, "500"));

        goods.add(new GoodInfo("004", "Soup", "Cow soup", null, "300"));

        goods.add(new GoodInfo("005", "Chicken", "Grilled chicken. Zile za pono", null, "600"));

        goods.add(new GoodInfo("006", "Kebab", "Very sweet kebab", null, "100"));


    }

    private void setShops(){
        shops.add(new ShopInfo("0002", "Kempinsky", "Nairobi", goodInfo));

        shops.add(new ShopInfo("00004", "NewsCafe", "Nairobi", goodInfo));

        shops.add(new ShopInfo("0005", "Kibandansky", "Westlands", goodInfo));

        shops.add(new ShopInfo("0006", "Chipotle", "Wisconsin", goodInfo));

        shops.add(new ShopInfo("0007", "Pizza Inn", "Town CBD", null));
    }

    private void setOrders(){
        orders.add(new OrderInfo("001", customerInfo, goodInfo));
        orders.add(new OrderInfo("002", customerInfo, goodInfo));
        orders.add(new OrderInfo("003", customerInfo, goodInfo));
        orders.add(new OrderInfo("004", customerInfo, goodInfo));
        orders.add(new OrderInfo("005", customerInfo, goodInfo));
        orders.add(new OrderInfo("006", customerInfo, goodInfo));
    }

}


