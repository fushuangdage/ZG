package com.example.admin.zgapplication.ui.activity;

import android.app.Activity;
import android.content.Intent;
import android.util.Log;
import android.view.View;

import com.example.admin.zgapplication.R;
import com.example.admin.zgapplication.base.BaseActivity;
import com.pingplusplus.android.Pingpp;

import butterknife.OnClick;

/**
 * 调用支付宝微信支付 选择界面
 */
public class PayOnlineActivity extends BaseActivity {

    @Override
    public int setLayout() {
        return R.layout.activity_pay_online;
    }

    @Override
    public void initEvent() {

    }

    @Override
    public void initData() {

    }

    @OnClick({R.id.tv_pay,R.id.iv_left})
    public void onClick(View view){
        switch (view.getId()) {

            case R.id.tv_pay:
                // TODO: 2017/10/20 调用支付宝
//                Pingpp.createPayment(mActivity,);

                startActivity(PaidBillDetailActivity.class);
                break;
            case R.id.iv_left:
                finish();
                break;
        }
    }

    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        //支付页面返回处理
        if (requestCode == Pingpp.REQUEST_CODE_PAYMENT) {
            if (resultCode == Activity.RESULT_OK) {
                String result = data.getExtras().getString("pay_result");
            /* 处理返回值
             * "success" - 支付
             * 成功
             * "fail"    - 支付失败
             * "cancel"  - 取消支付
             * "invalid" - 支付插件未安装（一般是微信客户端未安装的情况）
             * "unknown" - app进程异常被杀死(一般是低内存状态下,app进程被杀死)
             */
                String errorMsg = data.getExtras().getString("error_msg"); // 错误信息
                String extraMsg = data.getExtras().getString("extra_msg"); // 错误信息
//                showMsg(result, errorMsg, extraMsg);
                Log.d("88888888888", "onActivityResult: ");
            }
        }
    }
}
