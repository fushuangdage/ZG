package com.example.admin.zgapplication.ui.activity;

import android.support.design.widget.TabLayout;
import android.view.View;
import android.widget.FrameLayout;

import com.example.admin.zgapplication.R;
import com.example.admin.zgapplication.base.BaseActivity;
import com.example.admin.zgapplication.ui.fragment.HouseDetailFragment;
import com.example.admin.zgapplication.ui.fragment.HouseEvaluteFragment;
import com.example.admin.zgapplication.ui.view.RoomPickDialog;

import butterknife.BindView;
import butterknife.OnClick;

public class HouseDetailActivity extends BaseActivity {


    @BindView(R.id.fl_content)
    FrameLayout fl_content;
    @BindView(R.id.tabLayout)
    TabLayout tabLayout;
    private HouseEvaluteFragment evaluteFragment;
    private HouseDetailFragment detailFragment;

    @Override
    public int setLayout() {
        return R.layout.activity_house_detail;
    }

    @Override
    public void initEvent() {
        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                switch (tab.getText().toString()) {
                    case "详情":
                        getSupportFragmentManager().beginTransaction().hide(evaluteFragment)
                                .show(detailFragment).commit();
                        break;

                    case "评价":
                        getSupportFragmentManager().beginTransaction().hide(detailFragment)
                                .show(evaluteFragment).commit();
                        break;
                }
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
        evaluteFragment = new HouseEvaluteFragment();
        detailFragment = new HouseDetailFragment();

        getSupportFragmentManager().beginTransaction()
                .add(R.id.fl_content,detailFragment).add(R.id.fl_content,evaluteFragment)
                .hide(evaluteFragment).commit();
    }

    @Override
    public void initData() {

    }

    @OnClick({R.id.tv_write_order})
    public void onClick(View view){
        switch (view.getId()) {

            case R.id.tv_write_order:
                RoomPickDialog roomPickDialog = new RoomPickDialog(this,R.style.room_pick_dialog);
                roomPickDialog.show();
                break;
        }
    }
}
