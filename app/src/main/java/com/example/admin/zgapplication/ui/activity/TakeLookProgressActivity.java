package com.example.admin.zgapplication.ui.activity;

import android.content.Intent;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.admin.zgapplication.Constant;
import com.example.admin.zgapplication.R;
import com.example.admin.zgapplication.base.BaseActivity;
import com.example.admin.zgapplication.mvp.module.BaseResponse;
import com.example.admin.zgapplication.mvp.module.TakeLookDetail;
import com.example.admin.zgapplication.retrofit.RetrofitHelper;
import com.example.admin.zgapplication.retrofit.rx.BaseObserver;
import com.example.admin.zgapplication.retrofit.rx.RxScheduler;
import com.example.admin.zgapplication.utils.date.TimeUtil;

import butterknife.BindView;
import butterknife.OnClick;

public class TakeLookProgressActivity extends BaseActivity {

    @BindView(R.id.tv_house_name)
    TextView tv_house_name;
    @BindView(R.id.tv_house_location)
    TextView tv_house_location;
    @BindView(R.id.tv_house_info)
    TextView tv_house_info;
    @BindView(R.id.tv_house_rent)
    TextView tv_house_rent;
    @BindView(R.id.iv_house)
    ImageView iv_house;
    @BindView(R.id.ll_tag_container)
    LinearLayout ll_tag_container;
    @BindView(R.id.tv_status)
    TextView tv_status;
    @BindView(R.id.tv_custom_name)
    TextView tv_custom_name;
    @BindView(R.id.tv_tel)
    TextView tv_tel;
    @BindView(R.id.tv_expect_time)
    TextView tv_expect_time;
    @BindView(R.id.iv_agent_icon)
    ImageView iv_agent_icon;

    @BindView(R.id.tv_agent_name)
    TextView tv_agent_name;

    @BindView(R.id.tv_agent_company)
    TextView tv_agent_company;
    @BindView(R.id.rl_bottom)
    RelativeLayout rl_bottom;
    @BindView(R.id.tv_cancel)
    TextView tv_cancel;
    @BindView(R.id.tv_confirm)
    TextView tv_confirm;


    private String intentID;
    private TakeLookDetail.DataBean.ListBean bean;


    @Override
    public int setLayout() {
        return R.layout.activity_take_look_progress;
    }

    @Override
    public void initEvent() {

    }

    @Override
    public void initData() {
        intentID = getIntent().getStringExtra("id");
        RetrofitHelper.getApiWithUid().getTakelookDetail(intentID)
                .compose(RxScheduler.<TakeLookDetail>defaultScheduler())
                .subscribe(new BaseObserver<TakeLookDetail>(mActivity) {
                    @Override
                    public void error(Throwable e) {

                    }

                    @Override
                    public void next(TakeLookDetail takeLookDetail) {
                        bean = takeLookDetail.getData().getList();
                        switch (bean.getStatus()) {
                            case "1":
                                tv_status.setText("用户待确认");
                                rl_bottom.setVisibility(View.VISIBLE);
                                break;
                            case "2":
                                tv_status.setText("经纪人待确认");
                                break;
                            case "3":
                                tv_status.setText("预约成功");
                                break;
                            case "4":
                                tv_status.setText("带看完成");
                                rl_bottom.setVisibility(View.VISIBLE);
                                tv_confirm.setText("去评价");
                                tv_cancel.setVisibility(View.GONE);
                                break;
                            case "5":
                                tv_status.setText("已取消");
                                rl_bottom.setVisibility(View.VISIBLE);
                                tv_cancel.setText("删除");
                                tv_confirm.setText(View.GONE);
                                break;
                            default:
                                tv_status.setText("未指定");

                                break;
                        }


                        Glide.with(mActivity).load(bean.getHouse_photo()).into(iv_house);
                        tv_house_name.setText(bean.getHouse_title());
                        tv_house_location.setText(bean.getHouse_address());
                        tv_house_info.setText(bean.getHouse_info());
                        tv_house_rent.setText(bean.getHouse_rental() + "元/月");
                        tv_expect_time.setText(TimeUtil.formatData(TimeUtil.dateFormat, bean.getExpect_time()));
                        tv_custom_name.setText(bean.getName());
                        tv_tel.setText(bean.getPhone_number());


                        Glide.with(mActivity).load(bean.getAvatar()).into(iv_agent_icon);
                        tv_agent_name.setText(bean.getUsername());
                        tv_agent_company.setText(bean.getCompany_name());
                        showThreeTag(bean, ll_tag_container);
                    }

                    @Override
                    public void complete() {

                    }
                });
    }

    private void showThreeTag(TakeLookDetail.DataBean.ListBean bean, LinearLayout ll_tag_container) {
        for (int i = 0; i < 3 && bean.getHouse_label() != null && bean.getHouse_label().size() > i; i++) {
            TextView childAt = ((TextView) ll_tag_container.getChildAt(i));
            childAt.setVisibility(View.VISIBLE);
            childAt.setText(bean.getHouse_label().get(i));
        }
    }

    @OnClick({R.id.tv_confirm, R.id.tv_cancel})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.tv_confirm:
                if(bean.getStatus().equals("4")){
                    Intent evaluate = new Intent(mActivity, MakeEvaluateActivity.class);
                    evaluate.putExtra("id",intentID);
                    evaluate.putExtra("method","2");
                    evaluate.putExtra("evaluated",false);
                    startActivity(evaluate);
                }else{
                    RetrofitHelper.getApi().confirmTakeLook(Constant.uid, intentID)
                            .compose(RxScheduler.<BaseResponse>defaultScheduler())
                            .subscribe(new BaseObserver<BaseResponse>(mActivity) {
                                @Override
                                public void error(Throwable e) {

                                }

                                @Override
                                public void next(BaseResponse baseResponse) {
                                    Toast.makeText(mActivity, baseResponse.getMsg(), Toast.LENGTH_SHORT).show();
                                    finish();
                                }

                                @Override
                                public void complete() {

                                }
                            });
                }
                break;
            case R.id.tv_cancel:
                if (bean.getStatus().equals("5")) {
                    RetrofitHelper.getApi().deleteTakeLookResponse(Constant.uid, intentID)
                            .compose(RxScheduler.<BaseResponse>defaultScheduler())
                            .subscribe(new BaseObserver<BaseResponse>(mActivity) {
                                @Override
                                public void error(Throwable e) {

                                }

                                @Override
                                public void next(BaseResponse baseResponse) {
                                    finish();
                                }

                                @Override
                                public void complete() {

                                }
                            });
                } else {
                    RetrofitHelper.getApi().cancelTakeLook(Constant.uid, intentID, null)
                            .compose(RxScheduler.<BaseResponse>defaultScheduler())
                            .subscribe(new BaseObserver<BaseResponse>(mActivity) {
                                @Override
                                public void error(Throwable e) {

                                }

                                @Override
                                public void next(BaseResponse baseResponse) {
                                    Toast.makeText(mActivity, baseResponse.getMsg(), Toast.LENGTH_SHORT).show();
                                    finish();
                                }

                                @Override
                                public void complete() {

                                }
                            });
                }
                break;
        }
    }
}
