package com.example.admin.zgapplication.mvp.module;

/**
 * Created by fushuang on 2017/12/28.
 */

public class StartIntent extends BaseResponse<StartIntent.DataBean> {



    public static class DataBean extends BaseResponse.DataBean {
        /**
         * id : 38
         */

        private int id;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }
    }
}
