package com.example.admin.zgapplication.mvp.module;

import java.util.List;

/**
 * Created by fushuang on 2018/1/24.
 */

public class LifeRentBillResponse extends BaseResponse <LifeRentBillResponse.DataBean>{

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
             * type : 2
             * id : 13
             * bill_num : 201801101132065512620354
             * item_name : 门卡押金
             * circle : 2018-01-09至2019-01-09
             * payment : 200.00
             */

            private int type;
            private String id;
            private String bill_num;
            private String item_name;
            private String circle;
            private int payment;
            private String last_pay;
            public boolean isCheck=false;
            public String pay_time;
            public String status;


            public String getLast_pay() {
                return last_pay;
            }

            public void setLast_pay(String last_pay) {
                this.last_pay = last_pay;
            }

            public int getType() {
                return type;
            }

            public void setType(int type) {
                this.type = type;
            }

            public String getId() {
                return id;
            }

            public void setId(String id) {
                this.id = id;
            }

            public String getBill_num() {
                return bill_num;
            }

            public void setBill_num(String bill_num) {
                this.bill_num = bill_num;
            }

            public String getItem_name() {
                return item_name;
            }

            public void setItem_name(String item_name) {
                this.item_name = item_name;
            }

            public String getCircle() {
                return circle;
            }

            public void setCircle(String circle) {
                this.circle = circle;
            }

            public int getPayment() {
                return payment;
            }

            public void setPayment(int payment) {
                this.payment = payment;
            }
        }
    }
}
