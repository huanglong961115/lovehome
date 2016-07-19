package com.example.lovehometown.model;

import java.util.List;

/**
 * Created by Administrator on 2016/7/19.
 */
public class PublishList {

    /**
     * name : 商品1
     */

    private List<NamesBean> names;

    public List<NamesBean> getNames() {
        return names;
    }

    public void setNames(List<NamesBean> names) {
        this.names = names;
    }

    public PublishList() {
    }

    public static class NamesBean {
        private String name;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        @Override
        public String toString() {
            return "NamesBean{" +
                    "name='" + name + '\'' +
                    '}';
        }
    }

    @Override
    public String toString() {
        return "PublishList{" +
                "names=" + names +
                '}';
    }
}
