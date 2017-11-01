package com.example.admin.zgapplication.ui.activity;

import android.view.View;
import android.widget.TextView;

import com.example.admin.zgapplication.R;
import com.example.admin.zgapplication.base.BaseActivity;

import butterknife.BindView;
import butterknife.OnClick;

public class FixOnlineActivity extends BaseActivity {


    @BindView(R.id.iv_right)
    TextView tv_right;
    @BindView(R.id.tv_title)
    TextView tv_title;
    @Override
    public int setLayout() {
        return R.layout.activity_fix_online;
    }

    @Override
    public void initEvent() {
        tv_right.setText("提交");
        tv_title.setText("在线报修申请");
    }

    @Override
    public void initData() {

    }

    @OnClick({R.id.iv_right,R.id.iv_left})
    public void onClick(View view){
        switch (view.getId()) {
            case R.id.iv_right:
                break;
            case R.id.iv_left:
                finish();
                break;
        }
    }
}
