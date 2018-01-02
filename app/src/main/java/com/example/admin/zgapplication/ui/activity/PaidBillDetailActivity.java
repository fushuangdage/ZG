package com.example.admin.zgapplication.ui.activity;

import android.view.View;
import android.widget.TextView;

import com.example.admin.zgapplication.R;
import com.example.admin.zgapplication.base.BaseActivity;
import com.example.admin.zgapplication.mvp.module.BillFinishResponse;
import com.example.admin.zgapplication.retrofit.RetrofitHelper;
import com.example.admin.zgapplication.retrofit.rx.BaseObserver;
import com.example.admin.zgapplication.retrofit.rx.RxScheduler;

import butterknife.BindView;
import butterknife.OnClick;

public class PaidBillDetailActivity extends BaseActivity {


    @BindView(R.id.ll_agent_fee)
    View ll_agent_fee;
    @BindView(R.id.ll_deposit)
    View ll_deposit;
    @BindView(R.id.ll_serve_fee)
    View ll_serve_fee;
    @BindView(R.id.tv_title)
    TextView tv_title;
    @BindView(R.id.ll_sale_promotion)
    View ll_sale_promotion;

    @BindView(R.id.bill_num)
    TextView bill_num;
    @BindView(R.id.info)
    TextView info;
    @BindView(R.id.rent_stage)
    TextView rent_stage;
    @BindView(R.id.lease_term)
    TextView lease_term;
    @BindView(R.id.tv_rent_count)
    TextView tv_rent_count;
    @BindView(R.id.rent_money)
    TextView rent_money;
    @BindView(R.id.tv_rent_sum)
    TextView tv_rent_sum;
    @BindView(R.id.tv_bill_num)
    TextView tv_bill_num;
    @BindView(R.id.tv_pay_time)
    TextView tv_pay_time;
    @BindView(R.id.tv_pay_kind)
    TextView tv_pay_kind;


    @Override
    public int setLayout() {
        return R.layout.activity_bill_pay_detail;
    }

    @Override
    public void initEvent() {
        ll_agent_fee.setVisibility(View.GONE);
        ll_deposit.setVisibility(View.GONE);
        ll_serve_fee.setVisibility(View.GONE);
        ll_sale_promotion.setVisibility(View.GONE);
        tv_title.setText("支付详情");

    }

    @Override
    public void initData() {
        String order_num = getIntent().getStringExtra("bill_num");
        RetrofitHelper.getApi().getDoneBill(order_num)
                .compose(RxScheduler.<BillFinishResponse>defaultScheduler())
                .subscribe(new BaseObserver<BillFinishResponse>(mActivity) {
                    @Override
                    public void error(Throwable e) {

                    }

                    @Override
                    public void next(BillFinishResponse billFinishResponse) {
                        BillFinishResponse.DataBean data = billFinishResponse.getData();
                        bill_num.setText(data.getOrder_num());
                        info.setText(data.getTitle());
                        lease_term.setText(data.getWeek());
                        rent_money.setText("¥"+data.getRent());
                        tv_rent_count.setText("*"+data.getMonth());
                        tv_rent_sum.setText("¥"+data.getRent_sum());
                        tv_bill_num.setText(data.getBill_num());
                        tv_pay_time.setText(data.getPay_time());
                        tv_pay_kind.setText(data.getPay_kind());

                    }

                    @Override
                    public void complete() {

                    }
                });
    }

    @OnClick({R.id.iv_left})
    public void onClick(View view ){
        switch (view.getId()) {
            case R.id.iv_left:
                finish();
                break;
        }
    }

}
