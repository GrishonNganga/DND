package com.project.balmer.dnd.Model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ShopInfo implements Parcelable {
    @SerializedName("id")
    @Expose
    private String id;

    @SerializedName("name")
    @Expose
    private String name;

    @SerializedName("location")
    @Expose
    private String location;
    
    private List<GoodInfo> goodInfo = null;

    @SerializedName("image")
    @Expose
    private String image;


    protected ShopInfo(Parcel in) {
        id = in.readString();
        name = in.readString();
        location = in.readString();
        goodInfo = in.createTypedArrayList(GoodInfo.CREATOR);
        image = in.readString();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(id);
        dest.writeString(name);
        dest.writeString(location);
        dest.writeTypedList(goodInfo);
        dest.writeString(image);
    }

    @Override
    public int describeContents() {
        return 0;
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

    public List<GoodInfo> getGoodInfo() {
         return goodInfo;
    }

    public void setGoodInfo(List<GoodInfo> goodInfo) {
        this.goodInfo = goodInfo;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public ShopInfo(String id, String name, String location, List<GoodInfo> goodInfo, String image){
        this.id = id;
        this.name = name;
        this.location = location;
        this.goodInfo = goodInfo;
        this.image = image;

    }
}
