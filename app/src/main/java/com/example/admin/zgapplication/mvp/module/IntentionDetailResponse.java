package com.example.admin.zgapplication.mvp.module;

import java.util.List;

/**
 * Created by fushuang on 2018/1/28.
 */

public class IntentionDetailResponse  {

    /**
     * msg : 获取成功
     * code : 0
     * data : {"id":"2","outset":"2000","cutoff":"4000","room":"一室,二室,三室","method":"2","create_at":"1514267197","sum":"2","parent":"昌平","district":"回龙观","price":"2000-4000","room_id":"1,2,3","list":[{"username":"李四-链家公寓","telephone":"13261129587","avatar":"","company_name":"链家公寓","id":"2","district":"回龙观,望京","house_sum":"54","visit_sum":"2","order_sum":"7","score":"4.0","chat_intention":"快","label":["准时到达","服务很nice"]},{"username":"王五-自如公寓","telephone":"13261129588","avatar":"","company_name":"自如公寓","id":"3","district":"回龙观,望京","house_sum":"162","visit_sum":"0","order_sum":"101","score":0,"label":["准时到达","服务很nice"]}],"page":1,"sum_page":1}
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
         * id : 2
         * outset : 2000
         * cutoff : 4000
         * room : 一室,二室,三室
         * method : 2
         * create_at : 1514267197
         * sum : 2
         * parent : 昌平
         * district : 回龙观
         * price : 2000-4000
         * room_id : 1,2,3
         * list : [{"username":"李四-链家公寓","telephone":"13261129587","avatar":"","company_name":"链家公寓","id":"2","district":"回龙观,望京","house_sum":"54","visit_sum":"2","order_sum":"7","score":"4.0","chat_intention":"快","label":["准时到达","服务很nice"]},{"username":"王五-自如公寓","telephone":"13261129588","avatar":"","company_name":"自如公寓","id":"3","district":"回龙观,望京","house_sum":"162","visit_sum":"0","order_sum":"101","score":0,"label":["准时到达","服务很nice"]}]
         * page : 1
         * sum_page : 1
         */

        private String id;
        private String outset;
        private String cutoff;
        private String room;
        private int method;
        private long create_at;
        private String sum;
        private String parent;
        private String district;
        private String price;
        private String room_id;
        private int page;
        private int sum_page;
        private List<ListBean> list;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getOutset() {
            return outset;
        }

        public void setOutset(String outset) {
            this.outset = outset;
        }

        public String getCutoff() {
            return cutoff;
        }

        public void setCutoff(String cutoff) {
            this.cutoff = cutoff;
        }

        public String getRoom() {
            return room;
        }

        public void setRoom(String room) {
            this.room = room;
        }

        public int getMethod() {
            return method;
        }

        public void setMethod(int method) {
            this.method = method;
        }

        public long getCreate_at() {
            return create_at;
        }

        public void setCreate_at(long create_at) {
            this.create_at = create_at;
        }

        public String getSum() {
            return sum;
        }

        public void setSum(String sum) {
            this.sum = sum;
        }

        public String getParent() {
            return parent;
        }

        public void setParent(String parent) {
            this.parent = parent;
        }

        public String getDistrict() {
            return district;
        }

        public void setDistrict(String district) {
            this.district = district;
        }

        public String getPrice() {
            return price;
        }

        public void setPrice(String price) {
            this.price = price;
        }

        public String getRoom_id() {
            return room_id;
        }

        public void setRoom_id(String room_id) {
            this.room_id = room_id;
        }

        public int getPage() {
            return page;
        }

        public void setPage(int page) {
            this.page = page;
        }

        public int getSum_page() {
            return sum_page;
        }

        public void setSum_page(int sum_page) {
            this.sum_page = sum_page;
        }

        public List<ListBean> getList() {
            return list;
        }

        public void setList(List<ListBean> list) {
            this.list = list;
        }

        public static class ListBean {
            /**
             * username : 李四-链家公寓
             * telephone : 13261129587
             * avatar :
             * company_name : 链家公寓
             * id : 2
             * district : 回龙观,望京
             * house_sum : 54
             * visit_sum : 2
             * order_sum : 7
             * score : 4.0
             * chat_intention : 快
             * label : ["准时到达","服务很nice"]
             */

            private String username;
            private String telephone;
            private String avatar;
            private String company_name;
            private String id;
            private String district;
            private String house_sum;
            private String visit_sum;
            private String order_sum;
            private String score;
            private String chat_intention;
            private List<String> label;
            private String hx_username;

            public String getHx_username() {
                return hx_username;
            }

            public void setHx_username(String hx_username) {
                this.hx_username = hx_username;
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

            public String getScore() {
                return score;
            }

            public void setScore(String score) {
                this.score = score;
            }

            public String getChat_intention() {
                return chat_intention;
            }

            public void setChat_intention(String chat_intention) {
                this.chat_intention = chat_intention;
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
