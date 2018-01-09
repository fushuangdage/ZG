package com.example.admin.zgapplication.mvp.module;

import java.util.List;

/**
 * Created by fushuang on 2018/1/8.
 */

public class CreatTakeLookResponse extends BaseResponse<CreatTakeLookResponse.DataBean>{



    public static class DataBean extends BaseResponse.DataBean{
        /**
         * data : {"username":"李四-链家公寓","company_name":null,"avatar":"","member_id":"2","house_id":null,"type":"1","room_id":"0","house_photo":"http://zy.zhagen.com/","house_title":"-","house_info":"-","house_address":"市区","house_rental":null,"house_label":[],"phone_number":"18662033577"}
         */

        private ListBean list;

        public ListBean getList() {
            return list;
        }

        public void setList(ListBean list) {
            this.list = list;
        }

        public static class ListBean {
            /**
             * username : 李四-链家公寓
             * company_name : null
             * avatar :
             * member_id : 2
             * house_id : null
             * type : 1
             * room_id : 0
             * house_photo : http://zy.zhagen.com/
             * house_title : -
             * house_info : -
             * house_address : 市区
             * house_rental : null
             * house_label : []
             * phone_number : 18662033577
             */

            private String username;
            private Object company_name;
            private String avatar;
            private String member_id;
            private Object house_id;
            private String type;
            private String room_id;
            private String house_photo;
            private String house_title;
            private String house_info;
            private String house_address;
            private Object house_rental;
            private String phone_number;
            private List<?> house_label;

            public String getUsername() {
                return username;
            }

            public void setUsername(String username) {
                this.username = username;
            }

            public Object getCompany_name() {
                return company_name;
            }

            public void setCompany_name(Object company_name) {
                this.company_name = company_name;
            }

            public String getAvatar() {
                return avatar;
            }

            public void setAvatar(String avatar) {
                this.avatar = avatar;
            }

            public String getMember_id() {
                return member_id;
            }

            public void setMember_id(String member_id) {
                this.member_id = member_id;
            }

            public Object getHouse_id() {
                return house_id;
            }

            public void setHouse_id(Object house_id) {
                this.house_id = house_id;
            }

            public String getType() {
                return type;
            }

            public void setType(String type) {
                this.type = type;
            }

            public String getRoom_id() {
                return room_id;
            }

            public void setRoom_id(String room_id) {
                this.room_id = room_id;
            }

            public String getHouse_photo() {
                return house_photo;
            }

            public void setHouse_photo(String house_photo) {
                this.house_photo = house_photo;
            }

            public String getHouse_title() {
                return house_title;
            }

            public void setHouse_title(String house_title) {
                this.house_title = house_title;
            }

            public String getHouse_info() {
                return house_info;
            }

            public void setHouse_info(String house_info) {
                this.house_info = house_info;
            }

            public String getHouse_address() {
                return house_address;
            }

            public void setHouse_address(String house_address) {
                this.house_address = house_address;
            }

            public Object getHouse_rental() {
                return house_rental;
            }

            public void setHouse_rental(Object house_rental) {
                this.house_rental = house_rental;
            }

            public String getPhone_number() {
                return phone_number;
            }

            public void setPhone_number(String phone_number) {
                this.phone_number = phone_number;
            }

            public List<?> getHouse_label() {
                return house_label;
            }

            public void setHouse_label(List<?> house_label) {
                this.house_label = house_label;
            }
        }
    }
}
