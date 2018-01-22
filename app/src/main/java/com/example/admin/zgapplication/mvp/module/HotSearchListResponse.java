package com.example.admin.zgapplication.mvp.module;

import java.util.List;

/**
 * Created by fushuang on 2018/1/15.
 */

public class HotSearchListResponse {

    /**
     * msg : 操作成功
     * code : 0
     * data : [{"company_id":"1","company_name":"扎根公寓"},{"company_id":"2","company_name":"链家公寓"}]
     */

    private String msg;
    private int code;
    private List<DataBean> data;

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * company_id : 1
         * company_name : 扎根公寓
         */

        private String company_id;
        private String company_name;

        public String getCompany_id() {
            return company_id;
        }

        public void setCompany_id(String company_id) {
            this.company_id = company_id;
        }

        public String getCompany_name() {
            return company_name;
        }

        public void setCompany_name(String company_name) {
            this.company_name = company_name;
        }
    }
}
