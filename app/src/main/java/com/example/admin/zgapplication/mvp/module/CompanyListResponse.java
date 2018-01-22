package com.example.admin.zgapplication.mvp.module;

import java.util.List;

/**
 * Created by fushuang on 2018/1/17.
 */

public class CompanyListResponse extends BaseResponse<CompanyListResponse.DataBean>{

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
             * company_id : 1
             * logo : origin/20171203/a4c8c8bfde0bbed7996a040b799e43ac_1512270513.png
             * company_name : 扎根公寓
             * count_house : 143
             * count_member : 28
             * count_score : 5
             */

            private int company_id;
            private String logo;
            private String company_name;
            private int count_house;
            private String count_member;
            private String count_score;

            public int getCompany_id() {
                return company_id;
            }

            public void setCompany_id(int company_id) {
                this.company_id = company_id;
            }

            public String getLogo() {
                return logo;
            }

            public void setLogo(String logo) {
                this.logo = logo;
            }

            public String getCompany_name() {
                return company_name;
            }

            public void setCompany_name(String company_name) {
                this.company_name = company_name;
            }

            public int getCount_house() {
                return count_house;
            }

            public void setCount_house(int count_house) {
                this.count_house = count_house;
            }

            public String getCount_member() {
                return count_member;
            }

            public void setCount_member(String count_member) {
                this.count_member = count_member;
            }

            public String getCount_score() {
                return count_score;
            }

            public void setCount_score(String count_score) {
                this.count_score = count_score;
            }
        }
    }
}
