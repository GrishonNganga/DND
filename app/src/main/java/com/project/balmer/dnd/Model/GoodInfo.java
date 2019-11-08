package com.project.balmer.dnd.Model;

import android.os.Parcel;
import android.os.Parcelable;

public class GoodInfo implements Parcelable {
    private String id;
    private String name;
    private String description;
    private ShopInfo shopInfo;
    private String price;
    private String image;

    protected GoodInfo(Parcel in) {
        id = in.readString();
        name = in.readString();
        description = in.readString();
        shopInfo = in.readParcelable(ShopInfo.class.getClassLoader());
        price = in.readString();
        image = in.readString();
    }

    public static final Creator<GoodInfo> CREATOR = new Creator<GoodInfo>() {
        @Override
        public GoodInfo createFromParcel(Parcel in) {
            return new GoodInfo(in);
        }

        @Override
        public GoodInfo[] newArray(int size) {
            return new GoodInfo[size];
        }
    };

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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public ShopInfo getShopInfo() {
        return shopInfo;
    }

    public void setShopInfo(ShopInfo shopInfo) {
        this.shopInfo = shopInfo;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public GoodInfo(String goodId, String name, String description, ShopInfo shopInfo, String price, String image){
        this.id = goodId;
        this.name = name;
        this.description = description;
        this.shopInfo = shopInfo;
        this.price = price;
        this.image = image;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(id);
        parcel.writeString(name);
        parcel.writeString(description);
        parcel.writeParcelable(shopInfo, i);
        parcel.writeString(price);
        parcel.writeString(image);
    }
}
