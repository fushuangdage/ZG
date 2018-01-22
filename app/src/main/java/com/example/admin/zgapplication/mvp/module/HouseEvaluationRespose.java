package com.example.admin.zgapplication.mvp.module;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by fushuang on 2018/1/15.
 */

public class HouseEvaluationRespose extends BaseResponse<HouseEvaluationRespose.DataBean>{


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
             * avatar :
             * real_name : 测试2
             * label : [["准时到达","服务很nice"]]
             * content : 曾几何时，还很懵懂无知，没有烦恼，不作挣扎，朦胧的叩响一颗心，没有习惯，却很执著，热枕想象的期待。风景总是不能回头，错失遗憾，却没有想象中的简单，幻想做一个温情的人，出走半生，相遇并不完美，遇一简单的人，往事随风。
             * album : ["http://zy.zhagen.com/uploads/images/20171113/15105384199c30f665e9326c8b.jpg","http://zy.zhagen.com/uploads/images/20171113/151053841913c13d40df2dc277.jpg","http://zy.zhagen.com/uploads/images/20171113/151053841974092a7c129871c7.jpg","http://zy.zhagen.com/uploads/images/20171113/151053841968b0e3b5da3e6006.jpg","http://zy.zhagen.com/uploads/images/20171113/1510538419aabe078a98674fb1.jpg","http://zy.zhagen.com/uploads/images/20171113/151053842061d25efd2882553d.jpg"]
             * score : 5.0
             * create_at : 1515740686
             * hide : 1
             */

            @SerializedName("id")
            private String idX;
            private String avatar;
            private String real_name;
            private String content;
            private String score;
            @SerializedName("create_at")
            private long create_atX;
            private String hide;
            private List<String> label;
            private List<String> album;

            public String getIdX() {
                return idX;
            }

            public void setIdX(String idX) {
                this.idX = idX;
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

            public String getContent() {
                return content;
            }

            public void setContent(String content) {
                this.content = content;
            }

            public String getScore() {
                return score;
            }

            public void setScore(String score) {
                this.score = score;
            }

            public long getCreate_atX() {
                return create_atX;
            }

            public void setCreate_atX(long create_atX) {
                this.create_atX = create_atX;
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

            public List<String> getAlbum() {
                return album;
            }

            public void setAlbum(List<String> album) {
                this.album = album;
            }
        }
    }
}
