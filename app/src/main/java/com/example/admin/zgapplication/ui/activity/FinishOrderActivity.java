package com.example.admin.zgapplication.ui.activity;

import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.admin.zgapplication.R;
import com.example.admin.zgapplication.base.BaseActivity;
import com.example.admin.zgapplication.mvp.module.OrderDetailResponse;
import com.example.admin.zgapplication.retrofit.RetrofitHelper;
import com.example.admin.zgapplication.retrofit.rx.BaseObserver;
import com.example.admin.zgapplication.retrofit.rx.RxScheduler;
import com.example.admin.zgapplication.ui.view.PriceDetailDialog;
import com.example.admin.zgapplication.utils.date.TimeUtil;

import butterknife.BindView;
import butterknife.OnClick;


public class FinishOrderActivity extends BaseActivity {

    @BindView(R.id.iv_house)
    ImageView iv_house;
    @BindView(R.id.tv_house_name)
    TextView tv_house_name;
    @BindView(R.id.tv_house_info)
    TextView tv_house_info;
    @BindView(R.id.tv_house_location)
    TextView tv_house_location;
    @BindView(R.id.tv_house_rent)
    TextView tv_house_rent;
    @BindView(R.id.ll_tag_container)
    LinearLayout ll_tag_container;

    @BindView(R.id.rent_month)
    TextView rent_month;
    @BindView(R.id.pay_type)
    TextView pay_type;
    @BindView(R.id.room_no)
    TextView room_no;

    @BindView(R.id.tv_order_no)
    TextView tv_order_no;
    @BindView(R.id.tv_order_pay_time)
    TextView tv_order_pay_time;
    @BindView(R.id.tv_order_payment)
    TextView tv_order_payment;
    @BindView(R.id.tv_order_net)
    TextView tv_order_net;
    private OrderDetailResponse.OrderDetailDataBean.ListBean bean;


    @Override
    public int setLayout() {
        return R.layout.activity_finish_order;
    }

    @Override
    public void initEvent() {

    }

    @Override
    public void initData() {
        final String order_id = getIntent().getStringExtra("order_id");
        RetrofitHelper.getApiWithUid().getOrderDetail(order_id)
                .compose(RxScheduler.<OrderDetailResponse>defaultScheduler())
                .subscribe(new BaseObserver<OrderDetailResponse>(mActivity) {
                    @Override
                    public void error(Throwable e) {

                    }

                    @Override
                    public void next(OrderDetailResponse orderDetailResponse) {
                        bean = (OrderDetailResponse.OrderDetailDataBean.ListBean) orderDetailResponse.getData().getList();
                        tv_house_name.setText(bean.getHouse_title());
                        tv_house_info.setText(bean.getHouse_area());
                        tv_house_location.setText(bean.getAddress());
                        tv_house_rent.setText(bean.getRent()+"元/月");

//                        showThreeTag(bean,ll_tag_container);

                        rent_month.setText(bean.getRent_month()+"个月");
                        pay_type.setText(bean.getPay_type());
                        room_no.setText(bean.getRoom_num());


                        tv_order_no.setText("订单号："+ bean.getOrder());
                        tv_order_net.setText("代理商："+ bean.getNet());
                        tv_order_pay_time.setText("支付时间："+TimeUtil.formatData(TimeUtil.dateFormatYMD3, bean.getPay_time()));
                        tv_order_payment.setText("支付金额: "+ bean.getPayment());

                    }

                    @Override
                    public void complete() {

                    }
                });
    }

//    private void  showThreeTag(OrderDetailResponse.OrderListDataBean.ListBean bean, LinearLayout ll_tag_container) {
//        for (int i = 0; i < 3&&bean.getLabel()!=null&&bean.getLabel().size()>i; i++) {
//            TextView childAt = ((TextView) ll_tag_container.getChildAt(i));
//            childAt.setVisibility(View.VISIBLE);
//            childAt.setText(bean.getLabel().get(i));
//        }
//    }

    @OnClick({R.id.tv_price_detail,R.id.tv_evaluate})
    public void onClick(View view){
        switch (view.getId()) {
            case R.id.tv_price_detail:
                PriceDetailDialog dialog = new PriceDetailDialog(this);
                dialog.show();
//                dialog.setBean(bean);
                break;
            case R.id.tv_evaluate:
                startActivity(MakeEvaluateActivity.class);
                break;
            case R.id.iv_left:
                finish();
                break;
        }
    }


}
