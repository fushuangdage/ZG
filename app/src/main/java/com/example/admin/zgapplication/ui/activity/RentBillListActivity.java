package com.example.admin.zgapplication.ui.activity;

import android.content.Intent;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;

import com.example.admin.zgapplication.R;
import com.example.admin.zgapplication.base.BaseActivity;
import com.example.admin.zgapplication.mvp.module.LifeRentBillResponse;
import com.example.admin.zgapplication.mvp.module.RentBillResponse;
import com.example.admin.zgapplication.retrofit.RetrofitHelper;
import com.example.admin.zgapplication.retrofit.rx.BaseObserver;
import com.example.admin.zgapplication.retrofit.rx.FinishLoadConsumer;
import com.example.admin.zgapplication.retrofit.rx.RxScheduler;
import com.example.admin.zgapplication.ui.adapter.ZhyBaseRecycleAdapter.CommonAdapter;
import com.example.admin.zgapplication.ui.adapter.ZhyBaseRecycleAdapter.MultiItemTypeAdapter;
import com.example.admin.zgapplication.ui.adapter.ZhyBaseRecycleAdapter.base.ViewHolder;
import com.scwang.smartrefresh.layout.api.RefreshLayout;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

import butterknife.BindView;
import butterknife.OnClick;

public class RentBillListActivity extends BaseActivity implements MultiItemTypeAdapter.OnItemClickListener {

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

    @BindView(R.id.tv_pay_sum)
    TextView tv_pay_sum;
    @BindView(R.id.cb_check_all)
    CheckBox cb_check_all;


    private String title;
    private Set<Integer> life_bill = new HashSet<>();
    private ArrayList<RentBillResponse.DataBean.ListBean> list = new ArrayList<RentBillResponse.DataBean.ListBean>();
    private ArrayList<LifeRentBillResponse.DataBean.ListBean> life_list = new ArrayList<LifeRentBillResponse.DataBean.ListBean>();
    private int currentPage = 0;
    private CommonAdapter<LifeRentBillResponse.DataBean.ListBean> life_adapter;
    private double sum_pay;
    private String pay_id;

    @Override
    public int setLayout() {
        return R.layout.activity_rent_bill_list;
    }

    @Override
    public void initEvent() {
        title = getIntent().getStringExtra("title");
        tv_title.setText(title);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        if (title.equals("房租账单")) {
            ll_pay_all.setVisibility(View.GONE);
            iv_right.setVisibility(View.GONE);
            adapter = new CommonAdapter<RentBillResponse.DataBean.ListBean>(this, R.layout.item_rent_bill, list) {
                @Override
                protected void convert(ViewHolder holder, final RentBillResponse.DataBean.ListBean s, int position) {
                    ((TextView) holder.getView(R.id.tv_order_stage)).setText(String.format("第%d期房租", s.getWeek()));
                    if (s.getStatus().equals("已完成")) {

                        ((TextView) holder.getView(R.id.tv_status)).setText(s.getStatus());
                        ((TextView) holder.getView(R.id.tv_pay)).setText("查看详情");
                        ((TextView) holder.getView(R.id.tv_pay)).setBackgroundResource(R.drawable.white_ract_border_bg);

                        holder.setOnClickListener(R.id.tv_pay, new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                Intent intent = new Intent(mActivity, PaidBillDetailActivity.class);
                                intent.putExtra("bill_num", s.getBill_num());
                                startActivity(intent);
                            }
                        });
                    } else {
                        ((TextView) holder.getView(R.id.tv_pay)).setText("去支付");

                        ((TextView) holder.getView(R.id.tv_pay)).setBackgroundResource(R.drawable.green_border_bg);

                        holder.setOnClickListener(R.id.tv_pay, new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                Intent intent = new Intent(mActivity, BillDetailActivity.class);
                                intent.putExtra("bill_num", s.getBill_num());
                                startActivity(intent);
                            }
                        });
                    }

