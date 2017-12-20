package com.example.admin.zgapplication.mvp.module;

/**
 * Created by fushuang on 2017/12/20.
 */

public class BaseResponse {

    private String msg;
    private int code;

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
