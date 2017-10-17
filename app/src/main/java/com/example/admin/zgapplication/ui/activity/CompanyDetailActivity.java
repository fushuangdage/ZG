package com.example.admin.zgapplication.ui.activity;

import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;

import com.example.admin.zgapplication.R;
import com.example.admin.zgapplication.base.BaseActivity;

import butterknife.BindView;

public class CompanyDetailActivity extends BaseActivity {


    @BindView(R.id.viewPager)
    ViewPager viewPager;
    @BindView(R.id.tabLayout)
    TabLayout tabLayout;


    @Override
    public int setLayout() {
        return R.layout.activity_company_detail;
    }

    @Override
    public void initEvent() {

        tabLayout.setupWithViewPager(viewPager);
        
    }

    @Override
    public void initData() {

    }
}
