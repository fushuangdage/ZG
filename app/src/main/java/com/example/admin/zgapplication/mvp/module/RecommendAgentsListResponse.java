package com.example.admin.zgapplication.mvp.module;

import java.util.List;

/**
 * Created by fushuang on 2018/1/10.
 */

public class RecommendAgentsListResponse extends BaseResponse<RecommendAgentsListResponse.DataBean> {


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
             * id : 24
             * username : 小华-扎根公寓
             * district : 回龙观,望京
             * avatar :
             * company_name : 扎根公寓
             * house_sum : 0
             * visit_sum : 0
             * order_sum : 0
             * score : 0
             * label : ["准时到达","服务很nice"]
             */

            private String id;
            private String username;
            private String district;
            private String avatar;
            private String company_name;
            private String house_sum;
            private String visit_sum;
            private String order_sum;
            private int score;
            private List<String> label;

            public String getId() {
                return id;
            }

            public void setId(String id) {
                this.id = id;
            }

            public String getUsername() {
                return username;
            }

            public void setUsername(String username) {
                this.username = username;
            }

            public String getDistrict() {
                return district;
            }

            public void setDistrict(String district) {
                this.district = district;
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

            public int getScore() {
                return score;
            }

            public void setScore(int score) {
                this.score = score;
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
