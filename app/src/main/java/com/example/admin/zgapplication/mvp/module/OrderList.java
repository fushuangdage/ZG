package com.example.admin.zgapplication.mvp.module;

import java.util.List;

/**
 * Created by fushuang on 2017/12/18.
 */

public class OrderList  extends BaseResponse{


    /**
     * msg : 操作成功
     * code : 0
     * data : {"list":[{"id":"1259","company_name":"青年公寓","agent":"李琦","status":"待支付","pay_type":"押1付3、501","house_photo":"http://zy.zhagen.com/uploads/images/20171204/15123736509c30f665e9326c8b.jpg","house_title":"青年公寓回龙观小区103号楼-501","address":"回龙观小区","rent_money":"2300.00","label":["可以养宠物","全新墙纸","阳台视野广阔"],"payment":"3831.20"},{"id":"1083","company_name":"爱家公寓","agent":"福","status":"待支付","pay_type":"押0付3、8888","house_photo":"http://zy.zhagen.com/uploads/images/20171113/15105517505b6ea9d1e6242380.jpg","house_title":"爱家公寓凯泰大厦10号楼-8888","address":"凯泰大厦","rent_money":"80000.00","label":["简单大方","装修地中海风","可以养宠物","全新墙纸","阳台视野广阔"],"payment":"368000.00"},{"id":"1082","company_name":"爱家公寓","agent":"福","status":"待支付","pay_type":"押0付3、8888","house_photo":"http://zy.zhagen.com/uploads/images/20171113/15105517505b6ea9d1e6242380.jpg","house_title":"爱家公寓凯泰大厦10号楼-8888","address":"凯泰大厦","rent_money":"80000.00","label":["简单大方","装修地中海风","可以养宠物","全新墙纸","阳台视野广阔"],"payment":"368000.00"},{"id":"1081","company_name":"爱家公寓","agent":"福","status":"待支付","pay_type":"押0付3、8888","house_photo":"http://zy.zhagen.com/uploads/images/20171113/15105517505b6ea9d1e6242380.jpg","house_title":"爱家公寓凯泰大厦10号楼-8888","address":"凯泰大厦","rent_money":"80000.00","label":["简单大方","装修地中海风","可以养宠物","全新墙纸","阳台视野广阔"],"payment":"368000.00"},{"id":"962","company_name":"扎根公寓","agent":"张三-扎根公寓","status":"待支付","pay_type":"押1付3、401","house_photo":"http://zy.zhagen.com/uploads/images/20171119/1511059914f9c6ba1bac0893ab.jpg","house_title":"扎根公寓小关北里A座2号楼-401","address":"小关北里","rent_money":"3500.00","label":["简单大方","装修地中海风","可以养宠物","全新墙纸","阳台视野广阔"],"payment":"17500.00"}],"page":"1","sum_page":3}
     */

    private DataBean data;


    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * list : [{"id":"1259","company_name":"青年公寓","agent":"李琦","status":"待支付","pay_type":"押1付3、501","house_photo":"http://zy.zhagen.com/uploads/images/20171204/15123736509c30f665e9326c8b.jpg","house_title":"青年公寓回龙观小区103号楼-501","address":"回龙观小区","rent_money":"2300.00","label":["可以养宠物","全新墙纸","阳台视野广阔"],"payment":"3831.20"},{"id":"1083","company_name":"爱家公寓","agent":"福","status":"待支付","pay_type":"押0付3、8888","house_photo":"http://zy.zhagen.com/uploads/images/20171113/15105517505b6ea9d1e6242380.jpg","house_title":"爱家公寓凯泰大厦10号楼-8888","address":"凯泰大厦","rent_money":"80000.00","label":["简单大方","装修地中海风","可以养宠物","全新墙纸","阳台视野广阔"],"payment":"368000.00"},{"id":"1082","company_name":"爱家公寓","agent":"福","status":"待支付","pay_type":"押0付3、8888","house_photo":"http://zy.zhagen.com/uploads/images/20171113/15105517505b6ea9d1e6242380.jpg","house_title":"爱家公寓凯泰大厦10号楼-8888","address":"凯泰大厦","rent_money":"80000.00","label":["简单大方","装修地中海风","可以养宠物","全新墙纸","阳台视野广阔"],"payment":"368000.00"},{"id":"1081","company_name":"爱家公寓","agent":"福","status":"待支付","pay_type":"押0付3、8888","house_photo":"http://zy.zhagen.com/uploads/images/20171113/15105517505b6ea9d1e6242380.jpg","house_title":"爱家公寓凯泰大厦10号楼-8888","address":"凯泰大厦","rent_money":"80000.00","label":["简单大方","装修地中海风","可以养宠物","全新墙纸","阳台视野广阔"],"payment":"368000.00"},{"id":"962","company_name":"扎根公寓","agent":"张三-扎根公寓","status":"待支付","pay_type":"押1付3、401","house_photo":"http://zy.zhagen.com/uploads/images/20171119/1511059914f9c6ba1bac0893ab.jpg","house_title":"扎根公寓小关北里A座2号楼-401","address":"小关北里","rent_money":"3500.00","label":["简单大方","装修地中海风","可以养宠物","全新墙纸","阳台视野广阔"],"payment":"17500.00"}]
         * page : 1
         * sum_page : 3
         */

        private String page;
        private int sum_page;
        private List<ListBean> list;

        public String getPage() {
            return page;
        }

        public void setPage(String page) {
            this.page = page;
        }

        public int getSum_page() {
            return sum_page;
        }

        public void setSum_page(int sum_page) {
            this.sum_page = sum_page;
        }

        public List<ListBean> getList() {
            return list;
        }

        public void setList(List<ListBean> list) {
            this.list = list;
        }

        public static class ListBean {
            /**
             * id : 1259
             * company_name : 青年公寓
             * agent : 李琦
             * status : 待支付
             * pay_type : 押1付3、501
             * house_photo : http://zy.zhagen.com/uploads/images/20171204/15123736509c30f665e9326c8b.jpg
             * house_title : 青年公寓回龙观小区103号楼-501
             * address : 回龙观小区
             * rent_money : 2300.00
             * label : ["可以养宠物","全新墙纸","阳台视野广阔"]
             * payment : 3831.20
             */

            private String id;
            private String company_name;
            private String agent;
            private String status;
            private String pay_type;
            private String house_photo;
            private String house_title;
            private String address;
            private String rent_money;
            private String payment;
            private List<String> label;

            public String getId() {
                return id;
            }

            public void setId(String id) {
                this.id = id;
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

            public String getRent_money() {
                return rent_money;
            }

            public void setRent_money(String rent_money) {
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
