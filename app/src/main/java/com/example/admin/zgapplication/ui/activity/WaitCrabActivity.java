package com.example.admin.zgapplication.ui.activity;

import android.content.Intent;
import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewCompat;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.admin.zgapplication.R;
import com.example.admin.zgapplication.base.BaseActivity;
import com.example.admin.zgapplication.mvp.module.BaseResponse;
import com.example.admin.zgapplication.mvp.module.CrabCountResponse;
import com.example.admin.zgapplication.retrofit.RetrofitHelper;
import com.example.admin.zgapplication.retrofit.rx.BaseObserver;
import com.example.admin.zgapplication.retrofit.rx.RxScheduler;
import com.example.admin.zgapplication.ui.fragment.RecommendDamiFragment;
import com.example.admin.zgapplication.ui.fragment.RecommendHouseFragment;
import com.example.admin.zgapplication.ui.view.BeforeBTBehavior;
import com.example.admin.zgapplication.ui.view.CustomProgressView;

import java.util.concurrent.TimeUnit;

import butterknife.BindView;
import butterknife.OnClick;
import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class WaitCrabActivity extends BaseActivity {


    @BindView(R.id.mask_view)
    View mask_view;
    @BindView(R.id.vp)
    ViewPager mViewPager;
    @BindView(R.id.tabLayout)
    TabLayout mTabLayout;
    @BindView(R.id.bt_check_crab_list)
    TextView bt_check_crab_list;
    private Disposable disposable;
    @BindView(R.id.progress_bar)
    CustomProgressView progress_bar;


    @OnClick({R.id.bt_check_crab,R.id.bt_check_crab_list,R.id.iv_left,R.id.bt_reset_crab})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.bt_check_crab:
            case R.id.bt_check_crab_list:
                int iid = getIntent().getIntExtra("iid", 0);
                Intent intent = new Intent(mActivity, GrabListActivity.class);
                intent.putExtra("iid",iid);
                startActivity(intent);
                break;
            case R.id.iv_left:
                finish();
                break;
            case R.id.bt_reset_crab:
                RetrofitHelper.getApiWithUid().cancleIntention(getIntent().getIntExtra("iid", 0))
                        .compose(RxScheduler.<BaseResponse>defaultScheduler())
                        .subscribe(new BaseObserver<BaseResponse>(mActivity) {
                            @Override
                            public void error(Throwable e) {

                            }

                            @Override
                            public void next(BaseResponse baseResponse) {
                                if (baseResponse.getCode()==0) {
                                    Toast.makeText(WaitCrabActivity.this, baseResponse.getMsg(), Toast.LENGTH_SHORT).show();
                                    finish();
                                }
                            }

                            @Override
                            public void complete() {

                            }
                        });

//                HomeActivity activity = (HomeActivity) AppManager.getActivities().get(AppManager.getActivities().size() - 2);
//                activity.personFragment.startCrabIntent();
                break;
        }
    }

    private RecommendDamiFragment recommendDamiFragment;
    private RecommendHouseFragment recommendHouseFragment;


    private String[] titles = new String[]{"推荐大秘", "推荐房源"};

    @Override
    public int setLayout() {
        return R.layout.activity_wait_crab;
    }

    @Override
    public void initEvent() {


        ViewCompat.setAlpha(mask_view, 0);

        recommendDamiFragment = new RecommendDamiFragment();
        recommendHouseFragment = new RecommendHouseFragment();

        progress_bar.startReversal();

        View bottomSheet = findViewById(R.id.rl_bottom_sheet);
        final View rl_wait_vrab_content = findViewById(R.id.rl_wait_crab_content);
        final BeforeBTBehavior<View> sheetBehavior = BeforeBTBehavior.from(bottomSheet);
        sheetBehavior.setBottomSheetCallback(new BeforeBTBehavior.BottomSheetCallback() {
            @Override
            public void onStateChanged(@NonNull View bottomSheet, int newState) {
                if (newState == BeforeBTBehavior.STATE_EXPANDED) {
                    ((CoordinatorLayout.LayoutParams) bottomSheet.getLayoutParams()).setBehavior(null);
                }
            }

            @Override
            public void onSlide(@NonNull View bottomSheet, float slideOffset) {
                ViewCompat.setAlpha(mask_view, slideOffset);
//                mask_view.getBackground().setAlpha((int) (slideOffset*100));
//                Log.d("66666666", "onSlide: "+slideOffset);
            }
        });

        mViewPager.setAdapter(new FragmentPagerAdapter(getSupportFragmentManager()) {
            @Override
            public Fragment getItem(int position) {
                if (position == 0) {
                    return recommendDamiFragment;
                }
                if (position == 1) {
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
        mTabLayout.setTabTextColors(Color.parseColor("#2A2E32"), Color.parseColor("#4dad01"));

    }

    @Override
    public void initData() {

        Observable.interval(5, TimeUnit.SECONDS).
                subscribeOn(Schedulers.io()).
                takeUntil(Observable.timer(60, TimeUnit.SECONDS)).
                subscribe(new Observer<Long>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                        disposable = d;
                    }

                    @Override
                    public void onNext(Long aLong) {
                        RetrofitHelper.getApiWithUid().getCrabCount(getIntent().getIntExtra("iid",0))
                                .compose(RxScheduler.<CrabCountResponse>defaultScheduler())
                                .subscribe(new Observer<CrabCountResponse>() {

                                    @Override
                                    public void onSubscribe(Disposable d) {

                                    }

                                    @Override
                                    public void onNext(CrabCountResponse crabCountResponse) {

                                        if (crabCountResponse.getCode()==0){
                                            CrabCountResponse.DataBean data = crabCountResponse.getData();
                                            bt_check_crab_list.setText(String.format("查看抢单(%s)",data.getCount()));

                                            Log.d("88888888", "accept: 查看抢单"+data.getCount());

                                        }else {
                                            Toast.makeText(mActivity, crabCountResponse.getMsg(), Toast.LENGTH_SHORT).show();
                                        }

                                    }

                                    @Override
                                    public void onError(Throwable e) {

                                    }

                                    @Override
                                    public void onComplete() {

                                    }
                                });
                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });

    }


    @Override
    protected void onStop() {
        if (!disposable.isDisposed())
        disposable.dispose();
        super.onStop();
    }
}
