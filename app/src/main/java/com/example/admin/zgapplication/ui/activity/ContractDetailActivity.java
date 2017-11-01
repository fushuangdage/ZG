package com.example.admin.zgapplication.ui.activity;

import android.os.Bundle;
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
        tv_title.setText("合同详情");
    }

    @Override
    public void initData() {

    }

    @OnClick({R.id.tv_go_pay,R.id.iv_left,R.id.tv_rent_bill_list,R.id.tv_life_bill_list})
    public void onClick(View view){
        Bundle bundle=new Bundle();
        switch (view.getId()) {
            case R.id.tv_go_pay:
                startActivity(BillDetailActivity.class);
                break;
            case R.id.iv_left:
                finish();
                break;
            case R.id.tv_rent_bill_list:
                 bundle = new Bundle();
                bundle.putString("title","房租账单");
                startActivity(RentBillListActivity.class,bundle);
                break;
            case R.id.tv_life_bill_list:
                bundle.putString("title","生活账单");
                startActivity(RentBillListActivity.class,bundle);
                break;
        }
    }
}
