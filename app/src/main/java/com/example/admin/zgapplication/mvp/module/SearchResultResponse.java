package com.example.admin.zgapplication.mvp.module;

import java.io.Serializable;
import java.util.List;

/**
 * Created by fushuang on 2018/1/17.
 */

public class SearchResultResponse implements Serializable {


    /**
     * msg : 操作成功
     * code : 0
     * data : {"list":[{"house_id":"274","room_id":"0","type":"2","code_id":"2","house_photo":"http://zy.zhagen.com/uploads/images/20171113/1510542838dfe72487a81924e3.jpg","house_title":"和谐家园二区一号院101号楼-两居室","house_info":"二室一厅一卫-100.00","house_address":"北京市昌平区回龙观","house_rental":"1300","house_label":["简单大方","装修地中海风","可以养宠物","全新墙纸","阳台视野广阔"]},{"house_id":"370","room_id":"0","type":"2","code_id":"111","house_photo":"http://zy.zhagen.com/uploads/images/20171222/1513925528408500e339b7004c.jpg","house_title":"资尚财富公寓1号楼-A户型","house_info":"一室零厅一卫-30.00","house_address":"北京市昌平区回龙观","house_rental":"2000","house_label":["阳台视野广阔"]},{"house_id":"77","type":"1","room_id":"97","code_id":"2","house_photo":"http://zy.zhagen.com/uploads/images/20171116/1510835458dfe72487a81924e3.jpg","house_title":"链家公寓韩家胡同102号楼-整租","house_info":"一室一厅一卫-100.00","house_address":"北京市西城区大栅栏","house_rental":"7000","house_label":[]},{"house_id":"80","type":"1","room_id":"101","code_id":"4","house_photo":"http://zy.zhagen.com/uploads/images/20171117/1510886785af4a7b6060487f03.jpg","house_title":"爱家公寓中海瓦尔登湖3号楼-整租","house_info":"二室一厅一卫-0.00","house_address":"北京市昌平区回龙观","house_rental":"-1000","house_label":["阳台视野广阔"]}],"company":[{"company_id":1,"logo":"origin/20171203/a4c8c8bfde0bbed7996a040b799e43ac_1512270513.png","company_name":"扎根公寓","count_house":143,"count_member":"28","count_score":"5"},{"company_id":2,"logo":null,"company_name":"链家公寓","count_house":102,"count_member":"34","count_score":"5"},{"company_id":3,"logo":null,"company_name":"自如公寓","count_house":137,"count_member":"18","count_score":"5"},{"company_id":4,"logo":null,"company_name":"爱家公寓","count_house":51,"count_member":"18","count_score":"5"}],"keyword":"公寓"}
     */

    private String msg;
    private int code;
    private DataBean data;

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public static class DataBean implements Serializable{
        /**
         * list : [{"house_id":"274","room_id":"0","type":"2","code_id":"2","house_photo":"http://zy.zhagen.com/uploads/images/20171113/1510542838dfe72487a81924e3.jpg","house_title":"和谐家园二区一号院101号楼-两居室","house_info":"二室一厅一卫-100.00","house_address":"北京市昌平区回龙观","house_rental":"1300","house_label":["简单大方","装修地中海风","可以养宠物","全新墙纸","阳台视野广阔"]},{"house_id":"370","room_id":"0","type":"2","code_id":"111","house_photo":"http://zy.zhagen.com/uploads/images/20171222/1513925528408500e339b7004c.jpg","house_title":"资尚财富公寓1号楼-A户型","house_info":"一室零厅一卫-30.00","house_address":"北京市昌平区回龙观","house_rental":"2000","house_label":["阳台视野广阔"]},{"house_id":"77","type":"1","room_id":"97","code_id":"2","house_photo":"http://zy.zhagen.com/uploads/images/20171116/1510835458dfe72487a81924e3.jpg","house_title":"链家公寓韩家胡同102号楼-整租","house_info":"一室一厅一卫-100.00","house_address":"北京市西城区大栅栏","house_rental":"7000","house_label":[]},{"house_id":"80","type":"1","room_id":"101","code_id":"4","house_photo":"http://zy.zhagen.com/uploads/images/20171117/1510886785af4a7b6060487f03.jpg","house_title":"爱家公寓中海瓦尔登湖3号楼-整租","house_info":"二室一厅一卫-0.00","house_address":"北京市昌平区回龙观","house_rental":"-1000","house_label":["阳台视野广阔"]}]
         * company : [{"company_id":1,"logo":"origin/20171203/a4c8c8bfde0bbed7996a040b799e43ac_1512270513.png","company_name":"扎根公寓","count_house":143,"count_member":"28","count_score":"5"},{"company_id":2,"logo":null,"company_name":"链家公寓","count_house":102,"count_member":"34","count_score":"5"},{"company_id":3,"logo":null,"company_name":"自如公寓","count_house":137,"count_member":"18","count_score":"5"},{"company_id":4,"logo":null,"company_name":"爱家公寓","count_house":51,"count_member":"18","count_score":"5"}]
         * keyword : 公寓
         */

        private String keyword;
        private List<ListBean> list;
        private List<CompanyBean> company;

        public String getKeyword() {
            return keyword;
        }

        public void setKeyword(String keyword) {
            this.keyword = keyword;
        }

        public List<ListBean> getList() {
            return list;
        }

        public void setList(List<ListBean> list) {
            this.list = list;
        }

        public List<CompanyBean> getCompany() {
            return company;
        }

        public void setCompany(List<CompanyBean> company) {
            this.company = company;
        }

        public static class ListBean  implements Serializable{
            /**
             * house_id : 274
             * room_id : 0
             * type : 2
             * code_id : 2
             * house_photo : http://zy.zhagen.com/uploads/images/20171113/1510542838dfe72487a81924e3.jpg
             * house_title : 和谐家园二区一号院101号楼-两居室
             * house_info : 二室一厅一卫-100.00
             * house_address : 北京市昌平区回龙观
             * house_rental : 1300
             * house_label : ["简单大方","装修地中海风","可以养宠物","全新墙纸","阳台视野广阔"]
             */

            private String house_id;
            private String room_id;
            private String type;
            private String code_id;
            private String house_photo;
            private String house_title;
            private String house_info;
            private String house_address;
            private String house_rental;
            private List<String> house_label;

            public String getHouse_id() {
                return house_id;
            }

            public void setHouse_id(String house_id) {
                this.house_id = house_id;
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

            public String getCode_id() {
                return code_id;
            }

            public void setCode_id(String code_id) {
                this.code_id = code_id;
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

            public List<String> getHouse_label() {
                return house_label;
            }

            public void setHouse_label(List<String> house_label) {
                this.house_label = house_label;
            }
        }

        public static class CompanyBean implements Serializable {
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
