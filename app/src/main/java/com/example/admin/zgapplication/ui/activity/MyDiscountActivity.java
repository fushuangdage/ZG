package com.example.admin.zgapplication.ui.activity;

import android.support.design.widget.TabLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.TextView;

import com.example.admin.zgapplication.R;
import com.example.admin.zgapplication.base.BaseActivity;
import com.example.admin.zgapplication.mvp.module.DiscountListResponse;
import com.example.admin.zgapplication.retrofit.RetrofitHelper;
import com.example.admin.zgapplication.retrofit.rx.BaseObserver;
import com.example.admin.zgapplication.retrofit.rx.RxScheduler;
import com.example.admin.zgapplication.ui.adapter.ZhyBaseRecycleAdapter.CommonAdapter;
import com.example.admin.zgapplication.ui.adapter.ZhyBaseRecycleAdapter.base.ViewHolder;
import com.example.admin.zgapplication.utils.date.TimeUtil;

import java.util.ArrayList;

import butterknife.BindView;

public class MyDiscountActivity extends BaseActivity {


    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;


    @BindView(R.id.tabLayout)
    TabLayout tabLayout;
    private ArrayList<DiscountListResponse.DataBean.ListBean> list;
    private CommonAdapter<DiscountListResponse.DataBean.ListBean> adapter;
    private int tabSelectPosition;


    @Override
    public int setLayout() {
        return R.layout.activity_my_discount;
    }

    @Override
    public void initEvent() {

        tabSelectPosition = tabLayout.getSelectedTabPosition();
        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                tabSelectPosition=tab.getPosition();
                loadDiscountList();
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
        list = new ArrayList<>();
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter = new CommonAdapter<DiscountListResponse.DataBean.ListBean>(this, R.layout.item_coupon, list) {
            @Override
            protected void convert(ViewHolder holder, DiscountListResponse.DataBean.ListBean o, int position) {
                switch (o.getStatus()) {
                    case 1:
                        holder.getConvertView().setBackgroundResource(R.drawable.zhagenweishiyong);
                        break;
                    case 2:
                        holder.getConvertView().setBackgroundResource(R.drawable.zhagenyishiyong);
                        break;
                    case 3:
                        holder.getConvertView().setBackgroundResource(R.drawable.yiguoqi);
                        break;
                }
                ((TextView) holder.getView(R.id.tv_company_name)).setText(o.getCoupon_name());
                ((TextView) holder.getView(R.id.tv_price)).setText(o.getMoney()+"");
                ((TextView) holder.getView(R.id.tv_time)).setText(TimeUtil.
                        formatData(TimeUtil.dateFormatYMD,o.getStart_time())
                        +" - "+TimeUtil.formatData(TimeUtil.dateFormatYMD,o.getExpire_time()));
            }
        };
        recyclerView.setAdapter(adapter);
    }

    @Override
    public void initData() {
        loadDiscountList();
    }

    private void loadDiscountList() {
        RetrofitHelper.getApi().getDiscountList(tabSelectPosition+1).compose(RxScheduler.<DiscountListResponse>defaultScheduler())
                .subscribe(new BaseObserver<DiscountListResponse>(this,mActivity.getClass().getName()) {
                    @Override
                    public void error(Throwable e) {

                    }

                    @Override
                    public void next(DiscountListResponse discountListResponse) {
                        list.clear();
                        list.addAll(discountListResponse.getData().getList());
                        adapter.notifyDataSetChanged();
                    }

                    @Override
                    public void complete() {

                    }
                });
    }
}
