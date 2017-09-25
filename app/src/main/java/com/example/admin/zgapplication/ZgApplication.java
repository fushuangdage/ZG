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

        PlatformConfig.setWeixin("wx967daebe835fbeac", "5bb696d9ccd75a38c8a0bfe0675559b3");
        PlatformConfig.setQQZone("100424468", "c7394704798a158208a74ab60104f0ba");
        PlatformConfig.setSinaWeibo("3921700954", "04b48b094faeb16683c32669824ebdad", "http://sns.whalecloud.com");

        UMShareAPI.get(this);

        SDKInitializer.initialize(this);
    }
}
