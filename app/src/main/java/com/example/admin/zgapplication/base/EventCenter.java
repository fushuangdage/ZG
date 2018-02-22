package com.example.admin.zgapplication.base;

/**
 * Created by Serenade on 17/6/13.
 */

public class EventCenter<T> {


    public static int REFRESH_SELF_INFO=1;
    private int code;
    private T data;

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public EventCenter(int code, T data) {
        this.code = code;
        this.data = data;
    }
    public EventCenter(int code) {
        this.code = code;
    }

    public EventCenter() {
    }
}
