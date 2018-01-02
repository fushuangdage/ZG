package com.example.admin.zgapplication.mvp.module;

/**
 * Created by fushuang on 2018/1/2.
 */

public class BillFinishResponse extends BaseResponse<BillFinishResponse.DataBean> {


    public static class DataBean extends BaseResponse.DataBean{
        /**
         * order_num : 201711281420545005452852
         * title : 小关北里A座2号楼-次卧
         * week : 2017-11-30至2018-03-02
         * rent : 2500.00
         * month : 3
         * rent_sum : 7500
         * coupon : 0
         * should_pay : 7500
         * bill_num : 201711281420545005455410
         * pay_time : 2017-11-28
         * pay_kind : 微信
         */

        private String order_num;
        private String title;
        private String week;
        private String rent;
        private String month;
        private int rent_sum;
        private int coupon;
        private int should_pay;
        private String bill_num;
        private String pay_time;
        private String pay_kind;

        public String getOrder_num() {
            return order_num;
        }

        public void setOrder_num(String order_num) {
            this.order_num = order_num;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getWeek() {
            return week;
        }

        public void setWeek(String week) {
            this.week = week;
        }

        public String getRent() {
            return rent;
        }

        public void setRent(String rent) {
            this.rent = rent;
        }

        public String getMonth() {
            return month;
        }

        public void setMonth(String month) {
            this.month = month;
        }

        public int getRent_sum() {
            return rent_sum;
        }

        public void setRent_sum(int rent_sum) {
            this.rent_sum = rent_sum;
        }

        public int getCoupon() {
            return coupon;
        }

        public void setCoupon(int coupon) {
            this.coupon = coupon;
        }

        public int getShould_pay() {
            return should_pay;
        }

        public void setShould_pay(int should_pay) {
            this.should_pay = should_pay;
        }

        public String getBill_num() {
            return bill_num;
        }

        public void setBill_num(String bill_num) {
            this.bill_num = bill_num;
        }

        public String getPay_time() {
            return pay_time;
        }

        public void setPay_time(String pay_time) {
            this.pay_time = pay_time;
        }

        public String getPay_kind() {
            return pay_kind;
        }

        public void setPay_kind(String pay_kind) {
            this.pay_kind = pay_kind;
        }
    }
}
