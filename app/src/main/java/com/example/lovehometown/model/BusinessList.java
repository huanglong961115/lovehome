package com.example.lovehometown.model;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2016/7/21.
 */
public class BusinessList implements Parcelable {

    /**
     * code : 1
     * msg : 获取成功
     */

    private ResultsBean Results;
    /**
     * businessAddress : 东路90号
     * businessDetails : 花艺生活馆
     * businessEndtime :
     * businessId : 11
     * businessLinkman : 店家
     * businessMement : 0
     * businessName : 花百度花艺
     * businessPhone : 18257710155
     * businessPrice : 0
     * businessStarttime :
     * childType : 3a
     * istakeaway : 0
     * publishImg :
     * takeawayEnd :
     * takeawayFee :
     * takeawayStart :
     * xiangxifenlei : 酒店
     * type : 5
     * workSalary :
     * workTitle :
     */

    private List<PublistBean> publist;

    public ResultsBean getResults() {
        return Results;
    }

    public void setResults(ResultsBean Results) {
        this.Results = Results;
    }

    public List<PublistBean> getPublist() {
        return publist;
    }

    public void setPublist(List<PublistBean> publist) {
        this.publist = publist;
    }

    public static class ResultsBean implements Parcelable {
        private int code;
        private String msg;

        public int getCode() {
            return code;
        }

        public void setCode(int code) {
            this.code = code;
        }

        public String getMsg() {
            return msg;
        }

        public void setMsg(String msg) {
            this.msg = msg;
        }

        @Override
        public int describeContents() {
            return 0;
        }

        @Override
        public void writeToParcel(Parcel dest, int flags) {
            dest.writeInt(this.code);
            dest.writeString(this.msg);
        }

        public ResultsBean() {
        }

        protected ResultsBean(Parcel in) {
            this.code = in.readInt();
            this.msg = in.readString();
        }

        public static final Creator<ResultsBean> CREATOR = new Creator<ResultsBean>() {
            @Override
            public ResultsBean createFromParcel(Parcel source) {
                return new ResultsBean(source);
            }

            @Override
            public ResultsBean[] newArray(int size) {
                return new ResultsBean[size];
            }
        };
    }

    public static class PublistBean implements Parcelable {
        private String businessAddress;
        private String businessDetails;
        private String businessEndtime;
        private int businessId;
        private String businessLinkman;
        private String businessMement;
        private String businessName;
        private String businessPhone;
        private String businessPrice;
        private String businessStarttime;
        private String childType;
        private int istakeaway;
        private String publishImg;
        private String takeawayEnd;
        private String takeawayFee;
        private String takeawayStart;
        private String xiangxifenlei;
        private String type;
        private String workSalary;
        private String workTitle;

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

        @Override
        public int describeContents() {
            return 0;
        }

        @Override
        public void writeToParcel(Parcel dest, int flags) {
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
        }

        public PublistBean() {
        }

        protected PublistBean(Parcel in) {
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
        }

        public static final Creator<PublistBean> CREATOR = new Creator<PublistBean>() {
            @Override
            public PublistBean createFromParcel(Parcel source) {
                return new PublistBean(source);
            }

            @Override
            public PublistBean[] newArray(int size) {
                return new PublistBean[size];
            }
        };
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeParcelable(this.Results, flags);
        dest.writeList(this.publist);
    }

    public BusinessList() {
    }

    protected BusinessList(Parcel in) {
        this.Results = in.readParcelable(ResultsBean.class.getClassLoader());
        this.publist = new ArrayList<PublistBean>();
        in.readList(this.publist, PublistBean.class.getClassLoader());
    }

    public static final Parcelable.Creator<BusinessList> CREATOR = new Parcelable.Creator<BusinessList>() {
        @Override
        public BusinessList createFromParcel(Parcel source) {
            return new BusinessList(source);
        }

        @Override
        public BusinessList[] newArray(int size) {
            return new BusinessList[size];
        }
    };
}
