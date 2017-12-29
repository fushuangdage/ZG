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
import android.widget.TextView;
import android.widget.Toast;

import com.example.admin.zgapplication.Constant;
import com.example.admin.zgapplication.R;
import com.example.admin.zgapplication.base.BaseSupportFragment;
import com.example.admin.zgapplication.base.EventCenter;
import com.example.admin.zgapplication.base.EventRegionSelect;
import com.example.admin.zgapplication.mvp.module.BaseResponse;
import com.example.admin.zgapplication.mvp.module.RegionResponse;
import com.example.admin.zgapplication.mvp.module.StartIntent;
import com.example.admin.zgapplication.retrofit.RetrofitHelper;
import com.example.admin.zgapplication.retrofit.rx.BaseObserver;
import com.example.admin.zgapplication.retrofit.rx.RxScheduler;
import com.example.admin.zgapplication.ui.activity.WaitCrabActivity;
import com.example.admin.zgapplication.ui.adapter.HomePositionAdapter;
import com.example.admin.zgapplication.ui.view.HouseFilterDialog;

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
    @BindView(R.id.tv_filter)
    TextView tv_filter;
    @BindView(R.id.btn_call_agent)
    TextView btn_call_agent;

    private List<RegionResponse.BaseRegion> regionList=new ArrayList<RegionResponse.BaseRegion>();
    private HomePositionAdapter regionAdapter;
    private EventRegionSelect customFilter;
    private RegionResponse.BaseRegion select_region;


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
        tv_filter.setOnClickListener(this);
        btn_call_agent.setOnClickListener(this);
        regionAdapter = new HomePositionAdapter(getActivity(), R.layout.home_position_item, regionList);
        mRecyclerView.setAdapter(regionAdapter);
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
            case R.id.tv_filter:
                HouseFilterDialog dialog = new HouseFilterDialog(getContext(),R.style.translucent_dialog);
                dialog.show();
                break;
            case R.id.btn_call_agent:
                if (customFilter != null) {
                    String s = customFilter.getHouse_type_set().toString();
                    int rentWayFlay=0;
                    if (customFilter.getRent_way_set().toString().contains(",")) {
                        rentWayFlay=1;
                    }else if (customFilter.getRent_way_set().toString().contains("1")){
                        rentWayFlay=2;
                    }else {
                        rentWayFlay=3;
                    }

                    RetrofitHelper.getApiWithUid().startIntention(
                            select_region==null?null:select_region.getId(),
                            customFilter.getLeftProgress(),
                            customFilter.getRightProgress(),
//                            customFilter.getRent_way_set()==null?null:customFilter.getRent_way_set().toString(),
                           1,
                            customFilter.getHouse_type_set()==null?null:customFilter.getHouse_type_set().toString().replace("b",""))
                            .compose(RxScheduler.<BaseResponse>defaultScheduler())
                            .subscribe(new BaseObserver<BaseResponse>(mActivity) {
                                @Override
                                public void error(Throwable e) {

                                }

                                @Override
                                public void next(BaseResponse baseResponse) {
                                    Toast.makeText(mActivity, baseResponse.getMsg(), Toast.LENGTH_SHORT).show();
                                }

                                @Override
                                public void complete() {

                                }
                            });

                }else {
                    RetrofitHelper.getApiWithUid().startIntention( select_region==null?null:select_region.getId(),null,null,null,null)
                            .compose(RxScheduler.<StartIntent>defaultScheduler())
                            .subscribe(new BaseObserver<StartIntent>(mActivity){
                                @Override
                                public void error(Throwable e) {

                                }

                                @Override
                                public void next(StartIntent baseResponse) {
                                    Toast.makeText(mActivity, baseResponse.getMsg(), Toast.LENGTH_SHORT).show();
                                }

                                @Override
                                public void complete() {

                                }
                            });
                }
                break;
        }
    }

    @Override
    public void onEventBusResult(EventCenter event) {
        if (event.getCode()== Constant.REGION_SELECT) {
            customFilter = (EventRegionSelect) event.getData();
        } if (event.getCode()== Constant.SECOND_LEVEL_REGION) {
            //上拉区域选择区域回传
            select_region = ((RegionResponse.BaseRegion) event.getData());
        }
    }

    public void setRegionList(RegionResponse regionResponse) {
        regionList.clear();
        regionList.addAll(regionResponse.getData().getList());
        regionAdapter.notifyDataSetChanged();
    }
}
