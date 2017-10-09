package com.example.admin.zgapplication.ui.activity;

import android.support.annotation.NonNull;
import android.support.design.widget.CoordinatorLayout;
import android.support.v4.view.ViewCompat;
import android.view.View;

import com.example.admin.zgapplication.R;
import com.example.admin.zgapplication.base.BaseActivity;
import com.example.admin.zgapplication.ui.view.BeforeBTBehavior;

import butterknife.BindView;

public class WaitCrabActivity extends BaseActivity {


    @BindView(R.id.mask_view)
    View mask_view;


    @Override
    public int setLayout() {
        return R.layout.activity_wait_crab;
    }

    @Override
    public void initEvent() {
        View bottomSheet = findViewById(R.id.rl_bottom_sheet);
        final View rl_wait_vrab_content = findViewById(R.id.rl_wait_crab_content);
        final BeforeBTBehavior<View> sheetBehavior = BeforeBTBehavior.from(bottomSheet);
        sheetBehavior.setBottomSheetCallback(new BeforeBTBehavior.BottomSheetCallback() {
            @Override
            public void onStateChanged(@NonNull View bottomSheet, int newState) {
                if (newState==BeforeBTBehavior.STATE_EXPANDED){
                    ((CoordinatorLayout.LayoutParams) bottomSheet.getLayoutParams()).setBehavior(null);
                }
            }

            @Override
            public void onSlide(@NonNull View bottomSheet, float slideOffset) {
                ViewCompat.setAlpha(mask_view,1-slideOffset);
            }
        });
    }

    @Override
    public void initData() {

    }
}
