package com.project.balmer.dnd.Model;

import android.os.Parcel;
import android.os.Parcelable;

public class ShopInfo implements Parcelable {
    private String id;
    private String name;
    private String location;
    private GoodInfo goodInfo;
    private String image;

    protected ShopInfo(Parcel in) {
        id = in.readString();
        name = in.readString();
        location = in.readString();
        image = in.readString();
    }

    public static final Creator<ShopInfo> CREATOR = new Creator<ShopInfo>() {
        @Override
        public ShopInfo createFromParcel(Parcel in) {
            return new ShopInfo(in);
        }

        @Override
        public ShopInfo[] newArray(int size) {
            return new ShopInfo[size];
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

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public GoodInfo getGoodInfo() {
        return goodInfo;
    }

    public void setGoodInfo(GoodInfo goodInfo) {
        this.goodInfo = goodInfo;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public ShopInfo(String id, String name, String location, GoodInfo goodInfo, String image){
        this.id = id;
        this.name = name;
        this.location = location;
        this.goodInfo = goodInfo;
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
        parcel.writeString(location);
        parcel.writeString(image);
    }
}
