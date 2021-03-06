package com.example.admin.zgapplication.mvp.module;

import java.util.List;

/**
 * Created by fushuang on 2018/1/16.
 */

public class CrabListResponse extends BaseResponse<CrabListResponse.DataBean> {


    public static class DataBean extends BaseResponse.DataBean{
        /**
         * list : [{"username":"李四-链家公寓","telephone":"13261129587","avatar":"","company_name":"链家公寓","id":"2","district":"双井","house_sum":"54","visit_sum":"2","order_sum":"7","score":"4.0","chat_intention":"快","label":["准时到达","服务很nice"]},{"username":"王五-自如公寓","telephone":"13261129588","avatar":"","company_name":"自如公寓","id":"3","district":"双井","house_sum":"162","visit_sum":"0","order_sum":"101","score":0,"label":["准时到达","服务很nice"]}]
         * page : 1
         * sum_page : 1
         */

        private List<ListBean> list;

        private String str;

        public String getStr() {
            return str;
        }

        public void setStr(String str) {
            this.str = str;
        }

        public List<ListBean> getList() {
            return list;
        }

        public void setList(List<ListBean> list) {
            this.list = list;
        }

        public static class ListBean {
            /**
             * username : 李四-链家公寓
             * telephone : 13261129587
             * avatar :
             * company_name : 链家公寓
             * id : 2
             * district : 双井
             * house_sum : 54
             * visit_sum : 2
             * order_sum : 7
             * score : 4.0
             * chat_intention : 快
             * label : ["准时到达","服务很nice"]
             */

            private String username;
            private String telephone;
            private String avatar;
            private String company_name;
            private String id;
            private String district;
            private String house_sum;
            private String visit_sum;
            private String order_sum;
            private String score;
            private String chat_intention;
            private String hx_username;
            private List<String> label;

            public String getHx_username() {
                return hx_username;
            }

            public void setHx_username(String hx_username) {
                this.hx_username = hx_username;
            }

            public String getUsername() {
                return username;
            }

            public void setUsername(String username) {
                this.username = username;
            }

            public String getTelephone() {
                return telephone;
            }

            public void setTelephone(String telephone) {
                this.telephone = telephone;
            }

            public String getAvatar() {
                return avatar;
            }

            public void setAvatar(String avatar) {
                this.avatar = avatar;
            }

            public String getCompany_name() {
                return company_name;
            }

            public void setCompany_name(String company_name) {
                this.company_name = company_name;
            }

            public String getId() {
                return id;
            }

            public void setId(String id) {
                this.id = id;
            }

            public String getDistrict() {
                return district;
            }

            public void setDistrict(String district) {
                this.district = district;
            }

            public String getHouse_sum() {
                return house_sum;
            }

            public void setHouse_sum(String house_sum) {
                this.house_sum = house_sum;
            }

            public String getVisit_sum() {
                return visit_sum;
            }

            public void setVisit_sum(String visit_sum) {
                this.visit_sum = visit_sum;
            }

            public String getOrder_sum() {
                return order_sum;
            }

            public void setOrder_sum(String order_sum) {
                this.order_sum = order_sum;
            }

            public String getScore() {
                return score;
            }

            public void setScore(String score) {
                this.score = score;
            }

            public String getChat_intention() {
                return chat_intention;
            }

            public void setChat_intention(String chat_intention) {
                this.chat_intention = chat_intention;
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
