package com.example.admin.zgapplication.mvp.module;

/**
 * Created by fushuang on 2018/1/31.
 */

public class RentReadyPayResponse extends BaseResponse<RentReadyPayResponse.DataBean> {


    public static class DataBean extends BaseResponse.DataBean{
        /**
         * rent_pay : 12
         * r_money : 12000
         * deposit : 1
         * pay : 1000
         * middle_money : 1000
         * service_count : x6x5.00%
         * service_money : 300
         * discount : 0
         * payment : 14300.00
         * friend_link : h5.zhagen.com/order/friend-pay?bill_num=201801301700119907002811
         * bill_num : 201801301700119907002811
         */

        private String rent_pay;
        private int r_money;
        private String deposit;
        private int pay;
        private int middle_money;
        private String service_count;
        private int service_money;
        private int discount;
        private String payment;
        private String friend_link;
        private String bill_num;
        private String middle_count;

        public String getMiddle_count() {
            return middle_count;
        }

        public void setMiddle_count(String middle_count) {
            this.middle_count = middle_count;
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

        public int getService_money() {
            return service_money;
        }

        public void setService_money(int service_money) {
            this.service_money = service_money;
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

        public String getFriend_link() {
            return friend_link;
        }

        public void setFriend_link(String friend_link) {
            this.friend_link = friend_link;
        }

        public String getBill_num() {
            return bill_num;
        }

        public void setBill_num(String bill_num) {
            this.bill_num = bill_num;
        }
    }
}
