package com.example.admin.zgapplication.ui.activity;

import android.content.Intent;
import android.view.View;
import android.widget.TextView;

import com.example.admin.zgapplication.R;
import com.example.admin.zgapplication.base.BaseActivity;

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


    @Override
    public int setLayout() {
        return R.layout.activity_order_detail;
    }

    @Override
    public void initEvent() {


    }

    @Override
    public void initData() {

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
