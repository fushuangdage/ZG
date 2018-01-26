package com.example.admin.zgapplication.mvp.module;

import java.io.Serializable;

/**
 * Created by fushuang on 2018/1/25.
 */

public class LoginResponse implements Serializable {

    /**
     * msg : 登录成功
     * code : 0
     * data : {"username":"15811400212","password":"$2y$13$YRWQ0zLMHrznxEoQD9zasuYq2Oxp6dMJsOYqv.pIVjeFJlrcHFVK.","phone_number":"15811400212","nick_name":"15811400212","hx_username":"zhagen15811400212","hx_password":"9465ab8b6a45ab9fa6a1ea1a9ab73828","avatar":"","gender":-1,"motto":"","birth_date":"0000-00-00","age":0,"register_time":0,"last_login":0,"create_at":1516859846,"update_at":1516859846,"deleted":0,"id":150}
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

    public static class DataBean implements Serializable{
        /**
         * username : 15811400212
         * password : $2y$13$YRWQ0zLMHrznxEoQD9zasuYq2Oxp6dMJsOYqv.pIVjeFJlrcHFVK.
         * phone_number : 15811400212
         * nick_name : 15811400212
         * hx_username : zhagen15811400212
         * hx_password : 9465ab8b6a45ab9fa6a1ea1a9ab73828
         * avatar :
         * gender : -1
         * motto :
         * birth_date : 0000-00-00
         * age : 0
         * register_time : 0
         * last_login : 0
         * create_at : 1516859846
         * update_at : 1516859846
         * deleted : 0
         * id : 150
         */

        private String username;
        private String password;
        private String phone_number;
        private String nick_name;
        private String hx_username;
        private String hx_password;
        private String avatar;
        private int gender;
        private String motto;
        private String birth_date;
        private int age;
        private int register_time;
        private int last_login;
        private int create_at;
        private int update_at;
        private int deleted;
        private int id;

        public String getUsername() {
            return username;
        }

        public void setUsername(String username) {
            this.username = username;
        }

        public String getPassword() {
            return password;
        }

        public void setPassword(String password) {
            this.password = password;
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

        public String getMotto() {
            return motto;
        }

        public void setMotto(String motto) {
            this.motto = motto;
        }

        public String getBirth_date() {
            return birth_date;
        }

        public void setBirth_date(String birth_date) {
            this.birth_date = birth_date;
        }

        public int getAge() {
            return age;
        }

        public void setAge(int age) {
            this.age = age;
        }

        public int getRegister_time() {
            return register_time;
        }

        public void setRegister_time(int register_time) {
            this.register_time = register_time;
        }

        public int getLast_login() {
            return last_login;
        }

        public void setLast_login(int last_login) {
            this.last_login = last_login;
        }

        public int getCreate_at() {
            return create_at;
        }

        public void setCreate_at(int create_at) {
            this.create_at = create_at;
        }

        public int getUpdate_at() {
            return update_at;
        }

        public void setUpdate_at(int update_at) {
            this.update_at = update_at;
        }

        public int getDeleted() {
            return deleted;
        }

        public void setDeleted(int deleted) {
            this.deleted = deleted;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }
    }
}
