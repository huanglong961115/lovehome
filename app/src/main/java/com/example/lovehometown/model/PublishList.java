package com.example.lovehometown.model;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2016/7/19.
 */
public class PublishList implements Parcelable {

    /**
     * code : 1
     * msg : 获取成功
     */

    private ResultsBean Results;
    /**
     * ReleaseList : {"publishName":"酒店","publishType":1}
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

        @Override
        public String toString() {
            return "ResultsBean{" +
                    "code=" + code +
                    ", msg='" + msg + '\'' +
                    '}';
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

    public static class PublistBean {
        /**
         * publishName : 酒店
         * publishType : 1
         */

        private ReleaseListBean ReleaseList;

        @Override
        public String toString() {
            return "PublistBean{" +
                    "ReleaseList=" + ReleaseList +
                    '}';
        }

        public ReleaseListBean getReleaseList() {
            return ReleaseList;
        }

        public void setReleaseList(ReleaseListBean ReleaseList) {
            this.ReleaseList = ReleaseList;
        }

        public static class ReleaseListBean implements Parcelable {
            private String publishName;
            private int publishType;

            public String getPublishName() {
                return publishName;
            }

            public void setPublishName(String publishName) {
                this.publishName = publishName;
            }

            public int getPublishType() {
                return publishType;
            }

            public void setPublishType(int publishType) {
                this.publishType = publishType;
            }

            @Override
            public int describeContents() {
                return 0;
            }

            @Override
            public void writeToParcel(Parcel dest, int flags) {
                dest.writeString(this.publishName);
                dest.writeInt(this.publishType);
            }

            public ReleaseListBean() {
            }

            protected ReleaseListBean(Parcel in) {
                this.publishName = in.readString();
                this.publishType = in.readInt();
            }

            @Override
            public String toString() {
                return "ReleaseListBean{" +
                        "publishName='" + publishName + '\'' +
                        ", publishType=" + publishType +
                        '}';
            }

            public static final Creator<ReleaseListBean> CREATOR = new Creator<ReleaseListBean>() {
                @Override
                public ReleaseListBean createFromParcel(Parcel source) {
                    return new ReleaseListBean(source);
                }

                @Override
                public ReleaseListBean[] newArray(int size) {
                    return new ReleaseListBean[size];
                }
            };
        }
    }

    public PublishList() {
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

    protected PublishList(Parcel in) {
        this.Results = in.readParcelable(ResultsBean.class.getClassLoader());
        this.publist = new ArrayList<PublistBean>();
        in.readList(this.publist, PublistBean.class.getClassLoader());
    }

    public static final Creator<PublishList> CREATOR = new Creator<PublishList>() {
        @Override
        public PublishList createFromParcel(Parcel source) {
            return new PublishList(source);
        }

        @Override
        public PublishList[] newArray(int size) {
            return new PublishList[size];
        }
    };

    @Override
    public String toString() {
        return "PublishList{" +
                "Results=" + Results +
                ", publist=" + publist +
                '}';
    }
}
