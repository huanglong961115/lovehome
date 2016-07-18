package com.example.lovehometown.model;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by Administrator on 2016/7/18.
 */
public class ShopInfo implements Parcelable {
    private int id;
    private int shopImage;
    private String name;
    private String price;
    private String time;
    private String phone;
    private String info;
    private String linkMan;

    public String getLinkMan() {
        return linkMan;
    }

    public void setLinkMan(String linkMan) {
        this.linkMan = linkMan;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public int getShopImage() {
        return shopImage;
    }

    public void setShopImage(int shopImage) {
        this.shopImage = shopImage;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public int describeContents() {

        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this.id);
        dest.writeInt(this.shopImage);
        dest.writeString(this.name);
        dest.writeString(this.price);
        dest.writeString(this.time);
        dest.writeString(this.phone);
        dest.writeString(this.info);
        dest.writeString(this.linkMan);
    }

    public ShopInfo() {
    }

    protected ShopInfo(Parcel in) {
        this.id = in.readInt();
        this.shopImage = in.readInt();
        this.name = in.readString();
        this.price = in.readString();
        this.time = in.readString();
        this.phone = in.readString();
        this.info = in.readString();
        this.linkMan = in.readString();
    }

    public static final Parcelable.Creator<ShopInfo> CREATOR = new Parcelable.Creator<ShopInfo>() {
        @Override
        public ShopInfo createFromParcel(Parcel source) {
            return new ShopInfo(source);
        }

        @Override
        public ShopInfo[] newArray(int size) {
            return new ShopInfo[size];
        }
    };

    public ShopInfo(int id, int shopImage, String name, String price, String time, String phone, String info, String linkMan) {
        this.id = id;
        this.shopImage = shopImage;
        this.name = name;
        this.price = price;
        this.time = time;
        this.phone = phone;
        this.info = info;
        this.linkMan = linkMan;
    }
}
