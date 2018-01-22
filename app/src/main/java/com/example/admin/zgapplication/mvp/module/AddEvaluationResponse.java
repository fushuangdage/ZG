package com.example.admin.zgapplication.mvp.module;

/**
 * Created by fushuang on 2018/1/22.
 */

public class AddEvaluationResponse {

    /**
     * msg : 操作成功
     * code : 0
     * data : {"single_id":"1396","method":"1"}
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
         * single_id : 1396
         * method : 1
         */

        private String single_id;
        private String method;

        public String getSingle_id() {
            return single_id;
        }

        public void setSingle_id(String single_id) {
            this.single_id = single_id;
        }

        public String getMethod() {
            return method;
        }

        public void setMethod(String method) {
            this.method = method;
        }
    }
}
