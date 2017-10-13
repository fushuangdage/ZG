package com.example.admin.zgapplication.ui.activity;

import android.support.annotation.IdRes;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.RadioGroup;

import com.example.admin.zgapplication.R;
import com.example.admin.zgapplication.base.MVPBaseActivity;
import com.example.admin.zgapplication.mvp.presenter.HomePresenter;
import com.example.admin.zgapplication.ui.fragment.HomeFindHouseFragment;
import com.example.admin.zgapplication.ui.fragment.HomeFindPersonFragment;

import butterknife.BindView;


public class HomeActivity extends MVPBaseActivity<HomePresenter> implements View.OnClickListener, RadioGroup.OnCheckedChangeListener {


    private static final String TAG = "66666666666";

    @BindView(R.id.radio_group)
    RadioGroup radioGroup;

    @BindView(R.id.main_container)
    FrameLayout fl_container;
    private HomeFindHouseFragment houseFragment;
    private HomeFindPersonFragment personFragment;


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
        houseFragment = new HomeFindHouseFragment();
        personFragment = new HomeFindPersonFragment();
        getSupportFragmentManager().beginTransaction()
                .add(R.id.main_container,houseFragment)
                .add(R.id.main_container,personFragment)
                .hide(houseFragment).commit();
        radioGroup.setOnCheckedChangeListener(this);
    }


    @Override
    public void initData() {

    }

    @Override
    public void onClick(View v) {

        switch (v.getId()) {

        }
    }

    @Override
    public void onCheckedChanged(RadioGroup group, @IdRes int checkedId) {

        if(checkedId==R.id.rb_findHouse){
            getSupportFragmentManager().beginTransaction().hide(personFragment).show(houseFragment).commit();
        }else {
            getSupportFragmentManager().beginTransaction().hide(houseFragment).show(personFragment).commit();
        }

    }


}
