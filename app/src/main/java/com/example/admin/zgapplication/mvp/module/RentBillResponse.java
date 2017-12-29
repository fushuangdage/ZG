package com.example.admin.zgapplication.mvp.module;

import java.util.List;

/**
 * Created by fushuang on 2017/12/29.
 */

public class RentBillResponse extends BaseResponse<RentBillResponse.DataBean> {


    public static class DataBean extends BaseResponse.DataBean {
        /**
         * list : [{"bill_num":"201712221742053572560456","week":2,"status":"已完成","circle":"2017-12-22至2018-01-22","pay":"押1付12","payment":"104000.00","leave":"104000.00"}]
         * page : 1
         * sum_page : 1
         */

        private List<ListBean> list;


        public List<ListBean> getList() {
            return list;
        }

        public void setList(List<ListBean> list) {
            this.list = list;
        }

        public static class ListBean {
            /**
             * bill_num : 201712221742053572560456
             * week : 2
             * status : 已完成
             * circle : 2017-12-22至2018-01-22
             * pay : 押1付12
             * payment : 104000.00
             * leave : 104000.00
             */

            private String bill_num;
            private int week;
            private String status;
            private String circle;
            private String pay;
            private String payment;
            private String leave;

            public String getBill_num() {
                return bill_num;
            }

            public void setBill_num(String bill_num) {
                this.bill_num = bill_num;
            }

            public int getWeek() {
                return week;
            }

            public void setWeek(int week) {
                this.week = week;
            }

            public String getStatus() {
                return status;
            }

            public void setStatus(String status) {
                this.status = status;
            }

            public String getCircle() {
                return circle;
            }

            public void setCircle(String circle) {
                this.circle = circle;
            }

            public String getPay() {
                return pay;
            }

            public void setPay(String pay) {
                this.pay = pay;
            }

            public String getPayment() {
                return payment;
            }

            public void setPayment(String payment) {
                this.payment = payment;
            }

            public String getLeave() {
                return leave;
            }

            public void setLeave(String leave) {
                this.leave = leave;
            }
        }
    }
}
