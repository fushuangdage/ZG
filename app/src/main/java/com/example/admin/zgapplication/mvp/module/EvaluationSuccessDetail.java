package com.example.admin.zgapplication.mvp.module;

import java.util.List;

/**
 * Created by fushuang on 2018/1/22.
 */

public class EvaluationSuccessDetail extends BaseResponse <EvaluationSuccessDetail.DataBean>{


    public static class DataBean extends BaseResponse.DataBean{
        /**
         * list : {"id":"1281","username":"王五-自如公寓","room_id":"4","type":"1","avatar":"http://zy.zhagen.com/origin/20180118/708dcdc50e0cfa5a2fef0b24c6407e68_1516252198.png","company_name":"自如公寓","logo":null,"house_title":"望花路10号院12号楼-次卧","house_area":"二室一厅一卫-11.00m²","company_score":"4.0","company_conent":"sadfsazjsdj","company_label":["衣着整洁","准时到达"],"member_score":"4.0","member_conent":"嗯嗯呃","member_label":["准时到达","服务很nice","态度很好"],"house_score":"5.0","house_conent":"asdsaszadsdd","album":["http://zy.zhagen.com/uploads/images/20171113/15105384199c30f665e9326c8b.jpg","http://zy.zhagen.com/uploads/images/20171113/151053841913c13d40df2dc277.jpg","http://zy.zhagen.com/uploads/images/20171113/151053841974092a7c129871c7.jpg","http://zy.zhagen.com/uploads/images/20171113/151053841968b0e3b5da3e6006.jpg","http://zy.zhagen.com/uploads/images/20171113/1510538419aabe078a98674fb1.jpg","http://zy.zhagen.com/uploads/images/20171113/151053842061d25efd2882553d.jpg"],"house_label":["准时到达","服务很nice"]}
         */

        private ListBean list;

        public ListBean getList() {
            return list;
        }

        public void setList(ListBean list) {
            this.list = list;
        }

        public static class ListBean {
            /**
             * id : 1281
             * username : 王五-自如公寓
             * room_id : 4
             * type : 1
             * avatar : http://zy.zhagen.com/origin/20180118/708dcdc50e0cfa5a2fef0b24c6407e68_1516252198.png
             * company_name : 自如公寓
             * logo : null
             * house_title : 望花路10号院12号楼-次卧
             * house_area : 二室一厅一卫-11.00m²
             * company_score : 4.0
             * company_conent : sadfsazjsdj
             * company_label : ["衣着整洁","准时到达"]
             * member_score : 4.0
             * member_conent : 嗯嗯呃
             * member_label : ["准时到达","服务很nice","态度很好"]
             * house_score : 5.0
             * house_conent : asdsaszadsdd
             * album : ["http://zy.zhagen.com/uploads/images/20171113/15105384199c30f665e9326c8b.jpg","http://zy.zhagen.com/uploads/images/20171113/151053841913c13d40df2dc277.jpg","http://zy.zhagen.com/uploads/images/20171113/151053841974092a7c129871c7.jpg","http://zy.zhagen.com/uploads/images/20171113/151053841968b0e3b5da3e6006.jpg","http://zy.zhagen.com/uploads/images/20171113/1510538419aabe078a98674fb1.jpg","http://zy.zhagen.com/uploads/images/20171113/151053842061d25efd2882553d.jpg"]
             * house_label : ["准时到达","服务很nice"]
             */

            private String id;
            private String username;
            private String room_id;
            private String type;
            private String avatar;
            private String company_name;
            private Object logo;
            private String house_title;
            private String house_area;
            private String company_score;
            private String company_conent;
            private String member_score;
            private String member_conent;
            private String house_score;
            private String house_conent;
            private List<String> company_label;
            private List<String> member_label;
            private List<String> album;
            private List<String> house_label;

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

            public String getRoom_id() {
                return room_id;
            }

            public void setRoom_id(String room_id) {
                this.room_id = room_id;
            }

            public String getType() {
                return type;
            }

            public void setType(String type) {
                this.type = type;
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

            public Object getLogo() {
                return logo;
            }

            public void setLogo(Object logo) {
                this.logo = logo;
            }

            public String getHouse_title() {
                return house_title;
            }

            public void setHouse_title(String house_title) {
                this.house_title = house_title;
            }

            public String getHouse_area() {
                return house_area;
            }

            public void setHouse_area(String house_area) {
                this.house_area = house_area;
            }

            public String getCompany_score() {
                return company_score;
            }

            public void setCompany_score(String company_score) {
                this.company_score = company_score;
            }

            public String getCompany_conent() {
                return company_conent;
            }

            public void setCompany_conent(String company_conent) {
                this.company_conent = company_conent;
            }

            public String getMember_score() {
                return member_score;
            }

            public void setMember_score(String member_score) {
                this.member_score = member_score;
            }

            public String getMember_conent() {
                return member_conent;
            }

            public void setMember_conent(String member_conent) {
                this.member_conent = member_conent;
            }

            public String getHouse_score() {
                return house_score;
            }

            public void setHouse_score(String house_score) {
                this.house_score = house_score;
            }

            public String getHouse_conent() {
                return house_conent;
            }

            public void setHouse_conent(String house_conent) {
                this.house_conent = house_conent;
            }

            public List<String> getCompany_label() {
                return company_label;
            }

            public void setCompany_label(List<String> company_label) {
                this.company_label = company_label;
            }

            public List<String> getMember_label() {
                return member_label;
            }

            public void setMember_label(List<String> member_label) {
                this.member_label = member_label;
            }

            public List<String> getAlbum() {
                return album;
            }

            public void setAlbum(List<String> album) {
                this.album = album;
            }

            public List<String> getHouse_label() {
                return house_label;
            }

            public void setHouse_label(List<String> house_label) {
                this.house_label = house_label;
            }
        }
    }
}
