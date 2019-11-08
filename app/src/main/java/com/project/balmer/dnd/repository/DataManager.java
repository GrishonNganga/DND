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
    ShopInfo shopInfo = new ShopInfo("0001", "Safari Park", "Kasarani", null, "food6.jpg");
    GoodInfo goodInfo = new GoodInfo("0001","Pizza", "The best pizza you have ever tasted", shopInfo, "Ksh 1500", "food3");
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
        goods.add(new GoodInfo("001", "Cassava", "Nduma imewekwa kwa mafuta", null, "Ksh 1200", "food5"));

        goods.add(new GoodInfo("002", "Chips", "Best chips you will ever taste my friend", null, "Ksh 1000", "food7"));

        goods.add(new GoodInfo("003", "Tea", "Chai moto ya maziwa", null, "Ksh 500", "food8"));

        goods.add(new GoodInfo("004", "Soup", "Cow soup", null, "Ksh 300", "food11"));

        goods.add(new GoodInfo("005", "Chicken", "Grilled chicken. Zile za pono", null, "Ksh 600", "food9"));

        goods.add(new GoodInfo("006", "Kebab", "Very sweet kebab", null, "Ksh 100", "food10"));


    }

    private void setShops(){
        shops.add(new ShopInfo("0002", "Kempinsky", "Nairobi", goodInfo, "food1"));

        shops.add(new ShopInfo("00004", "NewsCafe", "Nairobi", goodInfo, "food2"));

        shops.add(new ShopInfo("0005", "Kibandansky", "Westlands", goodInfo, "food3"));

        shops.add(new ShopInfo("0006", "Chipotle", "Wisconsin", goodInfo, "food4"));

        shops.add(new ShopInfo("0007", "Pizza Inn", "Town CBD", null, "food5"));
    }

    private void setOrders(){
        orders.add(new OrderInfo("001", customerInfo, goodInfo, null));
        orders.add(new OrderInfo("002", customerInfo, goodInfo, null));
        orders.add(new OrderInfo("003", customerInfo, goodInfo, null));
        orders.add(new OrderInfo("004", customerInfo, goodInfo, null));
        orders.add(new OrderInfo("005", customerInfo, goodInfo, null));
        orders.add(new OrderInfo("006", customerInfo, goodInfo, null));
    }

}


