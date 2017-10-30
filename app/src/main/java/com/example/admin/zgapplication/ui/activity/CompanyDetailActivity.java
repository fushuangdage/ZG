package com.example.admin.zgapplication.ui.activity;

import android.graphics.Color;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;

import com.example.admin.zgapplication.R;
import com.example.admin.zgapplication.base.BaseActivity;
import com.example.admin.zgapplication.ui.fragment.CompanyEvaluateFragment;
import com.example.admin.zgapplication.ui.fragment.CompanyHouseFragment;
import com.example.admin.zgapplication.ui.fragment.CompanyMainFragment;

import butterknife.BindView;
import butterknife.OnClick;

public class CompanyDetailActivity extends BaseActivity {


    @BindView(R.id.viewPager)
    ViewPager viewPager;
    @BindView(R.id.tabLayout)
    TabLayout tabLayout;
    private String[] titles = new String[]{"主页", "房源","评价"};
    private CompanyEvaluateFragment companyEvaluateFragment;
    private CompanyHouseFragment companyHouseFragment;
    private CompanyMainFragment companyMainFragment;


    @Override
    public int setLayout() {
        return R.layout.activity_company_detail;
    }

    @Override
    public void initEvent() {

        companyEvaluateFragment = new CompanyEvaluateFragment();
        companyHouseFragment = new CompanyHouseFragment();
        companyMainFragment = new CompanyMainFragment();

        viewPager.setAdapter(new FragmentPagerAdapter(getSupportFragmentManager()) {
            @Override
            public Fragment getItem(int position) {
                if (position==0){
                    return companyMainFragment;
                }else if ((position==1)){
                    return companyHouseFragment;
                }else {
                    return companyEvaluateFragment;
                }
            }

            @Override
            public int getCount() {
                return 3;
            }

            @Override
            public CharSequence getPageTitle(int position) {
                return titles[position];
            }
        });
        tabLayout.setupWithViewPager(viewPager);
        tabLayout.setTabTextColors(Color.parseColor("#2A2E32"), Color.parseColor("#4dad01"));

    }

    @Override
    public void initData() {

    }

    @OnClick({R.id.iv_left})
    public void onClick(View view){
        switch (view.getId()) {
            case R.id.iv_left:
                finish();
                break;
        }
    }

}
