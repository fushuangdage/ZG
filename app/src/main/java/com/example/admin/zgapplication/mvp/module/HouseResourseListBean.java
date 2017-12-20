package com.example.admin.zgapplication.mvp.module;

import java.util.List;

/**
 * Created by fushuang on 2017/12/15.
 */

public class HouseResourseListBean  extends BaseResponse{


    /**
     * msg : 操作成功
     * code : 0
     * data : {"list":[{"house_photo":"http://zy.zhagen.com/uploads/images/20171215/1513321460af3de894c5d33285.jpg","house_title":"骊龙园28号楼-28新户型","house_info":"一室零厅零卫-30.00","house_address":"北京市昌平区回龙观","house_rental":"1500","house_label":["全新墙纸","阳台视野广阔"]},{"house_photo":"http://zy.zhagen.com/uploads/images/20171119/15110709459c30f665e9326c8b.jpg","house_title":"西土城路8号院A区102号楼-蓝领间","house_info":"一室零厅零卫-30.00","house_address":"北京市海淀区蓟门桥","house_rental":"1500","house_label":["简单大方","阳台视野广阔"]},{"house_photo":"http://zy.zhagen.com/uploads/images/20171114/1510652326e18a7e9cdd7e5e0d.jpg","house_title":"北优soho1202号楼-E户型","house_info":"一室一厅一卫-30.00","house_address":"北京市昌平区沙河","house_rental":"3000"},{"house_photo":"http://zy.zhagen.com/uploads/images/20171205/151244203501f30b2b3659e27c.jpg","house_title":"刘家村126号院8号楼-BB户型","house_info":"二室一厅一卫-60.00","house_address":"北京市丰台区玉泉营","house_rental":"5000","house_label":["简单大方"]},{"house_photo":"http://zy.zhagen.com/uploads/images/20171114/1510652326e18a7e9cdd7e5e0d.jpg","house_title":"金鑫苑\t\t\t\t7号楼-E户型","house_info":"一室一厅一卫-30.00","house_address":"北京市房山区窦店","house_rental":"2800"},{"house_photo":"http://zy.zhagen.com/uploads/images/20171204/15123721509c30f665e9326c8b.jpg","house_title":"百子湾西里102号楼-大户型","house_info":"一室零厅一卫-32.00","house_address":"北京市朝阳区百子湾","house_rental":"1900","house_label":["阳台视野广阔"]},{"house_photo":"http://zy.zhagen.com/uploads/images/20171204/15123710514fb44972f92cdcfe.jpg","house_title":"领地一期A-A户型","house_info":"一室零厅零卫-30.00","house_address":"北京市朝阳区北苑","house_rental":"2500","house_label":["阳台视野广阔"]},{"house_photo":"http://zy.zhagen.com/uploads/images/20171204/1512366747633a6b0080f34d93.png","house_title":"北苑1号院Atop-a","house_info":"三室三厅三卫-30.00","house_address":"北京市朝阳区北苑","house_rental":"1000","house_label":["阳台视野广阔"]},{"house_photo":"http://zy.zhagen.com/uploads/images/20171114/15106499794fb44972f92cdcfe.jpg","house_title":"北苑1号院B区102号楼-A套户型","house_info":"一室零厅零卫-30.00","house_address":"北京市朝阳区北苑","house_rental":"2000","house_label":["简单大方","装修地中海风","可以养宠物","全新墙纸","阳台视野广阔"]},{"house_photo":"http://zy.zhagen.com/uploads/images/20171203/15123007282ce72def72685237.jpg","house_title":"建于果岭的上层建筑110-a","house_info":"二室一厅一卫-30.00","house_address":"北京市朝阳区望京","house_rental":"1000","house_label":["简单大方","装修地中海风","可以养宠物","全新墙纸","阳台视野广阔"]}],"page":1,"sum_page":7}
     */
    private DataBean data;

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * list : [{"house_photo":"http://zy.zhagen.com/uploads/images/20171215/1513321460af3de894c5d33285.jpg","house_title":"骊龙园28号楼-28新户型","house_info":"一室零厅零卫-30.00","house_address":"北京市昌平区回龙观","house_rental":"1500","house_label":["全新墙纸","阳台视野广阔"]},{"house_photo":"http://zy.zhagen.com/uploads/images/20171119/15110709459c30f665e9326c8b.jpg","house_title":"西土城路8号院A区102号楼-蓝领间","house_info":"一室零厅零卫-30.00","house_address":"北京市海淀区蓟门桥","house_rental":"1500","house_label":["简单大方","阳台视野广阔"]},{"house_photo":"http://zy.zhagen.com/uploads/images/20171114/1510652326e18a7e9cdd7e5e0d.jpg","house_title":"北优soho1202号楼-E户型","house_info":"一室一厅一卫-30.00","house_address":"北京市昌平区沙河","house_rental":"3000"},{"house_photo":"http://zy.zhagen.com/uploads/images/20171205/151244203501f30b2b3659e27c.jpg","house_title":"刘家村126号院8号楼-BB户型","house_info":"二室一厅一卫-60.00","house_address":"北京市丰台区玉泉营","house_rental":"5000","house_label":["简单大方"]},{"house_photo":"http://zy.zhagen.com/uploads/images/20171114/1510652326e18a7e9cdd7e5e0d.jpg","house_title":"金鑫苑\t\t\t\t7号楼-E户型","house_info":"一室一厅一卫-30.00","house_address":"北京市房山区窦店","house_rental":"2800"},{"house_photo":"http://zy.zhagen.com/uploads/images/20171204/15123721509c30f665e9326c8b.jpg","house_title":"百子湾西里102号楼-大户型","house_info":"一室零厅一卫-32.00","house_address":"北京市朝阳区百子湾","house_rental":"1900","house_label":["阳台视野广阔"]},{"house_photo":"http://zy.zhagen.com/uploads/images/20171204/15123710514fb44972f92cdcfe.jpg","house_title":"领地一期A-A户型","house_info":"一室零厅零卫-30.00","house_address":"北京市朝阳区北苑","house_rental":"2500","house_label":["阳台视野广阔"]},{"house_photo":"http://zy.zhagen.com/uploads/images/20171204/1512366747633a6b0080f34d93.png","house_title":"北苑1号院Atop-a","house_info":"三室三厅三卫-30.00","house_address":"北京市朝阳区北苑","house_rental":"1000","house_label":["阳台视野广阔"]},{"house_photo":"http://zy.zhagen.com/uploads/images/20171114/15106499794fb44972f92cdcfe.jpg","house_title":"北苑1号院B区102号楼-A套户型","house_info":"一室零厅零卫-30.00","house_address":"北京市朝阳区北苑","house_rental":"2000","house_label":["简单大方","装修地中海风","可以养宠物","全新墙纸","阳台视野广阔"]},{"house_photo":"http://zy.zhagen.com/uploads/images/20171203/15123007282ce72def72685237.jpg","house_title":"建于果岭的上层建筑110-a","house_info":"二室一厅一卫-30.00","house_address":"北京市朝阳区望京","house_rental":"1000","house_label":["简单大方","装修地中海风","可以养宠物","全新墙纸","阳台视野广阔"]}]
         * page : 1
         * sum_page : 7
         */

        private int page;
        private int sum_page;
        private List<ListBean> list;

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
             * house_photo : http://zy.zhagen.com/uploads/images/20171215/1513321460af3de894c5d33285.jpg
             * house_title : 骊龙园28号楼-28新户型
             * house_info : 一室零厅零卫-30.00
             * house_address : 北京市昌平区回龙观
             * house_rental : 1500
             * house_label : ["全新墙纸","阳台视野广阔"]
             */

            private String house_photo;
            private String house_title;
            private String house_info;
            private String house_address;
            private String house_rental;
            private List<String> house_label;

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
    }
}
