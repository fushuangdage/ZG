package com.example.admin.zgapplication.ui.activity;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.example.admin.zgapplication.R;
import com.example.admin.zgapplication.base.BaseActivity;
import com.example.admin.zgapplication.mvp.module.LifeBillRecordResponse;
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
import java.util.List;

import butterknife.BindView;

public class LifeBillShowListActivity extends BaseActivity {


    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;
    @BindView(R.id.tv_title)
    TextView tv_title;
    @BindView(R.id.refreshLayout)
    RefreshLayout refreshLayout;

    int page=1;

    public List<LifeBillRecordResponse.DataBean.ListBeanX.ListBean> list=new ArrayList<>();
    private CommonAdapter<LifeBillRecordResponse.DataBean.ListBeanX.ListBean> adapter;

    @Override
    public int setLayout() {
        return R.layout.activity_life_bill_show_list;
    }

    @Override
    public void initEvent() {
        tv_title.setText("缴费记录");

        refreshLayout.setOnLoadmoreListener(new OnLoadmoreListener() {
            @Override
            public void onLoadmore(RefreshLayout refreshlayout) {
                page++;
                initData();
            }
        });
        refreshLayout.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(RefreshLayout refreshlayout) {
                page=1;
                initData();
            }
        });


        recyclerView.setLayoutManager(new LinearLayoutManager(mActivity));
        adapter = new CommonAdapter<LifeBillRecordResponse.DataBean.ListBeanX.ListBean>(mActivity,R.layout.item_life_record,list) {
            @Override
            protected void convert(ViewHolder holder, LifeBillRecordResponse.DataBean.ListBeanX.ListBean listBean, int position) {
                if (listBean.time==null) {
                    //数据条目
                    holder.getView(R.id.cb_life_bill).setVisibility(View.GONE);
                    holder.setText(R.id.tv_status,listBean.getStatus());
                    holder.getView(R.id.tv_content).setVisibility(View.GONE);
                    holder.getView(R.id.charge_layout).setVisibility(View.VISIBLE);
                    holder.getView(R.id.tv_pay_time).setVisibility(View.VISIBLE);
                    holder.setText(R.id.tv_pay_time, "支付时间: " + TimeUtil.formatData(TimeUtil.dateFormatYMD3, listBean.getPay_time()));
                    holder.setText(R.id.tv_type, listBean.getItem_name());
                    holder.setText(R.id.tv_time, "订单号: " + listBean.getBill_num());
                    holder.setText(R.id.tv_last_pay, "账单周期: " + listBean.getCircle());
                }else {
                    holder.getView(R.id.tv_content).setVisibility(View.VISIBLE);
                    holder.getView(R.id.charge_layout).setVisibility(View.GONE);
                    holder.setText(R.id.tv_content,listBean.time);
                }
            }
        };
        recyclerView.setAdapter(adapter);
    }

    @Override
    public void initData() {
        String order_num = getIntent().getStringExtra("order_num");
        RetrofitHelper.getApiWithUid().getLifeRecordList(order_num)
                .compose(RxScheduler.<LifeBillRecordResponse>defaultScheduler())
                .doOnNext(new FinishLoadConsumer<LifeBillRecordResponse>(refreshLayout,page))
                .subscribe(new BaseObserver<LifeBillRecordResponse>(mActivity) {
                    @Override
                    public void error(Throwable e) {

                    }

                    @Override
                    public void next(LifeBillRecordResponse lifeBillRecordResponse) {
                        List<LifeBillRecordResponse.DataBean.ListBeanX> listX = lifeBillRecordResponse.getData().getList();
                        for (LifeBillRecordResponse.DataBean.ListBeanX x : listX) {
                            list.clear();
                            list.add(new LifeBillRecordResponse.DataBean.ListBeanX.ListBean(x.getTime()));
                            for (LifeBillRecordResponse.DataBean.ListBeanX.ListBean listBean : x.getList()) {
                                list.add(listBean);
                            }
                        }
                        adapter.notifyDataSetChanged();
                    }

                    @Override
                    public void complete() {

                    }
                });

    }


}
