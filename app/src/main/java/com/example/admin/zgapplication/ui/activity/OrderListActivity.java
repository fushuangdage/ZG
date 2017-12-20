package com.example.admin.zgapplication.ui.activity;

import android.support.design.widget.TabLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.admin.zgapplication.R;
import com.example.admin.zgapplication.base.BaseActivity;
import com.example.admin.zgapplication.mvp.module.OrderList;
import com.example.admin.zgapplication.retrofit.RetrofitHelper;
import com.example.admin.zgapplication.retrofit.rx.BaseObserver;
import com.example.admin.zgapplication.retrofit.rx.RxScheduler;
import com.example.admin.zgapplication.ui.adapter.ZhyBaseRecycleAdapter.CommonAdapter;
import com.example.admin.zgapplication.ui.adapter.ZhyBaseRecycleAdapter.base.ViewHolder;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnLoadmoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

public class OrderListActivity extends BaseActivity {


    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;

    @BindView(R.id.refreshLayout)
    RefreshLayout refreshLayout;

    @BindView(R.id.tv_title)
    TextView tv_title;

    @BindView(R.id.tabLayout)
    TabLayout tabLayout;

    public Integer currentPage=1;
    public Integer status;

    private List<OrderList.DataBean.ListBean> data=new ArrayList<>();
    private CommonAdapter<OrderList.DataBean.ListBean> adapter;

    @Override
    public int setLayout() {
        return R.layout.activity_order_list;
    }

    @Override
    public void initEvent() {


        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                status=tab.getPosition();
                loadData();
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
                loadData();
            }
        });

        refreshLayout.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(RefreshLayout refreshlayout) {
                refreshlayout.setEnableLoadmore(true);
                currentPage=1;
                loadData();
            }
        });

        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        adapter = new CommonAdapter<OrderList.DataBean.ListBean>(this, R.layout.item_order_list, data) {

            @Override
            protected void convert(ViewHolder holder, OrderList.DataBean.ListBean listBean, int position) {
                ((TextView) holder.getView(R.id.tv_user_tag)).setText(listBean.getCompany_name()+"  "+listBean.getAgent());
                ((TextView) holder.getView(R.id.tv_state)).setText(listBean.getAgent());
                ((TextView) holder.getView(R.id.tv_total_price)).setText(listBean.getPayment());
                ((TextView) holder.getView(R.id.tv_house_location)).setText(listBean.getAddress());
                ((TextView) holder.getView(R.id.tv_house_name)).setText(listBean.getHouse_title());
                Glide.with(mActivity).load(listBean.getHouse_photo()).into((ImageView) holder.getView(R.id.iv_house));
                ((TextView) holder.getView(R.id.tv_house_rent)).setText(listBean.getRent_money());
                showThreeTag(listBean, (LinearLayout) holder.getView(R.id.ll_tag_container));
            }
        };

        recyclerView.setAdapter(adapter);
        tv_title.setText("租房订单");
    }

    private void  showThreeTag(OrderList.DataBean.ListBean bean, LinearLayout ll_tag_container) {
        for (int i = 0; i < 3&&bean.getLabel()!=null&&bean.getLabel().size()>i; i++) {
            TextView childAt = ((TextView) ll_tag_container.getChildAt(i));
            childAt.setVisibility(View.VISIBLE);
            childAt.setText(bean.getLabel().get(i));
        }
    }
    @Override
    public void initData() {
        loadData();

    }

    private void loadData() {
        RetrofitHelper.getApiWithUid().getOrderList(status,currentPage).compose(RxScheduler.<OrderList>defaultScheduler())
                .subscribe(new BaseObserver<OrderList>(this,mActivity.getClass().getName()) {
            @Override
            public void error(Throwable e) {

            }

            @Override
            public void next(OrderList orderList) {

                /**
                 * 集中在返回数据时处理上拉加载和刷新的分页问题
                 */
                if (orderList.getData().getPage().equals("1")){
                    data.clear();
                    data.addAll(orderList.getData().getList());
                }else if (orderList.getData().getSum_page()==currentPage) {
                   refreshLayout.setEnableLoadmore(false);
                   data.addAll(orderList.getData().getList());
                }else {
                    data.addAll(orderList.getData().getList());
                }
                adapter.notifyDataSetChanged();
            }

            @Override
            public void complete() {
                refreshLayout.finishLoadmore();
                refreshLayout.finishRefresh();
            }
        });
    }
}
