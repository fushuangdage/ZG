package com.example.admin.zgapplication.retrofit;

/**
 * Created by fushuang on 2017/12/25.
 */

public class RequestBody {
    public String uid;
    public String code;

    public RequestBody(String uid, String code) {
        this.uid = uid;
        this.code = code;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }
}
