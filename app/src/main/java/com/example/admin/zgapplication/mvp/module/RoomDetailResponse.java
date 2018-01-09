package com.example.admin.zgapplication.mvp.module;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by fushuang on 2018/1/3.
 */

public class RoomDetailResponse extends BaseResponse<RoomDetailResponse.DataBean>{


    public static class DataBean extends BaseResponse.DataBean{
        /**
         * house_id : 100
         * room_id : 134
         * type : 1
         * room_number : 55
         * house_photo : ["uploads/images/20171122/151132927859f8e5b4b4aca154.jpg","uploads/images/20171122/1511329278dfe72487a81924e3.jpg","uploads/images/20171122/15113292786ed550d7ab5f2d8e.jpg","uploads/images/20171122/1511329279fc8b17a8afa13056.jpg","uploads/images/20171122/151132928001f30b2b3659e27c.jpg","uploads/images/20171122/15113292806d4dffaaf8242888.jpg","uploads/images/20171122/1511329281765af74efd45b1f3.jpg","uploads/images/20171122/1511329282b1c432a252b4cc1c.jpg","uploads/images/20171122/151132928257eb2cc4e58d1326.jpg","uploads/images/20171122/1511329282097f96f56252529e.jpg"]
         * house_title : 链家公寓大兴康乐园308号楼-整租
         * house_kind : 一室一厅二卫-90.00
         * rental : 9000
         * labels : []
         * service_charge : 5.0
         * intermediary_fee : 1.0
         * room_n : []
         * seven : 0
         * all : 0
         * collect : 0
         * house_code : BJ0002B18509
         * house_type : 一室一厅二卫
         * space_area : 90.00
         * floor : 5/5
         * room : ["1","0","0","0","0","0","0","0","0","0","0","0","0","0","0","0"]
         * public : ["0","1","0","0","0","0","1","1","1","1","1","1","1","1","0","0"]
         * house_address : 北京-大兴-旧宫;大兴大兴旧宫万源南里南侧
         * house_desc : 胜多负少的方式
         * logo : http://zy.zhagen.com/
         * company_name : 链家公寓
         * on_rent : 44
         * count_order : 114
         * agent_sum : 5
         * same : [{"house_photo":"http://zy.zhagen.com/uploads/images/20171116/1510835458dfe72487a81924e3.jpg","house_title":"西城区-系统标题-整租","rental":7000},{"house_photo":"http://zy.zhagen.com/uploads/images/20171117/15108871696d4dffaaf8242888.jpg","house_title":"朝阳区-系统标题-整租","rental":1200},{"house_photo":"http://zy.zhagen.com/uploads/images/20171121/1511247299820bb345dd97c324.jpg","house_title":"朝阳区-系统标题-整租","rental":1678},{"house_photo":"http://zy.zhagen.com/uploads/images/20171122/151132927859f8e5b4b4aca154.jpg","house_title":"大兴区-系统标题-整租","rental":9000},{"house_photo":"http://zy.zhagen.com/uploads/images/20171122/15113293079ed7be47aee5ddf3.jpg","house_title":"大兴区-系统标题-主卧","rental":5000}]
         * pay_method : [{"method_name":"押1付1","pay":"1","pledge":"1"},{"method_name":"押1付3","pay":"3","pledge":"1"}]
         * date : [1,2,3,4,5,6,7,8,9,10,11,12]
         */

        private String house_id;
        private String room_id;
        private String type;
        private String room_number;
        private String house_title;
        private String house_kind;
        private String rental;
        private String service_charge;
        private String intermediary_fee;
        private String seven;
        private String all;
        private String collect;
        private String house_code;
        private String house_type;
        private String space_area;
        private String floor;
        private String house_address;
        private String house_desc;
        private String logo;
        private String company_name;
        private int on_rent;
        private String count_order;
        private String agent_sum;
        private List<String> house_photo;
        private List<String> labels;
        private List<RoomNBean> room_n;
        private List<String> room;
        @SerializedName("public")
        private List<String> publicX;
        private List<SameBean> same;
        private List<PayMethodBean> pay_method;
        private List<Integer> date;

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

        public String getRoom_number() {
            return room_number;
        }

        public void setRoom_number(String room_number) {
            this.room_number = room_number;
        }

        public String getHouse_title() {
            return house_title;
        }

        public void setHouse_title(String house_title) {
            this.house_title = house_title;
        }

        public String getHouse_kind() {
            return house_kind;
        }

        public void setHouse_kind(String house_kind) {
            this.house_kind = house_kind;
        }

        public String getRental() {
            return rental;
        }

