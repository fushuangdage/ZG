package com.example.admin.zgapplication.mvp.module;

import java.util.List;

/**
 * Created by fushuang on 2018/1/24.
 */

public class LifeBillRecordResponse extends BaseResponse<LifeBillRecordResponse.DataBean> {

    public static class DataBean extends BaseResponse.DataBean{
        private List<ListBeanX> list;

        public List<ListBeanX> getList() {
            return list;
        }

        public void setList(List<ListBeanX> list) {
            this.list = list;
        }

        public static class ListBeanX {
            /**
             * time : 2017年05月
             * list : [{"id":"14","item_name":"钥匙押金","status":"已支付","bill_num":"201801101132065512696964","circle":"2018-01-09至2019-01-09","pay_time":"1496246400","payment":"160.00"}]
             */

            private String time;
            private List<ListBean> list;

            public String getTime() {
                return time;
            }

            public void setTime(String time) {
                this.time = time;
            }

            public List<ListBean> getList() {
                return list;
            }

            public void setList(List<ListBean> list) {
                this.list = list;
            }

            public static class ListBean {
                /**
                 * id : 14
                 * item_name : 钥匙押金
                 * status : 已支付
                 * bill_num : 201801101132065512696964
                 * circle : 2018-01-09至2019-01-09
                 * pay_time : 1496246400
                 * payment : 160.00
                 */

                private String id;
                private String item_name;
                private String status;
                private String bill_num;
                private String circle;
                private long pay_time;
                private String payment;

                public String time;  //时间条目

                public ListBean(String time) {
                    this.time = time;
                }

                public String getId() {
                    return id;
                }

                public void setId(String id) {
                    this.id = id;
                }

                public String getItem_name() {
                    return item_name;
                }

                public void setItem_name(String item_name) {
                    this.item_name = item_name;
                }

                public String getStatus() {
                    return status;
                }

                public void setStatus(String status) {
                    this.status = status;
                }

                public String getBill_num() {
                    return bill_num;
                }

                public void setBill_num(String bill_num) {
                    this.bill_num = bill_num;
                }

                public String getCircle() {
                    return circle;
                }

                public void setCircle(String circle) {
                    this.circle = circle;
                }

                public long getPay_time() {
                    return pay_time;
                }

                public void setPay_time(long pay_time) {
                    this.pay_time = pay_time;
                }

                public String getPayment() {
                    return payment;
                }

                public void setPayment(String payment) {
                    this.payment = payment;
                }
            }
        }
    }
}
