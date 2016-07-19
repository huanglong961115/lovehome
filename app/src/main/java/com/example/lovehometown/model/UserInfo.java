package com.example.lovehometown.model;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by Administrator on 2016/7/19.
 */
public class UserInfo implements Parcelable {

    /**
     * code : 1
     * msg : success
     */

    private ResultsBean Results;
    /**
     * headImg :
     * phoneNuber : 18074434526
     * userAddress :
     * username :
     */

    private UserBean User;

    public ResultsBean getResults() {
        return Results;
    }

    public void setResults(ResultsBean Results) {
        this.Results = Results;
    }

    public UserBean getUser() {
        return User;
    }

    public void setUser(UserBean User) {
        this.User = User;
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

    public static class UserBean implements Parcelable {
        private String headImg;
        private String phoneNuber;
        private String userAddress;
        private String username;

        public String getHeadImg() {
            return headImg;
        }

        public void setHeadImg(String headImg) {
            this.headImg = headImg;
        }

        public String getPhoneNuber() {
            return phoneNuber;
        }

        public void setPhoneNuber(String phoneNuber) {
            this.phoneNuber = phoneNuber;
        }

        public String getUserAddress() {
            return userAddress;
        }

        public void setUserAddress(String userAddress) {
            this.userAddress = userAddress;
        }

        public String getUsername() {
            return username;
        }

        public void setUsername(String username) {
            this.username = username;
        }

        @Override
        public int describeContents() {
            return 0;
        }

        @Override
        public void writeToParcel(Parcel dest, int flags) {
            dest.writeString(this.headImg);
            dest.writeString(this.phoneNuber);
            dest.writeString(this.userAddress);
            dest.writeString(this.username);
        }

        public UserBean() {
        }

        protected UserBean(Parcel in) {
            this.headImg = in.readString();
            this.phoneNuber = in.readString();
            this.userAddress = in.readString();
            this.username = in.readString();
        }

        public static final Creator<UserBean> CREATOR = new Creator<UserBean>() {
            @Override
            public UserBean createFromParcel(Parcel source) {
                return new UserBean(source);
            }

            @Override
            public UserBean[] newArray(int size) {
                return new UserBean[size];
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
        dest.writeParcelable(this.User, flags);
    }

    public UserInfo() {
    }

    protected UserInfo(Parcel in) {
        this.Results = in.readParcelable(ResultsBean.class.getClassLoader());
        this.User = in.readParcelable(UserBean.class.getClassLoader());
    }

    public static final Parcelable.Creator<UserInfo> CREATOR = new Parcelable.Creator<UserInfo>() {
        @Override
        public UserInfo createFromParcel(Parcel source) {
            return new UserInfo(source);
        }

        @Override
        public UserInfo[] newArray(int size) {
            return new UserInfo[size];
        }
    };
}
