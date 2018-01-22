package com.example.admin.zgapplication.mvp.module;

/**
 * Created by fushuang on 2018/1/19.
 */

public class GenerateOrderResponse {

    /**
     * msg : 操作成功
     * code : 0
     * data : {"uid":"33","order_id":"1427"}
     */

    private String msg;
    private int code;
    private DataBean data;

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

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * uid : 33
         * order_id : 1427
         */

        private int uid;
        private int order_id;

        public int getUid() {
            return uid;
        }

        public void setUid(int uid) {
            this.uid = uid;
        }

        public int getOrder_id() {
            return order_id;
        }

        public void setOrder_id(int order_id) {
            this.order_id = order_id;
        }
    }
}
