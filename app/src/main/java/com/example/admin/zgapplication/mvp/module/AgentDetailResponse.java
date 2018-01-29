package com.example.admin.zgapplication.mvp.module;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by fushuang on 2018/1/11.
 */

public class AgentDetailResponse extends BaseResponse<AgentDetailResponse.DataBean> {

    public static class DataBean extends BaseResponse.DataBean{
        /**
         * username : 王五-自如公寓
         * telephone : 13261129588
         * avatar :
         * company_name : 自如公寓
         * logo : null
         * id : 3
         * district : 回龙观,望京
         * code : ziru
         * house_sum : 162
         * visit_sum : 0
         * order_sum : 101
         * score : 0
         * label : ["准时到达","服务很nice"]
         * agent_count : 4
         * house_count : 80
         * list : [{"house_id":"170","type":"2","house_photo":"http://zy.zhagen.com/uploads/images/20171120/15111429314fb44972f92cdcfe.jpg","house_title":"北苑32号院205号楼-双人间","house_info":"一室零厅零卫-30.00","house_address":"北京市朝阳区北苑","house_rental":"1234","house_label":["简单大方","装修地中海风","可以养宠物","全新墙纸","阳台视野广阔"]},{"house_id":"197","type":"2","house_photo":"http://zy.zhagen.com/uploads/images/20171120/15111669166ed550d7ab5f2d8e.jpg","house_title":"花家地街5号院A座-简单风","house_info":"一室一厅一卫-30.00","house_address":"北京市朝阳区望京","house_rental":"1000","house_label":["简单大方","装修地中海风","可以养宠物","全新墙纸","阳台视野广阔"]},{"house_id":"225","type":"2","house_photo":"http://zy.zhagen.com/uploads/images/20171130/1512021935abe229268cf2d33d.jpg","house_title":"北苑131号院405号楼-田野风","house_info":"一室零厅零卫-30.00","house_address":"北京市通州区通州北苑","house_rental":"2323","house_label":[]},{"house_id":"245","type":"2","house_photo":"http://zy.zhagen.com/uploads/images/20171130/1512022090814c70cf24ced188.jpg","house_title":"海淀大街44楼2号楼-单身公寓","house_info":"四室一厅二卫-220.00","house_address":"北京市海淀区中关村","house_rental":"2000","house_label":["简单大方","装修地中海风","可以养宠物","全新墙纸","阳台视野广阔"]},{"house_id":"246","type":"2","house_photo":"http://zy.zhagen.com/uploads/images/20171130/1512024040cb323247e3901015.jpg","house_title":"海户西里33号院2号楼-小单间","house_info":"三室一厅二卫-150.00","house_address":"北京市丰台区马家堡","house_rental":"5000","house_label":["简单大方","装修地中海风","可以养宠物","全新墙纸","阳台视野广阔"]},{"house_id":"93","type":"1","room_id":"120","house_photo":"http://zy.zhagen.com/uploads/images/20171120/1511167312ab1d1373810d29e2.jpg","house_title":"北纬40度1101号楼-整租","house_info":"一室一厅一卫-20.00","house_address":"北京市朝阳区望京","house_rental":"2000","house_label":["阳台视野广阔"]},{"house_id":"94","type":"1","room_id":"121","house_photo":"http://zy.zhagen.com/uploads/images/20171121/1511230711ecbc13ad50f3e439.jpg","house_title":"望京路4号院12号楼-整租","house_info":"一室一厅一卫-20.00","house_address":"北京市朝阳区望京","house_rental":"2000","house_label":["阳台视野广阔"]},{"house_id":"104","type":"1","room_id":"144","house_photo":"http://zy.zhagen.com/uploads/images/20171127/151175371659f8e5b4b4aca154.jpg","house_title":"望京路4号院A座-整租","house_info":"一室一厅一卫-30.00","house_address":"北京市朝阳区望京","house_rental":"1000","house_label":["阳台视野广阔"]},{"house_id":"126","type":"1","room_id":"182","house_photo":"http://zy.zhagen.com/uploads/images/20171130/1512023735e8e8eebf6b1c253b.jpg","house_title":"东直门外大街34号12号楼-整租","house_info":"四室二厅二卫-150.00","house_address":"北京市东城区东直门","house_rental":"8000","house_label":["简单大方","装修地中海风","可以养宠物","全新墙纸","阳台视野广阔"]},{"house_id":"128","type":"1","room_id":"186","house_photo":"http://zy.zhagen.com/uploads/images/20171130/1512024285ab1d1373810d29e2.jpg","house_title":"东四十条甲34号院\t\t\t\t\t12号楼-整租","house_info":"三室一厅一卫-120.00","house_address":"北京市东城区东四十条","house_rental":"30000","house_label":["简单大方","装修地中海风","可以养宠物","全新墙纸","阳台视野广阔"]}]
         * sum_page : 4
         * page : 1
         */

