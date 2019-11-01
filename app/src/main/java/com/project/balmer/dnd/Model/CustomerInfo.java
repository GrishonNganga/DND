package com.project.balmer.dnd.Model;

public class CustomerInfo {
    private String id;
    private String name;
    private String phone;
    private OrderInfo orderInfo;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public OrderInfo getOrderInfo() {
        return orderInfo;
    }

    public void setOrderInfo(OrderInfo orderInfo) {
        this.orderInfo = orderInfo;
    }

    public CustomerInfo(String id, String name, String phone, OrderInfo orderInfo){
        this.id = id;
        this.name = name;
        this.phone = phone;
        this.orderInfo = orderInfo;
    }


}
