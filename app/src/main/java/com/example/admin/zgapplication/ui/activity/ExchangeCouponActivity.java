package com.example.admin.zgapplication.ui.activity;

import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.admin.zgapplication.R;
import com.example.admin.zgapplication.base.BaseActivity;
import com.example.admin.zgapplication.mvp.module.BaseResponse;
import com.example.admin.zgapplication.retrofit.RetrofitHelper;
import com.example.admin.zgapplication.retrofit.rx.BaseObserver;
import com.example.admin.zgapplication.retrofit.rx.RxScheduler;

import butterknife.BindView;
import butterknife.OnClick;

public class ExchangeCouponActivity extends BaseActivity {


    @BindView(R.id.et_exchange_coupon)
    EditText et_exchange_coupon;



    @Override
    public int setLayout() {
        return R.layout.activity_exchange_coupon;
    }

    @Override
    public void initEvent() {

    }

    @Override
    public void initData() {

    }

    @OnClick({R.id.tv_exchange})
    public void onClick(View view){
        switch (view.getId()) {
            case R.id.tv_exchange:
                RetrofitHelper.getApiWithUid().exChangeCoupon("33",et_exchange_coupon.getText().toString())
                        .compose(RxScheduler.<BaseResponse>defaultScheduler())
                        .subscribe(new BaseObserver<BaseResponse>(mActivity) {
                            @Override
                            public void error(Throwable e) {

                            }

                            @Override
                            public void next(BaseResponse baseResponse) {
                                Toast.makeText(mActivity, baseResponse.getMsg(), Toast.LENGTH_SHORT).show();
                            }

                            @Override
                            public void complete() {

                            }
                        });
                break;
        }
    }
}
