package com.example.admin.zgapplication.mvp.module;

import java.util.List;

/**
 * Created by fushuang on 2017/12/15.
 */

public class HouseResourseListBean  extends BaseResponse<HouseResourseListBean.HouseResourseDataBean>{

    public static class HouseResourseDataBean extends BaseResponse.DataBean{

        private List<ListBean> list;

        public List<ListBean> getList() {
            return list;
        }

        public void setList(List<ListBean> list) {
            this.list = list;

        }
        public static class ListBean {

            /**
             * house_photo : http://zy.zhagen.com/uploads/images/20171215/1513321460af3de894c5d33285.jpg
             * house_title : 骊龙园28号楼-28新户型
             * house_info : 一室零厅零卫-30.00
             * house_address : 北京市昌平区回龙观
             * house_rental : 1500
             * house_label : ["全新墙纸","阳台视野广阔"]
             */

            private String house_id;
            private String type;
            private String create_at;
            private String room_id;

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

            public String getType() {
                return type;
            }

            public void setType(String type) {
                this.type = type;
            }

            public String getCreate_at() {
                return create_at;
            }

            public void setCreate_at(String create_at) {
                this.create_at = create_at;
            }

            private String house_photo;
            private String house_title;
            private String house_info;
            private String house_address;
            private String house_rental;
            private List<String> house_label;

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
