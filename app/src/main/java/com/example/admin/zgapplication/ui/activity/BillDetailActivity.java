package com.example.admin.zgapplication.ui.activity;

import android.view.View;

import com.example.admin.zgapplication.R;
import com.example.admin.zgapplication.base.BaseActivity;

import butterknife.BindView;
import butterknife.OnClick;

public class BillDetailActivity extends BaseActivity {


    @BindView(R.id.ll_agent_fee)
    View ll_agent_fee;
    @BindView(R.id.ll_deposit)
    View ll_deposit;
    @BindView(R.id.ll_serve_fee)
    View ll_serve_fee;

    @Override
    public int setLayout() {
        return R.layout.activity_bill_detail;
    }

    @Override
    public void initEvent() {
        ll_agent_fee.setVisibility(View.GONE);
        ll_deposit.setVisibility(View.GONE);
        ll_serve_fee.setVisibility(View.GONE);


    }

    @Override
    public void initData() {

    }

    @OnClick({R.id.tv_commit})
    public void onClick(View view){
        switch (view.getId()) {
            case R.id.tv_commit:
                startActivity(PayOnlineActivity.class);
                break;

        }
    }
}
