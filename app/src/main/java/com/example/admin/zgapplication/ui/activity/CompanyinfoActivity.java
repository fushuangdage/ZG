package com.example.admin.zgapplication.ui.activity;

import android.widget.TextView;

import com.example.admin.zgapplication.R;
import com.example.admin.zgapplication.base.BaseActivity;

import butterknife.BindView;

public class CompanyinfoActivity extends BaseActivity {


    @BindView(R.id.tv_content)
    TextView tv_content;

    @Override
    public int setLayout() {
        return R.layout.activity_company_info;
    }

    @Override
    public void initEvent() {
        String content = getIntent().getStringExtra("content");
        tv_content.setText(content);
    }

    @Override
    public void initData() {

    }
}
