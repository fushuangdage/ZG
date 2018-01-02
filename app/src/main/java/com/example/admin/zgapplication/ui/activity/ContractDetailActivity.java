package com.example.admin.zgapplication.ui.activity;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.admin.zgapplication.R;
import com.example.admin.zgapplication.base.BaseActivity;
import com.example.admin.zgapplication.mvp.module.ContactDetailResponse;
import com.example.admin.zgapplication.mvp.module.RentBillResponse;
import com.example.admin.zgapplication.retrofit.RetrofitHelper;
import com.example.admin.zgapplication.retrofit.rx.BaseObserver;
import com.example.admin.zgapplication.retrofit.rx.RxScheduler;

import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;
import io.reactivex.ObservableSource;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;

public class ContractDetailActivity extends BaseActivity {


    @BindView(R.id.tv_title)
    TextView tv_title;
    @BindView(R.id.tv_room_name)
    TextView tv_room_name;
    @BindView(R.id.tv_status)
    TextView tv_status;
    @BindView(R.id.tv_order_no)
    TextView tv_order_no;
    @BindView(R.id.tv_time)
    TextView tv_time;
    @BindView(R.id.tv_price)
    TextView tv_price;
    @BindView(R.id.iv_room)
    ImageView iv_room;
    @BindView(R.id.iv_agent_icon)
    ImageView iv_agent_icon;
    @BindView(R.id.tv_agent_name)
    TextView tv_agent_name;
    @BindView(R.id.tv_agent_company)
    TextView tv_agent_company;
    @BindView(R.id.rent_month)
    TextView rent_mouth;
    @BindView(R.id.pay_type)
    TextView pay_type;
    @BindView(R.id.room_no)
    TextView room_no;
    @BindView(R.id.tv_house_name)
    TextView tv_house_name;
    @BindView(R.id.tv_bill_time)
    TextView tv_bill_time;
    @BindView(R.id.tv_to_pay_count)
    TextView tv_to_pay_count;
    @BindView(R.id.tv_go_pay_rent)
    TextView tv_pay_rent;



    private ContactDetailResponse.DataBean o;
    private RentBillResponse.DataBean.ListBean first_rent_list_bean;


    @Override
    public int setLayout() {
        return R.layout.activity_contract_detail;
    }

    @Override
    public void initEvent() {
        tv_title.setText("合同详情");
    }

    @Override
    public void initData() {
        final String contract_id = getIntent().getStringExtra("contract_id");
        RetrofitHelper.getApiWithUid().getContactDetail(contract_id)
                .compose(RxScheduler.<ContactDetailResponse>defaultScheduler())
                .doOnNext(new Consumer<ContactDetailResponse>() {
                    @Override
                    public void accept(ContactDetailResponse contactDetailResponse) throws Exception {
                        o = contactDetailResponse.getData();
                        tv_room_name.setText(o.getTitle());
                        tv_status.setText(o.getStatus());
                        tv_order_no.setText(o.getOrder());
                        tv_time.setText(o.getDate());
                        tv_price.setText(String.format("%s元/月 %s", o.getRent(), o.getPay()));
                        Glide.with(mActivity).load(o.getImg()).into(iv_room);
                        Glide.with(mActivity).load(o.getImg()).into(iv_agent_icon);
                        rent_mouth.setText(o.getRent_month()+"个月");
                        pay_type.setText(o.getPay());
                        room_no.setText(o.getRoom());

                        tv_house_name.setText(o.getTitle());
                        tv_bill_time.setText(o.getCircle());
                        tv_to_pay_count.setText(String.format("您有%s个账单待支付",o.getPaying()));
                    }
                }).observeOn(Schedulers.io())
                .flatMap(new Function<ContactDetailResponse, ObservableSource<RentBillResponse>>() {
                    @Override
                    public ObservableSource<RentBillResponse> apply(ContactDetailResponse contactDetailResponse) throws Exception {
                        return RetrofitHelper.getApi().getRentBill(contract_id);
                    }
                })
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new BaseObserver<RentBillResponse>(mActivity) {
                    @Override
                    public void error(Throwable e) {

                    }

                    @Override
                    public void next(RentBillResponse rentBillResponse) {
                        List<RentBillResponse.DataBean.ListBean> list = rentBillResponse.getData().getList();
                        if (list==null&&list.size()==0) {
                            tv_pay_rent.setClickable(false);
                        }else {
                            tv_pay_rent.setClickable(true);
                            first_rent_list_bean = list.get(0);
                        }
//                        Toast.makeText(ContractDetailActivity.this, rentBillResponse.getMsg(), Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void complete() {

                    }
                });

    }

    @OnClick({R.id.tv_go_pay_rent, R.id.iv_left, R.id.tv_rent_bill_list, R.id.tv_life_bill_list, R.id.tv_call})
    public void onClick(View view) {
        Intent intent;
        Bundle bundle = new Bundle();
        switch (view.getId()) {
            case R.id.tv_go_pay_rent:
                intent = new Intent(mActivity,BillDetailActivity.class);
                intent.putExtra("bill_num",first_rent_list_bean.getBill_num());
                startActivity(intent);
                break;
            case R.id.iv_left:
                finish();
                break;
            case R.id.tv_rent_bill_list:
                bundle = new Bundle();
                bundle.putString("title", "房租账单");
                bundle.putString("order_num",o.getOrder());
                startActivity(RentBillListActivity.class, bundle);
                break;
            case R.id.tv_life_bill_list:
                bundle.putString("title", "生活账单");
                startActivity(RentBillListActivity.class, bundle);
                break;
            case R.id.tv_call:
               intent = new Intent(Intent.ACTION_CALL, Uri.parse("tel:"+o.getTel()));
                if (ActivityCompat.checkSelfPermission(this, Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
                    // TODO: Consider calling
                    //    ActivityCompat#requestPermissions
                    // here to request the missing permissions, and then overriding
                    //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
                    //                                          int[] grantResults)
                    // to handle the case where the user grants the permission. See the documentation
                    // for ActivityCompat#requestPermissions for more details.
                    return;
                }
                startActivity(intent);
                break;
        }
    }
}
