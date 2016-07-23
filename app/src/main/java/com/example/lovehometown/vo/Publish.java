package com.example.lovehometown.vo;

import android.os.Parcel;
import android.os.Parcelable;

import org.xutils.db.annotation.Column;
import org.xutils.db.annotation.Table;

/**
 * Created by Administrator on 2016/7/22.
 */
@Table(name = "t_publish")
public class Publish implements Parcelable {
    @Column(name="publishid", isId=true,autoGen = true)
    private int loveId;
    @Column(name="usermobile")
    private String userMobile;
    @Column(name="publishorlove")
    private int publishorLove;
    @Column(name="businessaddress")
    private String businessAddress;
    @Column(name="businessdetails")
    private String businessDetails;
    @Column(name="businessendtime")
    private String businessEndtime;
    @Column(name="businessid")
    private int businessId;
    @Column(name="businesslinkman")
    private String businessLinkman;
    @Column(name="businessmement")
    private String businessMement;
    @Column(name="businessname")
    private String businessName;
    @Column(name="businessphone")
    private String businessPhone;
    @Column(name="businessprice")
    private String businessPrice;
    @Column(name="businessstarttime")
    private String businessStarttime;
    @Column(name="childtype")
    private String childType;
    @Column(name="istakeaway")
    private int istakeaway;
    @Column(name="publishimg")
    private String publishImg;
    @Column(name="takeawayend")
    private String takeawayEnd;
    @Column(name="takeawayfee")
    private String takeawayFee;
    @Column(name="takeawaystart")
    private String takeawayStart;
    @Column(name="xiangxifenlei")
    private String xiangxifenlei;
    @Column(name="type")
    private String type;
    @Column(name="worksalary")
    private String workSalary;
    @Column(name="worktitle")
    private String workTitle;

    public String getBigTypeName() {
        return bigTypeName;
    }

    public void setBigTypeName(String bigTypeName) {
        this.bigTypeName = bigTypeName;
    }

    @Column(name = "bigtypename")

    private String bigTypeName;

    public int getLoveId() {
        return loveId;
    }

    public void setLoveId(int loveId) {
        this.loveId = loveId;
    }

    public String getUserMobile() {
        return userMobile;
    }

    public void setUserMobile(String userMobile) {
        this.userMobile = userMobile;
    }

    public int getPublishorLove() {
        return publishorLove;
    }

    public void setPublishorLove(int publishorLove) {
        this.publishorLove = publishorLove;
    }

    public String getBusinessAddress() {
        return businessAddress;
    }

    public void setBusinessAddress(String businessAddress) {
        this.businessAddress = businessAddress;
    }

    public String getBusinessDetails() {
        return businessDetails;
    }

    public void setBusinessDetails(String businessDetails) {
        this.businessDetails = businessDetails;
    }

    public String getBusinessEndtime() {
        return businessEndtime;
    }

    public void setBusinessEndtime(String businessEndtime) {
        this.businessEndtime = businessEndtime;
    }

    public int getBusinessId() {
        return businessId;
    }

    public void setBusinessId(int businessId) {
        this.businessId = businessId;
    }

    public String getBusinessLinkman() {
        return businessLinkman;
    }

    public void setBusinessLinkman(String businessLinkman) {
        this.businessLinkman = businessLinkman;
    }

    public String getBusinessMement() {
        return businessMement;
    }

    public void setBusinessMement(String businessMement) {
        this.businessMement = businessMement;
    }

    public String getBusinessName() {
        return businessName;
    }

    public void setBusinessName(String businessName) {
        this.businessName = businessName;
    }

    public String getBusinessPhone() {
        return businessPhone;
    }

    public void setBusinessPhone(String businessPhone) {
        this.businessPhone = businessPhone;
    }

    public String getBusinessPrice() {
        return businessPrice;
    }

    public void setBusinessPrice(String businessPrice) {
        this.businessPrice = businessPrice;
    }

    public String getBusinessStarttime() {
        return businessStarttime;
    }

    public void setBusinessStarttime(String businessStarttime) {
        this.businessStarttime = businessStarttime;
    }

    public String getChildType() {
        return childType;
    }

    public void setChildType(String childType) {
        this.childType = childType;
    }

    public int getIstakeaway() {
        return istakeaway;
    }

    public void setIstakeaway(int istakeaway) {
        this.istakeaway = istakeaway;
    }

    public String getPublishImg() {
        return publishImg;
    }

    public void setPublishImg(String publishImg) {
        this.publishImg = publishImg;
    }

    public String getTakeawayEnd() {
        return takeawayEnd;
    }

