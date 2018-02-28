package com.example.admin.zgapplication.ui.activity;

import android.content.Intent;
import android.net.Uri;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
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


    @BindView(R.id.iv_userhead)
    ImageView iv_userhead;
    @BindView(R.id.tv_user_name)
    TextView tv_user_name;
    @BindView(R.id.tv_company_name)
    TextView tv_company_name;
    @BindView(R.id.tv_custom_info)
    TextView tv_custom_info;
    @BindView(R.id.tv_agent_info)
    TextView tv_agent_info;
    @BindView(R.id.tv_contact_agent)
    TextView tv_contact_agent;


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
                        bean = orderDetailResponse.getData().getList();
                        Glide.with(mActivity).load(bean.getAvatar()).into(iv_userhead);
                        tv_user_name.setText(bean.getAgent());
                        tv_company_name.setText(bean.getCompany_name());
                        Glide.with(mActivity).load(bean.getHouse_photo()).into(iv_house);


                        tv_house_name.setText(bean.getHouse_title());
                        tv_house_info.setText(bean.getHouse_area());
                        tv_house_location.setText(bean.getAddress());
                        tv_house_rent.setText(bean.getRent_money()+"元/月");

//                        showThreeTag(bean,ll_tag_container);

                        rent_month.setText(bean.getRent_month()+"个月");
                        pay_type.setText(bean.getPay_type());
                        room_no.setText(bean.getRoom_num());


                        tv_order_no.setText("订单号："+ bean.getOrder());
                        tv_order_net.setText("代理商：扎根网");
                        tv_order_pay_time.setText("支付时间："+TimeUtil.formatData(TimeUtil.dateFormatYMD3, bean.getPay_time()));
                        tv_order_payment.setText("支付金额: ¥"+ bean.getPayment());

                        tv_custom_info.setText(String.format("入住人信息：%s %s",bean.getPhone_number(),bean.getReal_name()));
                        tv_agent_info.setText(String.format("经纪人信息：%s %s",bean.getTelephone(),bean.getAgent()));
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

    @OnClick({R.id.tv_price_detail,R.id.tv_evaluate,R.id.tv_contact_agent})
    public void onClick(View view){
        switch (view.getId()) {
            case R.id.tv_contact_agent:
                Intent intent1 = new Intent(Intent.ACTION_DIAL, Uri.parse("tel:" + bean.getPhone_number()));
                intent1.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent1);
                break;


            case R.id.tv_price_detail:
                PriceDetailDialog dialog = new PriceDetailDialog(this);
                dialog.show();
//                dialog.setBean(bean);
                break;
            case R.id.tv_evaluate:
                Intent intent = new Intent(mActivity, MakeEvaluateActivity.class);
                intent.putExtra("id",bean.getOrder_id());
                intent.putExtra("method","1");
                intent.putExtra("evaluated",false);
                startActivity(intent);
                break;
            case R.id.iv_left:
                finish();
                break;
        }
    }


}
