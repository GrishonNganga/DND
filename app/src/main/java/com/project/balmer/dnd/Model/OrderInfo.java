package com.project.balmer.dnd.Model;

public class OrderInfo {
    private String orderNo;
    private CustomerInfo customerInfo;
    private GoodInfo goodInfo;

    public String getOrderNo() {
        return orderNo;
    }

    public void setOrderNo(String orderNo) {
        this.orderNo = orderNo;
    }

    public CustomerInfo getCustomerInfo() {
        return customerInfo;
    }

    public void setCustomerInfo(CustomerInfo customerInfo) {
        this.customerInfo = customerInfo;
    }

    public GoodInfo getGoodInfo() {
        return goodInfo;
    }

    public void setGoodInfo(GoodInfo goodInfo) {
        this.goodInfo = goodInfo;
    }

    public OrderInfo(String orderNo, CustomerInfo customerInfo, GoodInfo goodInfo){
        this.orderNo = orderNo;
        this.customerInfo = customerInfo;
        this.goodInfo = goodInfo;
    }
}
