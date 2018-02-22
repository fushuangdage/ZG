package com.example.admin.zgapplication.ui.fragment;


import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomSheetBehavior;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.baidu.location.BDLocation;
import com.baidu.location.BDLocationListener;
import com.baidu.location.LocationClient;
import com.baidu.location.LocationClientOption;
import com.baidu.mapapi.map.BaiduMap;
import com.baidu.mapapi.map.BitmapDescriptorFactory;
import com.baidu.mapapi.map.MapStatus;
import com.baidu.mapapi.map.MapStatusUpdate;
import com.baidu.mapapi.map.MapStatusUpdateFactory;
import com.baidu.mapapi.map.MapView;
import com.baidu.mapapi.map.MarkerOptions;
import com.baidu.mapapi.map.OverlayOptions;
import com.baidu.mapapi.model.LatLng;
import com.example.admin.zgapplication.Constant;
import com.example.admin.zgapplication.R;
import com.example.admin.zgapplication.base.BaseSupportFragment;
import com.example.admin.zgapplication.base.EventCenter;
import com.example.admin.zgapplication.base.EventRegionSelect;
import com.example.admin.zgapplication.mvp.module.AgentLocationResponse;
import com.example.admin.zgapplication.mvp.module.RegionResponse;
import com.example.admin.zgapplication.mvp.module.StartIntent;
import com.example.admin.zgapplication.retrofit.RetrofitHelper;
import com.example.admin.zgapplication.retrofit.rx.BaseObserver;
import com.example.admin.zgapplication.retrofit.rx.RxScheduler;
import com.example.admin.zgapplication.ui.activity.ExchangeCouponActivity;
import com.example.admin.zgapplication.ui.activity.LoginActivity;
import com.example.admin.zgapplication.ui.activity.WaitCrabActivity;
import com.example.admin.zgapplication.ui.adapter.HomePositionAdapter;
import com.example.admin.zgapplication.ui.view.CustomProgressView;
import com.example.admin.zgapplication.ui.view.HouseFilterDialog;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;


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
    CustomProgressView home_progress;
    @BindView(R.id.tv_filter)
    TextView tv_filter;
    @BindView(R.id.btn_call_agent)
    TextView btn_call_agent;
    @BindView(R.id.mapView)
    MapView mapView;

    private List<RegionResponse.BaseRegion> regionList=new ArrayList<RegionResponse.BaseRegion>();
    private HomePositionAdapter regionAdapter;
    private EventRegionSelect customFilter;
    private RegionResponse.BaseRegion select_region;
    private RegionResponse currentSelectRegion;
    public int iid;
    private BaiduMap baiduMap;


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
        regionAdapter = new HomePositionAdapter(getActivity(), R.layout.home_position_item, regionList,this);
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


        initLocation();


    }

    private void initLocation() {

        LocationClient locationClient = new LocationClient(getActivity());
        LocationClientOption option = new LocationClientOption();
        baiduMap = mapView.getMap();
        baiduMap.setMyLocationEnabled(true);
        option.setLocationMode(LocationClientOption.LocationMode.Hight_Accuracy);
        option.setCoorType("bd09ll");
        locationClient.setLocOption(option);

        locationClient.registerLocationListener(new BDLocationListener() {
            @Override
            public void onReceiveLocation(BDLocation bdLocation) {

                Log.d("88888888", "onReceiveLocation: "+bdLocation.getLongitude()+"  :   "+bdLocation.getLatitude());
                RetrofitHelper.getApiWithUid().getAgentLocation(bdLocation.getLatitude(),bdLocation.getLongitude())   //维度,精度
                        .compose(RxScheduler.<AgentLocationResponse>defaultScheduler())
                        .subscribe(new Observer<AgentLocationResponse>() {
                            @Override
                            public void onSubscribe(Disposable d) {

                            }

                            @Override
                            public void onNext(AgentLocationResponse agentLocationResponse) {
                                List<AgentLocationResponse.DataBean> data = agentLocationResponse.getData();
                                List<OverlayOptions> options = new ArrayList<OverlayOptions>();

                                if (data!=null){
                                    for (AgentLocationResponse.DataBean datum : data) {
                                        Log.d("88888888", "onNext: "+datum.getLat()+"   :  "+datum.getLng());
                                        MarkerOptions markerOptions = new MarkerOptions()
                                                .position(new LatLng(datum.getLat(), datum.getLng()))
                                                .icon(BitmapDescriptorFactory
                                                        .fromResource(R.drawable.person));
                                        options.add(markerOptions);
                                    }
                                }

                                baiduMap.addOverlays(options);

                            }

                            @Override
                            public void onError(Throwable e) {

                            }

                            @Override
                            public void onComplete() {

                            }
                        });


                    LatLng cenpt = new LatLng(bdLocation.getLatitude(), bdLocation.getLongitude());

                    //定义地图状态
                    MapStatus mMapStatus = new MapStatus.Builder()
                            .target(cenpt)
                            .zoom(18)
                            .build();
                    //定义MapStatusUpdate对象，以便描述地图状态将要发生的变化
                    MapStatusUpdate mMapStatusUpdate = MapStatusUpdateFactory.newMapStatus(mMapStatus);
                    //改变地图状态
                    baiduMap.setMapStatus(mMapStatusUpdate);

                }

        });

        locationClient.start();

    }

    @Override
    @OnClick({R.id.iv_red_packet})
    public void onClick(View v) {
        Intent intent;
        switch (v.getId()) {
            case R.id.iv_red_packet:
                intent = new Intent(getActivity(), ExchangeCouponActivity.class);
                startActivity(intent);
                break;
            case R.id.home_progress:
                intent = new Intent(getActivity(), WaitCrabActivity.class);
                intent.putExtra("iid",iid);
                startActivity(intent);
                break;
            case R.id.tv_filter:
                HouseFilterDialog dialog = new HouseFilterDialog(getContext(),R.style.translucent_dialog);
                dialog.show();
                break;
            case R.id.btn_call_agent:
                if ("".equals(Constant.uid)){
                    startActivity(new Intent(mActivity, LoginActivity.class));
                }else {
                    startCrabIntent();
                }
                break;
        }
    }

    public void startCrabIntent() {
        if (customFilter != null) {
            String room = customFilter.getHouse_type_set().toString().replaceAll(" ","");
            if (room.length()>2) {
                room=room.substring(1,room.length()-1);
            }else {
                room=null;
            }

            int method = 1;  //整租+合租  整租: 2 合租: 3
            if (customFilter.getRent_way_set().size()==1) {
                for (int i: customFilter.getRent_way_set()) {
                    method=i;
                }
            }

            RetrofitHelper.getApiWithUid().startIntention(
                    select_region==null?null:select_region.getId(),
                    customFilter.getLeftProgress(),
                    customFilter.getRightProgress(),
                    method,
                    room, Constant.uid)
                    .compose(RxScheduler.<StartIntent>defaultScheduler())
                    .subscribe(new BaseObserver<StartIntent>(mActivity) {
                        @Override
                        public void error(Throwable e) {

                        }

                        @Override
                        public void next(StartIntent baseResponse) {
                            Toast.makeText(mActivity, baseResponse.getMsg(), Toast.LENGTH_SHORT).show();
                            if (baseResponse.getCode()==0){
                                home_progress.setVisibility(View.VISIBLE);
                                home_progress.start();
                                Intent intent = new Intent(mActivity, WaitCrabActivity.class);
                                iid = baseResponse.getData().getId();
                                intent.putExtra("iid", iid);
                                startActivity(intent);
                            }
                        }

                        @Override
                        public void complete() {

                        }
                    });

        }else {
            RetrofitHelper.getApiWithUid().startIntention( select_region==null?null:select_region.getId(),null,null,null,null,Constant.uid)
                    .compose(RxScheduler.<StartIntent>defaultScheduler())
                    .subscribe(new BaseObserver<StartIntent>(mActivity){
                        @Override
                        public void error(Throwable e) {

                        }

                        @Override
                        public void next(StartIntent baseResponse) {
                            Toast.makeText(mActivity, baseResponse.getMsg(), Toast.LENGTH_SHORT).show();
                            if (baseResponse.getCode()==0){
                                home_progress.setVisibility(View.VISIBLE);
                                home_progress.start();
                                iid=baseResponse.getData().getId();
                                Intent intent = new Intent(mActivity, WaitCrabActivity.class);
                                intent.putExtra("iid",baseResponse.getData().getId());
                                startActivity(intent);
                            }
                        }

                        @Override
                        public void complete() {

                        }
                    });
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
        currentSelectRegion = regionResponse;
    }

    public void reloadFirstLevelData() {
        regionList.clear();
        regionList.addAll(currentSelectRegion.getData().getList());
        regionAdapter.notifyDataSetChanged();
    }
}
