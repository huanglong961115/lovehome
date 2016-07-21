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
     * ReleaseList : {"publishName":"酒店","publishType":1,"publishTypename":"1a"}
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
        /**
         * publishName : 酒店
         * publishType : 1
         * publishTypename : 1a
         */

        private ReleaseListBean ReleaseList;

        public ReleaseListBean getReleaseList() {
            return ReleaseList;
        }

        public void setReleaseList(ReleaseListBean ReleaseList) {
            this.ReleaseList = ReleaseList;
        }

        public static class ReleaseListBean implements Parcelable {
            private String publishName;
            private int publishType;
            private String publishTypename;

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

            public String getPublishTypename() {
                return publishTypename;
            }

            public void setPublishTypename(String publishTypename) {
                this.publishTypename = publishTypename;
            }

            @Override
            public int describeContents() {
                return 0;
            }

            @Override
            public void writeToParcel(Parcel dest, int flags) {
                dest.writeString(this.publishName);
                dest.writeInt(this.publishType);
                dest.writeString(this.publishTypename);
            }

            public ReleaseListBean() {
            }

            protected ReleaseListBean(Parcel in) {
                this.publishName = in.readString();
                this.publishType = in.readInt();
                this.publishTypename = in.readString();
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

        @Override
        public int describeContents() {
            return 0;
        }

        @Override
        public void writeToParcel(Parcel dest, int flags) {
            dest.writeParcelable(this.ReleaseList, flags);
        }

        public PublistBean() {
        }

        protected PublistBean(Parcel in) {
            this.ReleaseList = in.readParcelable(ReleaseListBean.class.getClassLoader());
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

    public PublishList() {
    }

    protected PublishList(Parcel in) {
        this.Results = in.readParcelable(ResultsBean.class.getClassLoader());
        this.publist = new ArrayList<PublistBean>();
        in.readList(this.publist, PublistBean.class.getClassLoader());
    }

    public static final Parcelable.Creator<PublishList> CREATOR = new Parcelable.Creator<PublishList>() {
        @Override
        public PublishList createFromParcel(Parcel source) {
            return new PublishList(source);
        }

        @Override
        public PublishList[] newArray(int size) {
            return new PublishList[size];
        }
    };
}
