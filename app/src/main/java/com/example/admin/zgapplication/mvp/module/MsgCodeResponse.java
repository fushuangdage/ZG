package com.example.admin.zgapplication.mvp.module;

/**
 * Created by fushuang on 2018/1/25.
 */

public class MsgCodeResponse {

    /**
     * msg : 发送成功
     * code : 0
     * data : {"verificationCode":682412}
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
         * verificationCode : 682412
         */

        private int verificationCode;

        public int getVerificationCode() {
            return verificationCode;
        }

        public void setVerificationCode(int verificationCode) {
            this.verificationCode = verificationCode;
        }
    }
}
