package com.example.admin.zgapplication.ui.activity;

import android.view.View;
import android.widget.TextView;

import com.example.admin.zgapplication.R;
import com.example.admin.zgapplication.base.BaseActivity;

import butterknife.BindView;
import butterknife.OnClick;

public class FollowUpPayResultActivity extends BaseActivity {


    @BindView(R.id.ll_agent_fee)
    View ll_agent_fee;
    @BindView(R.id.ll_deposit)
    View ll_deposit;
    @BindView(R.id.ll_serve_fee)
    View ll_serve_fee;
    @BindView(R.id.tv_title)
    TextView tv_title;
    @BindView(R.id.ll_sale_promotion)
    View ll_sale_promotion;

    @Override
    public int setLayout() {
        return R.layout.activity_follow_up_pay_result;
    }

    @Override
    public void initEvent() {
        ll_agent_fee.setVisibility(View.GONE);
        ll_deposit.setVisibility(View.GONE);
        ll_serve_fee.setVisibility(View.GONE);
        ll_sale_promotion.setVisibility(View.GONE);
        tv_title.setText("支付详情");

    }

    @Override
    public void initData() {

    }

    @OnClick({R.id.iv_left})
    public void onClick(View view ){
        switch (view.getId()) {
            case R.id.iv_left:
                finish();
                break;
        }
    }

}
