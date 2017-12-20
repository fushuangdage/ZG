package com.example.admin.zgapplication.ui.activity;

import android.Manifest;
import android.support.annotation.IdRes;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.RadioGroup;

import com.example.admin.zgapplication.R;
import com.example.admin.zgapplication.base.MVPBaseActivity;
import com.example.admin.zgapplication.mvp.presenter.HomePresenter;
import com.example.admin.zgapplication.ui.fragment.HomeFindHouseFragment;
import com.example.admin.zgapplication.ui.fragment.HomeFindPersonFragment;
import com.hyphenate.EMCallBack;
import com.hyphenate.chat.EMClient;
import com.tbruyelle.rxpermissions2.RxPermissions;

import butterknife.BindView;
import butterknife.OnClick;
import io.reactivex.annotations.NonNull;
import io.reactivex.functions.Consumer;


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

    private HomeFindHouseFragment houseFragment;
    private HomeFindPersonFragment personFragment;


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
                .add(R.id.main_container,houseFragment)
                .add(R.id.main_container,personFragment)
                .hide(houseFragment).commit();
        radioGroup.setOnCheckedChangeListener(this);

        View headerView = navigationView.getHeaderView(0);
        View evaluation = headerView.findViewById(R.id.ll_evaluation);
        View order = headerView.findViewById(R.id.ll_order);
        View contact = headerView.findViewById(R.id.ll_contract);
        headerView.findViewById(R.id.ll_shopping_car).setOnClickListener(this);
        headerView.findViewById(R.id.ll_intent).setOnClickListener(this);
        headerView.findViewById(R.id.ll_take_look).setOnClickListener(this);
        headerView.findViewById(R.id.ll_system_msg).setOnClickListener(this);
        headerView.findViewById(R.id.ll_discount).setOnClickListener(this);
        headerView.findViewById(R.id.ll_friend).setOnClickListener(this);

        evaluation.setOnClickListener(this);
        order.setOnClickListener(this);
        contact.setOnClickListener(this);

    }


    @Override
    public void initData() {
        EMClient.getInstance().login("954c8d7dd5367d0cd5b1d6d740c88e82u", "66cefbd4ccb8e9a3b4f80f22aa5be0f1", new EMCallBack() {
//        EMClient.getInstance().login("fushuang", "123456", new EMCallBack() {
            @Override
            public void onSuccess() {
                Log.d("888888888888888888888", "onSuccess: 登陆成功");
            }


            @Override
            public void onError(int i, String s) {
                Log.d("888888888888888888888", "onError: "+s);
            }

            @Override
            public void onProgress(int i, String s) {

            }
        });
        RxPermissions rxPermissions = new RxPermissions(this);
        String[] mPermissionList = new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE,
                Manifest.permission.ACCESS_FINE_LOCATION,
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

//      RetrofitHelper.getApi().downLoadMusic()
//              .compose(RxScheduler.<Dami>defaultScheduler())
//              .subscribe(new BaseObserver<Dami>(this,mActivity.getClass().getName()) {
//                  @Override
//                  public void error(Throwable e) {
//
//                  }
//
//                  @Override
//                  public void next(Dami dami) {
//                      Log.d(TAG, "next: "+dami);
//                  }
//
//                  @Override
//                  public void complete() {
//                      Log.d(TAG, "complete: ");
//                  }
//              });
    }



    @OnClick({R.id.home_find,R.id.home_message,R.id.mine})
    public void onClick(View view){
        switch (view.getId()) {
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
                startActivity(ShopCarActivity.class);
                break;
            case R.id.home_message:
                startActivity(EaseConversationListActivity.class);
                break;
        }
    }

    @Override
    public void onCheckedChanged(RadioGroup group, @IdRes int checkedId) {

        if(checkedId==R.id.rb_findHouse){
            getSupportFragmentManager().beginTransaction().hide(personFragment).show(houseFragment).commit();
        }else {
            getSupportFragmentManager().beginTransaction().hide(houseFragment).show(personFragment).commit();
        }

    }


}
