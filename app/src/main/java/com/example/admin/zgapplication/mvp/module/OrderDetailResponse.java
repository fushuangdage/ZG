package com.example.admin.zgapplication.mvp.module;

import java.util.List;

/**
 * Created by fushuang on 2017/12/21.
 */

public class OrderDetailResponse extends BaseResponse<OrderDetailResponse.OrderDetailDataBean> {


    public static class OrderDetailDataBean extends BaseResponse.DataBean {

        private ListBean list;

        public ListBean getList() {
            return list;
        }

        public void setList(ListBean list) {
            this.list = list;
        }

        public static class ListBean {
            /**
             * order_id : 1259
             * company_name : 青年公寓
             * agent : 李琦
             * house_title : 青年公寓回龙观小区103号楼-501
             * house_area : 一室零厅一卫-23.00
             * address : 北京市昌平区回龙观
             * rent_money : 2300
             * label : ["可以养宠物","全新墙纸","阳台视野广阔"]
             * rent_month : 12
             * pay_type : 押1付3
             * room_num : 501
             * phone_number : 18662033577
             * user_coupon_id : 0
             * user_coupon_money : 0
             * rent : 2300.00
             * rent_pay : 3
             * r_money : 6900
             * deposit : 1
             * pay : 2300
             * middle_count : 1.20
             * middle_money : 2300
             * service_count : *12*1.20
             * service_money : 331.2
             * reduce : 8000
             * discount : 0
             * payment : 3831.20
             * house_photo : http://zy.zhagen.com/uploads/images/20171204/151237365013c13d40df2dc277.jpg
             */

            private String order_id;
            private String company_name;
            private String agent;
            private String house_title;
            private String house_area;
            private String address;
            private int rent_money;
            private String rent_month;
            private String pay_type;
            private String room_num;
            private String phone_number;
            private String user_coupon_id;
            private String user_coupon_money;
            private String rent;
            private String rent_pay;
            private int r_money;
            private String deposit;
            private int pay;
            private String middle_count;
            private int middle_money;
            private String service_count;
            private double service_money;
            private String reduce;
            private int discount;
            private String house_photo;
            private List<String> label;
            private long pay_time;
            private String order;
            private String payment;
            private String net;
            private String real_name;
            private String telephone;


            public long getPay_time() {
                return pay_time;
            }

            public void setPay_time(long pay_time) {
                this.pay_time = pay_time;
            }

            public String getOrder() {
                return order;
            }

            public void setOrder(String order) {
                this.order = order;
            }

            public String getNet() {
                return net;
            }

            public void setNet(String net) {
                this.net = net;
            }

            public String getReal_name() {
                return real_name;
            }

            public void setReal_name(String real_name) {
                this.real_name = real_name;
            }

            public String getTelephone() {
                return telephone;
            }

            public void setTelephone(String telephone) {
                this.telephone = telephone;
            }

            public String getOrder_id() {
                return order_id;
            }

            public void setOrder_id(String order_id) {
                this.order_id = order_id;
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

            public String getHouse_title() {
                return house_title;
            }

            public void setHouse_title(String house_title) {
                this.house_title = house_title;
            }

            public String getHouse_area() {
                return house_area;
            }

            public void setHouse_area(String house_area) {
                this.house_area = house_area;
            }

            public String getAddress() {
                return address;
            }

            public void setAddress(String address) {
                this.address = address;
            }

            public int getRent_money() {
                return rent_money;
            }

            public void setRent_money(int rent_money) {
                this.rent_money = rent_money;
            }

            public String getRent_month() {
                return rent_month;
            }

            public void setRent_month(String rent_month) {
                this.rent_month = rent_month;
            }

            public String getPay_type() {
                return pay_type;
            }

            public void setPay_type(String pay_type) {
                this.pay_type = pay_type;
            }

            public String getRoom_num() {
                return room_num;
            }

            public void setRoom_num(String room_num) {
                this.room_num = room_num;
            }

            public String getPhone_number() {
                return phone_number;
            }

            public void setPhone_number(String phone_number) {
                this.phone_number = phone_number;
            }

            public String getUser_coupon_id() {
                return user_coupon_id;
            }

            public void setUser_coupon_id(String user_coupon_id) {
                this.user_coupon_id = user_coupon_id;
            }

            public String getUser_coupon_money() {
                return user_coupon_money;
            }

            public void setUser_coupon_money(String user_coupon_money) {
                this.user_coupon_money = user_coupon_money;
            }

            public String getRent() {
                return rent;
            }

            public void setRent(String rent) {
                this.rent = rent;
            }

            public String getRent_pay() {
                return rent_pay;
            }

            public void setRent_pay(String rent_pay) {
                this.rent_pay = rent_pay;
            }

            public int getR_money() {
                return r_money;
            }

            public void setR_money(int r_money) {
                this.r_money = r_money;
            }

            public String getDeposit() {
                return deposit;
            }

            public void setDeposit(String deposit) {
                this.deposit = deposit;
            }

            public int getPay() {
                return pay;
            }

            public void setPay(int pay) {
                this.pay = pay;
            }

            public String getMiddle_count() {
                return middle_count;
            }

            public void setMiddle_count(String middle_count) {
                this.middle_count = middle_count;
            }

            public int getMiddle_money() {
                return middle_money;
            }

            public void setMiddle_money(int middle_money) {
                this.middle_money = middle_money;
            }

            public String getService_count() {
                return service_count;
            }

            public void setService_count(String service_count) {
                this.service_count = service_count;
            }

            public double getService_money() {
                return service_money;
            }

            public void setService_money(double service_money) {
                this.service_money = service_money;
            }

            public String getReduce() {
                return reduce;
            }

            public void setReduce(String reduce) {
                this.reduce = reduce;
            }

            public int getDiscount() {
                return discount;
            }

            public void setDiscount(int discount) {
                this.discount = discount;
            }

            public String getPayment() {
                return payment;
            }

            public void setPayment(String payment) {
                this.payment = payment;
            }

            public String getHouse_photo() {
                return house_photo;
            }

            public void setHouse_photo(String house_photo) {
                this.house_photo = house_photo;
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
