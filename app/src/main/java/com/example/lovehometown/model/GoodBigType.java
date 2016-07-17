package com.example.lovehometown.model;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by Administrator on 2016/7/17.
 */
public class GoodBigType implements Parcelable {
    //id
    private int id;
    //名字
    private String name;
    //图片路径
    private int imgUrl;

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this.id);
        dest.writeString(this.name);
        dest.writeInt(this.imgUrl);
    }

    public GoodBigType() {
    }

    protected GoodBigType(Parcel in) {
        this.id = in.readInt();
        this.name = in.readString();
        this.imgUrl = in.readInt();
    }

    public static final Parcelable.Creator<GoodBigType> CREATOR = new Parcelable.Creator<GoodBigType>() {
        @Override
        public GoodBigType createFromParcel(Parcel source) {
            return new GoodBigType(source);
        }

        @Override
        public GoodBigType[] newArray(int size) {
            return new GoodBigType[size];
        }
    };

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getId() {
        return id;
    }

    @Override
    public String toString() {
        return "GoodBigType{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", imgUrl=" + imgUrl +
                '}';
    }

    public GoodBigType(int id, String name, int imgUrl) {
        this.id = id;
        this.name = name;
        this.imgUrl = imgUrl;
    }

    public void setId(int id) {

        this.id = id;
    }

    public int getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(int imgUrl) {
        this.imgUrl = imgUrl;
    }
}
