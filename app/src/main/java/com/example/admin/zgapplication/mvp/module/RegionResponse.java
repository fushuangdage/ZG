package com.example.admin.zgapplication.mvp.module;

import java.util.List;

/**
 * Created by fushuang on 2017/12/27.
 */

public class RegionResponse extends BaseResponse<RegionResponse.DataBean> {


    public static class DataBean extends BaseResponse.DataBean{

        private List<ListBean> list;

        public List<ListBean> getList() {
            return list;
        }

        public void setList(List<ListBean> list) {
            this.list = list;
        }

        public static class ListBean extends BaseRegion{

            /**
             * id : 20
             * name : 昌平
             * parent : 0
             * city : 655
             * province : 668
             * country : 654
             * create_at : 0
             * update_at : 0
             * deleted : 0
             * sub : [{"id":"54","name":"回龙观","parent":"20","city":"655","province":"668","country":"654","create_at":"0","update_at":"0","deleted":"0"},{"id":"335","name":"天通苑","parent":"20","city":"655","province":"668","country":"654","create_at":"0","update_at":"0","deleted":"0"},{"id":"345","name":"北七家","parent":"20","city":"655","province":"668","country":"654","create_at":"0","update_at":"0","deleted":"0"},{"id":"346","name":"昌平县城","parent":"20","city":"655","province":"668","country":"654","create_at":"0","update_at":"0","deleted":"0"},{"id":"348","name":"霍营","parent":"20","city":"655","province":"668","country":"654","create_at":"0","update_at":"0","deleted":"0"},{"id":"349","name":"立水桥","parent":"20","city":"655","province":"668","country":"654","create_at":"0","update_at":"0","deleted":"0"},{"id":"350","name":"龙泽","parent":"20","city":"655","province":"668","country":"654","create_at":"0","update_at":"0","deleted":"0"},{"id":"351","name":"南口","parent":"20","city":"655","province":"668","country":"654","create_at":"0","update_at":"0","deleted":"0"},{"id":"352","name":"沙河","parent":"20","city":"655","province":"668","country":"654","create_at":"0","update_at":"0","deleted":"0"},{"id":"353","name":"小汤山","parent":"20","city":"655","province":"668","country":"654","create_at":"0","update_at":"0","deleted":"0"},{"id":"354","name":"百善镇","parent":"20","city":"655","province":"668","country":"654","create_at":"0","update_at":"0","deleted":"0"},{"id":"355","name":"阳坊","parent":"20","city":"655","province":"668","country":"654","create_at":"0","update_at":"0","deleted":"0"}]
             */

            public List<BaseRegion> sub;

            public List<BaseRegion> getSub() {
                return sub;
            }

            public void setSub(List<BaseRegion> sub) {
                this.sub = sub;
            }

        }

    }

    public static class BaseRegion{

        private Integer id;
        private String name;
        private String parent;
        private String city;
        private String province;
        private String country;
        private String create_at;
        private String update_at;
        private String deleted;

        public Integer getId() {
            return id;
        }

        public void setId(Integer id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getParent() {
            return parent;
        }

        public void setParent(String parent) {
            this.parent = parent;
        }

        public String getCity() {
            return city;
        }

        public void setCity(String city) {
            this.city = city;
        }

        public String getProvince() {
            return province;
        }

        public void setProvince(String province) {
            this.province = province;
        }

        public String getCountry() {
            return country;
        }

        public void setCountry(String country) {
            this.country = country;
        }

        public String getCreate_at() {
            return create_at;
        }

        public void setCreate_at(String create_at) {
            this.create_at = create_at;
        }

        public String getUpdate_at() {
            return update_at;
        }

        public void setUpdate_at(String update_at) {
            this.update_at = update_at;
        }

        public String getDeleted() {
            return deleted;
        }

        public void setDeleted(String deleted) {
            this.deleted = deleted;
        }
    }

}
