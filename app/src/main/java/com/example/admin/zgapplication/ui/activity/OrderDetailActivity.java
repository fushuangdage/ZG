package com.example.admin.zgapplication.ui.activity;

import android.annotation.SuppressLint;
import android.content.Intent;
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

import butterknife.BindView;
import butterknife.OnClick;

import static com.example.admin.zgapplication.R.id.tv_cancel_over;


public class OrderDetailActivity extends BaseActivity {

    @BindView(tv_cancel_over)
    TextView tv_cancle_over;
    @BindView(R.id.rl_cancle_head1)
    View rl_cancle_head1;
    @BindView(R.id.rl_cancle_head0)
    View rl_cancle_head0;
    @BindView(R.id.ll_choose_coupon)
    View ll_choose_coupon;
    @BindView(R.id.tv_del)
    TextView tv_del;
    @BindView(R.id.tv_confirm)
    TextView tv_confirm;

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
    @BindView(R.id.tv_tel)
    TextView tv_tel;

    @BindView(R.id.tv_rent_count)
    TextView tv_rent_count;
    @BindView(R.id.tv_deposit_count)
    TextView tv_deposit_count;
    @BindView(R.id.middle_count)
    TextView middle_count;
    @BindView(R.id.rent_money)
    TextView rent_money;
    @BindView(R.id.deposit_money)
    TextView deposit_money;
    @BindView(R.id.middle_money)
    TextView middle_money;

    @BindView(R.id.service_count)
    TextView service_count;

    @BindView(R.id.service_money)
    TextView service_money;



    @Override
    public int setLayout() {
        return R.layout.activity_order_detail;
    }

    @Override
    public void initEvent() {


    }

    @Override
    public void initData() {
        String order_id = getIntent().getStringExtra("order_id");
        RetrofitHelper.getApiWithUid().getOrderDetail(order_id).compose(RxScheduler.<OrderDetailResponse>defaultScheduler())
                .subscribe(new BaseObserver<OrderDetailResponse>(this) {
                    @Override
                    public void error(Throwable e) {

                    }

                    @SuppressLint("SetTextI18n")
                    @Override
                    public void next(OrderDetailResponse orderDetailResponse) {
                        OrderDetailResponse.OrderDetailDataBean.ListBean bean = orderDetailResponse.getData().getList();
                        Glide.with(mActivity).load(bean.getHouse_photo()).into(iv_house);
                        tv_house_name.setText(bean.getHouse_title());
                        tv_house_info.setText(bean.getHouse_area());
                        tv_house_location.setText(bean.getAddress());
                        tv_house_rent.setText(bean.getRent()+"元/月");

                        showThreeTag(bean,ll_tag_container);

                        rent_month.setText(bean.getRent_month()+"个月");
                        pay_type.setText(bean.getPay_type());
                        room_no.setText(bean.getRoom_num());
                        tv_tel.setText(bean.getPhone_number());

                        rent_money.setText("¥"+bean.getRent()+"");
                        tv_rent_count.setText(bean.getRent_pay()+"");
                        deposit_money.setText("¥"+bean.getPay()+"");
                        tv_deposit_count.setText(bean.getDeposit());
                        middle_count.setText(bean.getMiddle_count());
                        middle_money.setText("¥"+bean.getMiddle_money());
                        service_count.setText(bean.getService_count());
                        service_money.setText("¥"+bean.getService_money());


                    }

                    @Override
                    public void complete() {

                    }
                });
    }



    private void  showThreeTag(OrderDetailResponse.OrderDetailDataBean.ListBean bean, LinearLayout ll_tag_container) {
        for (int i = 0; i < 3&&bean.getLabel()!=null&&bean.getLabel().size()>i; i++) {
            TextView childAt = ((TextView) ll_tag_container.getChildAt(i));
            childAt.setVisibility(View.VISIBLE);
            childAt.setText(bean.getLabel().get(i));
        }
    }


    @OnClick({R.id.rl_zg_coupon,R.id.rl_company_coupon,R.id.tv_confirm,R.id.iv_left})
    public void onClick(View view){
        Intent intent;
        switch (view.getId()) {
            case R.id.rl_zg_coupon:
                intent= new Intent(this,CouponChooseActivity.class);
                intent.putExtra("title","选择扎根优惠券");
                startActivity(intent);
                break;
            case R.id.rl_company_coupon:
                intent = new Intent(this,CouponChooseActivity.class);
                intent.putExtra("title","选择经纪公司优惠券");
                startActivity(intent);
                break;
            case R.id.tv_confirm:
                startActivityForResult(OrderDetail2Activity.class,0);
                break;
            case R.id.iv_left:
                finish();
                break;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode==0&&resultCode==1){
            //取消支付
            rl_cancle_head0.setVisibility(View.GONE);
            rl_cancle_head1.setVisibility(View.GONE);
            tv_cancle_over.setVisibility(View.VISIBLE);
            ll_choose_coupon.setVisibility(View.GONE);
            tv_confirm.setVisibility(View.GONE);
            tv_del.setVisibility(View.VISIBLE);
        }
        super.onActivityResult(requestCode, resultCode, data);
    }

}
