package com.example.admin.zgapplication.mvp.module;

import java.util.List;

/**
 * Created by fushuang on 2018/1/11.
 */

public class AgentEvaluationResponse extends BaseResponse<AgentEvaluationResponse.DataBean>  {


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
             * nick_name : 修改
             * avatar :
             * content : 嗯嗯呃
             * label : ["准时到达","服务很nice","态度很好"]
             * score : 4
             * create_at : 1515147454
             * hide : 1
             */

            private String nick_name;
            private String avatar;
            private String content;
            private float score;
            private long create_at;
            private String hide;
            private List<String> label;

            public String getNick_name() {
                return nick_name;
            }

            public void setNick_name(String nick_name) {
                this.nick_name = nick_name;
            }

            public String getAvatar() {
                return avatar;
            }

            public void setAvatar(String avatar) {
                this.avatar = avatar;
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

            public long getCreate_at() {
                return create_at;
            }

            public void setCreate_at(long create_at) {
                this.create_at = create_at;
            }

            public String getHide() {
                return hide;
            }

            public void setHide(String hide) {
                this.hide = hide;
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
