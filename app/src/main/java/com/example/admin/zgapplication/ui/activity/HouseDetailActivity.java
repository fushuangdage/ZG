package com.example.admin.zgapplication.ui.activity;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.admin.zgapplication.Constant;
import com.example.admin.zgapplication.R;
import com.example.admin.zgapplication.base.BaseActivity;
import com.example.admin.zgapplication.mvp.module.BaseResponse;
import com.example.admin.zgapplication.mvp.module.RoomDetailResponse;
import com.example.admin.zgapplication.mvp.module.SelectOrderInfoBean;
import com.example.admin.zgapplication.retrofit.RetrofitHelper;
import com.example.admin.zgapplication.retrofit.rx.BaseObserver;
import com.example.admin.zgapplication.retrofit.rx.RxScheduler;
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
    @BindView(R.id.tv_collect)
    TextView tv_collect;
    @BindView(R.id.iv_shoppingcar)
    ImageView iv_shoppingcar;

    private HouseEvaluteFragment evaluteFragment;
    private HouseDetailFragment detailFragment;
    private String house_id;
    private String type;
    private String room_id="0";

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
        house_id = getIntent().getStringExtra("house_id");
        type = getIntent().getStringExtra("type");
        room_id = getIntent().getStringExtra("room_id");
    }

    @OnClick({R.id.tv_write_order,R.id.tv_chat_agent,R.id.tv_collect,R.id.iv_shoppingcar})
    public void onClick(View view){
        switch (view.getId()) {

            case R.id.tv_write_order:
                RoomDetailResponse.DataBean data = detailFragment.getData();

                RoomPickDialog roomPickDialog = new RoomPickDialog(this,R.style.room_pick_dialog);
                roomPickDialog.setResultCallBack(new RoomPickDialog.ResultCallBack() {
                    @Override
                    public void resultCallBack(SelectOrderInfoBean bean) {
                        Intent intent = new Intent(mActivity, ChooseAgentActivity.class);
                        intent.putExtra("bean",bean);
                        intent.putExtra("house_id",bean.house_id);
                        intent.putExtra("room_id",bean.room_id);
                        intent.putExtra("type",bean.type);
                        startActivity(intent);
                    }
                });
                roomPickDialog.create();
                roomPickDialog.setData(data);
                roomPickDialog.show();
                break;

            case R.id.iv_left:
                finish();
                break;

            case R.id.tv_chat_agent:
                Bundle bundle = new Bundle();
                bundle.putString("from","HouseDetailActivity");
                bundle.putString("house_id",house_id);
                bundle.putString("type",type);
                bundle.putString("room_id",room_id);

                startActivity(ChooseAgentActivity.class,bundle);
                break;

            case R.id.tv_collect:
            case R.id.iv_shoppingcar:
                RetrofitHelper.getApi().addCollection(Constant.uid,house_id,room_id==null?"0":room_id,type)
                        .compose(RxScheduler.<BaseResponse>defaultScheduler())
                        .subscribe(new BaseObserver<BaseResponse>(mActivity) {
                            @Override
                            public void error(Throwable e) {

                            }
                            @Override
                            public void next(BaseResponse baseResponse) {
                                Toast.makeText(mActivity, baseResponse.getMsg(), Toast.LENGTH_SHORT).show();
                                if (baseResponse.getCode()==0) {
                                    Drawable drawable = getResources().getDrawable(R.drawable.shoucang_select);
                                    drawable.setBounds(0,0,drawable.getMinimumWidth(),drawable.getMinimumHeight());
                                    tv_collect.setCompoundDrawables(null,drawable,null,null);
                                    iv_shoppingcar.setBackgroundResource(R.drawable.shoucang_select);
                                }
                            }

                            @Override
                            public void complete() {

                            }
                        });
                break;

        }
    }
}
