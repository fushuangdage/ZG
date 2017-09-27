package com.example.admin.zgapplication.ui.activity;

import android.support.annotation.NonNull;
import android.support.design.widget.BottomSheetBehavior;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.example.admin.zgapplication.R;
import com.example.admin.zgapplication.base.MVPBaseActivity;
import com.example.admin.zgapplication.mvp.presenter.HomePresenter;
import com.example.admin.zgapplication.ui.adapter.HomePositionAdapter;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;


public class HomeActivity extends MVPBaseActivity<HomePresenter> {


    private static final String TAG = "66666666666";
    @BindView(R.id.bottom_sheet)
    RelativeLayout mBottomSheet;
    @BindView(R.id.position_list)
    RecyclerView mRecyclerView;
    @BindView(R.id.ll_float)
    LinearLayout ll_float;

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
        mBottomSheet.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                return true;
            }
        });

        List<String> strings = new ArrayList<>();
        for (int i = 0; i < 30; i++) {
            strings.add("");
        }
        mRecyclerView.setAdapter(new HomePositionAdapter(this, R.layout.home_position_item, strings));
        mRecyclerView.setLayoutManager(new GridLayoutManager(this, 4));
        final BottomSheetBehavior<RelativeLayout> behavior = BottomSheetBehavior.from(mBottomSheet);
       behavior.setState(BottomSheetBehavior.STATE_COLLAPSED);

        behavior.setBottomSheetCallback(new BottomSheetBehavior.BottomSheetCallback() {
            @Override
            public void onStateChanged(@NonNull View bottomSheet, int newState) {
                Log.d(TAG, "onStateChanged: "+newState);
            }

            @Override
            public void onSlide(@NonNull View bottomSheet, float slideOffset) {

            }


        });

    }


    @Override
    public void initData() {

    }

}
