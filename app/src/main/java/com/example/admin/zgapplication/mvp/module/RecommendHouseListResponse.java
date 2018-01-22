package com.example.admin.zgapplication.mvp.module;

import java.util.List;

/**
 * Created by fushuang on 2018/1/10.
 */

public class RecommendHouseListResponse extends BaseResponse<RecommendHouseListResponse.DataBean> {

    public static class DataBean extends BaseResponse.DataBean {

        private List<ListBean> list;

        public List<ListBean> getList() {
            return list;
        }

        public void setList(List<ListBean> list) {
            this.list = list;
        }

        public static class ListBean {
            /**
             * house_id : 116
             * type : 1
             * room_id : 165
             * create_at : 1511855110
             * house_photo : http://zy.zhagen.com/uploads/images/20171128/15118550389c30f665e9326c8b.jpg
             * house_title : 扎根公寓回龙观小区32号楼-主卧
             * house_info : 二室一厅一卫-32.00
             * house_address : 北京市昌平区回龙观
             * house_rental : 3000
             * house_label : ["阳台视野广阔"]
             */

            private String house_id;
            private String type;
            private String room_id;
            private String create_at;
            private String house_photo;
            private String house_title;
            private String house_info;
            private String house_address;
            private String house_rental;
            private List<String> house_label;

            public String getHouse_id() {
                return house_id;
            }

            public void setHouse_id(String house_id) {
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

            public String getCreate_at() {
                return create_at;
            }

            public void setCreate_at(String create_at) {
                this.create_at = create_at;
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
