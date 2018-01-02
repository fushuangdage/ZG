package com.example.admin.zgapplication.mvp.module;

/**
 * Created by fushuang on 2018/1/2.
 */

public class ConfirmPayResponse {

    /**
     * msg : 操作成功
     * code : 0
     * data : 4000.00
     */

    private String msg;
    private int code;
    private String data;

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

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }
}