        public void setRental(String rental) {
            this.rental = rental;
        }

        public String getService_charge() {
            return service_charge;
        }

        public void setService_charge(String service_charge) {
            this.service_charge = service_charge;
        }

        public String getIntermediary_fee() {
            return intermediary_fee;
        }

        public void setIntermediary_fee(String intermediary_fee) {
            this.intermediary_fee = intermediary_fee;
        }

        public String getSeven() {
            return seven;
        }

        public void setSeven(String seven) {
            this.seven = seven;
        }

        public String getAll() {
            return all;
        }

        public void setAll(String all) {
            this.all = all;
        }

        public String getCollect() {
            return collect;
        }

        public void setCollect(String collect) {
            this.collect = collect;
        }

        public String getHouse_code() {
            return house_code;
        }

        public void setHouse_code(String house_code) {
            this.house_code = house_code;
        }

        public String getHouse_type() {
            return house_type;
        }

        public void setHouse_type(String house_type) {
            this.house_type = house_type;
        }

        public String getSpace_area() {
            return space_area;
        }

        public void setSpace_area(String space_area) {
            this.space_area = space_area;
        }

        public String getFloor() {
            return floor;
        }

        public void setFloor(String floor) {
            this.floor = floor;
        }

        public String getHouse_address() {
            return house_address;
        }

        public void setHouse_address(String house_address) {
            this.house_address = house_address;
        }

        public String getHouse_desc() {
            return house_desc;
        }

        public void setHouse_desc(String house_desc) {
            this.house_desc = house_desc;
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

        public int getOn_rent() {
            return on_rent;
        }

        public void setOn_rent(int on_rent) {
            this.on_rent = on_rent;
        }

        public String getCount_order() {
            return count_order;
        }

        public void setCount_order(String count_order) {
            this.count_order = count_order;
        }

        public String getAgent_sum() {
            return agent_sum;
        }

        public void setAgent_sum(String agent_sum) {
            this.agent_sum = agent_sum;
        }

        public List<String> getHouse_photo() {
            return house_photo;
        }

        public void setHouse_photo(List<String> house_photo) {
            this.house_photo = house_photo;
        }

        public List<String> getLabels() {
            return labels;
        }

        public void setLabels(List<String> labels) {
            this.labels = labels;
        }

        public List<RoomNBean> getRoom_n() {
            return room_n;
        }

        public void setRoom_n(List<RoomNBean> room_n) {
            this.room_n = room_n;
        }

        public List<String> getRoom() {
            return room;
        }

        public void setRoom(List<String> room) {
            this.room = room;
        }

        public List<String> getPublicX() {
            return publicX;
        }

        public void setPublicX(List<String> publicX) {
            this.publicX = publicX;
        }

        public List<SameBean> getSame() {
            return same;
        }

        public void setSame(List<SameBean> same) {
            this.same = same;
        }

        public List<PayMethodBean> getPay_method() {
            return pay_method;
        }

        public void setPay_method(List<PayMethodBean> pay_method) {
            this.pay_method = pay_method;
        }

        public List<Integer> getDate() {
            return date;
        }

        public void setDate(List<Integer> date) {
            this.date = date;
        }


        public static class SameBean {
            /**
             * house_photo : http://zy.zhagen.com/uploads/images/20171116/1510835458dfe72487a81924e3.jpg
             * house_title : 西城区-系统标题-整租
             * rental : 7000
             */

            private String house_photo;
            private String house_title;
            private int rental;

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

            public int getRental() {
                return rental;
            }

            public void setRental(int rental) {
                this.rental = rental;
            }
        }

        public static class PayMethodBean {
            /**
             * method_name : 押1付1
             * pay : 1
             * pledge : 1
             */

            private String method_name;
            private String pay;
            private String pledge;

            public String getMethod_name() {
                return method_name;
            }

            public void setMethod_name(String method_name) {
                this.method_name = method_name;
            }

            public String getPay() {
                return pay;
            }

            public void setPay(String pay) {
                this.pay = pay;
            }

            public String getPledge() {
                return pledge;
            }

            public void setPledge(String pledge) {
                this.pledge = pledge;
            }
        }

        public static class RoomNBean {
            public String room_number;
            public String rental;
            public String room_id;

            public String getRoom_number() {
                return room_number;
            }

            public void setRoom_number(String room_number) {
                this.room_number = room_number;
            }

            public String getRental() {
                return rental;
            }

            public void setRental(String rental) {
                this.rental = rental;
            }

            public String getRoom_id() {
                return room_id;
            }

            public void setRoom_id(String room_id) {
                this.room_id = room_id;
            }
        }
    }
}
