package com.example.admin.zgapplication.mvp.module;

import java.util.List;

/**
 * Created by fushuang on 2017/12/18.
 */

public class OrderList extends BaseResponse<OrderList.OrderListDataBean>{

    public static class OrderListDataBean extends BaseResponse.DataBean{

        private List<ListBean> list;

        public List<ListBean> getList() {
            return list;
        }

        public void setList(List<ListBean> list) {
            this.list = list;
        }

        public static class ListBean {
            /**
             * order_id : 1336
             * company_name : 链家公寓
             * agent : 李四-链家公寓
             * status : 待付款
             * pay_type : 押1付3、55
             * house_photo : http://zy.zhagen.com/uploads/images/20171116/1510835458dfe72487a81924e3.jpg
             * house_title : 链家公寓韩家胡同102号楼-55
             * address : 韩家胡同
             * rent_money : 7000
             * label : []
             * payment : 35840.00
             */

            private String order_id;
            private String company_name;
            private String agent;
            private String status;
            private String pay_type;
            private String house_photo;
            private String house_title;
            private String address;
            private int rent_money;
            private String payment;
            private List<String> label;

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
