package com.example.admin.zgapplication.ui.activity;

import android.content.Intent;
import android.view.View;
import android.widget.TextView;

import com.example.admin.zgapplication.R;
import com.example.admin.zgapplication.base.BaseActivity;
import com.example.admin.zgapplication.mvp.module.AboutUsResponse;
import com.example.admin.zgapplication.retrofit.RetrofitHelper;
import com.example.admin.zgapplication.retrofit.rx.RxScheduler;

import butterknife.BindView;
import butterknife.OnClick;
import io.reactivex.functions.Consumer;

public class AboutUsActivity extends BaseActivity {


    @BindView(R.id.tv_official_website)
    TextView tv_official_website;

    @BindView(R.id.tv_wx)
    TextView wx;


    @BindView(R.id.tv_customer_service)
    TextView tv_customer_service;

    @BindView(R.id.tv_cooperation_hotline)
    TextView cooperation_hotline;

    @BindView(R.id.tv_title)
    TextView tv_title;
    private AboutUsResponse.DataBean data;


    @Override
    public int setLayout() {
        return R.layout.activity_about_us;
    }

    @Override
    public void initEvent() {
        tv_title.setText("关于我们");
    }

    @Override
    public void initData() {
        RetrofitHelper.getApi().getAboutUsResponse()
                .compose(RxScheduler.<AboutUsResponse>defaultScheduler())
                .subscribe(new Consumer<AboutUsResponse>() {
                    @Override
                    public void accept(AboutUsResponse aboutUsResponse) throws Exception {
                        data = aboutUsResponse.getData();
                        tv_official_website.setText(data.getOfficial_website());
                        wx.setText(data.getWeixin());
                        tv_customer_service.setText(data.getCustomer_service());
                        cooperation_hotline.setText(data.getCooperation_hotline());
                    }
                });
    }

    @OnClick({R.id.ll_company, R.id.iv_left})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.ll_company:
                Intent intent = new Intent(mActivity, CompanyinfoActivity.class);
                intent.putExtra("content",data.getIntroduction());
                startActivity(intent);
                break;
            case R.id.iv_left:

                break;
        }
    }

}
