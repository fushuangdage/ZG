package com.example.admin.zgapplication.ui.activity;

import android.content.Intent;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.admin.zgapplication.R;
import com.example.admin.zgapplication.base.BaseActivity;
import com.example.admin.zgapplication.mvp.module.BillFinishResponse;
import com.example.admin.zgapplication.mvp.module.ConfirmPayResponse;
import com.example.admin.zgapplication.retrofit.RetrofitHelper;
import com.example.admin.zgapplication.retrofit.rx.BaseObserver;
import com.example.admin.zgapplication.retrofit.rx.RxScheduler;

import butterknife.BindView;
import butterknife.OnClick;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

public class BillDetailActivity extends BaseActivity {


    @BindView(R.id.ll_agent_fee)
    View ll_agent_fee;
    @BindView(R.id.ll_deposit)
    View ll_deposit;
    @BindView(R.id.ll_serve_fee)
    View ll_serve_fee;
    @BindView(R.id.tv_title)
    TextView tv_title;
    @BindView(R.id.bill_num)
    TextView tv_bill_num;
    @BindView(R.id.info)
    TextView info;
    @BindView(R.id.lease_term)
    TextView lease_term;
    @BindView(R.id.tv_rent_count)
    TextView tv_rent_count;
    @BindView(R.id.rent_money)
    TextView rent_money;
    @BindView(R.id.tv_rent_sum)
    TextView tv_rent_sum;
    private String user_coupon_id;
    private String user_coupon_money;
    private String bill_num;


    @Override
    public int setLayout() {
        return R.layout.activity_bill_detail;
    }

    @Override
    public void initEvent() {
        tv_title.setText("账单详情");
        ll_agent_fee.setVisibility(View.GONE);
        ll_deposit.setVisibility(View.GONE);
        ll_serve_fee.setVisibility(View.GONE);
    }

    @Override
    public void initData() {
        bill_num = getIntent().getStringExtra("bill_num");
        RetrofitHelper.getApi().getToPayBill(bill_num)
                .compose(RxScheduler.<BillFinishResponse>defaultScheduler())
                .subscribe(new BaseObserver<BillFinishResponse>(mActivity) {
                    @Override
                    public void error(Throwable e) {

                    }

                    @Override
                    public void next(BillFinishResponse billFinishResponse) {
                        BillFinishResponse.DataBean data = billFinishResponse.getData();
                        tv_bill_num.setText(data.getOrder_num());
                        info.setText(data.getTitle());
                        lease_term.setText(data.getWeek());
                        rent_money.setText("¥"+data.getRent());
                        tv_rent_count.setText("*"+data.getMonth());
                        tv_rent_sum.setText("¥"+data.getRent_sum());
                    }

                    @Override
                    public void complete() {

                    }
                });
    }

    @OnClick({R.id.tv_commit,R.id.rl_zg_coupon})
    public void onClick(View view){
        Intent intent;
        switch (view.getId()) {
            case R.id.tv_commit:
                RetrofitHelper.getApi().confirmPayResponse(bill_num,user_coupon_id,user_coupon_money)
                        .compose(RxScheduler.<ConfirmPayResponse>defaultScheduler())
                        .subscribe(new Observer<ConfirmPayResponse>() {
                            @Override
                            public void onSubscribe(Disposable d) {

                            }

                            @Override
                            public void onNext(ConfirmPayResponse confirmPayResponse) {
                                if (confirmPayResponse.getCode()==0) {
                                    startActivity(PayOnlineActivity.class);
                                }else {
                                    Toast.makeText(mActivity, confirmPayResponse.getMsg(), Toast.LENGTH_SHORT).show();
                                }
                            }

                            @Override
                            public void onError(Throwable e) {

                            }

                            @Override
                            public void onComplete() {

                            }
                        });

                break;
            case R.id.rl_zg_coupon:
                intent= new Intent(this,CouponChooseActivity.class);
                intent.putExtra("title","选择扎根优惠券");
                startActivityForResult(intent,1);
                break;

        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode==1){
            //选择优惠卷回调
            user_coupon_id = data.getStringExtra("user_coupon_id");
            user_coupon_money = data.getStringExtra("user_coupon_money");

        }
    }
}
