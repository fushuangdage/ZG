package com.example.admin.zgapplication.mvp.module;

/**
 * Created by fushuang on 2017/12/26.
 */

public class SelfInfo extends BaseResponse<SelfInfo.DataBean> {

    public static class DataBean extends BaseResponse.DataBean{
        /**
         * id : 33
         * username : 18662033577
         * phone_number : 18662033577
         * nick_name : 规划
         * real_name : 测试2
         * hx_username :
         * hx_password :
         * avatar :
         * gender : -1
         */

        private int id;
        private String username;
        private String phone_number;
        private String nick_name;
        private String real_name;
        private String hx_username;
        private String hx_password;
        private String avatar;
        private int gender;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getUsername() {
            return username;
        }

        public void setUsername(String username) {
            this.username = username;
        }

        public String getPhone_number() {
            return phone_number;
        }

        public void setPhone_number(String phone_number) {
            this.phone_number = phone_number;
        }

        public String getNick_name() {
            return nick_name;
        }

        public void setNick_name(String nick_name) {
            this.nick_name = nick_name;
        }

        public String getReal_name() {
            return real_name;
        }

        public void setReal_name(String real_name) {
            this.real_name = real_name;
        }

        public String getHx_username() {
            return hx_username;
        }

        public void setHx_username(String hx_username) {
            this.hx_username = hx_username;
        }

        public String getHx_password() {
            return hx_password;
        }

        public void setHx_password(String hx_password) {
            this.hx_password = hx_password;
        }

        public String getAvatar() {
            return avatar;
        }

        public void setAvatar(String avatar) {
            this.avatar = avatar;
        }

        public int getGender() {
            return gender;
        }

        public void setGender(int gender) {
            this.gender = gender;
        }
    }
}
