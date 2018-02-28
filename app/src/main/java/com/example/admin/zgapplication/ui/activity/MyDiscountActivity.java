package com.example.admin.zgapplication.ui.activity;

import android.support.design.widget.TabLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.admin.zgapplication.R;
import com.example.admin.zgapplication.base.BaseActivity;
import com.example.admin.zgapplication.mvp.module.DiscountListResponse;
import com.example.admin.zgapplication.retrofit.RetrofitHelper;
import com.example.admin.zgapplication.retrofit.rx.BaseObserver;
import com.example.admin.zgapplication.retrofit.rx.FinishLoadConsumer;
import com.example.admin.zgapplication.retrofit.rx.RxScheduler;
import com.example.admin.zgapplication.ui.adapter.ZhyBaseRecycleAdapter.CommonAdapter;
import com.example.admin.zgapplication.ui.adapter.ZhyBaseRecycleAdapter.base.ViewHolder;
import com.example.admin.zgapplication.utils.date.TimeUtil;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnLoadmoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.OnClick;

public class MyDiscountActivity extends BaseActivity {


    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;


    @BindView(R.id.tabLayout)
    TabLayout tabLayout;

    @BindView(R.id.refreshLayout)
    RefreshLayout refreshLayout;
    private ArrayList<DiscountListResponse.DataBean.ListBean> list=new ArrayList<>();
    private CommonAdapter<DiscountListResponse.DataBean.ListBean> adapter;
    private int tabSelectPosition;
    private int currentPage=1;


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


        refreshLayout.setOnLoadmoreListener(new OnLoadmoreListener() {
            @Override
            public void onLoadmore(RefreshLayout refreshlayout) {
                currentPage++;
                loadDiscountList();
            }
        });

        refreshLayout.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(RefreshLayout refreshlayout) {
                refreshlayout.setEnableLoadmore(true);
                currentPage=1;
                loadDiscountList();
            }
        });


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
                Glide.with(mActivity).load(R.drawable.zhagen_logo).into((ImageView) holder.getView(R.id.iv_icon));
            }
        };
        recyclerView.setAdapter(adapter);


    }


    @Override
    public void initData() {
        loadDiscountList();
    }

    private void loadDiscountList() {
        RetrofitHelper.getApiWithUid().getDiscountList(tabSelectPosition+1,currentPage)
                .compose(RxScheduler.<DiscountListResponse>defaultScheduler())
                .doOnNext(new FinishLoadConsumer<DiscountListResponse>(refreshLayout,currentPage))
                .subscribe(new BaseObserver<DiscountListResponse>(this,mActivity.getClass().getName()) {
                    @Override
                    public void error(Throwable e) {

                    }

                    @Override
                    public void next(DiscountListResponse discountListResponse) {
                        DiscountListResponse.DataBean data = discountListResponse.getData();
                        /**
                         * 集中在返回数据时处理上拉加载和刷新的分页问题
                         */
                        if (data.getPage()==1){
                            list.clear();
                            list.addAll(data.getList());
                        }
                        else {
                            list.addAll(data.getList());
                        }
                        adapter.notifyDataSetChanged();
                    }

                    @Override
                    public void complete() {

                    }
                });
    }

    @OnClick({R.id.iv_left,R.id.tv_right})
    public void onClick(View view){
        switch (view.getId()) {
            case R.id.iv_left:
                finish();
                break;
            case R.id.tv_right:
                startActivity(ExchangeCouponActivity.class);
                break;
        }
    }
}
