package com.example.admin.zgapplication.ui.activity;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.admin.zgapplication.R;
import com.example.admin.zgapplication.base.BaseActivity;
import com.example.admin.zgapplication.mvp.module.SelfInfo;
import com.example.admin.zgapplication.retrofit.RetrofitHelper;
import com.example.admin.zgapplication.retrofit.rx.BaseObserver;
import com.example.admin.zgapplication.retrofit.rx.RxScheduler;

import butterknife.BindView;
import butterknife.OnClick;

public class SelfInfoActivity extends BaseActivity {


    @BindView(R.id.iv_user_icon)
    ImageView iv_user_icon;


    @BindView(R.id.tv_user_name)
    TextView tv_user_name;

    @BindView(R.id.tv_user_tel)
    TextView tv_user_tel;


    @Override
    public int setLayout() {
        return R.layout.activity_self_info;
    }

    @Override
    public void initEvent() {

    }

    @Override
    public void initData() {
        RetrofitHelper.getApiWithUid().getSelfInfo()
                .compose(RxScheduler.<SelfInfo>defaultScheduler())
                .subscribe(new BaseObserver<SelfInfo>(mActivity) {
                    @Override
                    public void error(Throwable e) {

                    }

                    @Override
                    public void next(SelfInfo selfInfo) {

                        SelfInfo.DataBean data = selfInfo.getData();
                        tv_user_tel.setText(data.getPhone_number());
                        tv_user_name.setText(data.getNick_name());

                    }

                    @Override
                    public void complete() {

                    }
                });
    }

    @OnClick({R.id.tv_login_exit,R.id.ll_nick_name})
    public void onClick(View view){
        switch (view.getId()) {
            case R.id.tv_login_exit:

                break;
            case R.id.iv_left:

                break;
            case R.id.ll_nick_name:
                startActivity(ModifyUserNameActivity.class);
                break;
        }
    }
}