                    ((TextView) holder.getView(R.id.tv_order_time)).setText("账单周期 : " + s.getCircle());
                    ((TextView) holder.getView(R.id.tv_pay_way)).setText("付款方式 : " + s.getPay());
                    ((TextView) holder.getView(R.id.tv_pay_count)).setText("应付金额 : " + s.getPayment());
                    ((TextView) holder.getView(R.id.tv_left_pay)).setText("需缴纳费用: ¥" + s.getLeave());


                }
            };
            recyclerView.setAdapter(adapter);
        } else if (title.equals("全部账单")) {
            life_adapter = new CommonAdapter<LifeRentBillResponse.DataBean.ListBean>(this, R.layout.item_life_bill, life_list) {
                @Override
                protected void convert(ViewHolder holder, LifeRentBillResponse.DataBean.ListBean s, int position) {
                    ((CheckBox) holder.getView(R.id.cb_life_bill)).setChecked(s.isCheck);
                    holder.setText(R.id.tv_type, s.getItem_name());
                    holder.setText(R.id.tv_time, s.getCircle());
                    holder.setText(R.id.tv_last_pay, "上次缴纳费用: ¥" + s.getLast_pay());
                    holder.setText(R.id.tv_should_pay, "需缴纳费用: ¥" + s.getPayment());
                }
            };
            life_adapter.setOnItemClickListener(this);
            recyclerView.setAdapter(life_adapter);
            cb_check_all.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                    getSumpay(isChecked);
                    life_adapter.notifyDataSetChanged();
                }
            });
        }
    }

    private void getSumpay(boolean isChecked) {
        sum_pay = 0;
        for (LifeRentBillResponse.DataBean.ListBean integer : life_list) {
            integer.isCheck = isChecked;
            if (isChecked) {
                sum_pay += integer.getPayment();
            }
        }
        tv_pay_sum.setText(String.format("账单总额:%.2f元", sum_pay));
    }

    @Override
    public void initData() {

        String order_num = getIntent().getStringExtra("order_num");

        if (title.equals("房租账单")) {
            RetrofitHelper.getApiWithUid().getRentBill(order_num)
                    .compose(RxScheduler.<RentBillResponse>defaultScheduler())
                    .doOnNext(new FinishLoadConsumer<RentBillResponse>(refreshLayout, currentPage))
                    .subscribe(new BaseObserver<RentBillResponse>(mActivity) {
                        @Override
                        public void error(Throwable e) {

                        }

                        @Override
                        public void next(RentBillResponse rentBillResponse) {
                            if (rentBillResponse.getData().getPage() == 1) {
                                list.clear();
                                list.addAll(rentBillResponse.getData().getList());
                            } else {
                                list.addAll(rentBillResponse.getData().getList());
                            }
                            adapter.notifyDataSetChanged();
                        }

                        @Override
                        public void complete() {

                        }
                    });
        } else if (title.equals("全部账单")) {
            RetrofitHelper.getApiWithUid().getLifeRentBillResponse(order_num)
                    .compose(RxScheduler.<LifeRentBillResponse>defaultScheduler())
                    .doOnNext(new FinishLoadConsumer<LifeRentBillResponse>(refreshLayout, currentPage))
                    .subscribe(new BaseObserver<LifeRentBillResponse>(mActivity) {
                        @Override
                        public void error(Throwable e) {

                        }

                        @Override
                        public void next(LifeRentBillResponse lifeRentBillResponse) {
                            if (currentPage == 1)
                                life_list.clear();
                            life_list.addAll(lifeRentBillResponse.getData().getList());
                            life_adapter.notifyDataSetChanged();
                        }

                        @Override
                        public void complete() {

                        }
                    });
        }

    }

    @OnClick({R.id.iv_left, R.id.tv_go_pay})
    public void onClick(View view) {
        Intent intent = new Intent(mActivity, PayOnlineActivity.class);
        switch (view.getId()) {
            case R.id.tv_go_pay:
                if (!title.equals("全部账单")) {
//                    intent = new Intent(mActivity, PayOnlineActivity.class);
                } else {
                    //加入生活账单所选中的id
                    StringBuilder builder = new StringBuilder();
                    for (LifeRentBillResponse.DataBean.ListBean bean : life_list) {
                        if (bean.isCheck) {
                            builder.append(bean.getId()).append(",");
                        }
                    }
                    String string = builder.toString();
                    if (string.length() > 1) {
                        pay_id = string.substring(0, string.length() - 1);
                    }
                    intent.putExtra("pay_id", pay_id);
                    intent.putExtra("type", "2");

                }
                startActivity(intent);
                break;
            case R.id.iv_left:
                finish();
                break;

        }
    }

    @Override
    public void onItemClick(View view, RecyclerView.ViewHolder holder, int position) {
        if (title.equals("全部账单")) {
            CheckBox checkBox = (CheckBox) holder.itemView.findViewById(R.id.cb_life_bill);
            checkBox.setChecked(!checkBox.isChecked());
            life_list.get(position).isCheck = checkBox.isChecked();
            sum_pay = 0;
            for (LifeRentBillResponse.DataBean.ListBean listBean : life_list) {
                if (listBean.isCheck) {
                    sum_pay += listBean.getPayment();
                }
            }
            tv_pay_sum.setText(String.format("账单总额:%.2f元", sum_pay));
        }
    }

    @Override
    public boolean onItemLongClick(View view, RecyclerView.ViewHolder holder, int position) {
        return false;
    }
}
