package com.example.admin.zgapplication.ui.activity;

import android.app.Activity;
import android.content.Intent;
import android.util.Log;
import android.view.View;
import android.widget.CheckBox;
import android.widget.TextView;
import android.widget.Toast;

import com.example.admin.zgapplication.R;
import com.example.admin.zgapplication.base.BaseActivity;
import com.example.admin.zgapplication.mvp.module.BillPayImmediately;
import com.example.admin.zgapplication.mvp.module.PayInfoResponse;
import com.example.admin.zgapplication.retrofit.RetrofitHelper;
import com.example.admin.zgapplication.retrofit.rx.RxScheduler;
import com.pingplusplus.android.Pingpp;

import butterknife.BindView;
import butterknife.OnClick;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

/**
 * 调用支付宝微信支付 选择界面
 */
public class PayOnlineActivity extends BaseActivity  {

    private String pay_id;

    @BindView(R.id.tv_money)
    TextView tv_money;

    @BindView(R.id.cb_ali)
    CheckBox cb_ali;
    @BindView(R.id.cb_weixin)
    CheckBox cb_weixin;
    private String water_num;
    private String type;   //订单类型1：租房账单， 2：生活账单
    private String method="1";
    private String order_num;

    @Override
    public int setLayout() {
        return R.layout.activity_pay_online;
    }

    @Override
    public void initEvent() {

    }

    @Override
    public void initData() {
        pay_id = getIntent().getStringExtra("pay_id");
        type = getIntent().getStringExtra("type");
        order_num = getIntent().getStringExtra("order_num");
        RetrofitHelper.getApiWithUid().getPayImmediately(pay_id)
                .compose(RxScheduler.<BillPayImmediately>defaultScheduler())
                .subscribe(new Observer<BillPayImmediately>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(BillPayImmediately billPayImmediately) {
                        tv_money.setText("¥ "+billPayImmediately.getData().getPayment());
                        water_num = billPayImmediately.getData().getWater_num();
                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });

    }

    @OnClick({R.id.tv_pay, R.id.iv_left,R.id.cb_ali,R.id.cb_weixin})
    public void onClick(View view) {
        switch (view.getId()) {

            case R.id.tv_pay:
                // TODO: 2017/10/20 调用支付宝
//                Pingpp.createPayment(mActivity,);
                RetrofitHelper.getApiWithUid().getPayInfo(type,water_num,method)
                        .compose(RxScheduler.<PayInfoResponse>defaultScheduler())
                        .subscribe(new Observer<PayInfoResponse>() {
                            @Override
                            public void onSubscribe(Disposable d) {

                            }

                            @Override
                            public void onNext(PayInfoResponse payInfoResponse) {
                                if (payInfoResponse.getCode()==0) {
                                    Pingpp.createPayment(mActivity,payInfoResponse.getData());
                                }else {
                                    Toast.makeText(mActivity, payInfoResponse.getMsg(), Toast.LENGTH_SHORT).show();
                                }
                            }

                            @Override
                            public void onError(Throwable e) {

                            }

                            @Override
                            public void onComplete() {

                            }
                        });

//                startActivity(PaidBillDetailActivity.class);
                break;
            case R.id.iv_left:
                finish();
                break;

            case R.id.cb_ali:
                method="1";
                solveCheck(view);
                break;
            case R.id.cb_weixin:
                method="2";
                solveCheck(view);
                break;
        }
    }

    public void solveCheck(View view){
        cb_weixin.setChecked(false);
        cb_ali.setChecked(false);
        ((CheckBox) view).setChecked(true);
    }

    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        //支付页面返回处理
        if (requestCode == Pingpp.REQUEST_CODE_PAYMENT) {
            if (resultCode == Activity.RESULT_OK) {
                String result = data.getExtras().getString("pay_result");
                Intent intent;
                if ("success".equals(result)){
                    if (type.equals("2")){
                         intent=new Intent(mActivity,LifeBillShowListActivity.class);
                         intent.putExtra("order_num",order_num);
                         startActivity(intent);
                    }
                }

            /* 处理返回值
             * "success" - 支付
             * 成功
             * "fail"    - 支付失败
             * "cancel"  - 取消支付
             * "invalid" - 支付插件未安装（一般是微信客户端未安装的情况）
             * "unknown" - app进程异常被杀死(一般是低内存状态下,app进程被杀死)
             */
                String errorMsg = data.getExtras().getString("error_msg"); // 错误信息
                String extraMsg = data.getExtras().getString("extra_msg"); // 错误信息
//                showMsg(result, errorMsg, extraMsg);
                Log.d("88888888888", "onActivityResult: "+result);

            }
        }
    }



}
