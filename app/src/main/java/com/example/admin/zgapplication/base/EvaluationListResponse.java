package com.example.admin.zgapplication.base;

import com.example.admin.zgapplication.mvp.module.BaseResponse;

import java.util.List;

/**
 * Created by fushuang on 2018/1/19.
 */

public class EvaluationListResponse extends BaseResponse<EvaluationListResponse.DataBean> {


    public static class DataBean extends BaseResponse.DataBean{

        private List<ListBean> list;

        public List<ListBean> getList() {
            return list;
        }

        public void setList(List<ListBean> list) {
            this.list = list;
        }

        public static class ListBean {
            /**
             * id : 887
             * method : 1
             * reviewed : 0
             * avatar :
             * telephone : 13111112222
             * company_name : 链家公寓
             * agent : 小佳-链家公寓
             * status : 已完成
             * pay_type : 押1付12、1
             * house_photo : http://zy.zhagen.com/uploads/images/20171120/151116727068b0e3b5da3e6006.jpg
             * house_title : 三虎桥南路小区一号院101号楼-小单间
             * house_address : 北京市海淀区花园桥
             * rent_money : 12123
             * label : ["阳台视野广阔"]
             * payment : 169722.00
             */

            private String id;
            private String method;
            private String reviewed;
            private String avatar;
            private String telephone;
            private String company_name;
            private String agent;
            private String status;
            private String pay_type;
            private String house_photo;
            private String house_title;
            private String house_address;
            private int house_rental;
            private String payment;
            private List<String> label;
            private List<String> house_label;

            public List<String> getHouse_label() {
                return house_label;
            }

            public void setHouse_label(List<String> house_label) {
                this.house_label = house_label;
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

            public long getExpect_time() {
                return expect_time;
            }

            public void setExpect_time(long expect_time) {
                this.expect_time = expect_time;
            }

            public String getType() {
                return type;
            }

            public void setType(String type) {
                this.type = type;
            }

            public String getUsername() {
                return username;
            }

            public void setUsername(String username) {
                this.username = username;
            }

            public String getMember_id() {
                return member_id;
            }

            public void setMember_id(String member_id) {
                this.member_id = member_id;
            }

            public String getHouse_info() {
                return house_info;
            }

            public void setHouse_info(String house_info) {
                this.house_info = house_info;
            }

            //带看数据
            public String room_id;
            public String house_id;
            public long expect_time;
            public String type;
            public String  username;
            public String  member_id;
            public String  house_info;



            public String getId() {
                return id;
            }

            public void setId(String id) {
                this.id = id;
            }

            public String getMethod() {
                return method;
            }

            public void setMethod(String method) {
                this.method = method;
            }

            public String getReviewed() {
                return reviewed;
            }

            public void setReviewed(String reviewed) {
                this.reviewed = reviewed;
            }

            public String getAvatar() {
                return avatar;
            }

            public void setAvatar(String avatar) {
                this.avatar = avatar;
            }

            public String getTelephone() {
                return telephone;
            }

            public void setTelephone(String telephone) {
                this.telephone = telephone;
            }

            public String getCompany_name() {
                return company_name;
            }

            public void setCompany_name(String company_name) {
                this.company_name = company_name;
            }

            public String getAgent() {
                return agent;
            }

            public void setAgent(String agent) {
                this.agent = agent;
            }

            public String getStatus() {
                return status;
            }

            public void setStatus(String status) {
                this.status = status;
            }

            public String getPay_type() {
                return pay_type;
            }

            public void setPay_type(String pay_type) {
                this.pay_type = pay_type;
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

            public String getHouse_address() {
                return house_address;
            }

            public void setHouse_address(String house_address) {
                this.house_address = house_address;
            }

            public int getRent_money() {
                return house_rental;
            }

            public void setRent_money(int rent_money) {
                this.house_rental = rent_money;
            }

            public String getPayment() {
                return payment;
            }

            public void setPayment(String payment) {
                this.payment = payment;
            }

            public List<String> getLabel() {
                return label;
            }

            public void setLabel(List<String> label) {
                this.label = label;
            }
        }
    }
}
