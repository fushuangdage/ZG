package com.example.admin.zgapplication.mvp.module;

import java.util.List;

/**
 * Created by fushuang on 2018/1/19.
 */

public class MakeEvaluationDetailResponse {


    /**
     * msg : 成功
     * code : 0
     * data : {"list":{"member_label":["衣着整洁","准时到达","服务很nice","态度很好"],"member_label":["1","2","3","4"],"house_label":["环境很nice","适合居住","阳光很好","交通便利"],"house_label":["5","6","7","8"],"company_label":["服务很好","值得信赖","销售达人"],"company_label":["9","10","11"],"id":"1396","room_id":"101","type":"1","member_id":"4","username":"赵六-爱家公寓","avatar":"","company_id":"4","company_name":"爱家公寓","logo":null,"house_title":"中海瓦尔登湖3号楼-整租","house_area":"二室一厅一卫-0.00m²"}}
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

    public static class DataBean {
        /**
         * list : {"member_label":["衣着整洁","准时到达","服务很nice","态度很好"],"member_label":["1","2","3","4"],"house_label":["环境很nice","适合居住","阳光很好","交通便利"],"house_label":["5","6","7","8"],"company_label":["服务很好","值得信赖","销售达人"],"company_label":["9","10","11"],"id":"1396","room_id":"101","type":"1","member_id":"4","username":"赵六-爱家公寓","avatar":"","company_id":"4","company_name":"爱家公寓","logo":null,"house_title":"中海瓦尔登湖3号楼-整租","house_area":"二室一厅一卫-0.00m²"}
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
             * member_label : ["衣着整洁","准时到达","服务很nice","态度很好"]
             * member_label : ["1","2","3","4"]
             * house_label : ["环境很nice","适合居住","阳光很好","交通便利"]
             * house_label : ["5","6","7","8"]
             * company_label : ["服务很好","值得信赖","销售达人"]
             * company_label : ["9","10","11"]
             * id : 1396
             * room_id : 101
             * type : 1
             * member_id : 4
             * username : 赵六-爱家公寓
             * avatar :
             * company_id : 4
             * company_name : 爱家公寓
             * logo : null
             * house_title : 中海瓦尔登湖3号楼-整租
             * house_area : 二室一厅一卫-0.00m²
             */

            private String id;
            private String room_id;
            private String type;
            private String member_id;
            private String username;
            private String avatar;
            private String company_id;
            private String company_name;
            private Object logo;
            private String house_title;
            private String house_area;
            private List<String> member_label;
            private List<String> house_label;
            private List<String> company_label;

            public String getId() {
                return id;
            }

            public void setId(String id) {
                this.id = id;
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

            public String getMember_id() {
                return member_id;
            }

            public void setMember_id(String member_id) {
                this.member_id = member_id;
            }

            public String getUsername() {
                return username;
            }

            public void setUsername(String username) {
                this.username = username;
            }

            public String getAvatar() {
                return avatar;
            }

            public void setAvatar(String avatar) {
                this.avatar = avatar;
            }

            public String getCompany_id() {
                return company_id;
            }

            public void setCompany_id(String company_id) {
                this.company_id = company_id;
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

            public List<String> getMember_label() {
                return member_label;
            }

            public void setMember_label(List<String> member_label) {
                this.member_label = member_label;
            }


            public List<String> getHouse_label() {
                return house_label;
            }

            public void setHouse_label(List<String> house_label) {
                this.house_label = house_label;
            }


            public List<String> getCompany_label() {
                return company_label;
            }

            public void setCompany_label(List<String> company_label) {
                this.company_label = company_label;
            }


        }
    }
}
