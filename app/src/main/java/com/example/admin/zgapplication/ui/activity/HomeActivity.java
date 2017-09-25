package com.example.admin.zgapplication.ui.activity;

import com.example.admin.zgapplication.R;
import com.example.admin.zgapplication.base.MVPBaseActivity;
import com.example.admin.zgapplication.mvp.presenter.HomePresenter;

public class HomeActivity extends MVPBaseActivity<HomePresenter> {


    @Override
    public HomePresenter createPresenter() {
        return new HomePresenter();
    }

    @Override
    public int setLayout() {
        return R.layout.activity_home;
    }

    @Override
    public void initEvent() {

    }

    @Override
    public void initData() {

    }
}
