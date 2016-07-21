package com.example.lovehometown.model;

import java.util.List;

/**
 * Created by Administrator on 2016/7/19.
 */
public class PublishList {


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

    public static class ResultsBean {
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
    }

    public static class PublistBean {
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

        public static class ReleaseListBean {
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
        }
    }
}
