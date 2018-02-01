package com.example.admin.zgapplication.ui.activity;

import android.Manifest;
import android.support.annotation.IdRes;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.admin.zgapplication.Constant;
import com.example.admin.zgapplication.R;
import com.example.admin.zgapplication.base.EventCenter;
import com.example.admin.zgapplication.base.MVPBaseActivity;
import com.example.admin.zgapplication.mvp.module.CityResponse;
import com.example.admin.zgapplication.mvp.module.RegionResponse;
import com.example.admin.zgapplication.mvp.presenter.HomePresenter;
import com.example.admin.zgapplication.retrofit.RetrofitHelper;
import com.example.admin.zgapplication.retrofit.rx.BaseObserver;
import com.example.admin.zgapplication.retrofit.rx.RxScheduler;
import com.example.admin.zgapplication.ui.adapter.ZhyBaseRecycleAdapter.CommonAdapter;
import com.example.admin.zgapplication.ui.adapter.ZhyBaseRecycleAdapter.MultiItemTypeAdapter;
import com.example.admin.zgapplication.ui.adapter.ZhyBaseRecycleAdapter.base.ViewHolder;
import com.example.admin.zgapplication.ui.fragment.HomeFindHouseFragment;
import com.example.admin.zgapplication.ui.fragment.HomeFindPersonFragment;
import com.example.admin.zgapplication.utils.img.GlideRoundTransform;
import com.tbruyelle.rxpermissions2.RxPermissions;

import java.io.Serializable;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;
import io.reactivex.ObservableSource;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;


public class HomeActivity extends MVPBaseActivity<HomePresenter> implements RadioGroup.OnCheckedChangeListener, View.OnClickListener {


    private static final String TAG = "66666666666";

    @BindView(R.id.radio_group)
    RadioGroup radioGroup;

    @BindView(R.id.main_container)
    FrameLayout fl_container;

    @BindView(R.id.home_navigationView)
    NavigationView navigationView;

    @BindView(R.id.home_drawer)
    DrawerLayout drawerLayout;


    @BindView(R.id.home_message)
    ImageView home_message;

    @BindView(R.id.tv_city)
    TextView tv_city;


    public HomeFindHouseFragment houseFragment;
    public HomeFindPersonFragment personFragment;
    private CityResponse cityResponse;
    private PopupWindow popupWindow;
    private List<CityResponse.DataBean.ListBean> cityList;
    private CityResponse.DataBean.ListBean currentCityBean;
    private CommonAdapter<CityResponse.DataBean.ListBean> cityAdapter;
    private RegionResponse regionResponse;
    private RegionResponse.BaseRegion select_region;


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
                .add(R.id.main_container, houseFragment)
                .add(R.id.main_container, personFragment)
                .hide(houseFragment).commit();
        radioGroup.setOnCheckedChangeListener(this);

        View headerView = navigationView.getHeaderView(0);
        View evaluation = headerView.findViewById(R.id.ll_evaluation);
        View order = headerView.findViewById(R.id.ll_order);
        View contact = headerView.findViewById(R.id.ll_contract);

        ImageView iv_agent_icon = (ImageView) headerView.findViewById(R.id.iv_agent_icon);

        Glide.with(mActivity).load(Constant.avatar).transform(new GlideRoundTransform(mActivity)).into(iv_agent_icon);
        ((TextView) headerView.findViewById(R.id.tv_user_name)).setText(Constant.username);


