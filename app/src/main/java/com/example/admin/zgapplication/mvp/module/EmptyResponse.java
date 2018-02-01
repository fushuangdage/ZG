package com.example.admin.zgapplication.mvp.module;

/**
 * Created by fushuang on 2018/1/31.
 */

public class EmptyResponse {

    /**
     * msg : 操作成功
     * code : 0
     * data : null
     */

    private String msg;
    private int code;
    private Object data;

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

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }
}
