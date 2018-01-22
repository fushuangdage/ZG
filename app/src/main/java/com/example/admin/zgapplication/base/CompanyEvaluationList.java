package com.example.admin.zgapplication.base;

import com.example.admin.zgapplication.mvp.module.BaseResponse;

import java.util.List;

/**
 * Created by fushuang on 2018/1/12.
 */

public class CompanyEvaluationList extends BaseResponse<CompanyEvaluationList.DataBean> {


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
             * id : 1
             * avatar :
             * real_name : WEeeeWe
             * phone_number : 186****7479
             * create_at : 1510480955
             * label : ["很好","嗯嗯"]
             * content : 这个是房子是房子是房子评价内容
             * score : 3
             */

            private String id;
            private String avatar;
            private String real_name;
            private String phone_number;
            private String create_at;
            private String content;
            private float score;
            private List<String> label;

            public String getId() {
                return id;
            }

            public void setId(String id) {
                this.id = id;
            }

            public String getAvatar() {
                return avatar;
            }

            public void setAvatar(String avatar) {
                this.avatar = avatar;
            }

            public String getReal_name() {
                return real_name;
            }

            public void setReal_name(String real_name) {
                this.real_name = real_name;
            }

            public String getPhone_number() {
                return phone_number;
            }

            public void setPhone_number(String phone_number) {
                this.phone_number = phone_number;
            }

            public String getCreate_at() {
                return create_at;
            }

            public void setCreate_at(String create_at) {
                this.create_at = create_at;
            }

            public String getContent() {
                return content;
            }

            public void setContent(String content) {
                this.content = content;
            }

            public float getScore() {
                return score;
            }

            public void setScore(float score) {
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