        private String username;
        private String telephone;
        private String avatar;
        private String company_name;
        private String company_id;
        private String logo;
        private String id;
        private String district;
        @SerializedName("code")
        private String codeX;
        private String house_sum;
        private String visit_sum;
        private String order_sum;
        private float score;
        private String agent_count;
        private String house_count;
        private List<String> label;
        private List<ListBean> list;


        public String getCompany_id() {
            return company_id;
        }

        public void setCompany_id(String company_id) {
            this.company_id = company_id;
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

        public String getLogo() {
            return logo;
        }

        public void setLogo(String logo) {
            this.logo = logo;
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

        public String getCodeX() {
            return codeX;
        }

        public void setCodeX(String codeX) {
            this.codeX = codeX;
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

        public float getScore() {
            return score;
        }

        public void setScore(float score) {
            this.score = score;
        }

        public String getAgent_count() {
            return agent_count;
        }

        public void setAgent_count(String agent_count) {
            this.agent_count = agent_count;
        }

        public String getHouse_count() {
            return house_count;
        }

        public void setHouse_count(String house_count) {
            this.house_count = house_count;
        }


        public List<String> getLabel() {
            return label;
        }

        public void setLabel(List<String> label) {
            this.label = label;
        }

        public List<ListBean> getList() {
            return list;
        }

        public void setList(List<ListBean> list) {
            this.list = list;
        }

        public static class ListBean {
            /**
             * house_id : 170
             * type : 2
             * house_photo : http://zy.zhagen.com/uploads/images/20171120/15111429314fb44972f92cdcfe.jpg
             * house_title : 北苑32号院205号楼-双人间
             * house_info : 一室零厅零卫-30.00
             * house_address : 北京市朝阳区北苑
             * house_rental : 1234
             * house_label : ["简单大方","装修地中海风","可以养宠物","全新墙纸","阳台视野广阔"]
             * room_id : 120
             */

            private String house_id;
            private String type;
            private String house_photo;
            private String house_title;
            private String house_info;
            private String house_address;
            private String house_rental;
            private String room_id;
            private List<String> house_label;

            public String getHouse_id() {
                return house_id;
            }

            public void setHouse_id(String house_id) {
                this.house_id = house_id;
            }

            public String getType() {
                return type;
            }

            public void setType(String type) {
                this.type = type;
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

            public String getHouse_info() {
                return house_info;
            }

            public void setHouse_info(String house_info) {
                this.house_info = house_info;
            }

            public String getHouse_address() {
                return house_address;
            }

            public void setHouse_address(String house_address) {
                this.house_address = house_address;
            }

            public String getHouse_rental() {
                return house_rental;
            }

            public void setHouse_rental(String house_rental) {
                this.house_rental = house_rental;
            }

            public String getRoom_id() {
                return room_id;
            }

            public void setRoom_id(String room_id) {
                this.room_id = room_id;
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
