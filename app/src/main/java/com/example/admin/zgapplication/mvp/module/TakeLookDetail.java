package com.example.admin.zgapplication.mvp.module;

import java.util.List;

/**
 * Created by fushuang on 2018/1/8.
 */

public class TakeLookDetail extends BaseResponse<TakeLookDetail.DataBean> {

    public static class DataBean extends BaseResponse.DataBean{
        /**
         * data : {"expect_time":"1514447580","room_id":"2","house_id":"90","name":"小明","type":"2","status":"5","phone_number":"18662033577","username":"李四-链家公寓","avatar":"","company_name":"自如公寓","house_photo":"http://zy.zhagen.com/uploads/images/20171113/1510541698ad5c1759595de401.jpg","house_title":"望花路10号院24号楼-两居室","house_info":"二室一厅一卫-80.00㎡","house_address":"北京市朝阳区望京","house_rental":"3000","house_label":["简单大方","装修地中海风","可以养宠物","全新墙纸","阳台视野广阔"]}
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
             * expect_time : 1514447580
             * room_id : 2
             * house_id : 90
             * name : 小明
             * type : 2
             * status : 5
             * phone_number : 18662033577
             * username : 李四-链家公寓
             * avatar :
             * company_name : 自如公寓
             * house_photo : http://zy.zhagen.com/uploads/images/20171113/1510541698ad5c1759595de401.jpg
             * house_title : 望花路10号院24号楼-两居室
             * house_info : 二室一厅一卫-80.00㎡
             * house_address : 北京市朝阳区望京
             * house_rental : 3000
             * house_label : ["简单大方","装修地中海风","可以养宠物","全新墙纸","阳台视野广阔"]
             */

            private long expect_time;
            private String room_id;
            private String house_id;
            private String name;
            private String type;
            private String status;
            private String phone_number;
            private String username;
            private String avatar;
            private String company_name;
            private String house_photo;
            private String house_title;
            private String house_info;
            private String house_address;
            private String house_rental;
            private List<String> house_label;

            public long getExpect_time() {
                return expect_time;
            }

            public void setExpect_time(long expect_time) {
                this.expect_time = expect_time;
            }

            public String getRoom_id() {
                return room_id;
            }

            public void setRoom_id(String room_id) {
                this.room_id = room_id;
            }

            public String getHouse_id() {
                return house_id;
            }

            public void setHouse_id(String house_id) {
                this.house_id = house_id;
            }

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }

            public String getType() {
                return type;
            }

            public void setType(String type) {
                this.type = type;
            }

            public String getStatus() {
                return status;
            }

            public void setStatus(String status) {
                this.status = status;
            }

            public String getPhone_number() {
                return phone_number;
            }

            public void setPhone_number(String phone_number) {
                this.phone_number = phone_number;
            }

            public String getUsername() {
                return username;
            }

            public void setUsername(String username) {
                this.username = username;
            }

            public String getAvatar() {
                return avatar;
            }

            public void setAvatar(String avatar) {
                this.avatar = avatar;
            }

            public String getCompany_name() {
                return company_name;
            }

            public void setCompany_name(String company_name) {
                this.company_name = company_name;
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

            public String getHouse_rental() {
                return house_rental;
            }

            public void setHouse_rental(String house_rental) {
                this.house_rental = house_rental;
            }

            public List<String> getHouse_label() {
                return house_label;
            }

            public void setHouse_label(List<String> house_label) {
                this.house_label = house_label;
            }
        }
    }
}
