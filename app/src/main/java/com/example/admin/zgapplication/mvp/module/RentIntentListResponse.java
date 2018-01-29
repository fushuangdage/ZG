package com.example.admin.zgapplication.mvp.module;

import java.util.List;

/**
 * Created by fushuang on 2017/12/26.
 */

public class RentIntentListResponse  extends BaseResponse<RentIntentListResponse.DataBean>{


    public static class DataBean extends BaseResponse.DataBean{
        /**
         * data : [{"id":"2","outset":"2000","cutoff":"4000","room":"1室,2室,3室","method":"2","create_at":"1514267197","sum":"2","parent":"昌平","district":"回龙观"},{"id":"4","outset":"2500","cutoff":"5000","room":"1室,2室,3室","method":"1","create_at":"1514267200","sum":"3","parent":"朝阳","district":"望京"}]
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
             * id : 2
             * outset : 2000
             * cutoff : 4000
             * room : 1室,2室,3室
             * method : 2
             * create_at : 1514267197
             * sum : 2
             * parent : 昌平
             * district : 回龙观
             */


            /**
             {  可能是改了
                "id": "414",
                "method": "1",
                "create_at": "1517038938",
                "parent": "门头沟",
                "district": "双峪",
                "price": "不限",
                "sum": "0"
            },
             */
            private String id;
            private String outset;
            private String cutoff;
            private String room;
            private String method;
            private String create_at;
            private String sum;
            private String parent;
            private String district;
            private String price;

            public String getPrice() {
                return price;
            }

            public void setPrice(String price) {
                this.price = price;
            }

            public String getId() {
                return id;
            }

            public void setId(String id) {
                this.id = id;
            }

            public String getOutset() {
                return outset;
            }

            public void setOutset(String outset) {
                this.outset = outset;
            }

            public String getCutoff() {
                return cutoff;
            }

            public void setCutoff(String cutoff) {
                this.cutoff = cutoff;
            }

            public String getRoom() {
                return room;
            }

            public void setRoom(String room) {
                this.room = room;
            }

            public String getMethod() {
                return method;
            }

            public void setMethod(String method) {
                this.method = method;
            }

            public String getCreate_at() {
                return create_at;
            }

            public void setCreate_at(String create_at) {
                this.create_at = create_at;
            }

            public String getSum() {
                return sum;
            }

            public void setSum(String sum) {
                this.sum = sum;
            }

            public String getParent() {
                return parent;
            }

            public void setParent(String parent) {
                this.parent = parent;
            }

            public String getDistrict() {
                return district;
            }

            public void setDistrict(String district) {
                this.district = district;
            }
        }
    }
}
