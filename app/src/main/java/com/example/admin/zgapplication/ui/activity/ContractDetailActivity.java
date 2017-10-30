package com.example.admin.zgapplication.ui.activity;

import android.view.View;
import android.widget.TextView;

import com.example.admin.zgapplication.R;
import com.example.admin.zgapplication.base.BaseActivity;

import butterknife.BindView;
import butterknife.OnClick;

public class ContractDetailActivity extends BaseActivity {



    @BindView(R.id.tv_title)
    TextView tv_title;

    @Override
    public int setLayout() {
        return R.layout.activity_contract_detail;
    }

    @Override
    public void initEvent() {

    }

    @Override
    public void initData() {

    }

    @OnClick({R.id.tv_go_pay,R.id.iv_left})
    public void onClick(View view){
        switch (view.getId()) {
            case R.id.tv_go_pay:
                startActivity(BillDetailActivity.class);
                break;
            case R.id.iv_left:
                finish();
                break;
        }
    }
}
