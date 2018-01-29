package com.example.admin.zgapplication.ui.activity;

import android.util.Log;

import com.example.admin.zgapplication.Constant;
import com.example.admin.zgapplication.R;
import com.example.admin.zgapplication.base.BaseActivity;
import com.example.admin.zgapplication.utils.SPUtil;
import com.hyphenate.EMCallBack;
import com.hyphenate.chat.EMClient;

import cn.jpush.android.api.JPushInterface;

public class LaunchActivity extends BaseActivity {

    @Override
    public int setLayout() {
        return R.layout.activity_launch;
    }

    @Override
    public void initEvent() {

    }

    @Override
    public void initData() {


        Constant.hx_username = (String) SPUtil.get(mActivity, "hx_username", "");
        Constant.hx_password = (String) SPUtil.get(mActivity, "hx_password", "");
        Constant.username = (String) SPUtil.get(mActivity, "username", "");
        Constant.password=(String) SPUtil.get(mActivity, "password", "");
        Constant.uid=(String) SPUtil.get(mActivity, "uid", "33");


        if (!Constant.uid.equals("")&& !Constant.hx_username.equals("")){

            JPushInterface.setAlias(mActivity, 1,"u"+Constant.uid);

            EMClient.getInstance().login(Constant.hx_username, Constant.hx_password, new EMCallBack() {


                //        EMClient.getInstance().login("fushuang", "123456", new EMCallBack() {
                @Override
                public void onSuccess() {
                    Log.d("888888888888888888888", "onSuccess: 登陆成功");
                }


                @Override
                public void onError(int i, String s) {
                    Log.d("888888888888888888888", "onError: " + s);
                }

                @Override
                public void onProgress(int i, String s) {

                }
            });

            startActivity(HomeActivity.class);
        }else {
            startActivity(LoginActivity.class);
        }


    }


}
