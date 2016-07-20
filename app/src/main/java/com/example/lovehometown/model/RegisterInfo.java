package com.example.lovehometown.model;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by Administrator on 2016/7/20.
 */
public class RegisterInfo implements Parcelable {

    /**
     * code : -3
     * msg : 验证码不能为空
     */

    private ResultsBean Results;

    public ResultsBean getResults() {
        return Results;
    }

    public void setResults(ResultsBean Results) {
        this.Results = Results;
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

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeParcelable(this.Results, flags);
    }

    public RegisterInfo() {
    }

    protected RegisterInfo(Parcel in) {
        this.Results = in.readParcelable(ResultsBean.class.getClassLoader());
    }

    public static final Parcelable.Creator<RegisterInfo> CREATOR = new Parcelable.Creator<RegisterInfo>() {
        @Override
        public RegisterInfo createFromParcel(Parcel source) {
            return new RegisterInfo(source);
        }

        @Override
        public RegisterInfo[] newArray(int size) {
            return new RegisterInfo[size];
        }
    };
}