        headerView.findViewById(R.id.ll_shopping_car).setOnClickListener(this);
        headerView.findViewById(R.id.ll_intent).setOnClickListener(this);
        headerView.findViewById(R.id.ll_take_look).setOnClickListener(this);
        headerView.findViewById(R.id.ll_system_msg).setOnClickListener(this);
        headerView.findViewById(R.id.ll_discount).setOnClickListener(this);
        headerView.findViewById(R.id.ll_friend).setOnClickListener(this);
        headerView.findViewById(R.id.ll_self).setOnClickListener(this);
        headerView.findViewById(R.id.tv_about_us).setOnClickListener(this);
        headerView.findViewById(R.id.tv_setting).setOnClickListener(this);
        evaluation.setOnClickListener(this);
        order.setOnClickListener(this);
        contact.setOnClickListener(this);

    }

    @Override
    public void initData() {

        Serializable loginBean = getIntent().getSerializableExtra("loginBean");

        RxPermissions rxPermissions = new RxPermissions(this);
        String[] mPermissionList = new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE,
                Manifest.permission.ACCESS_FINE_LOCATION,
                Manifest.permission.CAMERA,
                Manifest.permission.CALL_PHONE,
                Manifest.permission.READ_LOGS,
                Manifest.permission.READ_PHONE_STATE,
                Manifest.permission.READ_EXTERNAL_STORAGE,
                Manifest.permission.SET_DEBUG_APP,
                Manifest.permission.SYSTEM_ALERT_WINDOW,
                Manifest.permission.GET_ACCOUNTS,
                Manifest.permission.WRITE_APN_SETTINGS,
                Manifest.permission.RECORD_AUDIO};

        rxPermissions.request(mPermissionList).subscribe(new Consumer<Boolean>() {
            @Override
            public void accept(@NonNull Boolean aBoolean) throws Exception {

            }
        });


        RetrofitHelper.getApiWithUid().getCityList()
                .compose(RxScheduler.<CityResponse>defaultScheduler())
                .doOnNext(new Consumer<CityResponse>() {
                    @Override
                    public void accept(CityResponse cityResponse) throws Exception {
                        cityList = cityResponse.getData().getList();
                        tv_city.setText(cityList.get(0).getName());
                    }
                })
                .observeOn(Schedulers.io())
                .flatMap(new Function<CityResponse, ObservableSource<RegionResponse>>() {
                    @Override
                    public ObservableSource<RegionResponse> apply(CityResponse cityResponse) throws Exception {
                        return RetrofitHelper.getApi().getRegionResponse(cityResponse.getData().getList().get(0).getId());
                    }
                })
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new BaseObserver<RegionResponse>(mActivity) {
                    @Override
                    public void error(Throwable e) {

                    }

                    @Override
                    public void next(RegionResponse regionResponse) {
                        HomeActivity.this.regionResponse = regionResponse;
                        personFragment.setRegionList(regionResponse);
                    }

                    @Override
                    public void complete() {

                    }
                });


    }


    @OnClick({R.id.home_find, R.id.home_message, R.id.mine, R.id.tv_city})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.tv_city:
                View contentView = LayoutInflater.from(mActivity).inflate(R.layout.city_choose_panel, null, false);
                RecyclerView recyclerView = (RecyclerView) contentView.findViewById(R.id.recyclerView);
                cityAdapter = new CommonAdapter<CityResponse.DataBean.ListBean>(mActivity, R.layout.item_text, cityList) {
                    @Override
                    protected void convert(ViewHolder holder, CityResponse.DataBean.ListBean bean, int position) {
                        ((TextView) holder.getView(R.id.tv_content)).setText(bean.getName());
                    }
                };
                cityAdapter.setOnItemClickListener(new MultiItemTypeAdapter.OnItemClickListener() {
                    @Override
                    public void onItemClick(View view, RecyclerView.ViewHolder holder, int position) {

                        //点击刷新上拉抽屉列表地区选择
                        currentCityBean = cityList.get(position);
                        tv_city.setText(currentCityBean.getName());
                        popupWindow.dismiss();
                        RetrofitHelper.getApi().getRegionResponse(currentCityBean.getId())
                                .compose(RxScheduler.<RegionResponse>defaultScheduler())
                                .subscribe(new BaseObserver<RegionResponse>(mActivity) {
                                    @Override
                                    public void error(Throwable e) {

                                    }

                                    @Override
                                    public void next(RegionResponse regionResponse) {
                                        HomeActivity.this.regionResponse = regionResponse;
                                        personFragment.setRegionList(regionResponse);
                                    }

                                    @Override
                                    public void complete() {

                                    }
                                });


                    }

                    @Override
                    public boolean onItemLongClick(View view, RecyclerView.ViewHolder holder, int position) {
                        return false;
                    }
                });
                recyclerView.setLayoutManager(new LinearLayoutManager(mActivity));
                recyclerView.setAdapter(cityAdapter);
                popupWindow = new PopupWindow(contentView, ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT, true);
                popupWindow.getContentView().measure(View.MeasureSpec.EXACTLY, View.MeasureSpec.UNSPECIFIED);
                popupWindow.setContentView(contentView);
                popupWindow.showAsDropDown(tv_city);
                break;
            case R.id.mine:
                drawerLayout.openDrawer(Gravity.LEFT);
                break;
            case R.id.home_find:
                startActivity(SearchActivity.class);
                break;
            case R.id.ll_evaluation:
                startActivity(EvaluationListActivity.class);
                break;
            case R.id.ll_contract:
                startActivity(MyContractActivity.class);
                break;
            case R.id.ll_order:
                startActivity(OrderListActivity.class);
                break;
            case R.id.ll_friend:
                break;
            case R.id.ll_discount:
                startActivity(MyDiscountActivity.class);
                break;
            case R.id.ll_system_msg:
                break;
            case R.id.ll_take_look:
                startActivity(TakeLookListActivity.class);
                break;
            case R.id.ll_intent:
                startActivity(IntentListActivity.class);
                break;
            case R.id.ll_shopping_car:
                //购物车改为我的收藏
                startActivity(ShopCarActivity.class);
                break;
            case R.id.home_message:
                startActivity(EaseConversationListActivity.class);
                break;
            case R.id.ll_self:
                startActivity(SelfInfoActivity.class);
                break;
            case R.id.tv_setting :

                break;
            case R.id.tv_about_us:
                startActivity(AboutUsActivity.class);
                break;
        }
    }

    @Override
    public void onEventBusResult(EventCenter event) {
        if (event.getCode() == Constant.REFRESH_REGION_FITSTLEVEL_LIST) {
            //上拉区域选择区域回传
            personFragment.setRegionList(regionResponse);
            select_region = ((RegionResponse.BaseRegion) event.getData());
        }
    }

    @Override
    public void onCheckedChanged(RadioGroup group, @IdRes int checkedId) {

        if (checkedId == R.id.rb_findHouse) {
            getSupportFragmentManager().beginTransaction().hide(personFragment).show(houseFragment).commit();
        } else {
            getSupportFragmentManager().beginTransaction().hide(houseFragment).show(personFragment).commit();
        }
    }


}
