package com.example.admin.zgapplication;

import android.app.Application;
import android.content.Context;
import android.support.multidex.MultiDex;

import com.baidu.mapapi.SDKInitializer;
import com.hyphenate.chat.EMOptions;
import com.hyphenate.easeui.EaseUI;
import com.umeng.socialize.PlatformConfig;
import com.umeng.socialize.UMShareAPI;

import cn.jpush.android.api.JPushInterface;

/**
 * Created by fushuang on 2017/8/4.
 */

public class ZgApplication extends Application {

    public static  String uid;

    public static String getUid() {
        return uid;
    }

    public static void setUid(String uid) {
        ZgApplication.uid = uid;
    }

    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
        MultiDex.install(this);
    }

    @Override
    public void onCreate() {
        super.onCreate();
        EMOptions options = new EMOptions();
        options.setAcceptInvitationAlways(true);
//        EMClient.getInstance().setDebugMode(true);
        EaseUI.getInstance().init(this,options);

        JPushInterface.setDebugMode(true);
        JPushInterface.init(this);

        PlatformConfig.setWeixin("wx0abcf6b5fcd82284", "4ce0e0eea4a6b46980c8be25a48c4bd1");
        PlatformConfig.setQQZone("1104875105", "J4PnbQoQZKBx8iBF");
        PlatformConfig.setSinaWeibo("178756640", "85348a69e5e84725a21f8eded16eb771", "http://sns.whalecloud.com");
        PlatformConfig.setAlipay("2017030606078997");

        UMShareAPI.get(this);
        SDKInitializer.initialize(this);
    }
}
