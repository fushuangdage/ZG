package com.example.admin.zgapplication.mvp.module;

import java.util.List;

/**
 * Created by fushuang on 2018/1/9.
 */

public class AgentListResponse extends BaseResponse<AgentListResponse.DataBean> {


    public static class DataBean extends BaseResponse.DataBean{
        /**
         * data : [{"username":"张三-扎根公寓","telephone":"13261129586","id":"1","avatar":"","district":"回龙观,望京","company_name":"扎根公寓","house_sum":"189","visit_sum":"0","order_sum":"95","score":0,"label":["准时到达","服务很nice"]},{"username":"李四-链家公寓","telephone":"13261129587","id":"2","avatar":"","district":"回龙观,望京","company_name":"链家公寓","house_sum":"54","visit_sum":"2","order_sum":"7","score":0,"label":["准时到达","服务很nice"]},{"username":"王五-自如公寓","telephone":"13261129588","id":"3","avatar":"","district":"回龙观,望京","company_name":"自如公寓","house_sum":"162","visit_sum":"0","order_sum":"101","score":0,"label":["准时到达","服务很nice"]},{"username":"赵六-爱家公寓","telephone":"13261129589","id":"4","avatar":"","district":"回龙观,望京","company_name":"爱家公寓","house_sum":"31","visit_sum":"0","order_sum":"32","score":0,"label":["准时到达","服务很nice"]},{"username":"Carrot-链家公寓","telephone":"13811112222","id":"30","avatar":"","district":"回龙观,望京","company_name":"链家公寓","house_sum":"173","visit_sum":"0","order_sum":"36","score":0,"label":["准时到达","服务很nice"]},{"username":"丑帅的石老师","telephone":"18401453110","id":"69","avatar":"","district":"回龙观,望京","company_name":"扎根公寓","house_sum":"188","visit_sum":"0","order_sum":"35","score":0,"label":["准时到达","服务很nice"]},{"username":"又又","telephone":"13111112222","id":"82","avatar":"","district":"回龙观,望京","company_name":"链家公寓","house_sum":"166","visit_sum":"0","order_sum":"8","score":0,"label":["准时到达","服务很nice"]},{"username":"丑帅的石老师","telephone":"18401453110","id":"84","avatar":"","district":"回龙观,望京","company_name":"链家公寓","house_sum":"167","visit_sum":"0","order_sum":"10","score":0,"label":["准时到达","服务很nice"]},{"username":"李明","telephone":"18231518061","id":"138","avatar":"","district":"回龙观,望京","company_name":"梦想公寓","house_sum":"10","visit_sum":"0","order_sum":"3","score":0,"label":["准时到达","服务很nice"]},{"username":"我是鼠叔","telephone":"13811112222","id":"140","avatar":"","district":"回龙观,望京","company_name":"胡萝卜研究所","house_sum":"9","visit_sum":"0","order_sum":"2","score":0,"label":["准时到达","服务很nice"]}]
         * page : 1
         * sum_page : 4
         * house_id : 0
         * room_id : 3
         */
        private int house_id;
        private String room_id;
        private List<ListBean> list;

        public int getHouse_id() {
            return house_id;
        }

        public void setHouse_id(int house_id) {
            this.house_id = house_id;
        }

        public String getRoom_id() {
            return room_id;
        }

        public void setRoom_id(String room_id) {
            this.room_id = room_id;
        }

        public List<ListBean> getList() {
            return list;
        }

        public void setList(List<ListBean> list) {
            this.list = list;
        }

        public static class ListBean {
            /**
             * username : 张三-扎根公寓
             * telephone : 13261129586
             * id : 1
             * avatar :
             * district : 回龙观,望京
             * company_name : 扎根公寓
             * house_sum : 189
             * visit_sum : 0
             * order_sum : 95
             * score : 0
             * label : ["准时到达","服务很nice"]
             */

            private String username;
            private String telephone;
            private String id;
            private String avatar;
            private String district;
            private String company_name;
            private String house_sum;
            private String visit_sum;
            private String order_sum;
            private float score;
            private List<String> label;

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

            public String getDistrict() {
                return district;
            }

            public void setDistrict(String district) {
                this.district = district;
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
