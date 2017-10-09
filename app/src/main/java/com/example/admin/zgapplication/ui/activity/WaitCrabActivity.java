package com.example.admin.zgapplication.ui.activity;

import android.support.annotation.NonNull;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewCompat;
import android.support.v4.view.ViewPager;
import android.view.View;

import com.example.admin.zgapplication.R;
import com.example.admin.zgapplication.base.BaseActivity;
import com.example.admin.zgapplication.ui.fragment.RecommendDamiFragment;
import com.example.admin.zgapplication.ui.fragment.RecommendHouseFragment;
import com.example.admin.zgapplication.ui.view.BeforeBTBehavior;

import butterknife.BindView;

public class WaitCrabActivity extends BaseActivity {


    @BindView(R.id.mask_view)
    View mask_view;
    @BindView(R.id.vp)
    ViewPager mViewPager;
    @BindView(R.id.tabLayout)
    TabLayout mTabLayout;
    private RecommendDamiFragment recommendDamiFragment;
    private RecommendHouseFragment recommendHouseFragment;


    private String[] titles=new String[]{"推荐大秘","推荐房源"};

    @Override
    public int setLayout() {
        return R.layout.activity_wait_crab;
    }

    @Override
    public void initEvent() {

        recommendDamiFragment = new RecommendDamiFragment();
        recommendHouseFragment = new RecommendHouseFragment();


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
                ViewCompat.setAlpha(mask_view,slideOffset);
            }
        });

        mViewPager.setAdapter(new FragmentPagerAdapter(getSupportFragmentManager()) {
            @Override
            public Fragment getItem(int position) {
                if (position==0){
                    return recommendDamiFragment;
                }if (position==1){
                    return recommendHouseFragment;
                }
                return null;
            }

            @Override
            public int getCount() {
                return 2;
            }

            @Override
            public CharSequence getPageTitle(int position) {
                return titles[position];
            }
        });

        mTabLayout.setupWithViewPager(mViewPager);

    }

    @Override
    public void initData() {

    }
}
