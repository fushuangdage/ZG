package com.example.admin.zgapplication.mvp.module;

/**
 * Created by fushuang on 2017/12/28.
 */

public class ContactDetailResponse extends BaseResponse<ContactDetailResponse.DataBean> {


    public static class DataBean extends BaseResponse.DataBean {
        /**
         * title : 大华锦绣华城第12街区12号楼-C户型
         * status : 履行中
         * img : http://zy.zhagen.com/uploads/images/20171222/151393068459f8e5b4b4aca154.jpg
         * order : 201712221742053572558079
         * date : 2017-12-22至2018-12-21
         * rent : 8000
         * pay : 押1付12
         * photo :
         * agent : 沙沙
         * company_name : 白猫公寓
         * tel : 15555555555
         * rent_month : 12
         * room : 12
         * circle : 2017-12-22至2018-01-22
         * paying : 0
         */

        private String title;
        private String img;
        private String order;
        private String date;
        private String rent;
        private String pay;
        private String photo;
        private String agent;
        private String company_name;
        private String tel;
        private String rent_month;
        private String room;
        private String circle;
        private String paying;

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }


        public String getImg() {
            return img;
        }

        public void setImg(String img) {
            this.img = img;
        }

        public String getOrder() {
            return order;
        }

        public void setOrder(String order) {
            this.order = order;
        }

        public String getDate() {
            return date;
        }

        public void setDate(String date) {
            this.date = date;
        }

        public String getRent() {
            return rent;
        }

        public void setRent(String rent) {
            this.rent = rent;
        }

        public String getPay() {
            return pay;
        }

        public void setPay(String pay) {
            this.pay = pay;
        }

        public String getPhoto() {
            return photo;
        }

        public void setPhoto(String photo) {
            this.photo = photo;
        }

        public String getAgent() {
            return agent;
        }

        public void setAgent(String agent) {
            this.agent = agent;
        }

        public String getCompany_name() {
            return company_name;
        }

        public void setCompany_name(String company_name) {
            this.company_name = company_name;
        }

        public String getTel() {
            return tel;
        }

        public void setTel(String tel) {
            this.tel = tel;
        }

        public String getRent_month() {
            return rent_month;
        }

        public void setRent_month(String rent_month) {
            this.rent_month = rent_month;
        }

        public String getRoom() {
            return room;
        }

        public void setRoom(String room) {
            this.room = room;
        }

        public String getCircle() {
            return circle;
        }

        public void setCircle(String circle) {
            this.circle = circle;
        }

        public String getPaying() {
            return paying;
        }

        public void setPaying(String paying) {
            this.paying = paying;
        }
    }
}
