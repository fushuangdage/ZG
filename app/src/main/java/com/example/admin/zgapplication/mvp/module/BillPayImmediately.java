package com.example.admin.zgapplication.mvp.module;

/**
 * Created by fushuang on 2018/1/29.
 */

public class BillPayImmediately {

    /**
     * msg : 操作成功
     * code : 0
     * data : {"water_num":"life201801270713553723533856","payment":"460.00"}
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
         * water_num : life201801270713553723533856
         * payment : 460.00
         */

        private String water_num;
        private String payment;

        public String getWater_num() {
            return water_num;
        }

        public void setWater_num(String water_num) {
            this.water_num = water_num;
        }

        public String getPayment() {
            return payment;
        }

        public void setPayment(String payment) {
            this.payment = payment;
        }
    }
}
