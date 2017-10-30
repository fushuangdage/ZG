package com.example.admin.zgapplication.ui.activity;

import android.view.View;

import com.example.admin.zgapplication.R;
import com.example.admin.zgapplication.base.BaseActivity;
import com.example.admin.zgapplication.ui.view.PriceDetailDialog;

import butterknife.OnClick;


public class FinishOrderActivity extends BaseActivity {



    @Override
    public int setLayout() {
        return R.layout.activity_finish_order;
    }

    @Override
    public void initEvent() {

    }

    @Override
    public void initData() {

    }

    @OnClick({R.id.tv_price_detail,R.id.tv_evaluate})
    public void onClick(View view){
        switch (view.getId()) {
            case R.id.tv_price_detail:
                PriceDetailDialog dialog = new PriceDetailDialog(this);
                dialog.show();
                break;
            case R.id.tv_evaluate:
                startActivity(MakeEvaluateActivity.class);
                break;
            case R.id.iv_left:
                finish();
                break;
        }
    }


}
