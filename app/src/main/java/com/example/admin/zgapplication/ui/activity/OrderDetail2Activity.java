package com.example.admin.zgapplication.ui.activity;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.util.Log;
import android.view.View;
import android.widget.CheckBox;
import android.widget.TextView;
import android.widget.Toast;

import com.example.admin.zgapplication.R;
import com.example.admin.zgapplication.base.BaseActivity;
import com.example.admin.zgapplication.mvp.module.PayInfoResponse;
import com.example.admin.zgapplication.mvp.module.RentReadyPayResponse;
import com.example.admin.zgapplication.retrofit.RetrofitHelper;
import com.example.admin.zgapplication.retrofit.rx.BaseObserver;
import com.example.admin.zgapplication.retrofit.rx.RxScheduler;
import com.example.admin.zgapplication.ui.view.PriceDetailDialog;
import com.pingplusplus.android.Pingpp;
import com.umeng.socialize.ShareAction;
import com.umeng.socialize.UMShareListener;
import com.umeng.socialize.bean.SHARE_MEDIA;
import com.umeng.socialize.media.UMImage;
import com.umeng.socialize.media.UMWeb;

import butterknife.BindView;
import butterknife.OnClick;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

public class OrderDetail2Activity extends BaseActivity {

    @BindView(R.id.cb_ali)
    CheckBox cb_ali;
    @BindView(R.id.cb_weixin)
    CheckBox cb_weixin;

    @BindView(R.id.tv_should_pay)
    TextView tv_should_pay;


    public String method="1";
    private String data;
    private PriceDetailDialog dialog;
    private String bill_num;
    private int order_id;
    private RentReadyPayResponse rentReadyPayResponse;

    //    @BindView(R.id.recyclerView)
//    RecyclerView recyclerView;
//    private CommonAdapter<String> adapter;

    public void solveCheck(View view){
        cb_weixin.setChecked(false);
        cb_ali.setChecked(false);
        ((CheckBox) view).setChecked(true);
    }

    @OnClick({R.id.tv_cancel, R.id.tv_price_detail, R.id.tv_pay, R.id.tv_friend_pay, R.id.iv_left,R.id.cb_ali,R.id.cb_weixin})
    public void onClick(View view) {
        switch (view.getId()) {

            case R.id.cb_ali:
                solveCheck(view);
                method="1";
                break;

            case R.id.cb_weixin:
                solveCheck(view);
                method="2";
                break;

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
                String imageUrl ="https://"+rentReadyPayResponse.getData().getFriend_link();
                UMImage image = new UMImage(mActivity,R.drawable.zhagen_logo);
                image.compressStyle = UMImage.CompressStyle.SCALE;//大小压缩，默认为大小压缩，适合普通很大的图
                image.compressStyle = UMImage.CompressStyle.QUALITY;//质量压缩，适合长图的分享
                image.compressFormat = Bitmap.CompressFormat.PNG;//网络图片
                UMWeb web = new UMWeb(imageUrl);

                web.setTitle("我在扎根网平台要租一套房源");//标题
                web.setThumb(image);  //缩略图
                web.setDescription("快点来给人家支付啦");//描述
                new ShareAction(this)
                        .withMedia(web)
                        .setDisplayList(SHARE_MEDIA.SINA,SHARE_MEDIA.QQ,SHARE_MEDIA.WEIXIN,SHARE_MEDIA.ALIPAY)
                        .setCallback(new UMShareListener() {
                            @Override
                            public void onStart(SHARE_MEDIA share_media) {

                            }

                            @Override
                            public void onResult(SHARE_MEDIA share_media) {

                            }

                            @Override
                            public void onError(SHARE_MEDIA share_media, Throwable throwable) {

                            }

                            @Override
                            public void onCancel(SHARE_MEDIA share_media) {

                            }
                        })
                        .open();
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
                        OrderDetail2Activity.this.rentReadyPayResponse = rentReadyPayResponse;
                        tv_should_pay.setText("¥"+ rentReadyPayResponse.getData().getPayment());
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

                if ("success".equals(result)){
                    Intent intent = new Intent(mActivity, FinishOrderActivity.class);
                    intent.putExtra("order_id",order_id+"");
                    startActivity(intent);
                }else {
                    Toast.makeText(this, "支付调用失败", Toast.LENGTH_SHORT).show();
                }
            }
        }
    }

}
