package com.example.admin.zgapplication.ui.activity;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.example.admin.zgapplication.R;
import com.example.admin.zgapplication.base.BaseActivity;
import com.example.admin.zgapplication.mvp.module.RentBillResponse;
import com.example.admin.zgapplication.retrofit.RetrofitHelper;
import com.example.admin.zgapplication.retrofit.rx.BaseObserver;
import com.example.admin.zgapplication.retrofit.rx.FinishLoadConsumer;
import com.example.admin.zgapplication.retrofit.rx.RxScheduler;
import com.example.admin.zgapplication.ui.adapter.ZhyBaseRecycleAdapter.CommonAdapter;
import com.example.admin.zgapplication.ui.adapter.ZhyBaseRecycleAdapter.base.ViewHolder;
import com.scwang.smartrefresh.layout.api.RefreshLayout;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.OnClick;

public class RentBillListActivity extends BaseActivity {

    @BindView(R.id.tv_title)
    TextView tv_title;
    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;
    private CommonAdapter<RentBillResponse.DataBean.ListBean> adapter;
    @BindView(R.id.ll_pay_all)
    View ll_pay_all;
    @BindView(R.id.iv_right)
    View iv_right;
    @BindView(R.id.refreshLayout)
    RefreshLayout refreshLayout;
    private String title;
    private ArrayList<RentBillResponse.DataBean.ListBean> list=new ArrayList<RentBillResponse.DataBean.ListBean>();
    private int currentPage=0;

    @Override
    public int setLayout() {
        return R.layout.activity_rent_bill_list;
    }

    @Override
    public void initEvent() {
        title = getIntent().getStringExtra("title");
        tv_title.setText(title);


        if (title.equals("房租账单")) {
            ll_pay_all.setVisibility(View.GONE);
            iv_right.setVisibility(View.GONE);
            adapter = new CommonAdapter<RentBillResponse.DataBean.ListBean>(this, R.layout.item_rent_bill, list) {
                @Override
                protected void convert(ViewHolder holder, RentBillResponse.DataBean.ListBean s, int position) {
                    ((TextView) holder.getView(R.id.tv_order_stage)).setText(String.format("第%d期房租",s.getWeek()));
                    ((TextView) holder.getView(R.id.tv_status)).setText(s.getStatus());
                    ((TextView) holder.getView(R.id.tv_order_time)).setText("账单周期 : "+s.getCircle());
                    ((TextView) holder.getView(R.id.tv_pay_way)).setText("付款方式 : "+s.getPay());
                    ((TextView) holder.getView(R.id.tv_pay_count)).setText("应付金额 : "+s.getPayment());
                    ((TextView) holder.getView(R.id.tv_left_pay)).setText("需缴纳费用: ¥"+s.getLeave());

                }
            };
        }else {
          adapter = new CommonAdapter<RentBillResponse.DataBean.ListBean>(this, R.layout.item_life_bill, list) {
                @Override
                protected void convert(ViewHolder holder, RentBillResponse.DataBean.ListBean s, int position) {
                        holder.getView(R.id.tv_go_pay).setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                startActivity(PayOnlineActivity.class);
                            }
                        });
                }
            };
        }
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);

    }

    @Override
    public void initData() {

        String order_num = getIntent().getStringExtra("order_num");

        if (title.equals("房租账单")){
            RetrofitHelper.getApi().getRentBill(order_num)
                    .compose(RxScheduler.<RentBillResponse>defaultScheduler())
                    .doOnNext(new FinishLoadConsumer<RentBillResponse>(refreshLayout,currentPage))
                    .subscribe(new BaseObserver<RentBillResponse>(mActivity) {
                        @Override
                        public void error(Throwable e) {

                        }

                        @Override
                        public void next(RentBillResponse rentBillResponse) {
                            if (rentBillResponse.getData().getPage()==1){
                                list.clear();
                                list.addAll(rentBillResponse.getData().getList());
                            }else {
                                list.addAll(rentBillResponse.getData().getList());
                            }
                            adapter.notifyDataSetChanged();
                        }

                        @Override
                        public void complete() {

                        }
                    });
        }

    }

    @OnClick({R.id.iv_left,R.id.tv_go_pay})
    public void onClick(View view){
        switch (view.getId()) {
            case R.id.tv_go_pay:
                startActivity(PayOnlineActivity.class);
                break;
            case R.id.iv_left:
                finish();
                break;
        }
    }
}