    public void setTakeawayEnd(String takeawayEnd) {
        this.takeawayEnd = takeawayEnd;
    }

    public String getTakeawayFee() {
        return takeawayFee;
    }

    public void setTakeawayFee(String takeawayFee) {
        this.takeawayFee = takeawayFee;
    }

    public String getTakeawayStart() {
        return takeawayStart;
    }

    public void setTakeawayStart(String takeawayStart) {
        this.takeawayStart = takeawayStart;
    }

    public String getXiangxifenlei() {
        return xiangxifenlei;
    }

    public void setXiangxifenlei(String xiangxifenlei) {
        this.xiangxifenlei = xiangxifenlei;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getWorkSalary() {
        return workSalary;
    }

    public void setWorkSalary(String workSalary) {
        this.workSalary = workSalary;
    }

    public String getWorkTitle() {
        return workTitle;
    }

    public void setWorkTitle(String workTitle) {
        this.workTitle = workTitle;
    }

    public Publish() { super();}

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this.loveId);
        dest.writeString(this.userMobile);
        dest.writeInt(this.publishorLove);
        dest.writeString(this.businessAddress);
        dest.writeString(this.businessDetails);
        dest.writeString(this.businessEndtime);
        dest.writeInt(this.businessId);
        dest.writeString(this.businessLinkman);
        dest.writeString(this.businessMement);
        dest.writeString(this.businessName);
        dest.writeString(this.businessPhone);
        dest.writeString(this.businessPrice);
        dest.writeString(this.businessStarttime);
        dest.writeString(this.childType);
        dest.writeInt(this.istakeaway);
        dest.writeString(this.publishImg);
        dest.writeString(this.takeawayEnd);
        dest.writeString(this.takeawayFee);
        dest.writeString(this.takeawayStart);
        dest.writeString(this.xiangxifenlei);
        dest.writeString(this.type);
        dest.writeString(this.workSalary);
        dest.writeString(this.workTitle);
        dest.writeString(this.bigTypeName);
    }

    protected Publish(Parcel in) {
        this.loveId = in.readInt();
        this.userMobile = in.readString();
        this.publishorLove = in.readInt();
        this.businessAddress = in.readString();
        this.businessDetails = in.readString();
        this.businessEndtime = in.readString();
        this.businessId = in.readInt();
        this.businessLinkman = in.readString();
        this.businessMement = in.readString();
        this.businessName = in.readString();
        this.businessPhone = in.readString();
        this.businessPrice = in.readString();
        this.businessStarttime = in.readString();
        this.childType = in.readString();
        this.istakeaway = in.readInt();
        this.publishImg = in.readString();
        this.takeawayEnd = in.readString();
        this.takeawayFee = in.readString();
        this.takeawayStart = in.readString();
        this.xiangxifenlei = in.readString();
        this.type = in.readString();
        this.workSalary = in.readString();
        this.workTitle = in.readString();
        this.bigTypeName = in.readString();
    }

    public static final Creator<Publish> CREATOR = new Creator<Publish>() {
        @Override
        public Publish createFromParcel(Parcel source) {
            return new Publish(source);
        }

        @Override
        public Publish[] newArray(int size) {
            return new Publish[size];
        }
    };

    @Override
    public String toString() {
        return "Publish{" +
                "loveId='" + loveId + '\'' +
                ", userMobile='" + userMobile + '\'' +
                ", publishorLove=" + publishorLove +
                ", businessAddress='" + businessAddress + '\'' +
                ", businessDetails='" + businessDetails + '\'' +
                ", businessEndtime='" + businessEndtime + '\'' +
                ", businessId=" + businessId +
                ", businessLinkman='" + businessLinkman + '\'' +
                ", businessMement='" + businessMement + '\'' +
                ", businessName='" + businessName + '\'' +
                ", businessPhone='" + businessPhone + '\'' +
                ", businessPrice='" + businessPrice + '\'' +
                ", businessStarttime='" + businessStarttime + '\'' +
                ", childType='" + childType + '\'' +
                ", istakeaway=" + istakeaway +
                ", publishImg='" + publishImg + '\'' +
                ", takeawayEnd='" + takeawayEnd + '\'' +
                ", takeawayFee='" + takeawayFee + '\'' +
                ", takeawayStart='" + takeawayStart + '\'' +
                ", xiangxifenlei='" + xiangxifenlei + '\'' +
                ", type='" + type + '\'' +
                ", workSalary='" + workSalary + '\'' +
                ", workTitle='" + workTitle + '\'' +
                ", bigTypeName='" + bigTypeName + '\'' +
                '}';
    }
}
