package com.example.admin.zgapplication.mvp.module;

/**
 * Created by fushuang on 2018/1/23.
 */

public class AboutUsResponse {

    /**
     * msg : 操作成功
     * code : 0
     * data : {"introduction":"www.zhagen.com","official_website":"www.zhagen.com","weixin":"扎根北京","customer_service":"010-57795016","cooperation_hotline":"010-57795017"}
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
         * introduction : www.zhagen.com
         * official_website : www.zhagen.com
         * weixin : 扎根北京
         * customer_service : 010-57795016
         * cooperation_hotline : 010-57795017
         */

        private String introduction;
        private String official_website;
        private String weixin;
        private String customer_service;
        private String cooperation_hotline;

        public String getIntroduction() {
            return introduction;
        }

        public void setIntroduction(String introduction) {
            this.introduction = introduction;
        }

        public String getOfficial_website() {
            return official_website;
        }

        public void setOfficial_website(String official_website) {
            this.official_website = official_website;
        }

        public String getWeixin() {
            return weixin;
        }

        public void setWeixin(String weixin) {
            this.weixin = weixin;
        }

        public String getCustomer_service() {
            return customer_service;
        }

        public void setCustomer_service(String customer_service) {
            this.customer_service = customer_service;
        }

        public String getCooperation_hotline() {
            return cooperation_hotline;
        }

        public void setCooperation_hotline(String cooperation_hotline) {
            this.cooperation_hotline = cooperation_hotline;
        }
    }
}
