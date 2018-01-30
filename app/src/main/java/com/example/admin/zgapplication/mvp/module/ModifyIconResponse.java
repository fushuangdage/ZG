package com.example.admin.zgapplication.mvp.module;

/**
 * Created by fushuang on 2018/1/30.
 */

public class ModifyIconResponse {

    /**
     * msg : 头像上传成功
     * code : 0
     * data : {"avatar":"http://zy.zhagen.com/origin/20180130/4ac2f94f59bdaeb9fea5_1517291021.jpg"}
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
         * avatar : http://zy.zhagen.com/origin/20180130/4ac2f94f59bdaeb9fea5_1517291021.jpg
         */

        private String avatar;

        public String getAvatar() {
            return avatar;
        }

        public void setAvatar(String avatar) {
            this.avatar = avatar;
        }
    }
}
