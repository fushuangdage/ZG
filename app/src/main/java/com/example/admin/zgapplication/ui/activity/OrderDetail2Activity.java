package com.example.admin.zgapplication.ui.activity;

import android.app.Activity;
import android.content.Intent;
import android.util.Log;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;

import com.example.admin.zgapplication.R;
import com.example.admin.zgapplication.base.BaseActivity;
import com.example.admin.zgapplication.mvp.module.PayInfoResponse;
import com.example.admin.zgapplication.mvp.module.RentReadyPayResponse;
import com.example.admin.zgapplication.retrofit.RetrofitHelper;
import com.example.admin.zgapplication.retrofit.rx.BaseObserver;
import com.example.admin.zgapplication.retrofit.rx.RxScheduler;
import com.example.admin.zgapplication.ui.view.PriceDetailDialog;
import com.pingplusplus.android.Pingpp;

import butterknife.BindView;
import butterknife.OnClick;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

public class OrderDetail2Activity extends BaseActivity implements CompoundButton.OnCheckedChangeListener {

    @BindView(R.id.cb_ali)
    CheckBox cb_ali;
    @BindView(R.id.cb_weixin)
    CheckBox cb_weixin;

    public String method="1";
    private String data;
    private PriceDetailDialog dialog;
    private String bill_num;
    private int order_id;

    //    @BindView(R.id.recyclerView)
//    RecyclerView recyclerView;
//    private CommonAdapter<String> adapter;

    @OnClick({R.id.tv_cancel, R.id.tv_price_detail, R.id.tv_pay, R.id.tv_friend_pay, R.id.iv_left})
    public void onClick(View view) {
        switch (view.getId()) {

            case R.id.tv_cancel:
                Intent intent = new Intent();
                intent.putExtra("canclePay", true);
                setResult(1, intent);
                finish();
                break;
            case R.id.tv_price_detail:

                dialog.show();
                break;
            case R.id.tv_friend_pay:
                break;
            case R.id.tv_pay:
                //// TODO: 2017/10/20  调用支付宝支付

                RetrofitHelper.getApi().getPayInfo("1", bill_num, method)
                        .compose(RxScheduler.<PayInfoResponse>defaultScheduler())
                        .subscribe(new Observer<PayInfoResponse>() {
                            @Override
                            public void onSubscribe(Disposable d) {

                            }

                            @Override
                            public void onNext(PayInfoResponse payInfoResponse) {
                                if (payInfoResponse.getCode()==0) {
                                    data = payInfoResponse.getData();
                                    Pingpp.createPayment(mActivity,data);

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

            case R.id.iv_left:
                finish();
                break;

        }
    }

    @Override
    public int setLayout() {
        return R.layout.activity_order_detail2;
    }

    @Override
    public void initEvent() {

        cb_ali.setOnCheckedChangeListener(this);
        cb_weixin.setOnCheckedChangeListener(this);

        dialog = new PriceDetailDialog(this);
//        ArrayList<String> strings = new ArrayList<>();
//        for (int i = 0; i < 1; i++) {
//            strings.add("");
//        }

//        if (strings.size()<=3){
//            LinearLayout.LayoutParams params = (LinearLayout.LayoutParams) recyclerView.getLayoutParams();
//            params.height= LinearLayout.LayoutParams.WRAP_CONTENT;
//            recyclerView.setLayoutParams(params);
//        }
//        recyclerView.setLayoutManager(new LinearLayoutManager(this));
//        adapter = new CommonAdapter<String>(this, R.layout.item_price_already_pay, strings) {
//            @Override
//            protected void convert(ViewHolder holder, String o, int position) {
//
//            }
//        };
//        recyclerView.setAdapter(adapter);
    }

    @Override
    public void initData() {
        order_id = getIntent().getIntExtra("order_id", 0);
        RetrofitHelper.getApiWithUid().getRentReadyPayResponse(order_id+"")
                .compose(RxScheduler.<RentReadyPayResponse>defaultScheduler())
                .subscribe(new BaseObserver<RentReadyPayResponse>(mActivity) {
                    @Override
                    public void error(Throwable e) {

                    }

                    @Override
                    public void next(RentReadyPayResponse rentReadyPayResponse) {
                        dialog.setBean(rentReadyPayResponse.getData());
                        bill_num = rentReadyPayResponse.getData().getBill_num();
                    }

                    @Override
                    public void complete() {

                    }
                });

    }


    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        //支付页面返回处理
        if (requestCode == Pingpp.REQUEST_CODE_PAYMENT) {
            if (resultCode == Activity.RESULT_OK) {
                String result = data.getExtras().getString("pay_result");
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
                Log.d("88888888888", "onActivityResult: ");

                startActivity(FinishOrderActivity.class);
            }
        }
    }


    @Override
    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
        if (isChecked) {
            switch (buttonView.getId()) {
                case R.id.cb_ali:
                    method="1";
                    break;
                case R.id.cb_weixin:
                    method="2";
                    break;
            }
        }
    }
}
