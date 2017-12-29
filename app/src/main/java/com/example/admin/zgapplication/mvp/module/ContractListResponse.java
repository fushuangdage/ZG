package com.example.admin.zgapplication.mvp.module;

import java.util.List;

/**
 * Created by fushuang on 2017/12/26.
 */

public class ContractListResponse extends BaseResponse<ContractListResponse.DataBean> {

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
             * title : 大华锦绣华城第12街区12号楼-C户型
             * status : 履行中
             * img : http://zy.zhagen.com/uploads/images/20171222/151393068459f8e5b4b4aca154.jpg
             * order : 201712221742053572558079
             * date : 2017-12-22至2018-12-21
             * rent : 8000
             * pay : 押1付12
             */

            private String title;
            private String status;
            private String img;
            private String order;
            private String date;
            private String rent;
            private String pay;

            public String getTitle() {
                return title;
            }

            public void setTitle(String title) {
                this.title = title;
            }

            public String getStatus() {
                return status;
            }

            public void setStatus(String status) {
                this.status = status;
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
        }
    }
}
