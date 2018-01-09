package com.example.admin.zgapplication.mvp.module;

import java.util.List;

/**
 * Created by fushuang on 2017/12/19.
 */

public class DiscountListResponse extends BaseResponse<DiscountListResponse.DataBean> {


    public static class DataBean extends BaseResponse.DataBean{

        public List<ListBean> list;

        public List<ListBean> getList() {
            return list;
        }

        public void setList(List<ListBean> list) {
            this.list = list;
        }
        /**
         * data : [{"id":15,"uid":36,"coupon_name":"123123","money":123,"start_time":1509465600,"expire_time":1510070400,"status":3},{"id":16,"uid":36,"coupon_name":"我爱我家12","money":300012,"start_time":1511884800,"expire_time":1512662400,"status":3},{"id":18,"uid":36,"coupon_name":"新年优惠","money":100,"start_time":1511971200,"expire_time":1512057600,"status":3},{"id":39,"uid":36,"coupon_name":"阿迪阿斯蒂阿萨斯syukadhasifda","money":100,"start_time":1511884800,"expire_time":1511971200,"status":3},{"id":40,"uid":36,"coupon_name":"优惠","money":100,"start_time":1511798400,"expire_time":1511971200,"status":3},{"id":41,"uid":36,"coupon_name":"爱丽丝梦游仙境","money":0,"start_time":1509465600,"expire_time":1509552000,"status":3},{"id":42,"uid":36,"coupon_name":"湿哒哒是多撒","money":2000,"start_time":1511884800,"expire_time":1511971200,"status":3},{"id":43,"uid":36,"coupon_name":"的是哟大厦东方啊","money":111,"start_time":1511884800,"expire_time":1511971200,"status":3},{"id":44,"uid":36,"coupon_name":"test","money":12,"start_time":1511798400,"expire_time":1511884800,"status":3},{"id":45,"uid":36,"coupon_name":"test12","money":12,"start_time":1511884800,"expire_time":1511971200,"status":3}]
         */

        public static class ListBean {

            /**
             * id : 15
             * uid : 36
             * coupon_name : 123123
             * money : 123
             * start_time : 1509465600
             * expire_time : 1510070400
             * status : 3
             */

            private int id;
            private int uid;
            private String coupon_name;
            private int money;
            private int start_time;
            private int expire_time;
            private int status;

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }

            public int getUid() {
                return uid;
            }

            public void setUid(int uid) {
                this.uid = uid;
            }

            public String getCoupon_name() {
                return coupon_name;
            }

            public void setCoupon_name(String coupon_name) {
                this.coupon_name = coupon_name;
            }

            public int getMoney() {
                return money;
            }

            public void setMoney(int money) {
                this.money = money;
            }

            public int getStart_time() {
                return start_time;
            }

            public void setStart_time(int start_time) {
                this.start_time = start_time;
            }

            public int getExpire_time() {
                return expire_time;
            }

            public void setExpire_time(int expire_time) {
                this.expire_time = expire_time;
            }

            public int getStatus() {
                return status;
            }

            public void setStatus(int status) {
                this.status = status;
            }
        }
    }
}
