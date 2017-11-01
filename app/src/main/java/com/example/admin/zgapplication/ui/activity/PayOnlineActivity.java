package com.example.admin.zgapplication.ui.activity;

import android.view.View;

import com.example.admin.zgapplication.R;
import com.example.admin.zgapplication.base.BaseActivity;

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
                startActivity(FollowUpPayResultActivity.class);
                break;
            case R.id.iv_left:
                finish();
                break;
        }
    }
}
