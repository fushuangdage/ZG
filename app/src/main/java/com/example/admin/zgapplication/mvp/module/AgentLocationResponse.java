package com.example.admin.zgapplication.mvp.module;

import java.util.List;

/**
 * Created by fushuang on 2018/1/26.
 */

public class AgentLocationResponse {


    /**
     * msg : 操作成功
     * code : 0
     * data : [{"lng":100.00083479,"lat":50.00026726},{"lng":100.00011107,"lat":50.00075982},{"lng":99.99964778,"lat":49.99963719},{"lng":100.00044498,"lat":49.99956528},{"lng":100.00030947,"lat":49.99965692},{"lng":100.00041767,"lat":49.99923127},{"lng":100.00099215,"lat":49.99936278},{"lng":100.00044687,"lat":50.00091281},{"lng":99.9996979,"lat":50.00070073}]
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
         * lng : 100.00083479
         * lat : 50.00026726
         */

        private double lng;
        private double lat;

        public double getLng() {
            return lng;
        }

        public void setLng(double lng) {
            this.lng = lng;
        }

        public double getLat() {
            return lat;
        }

        public void setLat(double lat) {
            this.lat = lat;
        }
    }
}
