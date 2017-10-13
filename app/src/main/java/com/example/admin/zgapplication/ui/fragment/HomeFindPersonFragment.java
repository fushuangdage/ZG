package com.example.admin.zgapplication.ui.fragment;


import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomSheetBehavior;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.MotionEvent;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.example.admin.zgapplication.R;
import com.example.admin.zgapplication.base.BaseSupportFragment;
import com.example.admin.zgapplication.ui.activity.WaitCrabActivity;
import com.example.admin.zgapplication.ui.adapter.HomePositionAdapter;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;


/**
 * A simple {@link Fragment} subclass.
 */
public class HomeFindPersonFragment extends BaseSupportFragment implements View.OnClickListener {

    @BindView(R.id.bottom_sheet)
    RelativeLayout mBottomSheet;
    @BindView(R.id.position_list)
    RecyclerView mRecyclerView;
    @BindView(R.id.ll_float)
    LinearLayout ll_float;
    @BindView(R.id.home_progress)
    View home_progress;



    @Override
    protected int setLayout() {
        return R.layout.fragment_home_find_person;
    }

    @Override
    protected void init() {
        home_progress.setOnClickListener(this);

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
        mRecyclerView.setAdapter(new HomePositionAdapter(getActivity(), R.layout.home_position_item, strings));
        mRecyclerView.setLayoutManager(new GridLayoutManager(getActivity(), 4));
        final BottomSheetBehavior<RelativeLayout> behavior = BottomSheetBehavior.from(mBottomSheet);
        behavior.setState(BottomSheetBehavior.STATE_COLLAPSED);

        behavior.setBottomSheetCallback(new BottomSheetBehavior.BottomSheetCallback() {
            @Override
            public void onStateChanged(@NonNull View bottomSheet, int newState) {

            }

            @Override
            public void onSlide(@NonNull View bottomSheet, float slideOffset) {

            }


        });
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.home_progress:
                startActivity(new Intent(getActivity(),WaitCrabActivity.class));
                break;
        }
    }
}
