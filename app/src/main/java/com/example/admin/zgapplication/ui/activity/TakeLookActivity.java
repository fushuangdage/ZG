package com.example.admin.zgapplication.ui.activity;

import android.view.View;

import com.example.admin.zgapplication.R;
import com.example.admin.zgapplication.base.BaseActivity;

import butterknife.OnClick;

public class TakeLookActivity extends BaseActivity {

    @Override
    public int setLayout() {
        return R.layout.activity_take_look;
    }

    @Override
    public void initEvent() {

    }

    @Override
    public void initData() {

    }

    @OnClick({R.id.tv_submit_look})
    public void onClick(View view){
        switch (view.getId()) {
            case R.id.tv_submit_look:
                startActivity(TakeLookProgressActivity.class);
                break;
        }
    }
}
