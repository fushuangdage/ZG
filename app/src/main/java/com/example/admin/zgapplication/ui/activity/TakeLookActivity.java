package com.example.admin.zgapplication.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bigkoo.pickerview.TimePickerView;
import com.bumptech.glide.Glide;
import com.example.admin.zgapplication.Constant;
import com.example.admin.zgapplication.R;
import com.example.admin.zgapplication.base.BaseActivity;
import com.example.admin.zgapplication.mvp.module.LaunchTakeLookResponse;
import com.example.admin.zgapplication.mvp.module.TakeLookInfoResponse;
import com.example.admin.zgapplication.retrofit.RetrofitHelper;
import com.example.admin.zgapplication.retrofit.rx.BaseObserver;
import com.example.admin.zgapplication.retrofit.rx.RxScheduler;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import butterknife.BindView;
import butterknife.OnClick;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

public class TakeLookActivity extends BaseActivity {


    @BindView(R.id.iv_house)
    ImageView iv_house;
    @BindView(R.id.tv_house_name)
    TextView tv_house_name;
    @BindView(R.id.tv_house_location)
    TextView tv_house_location;
    @BindView(R.id.tv_house_info)
    TextView tv_house_info;
    @BindView(R.id.tv_house_rent)
    TextView tv_house_rent;
    @BindView(R.id.ll_tag_container)
    LinearLayout ll_tag_container;
    @BindView(R.id.iv_agent_icon)
    ImageView iv_agent_icon;


    @BindView(R.id.tv_tel)
    TextView tv_tel;

    @BindView(R.id.et_username)
    EditText et_username;
    private String house_id;
    private String room_id;
    private String type;
    private String member_id;
    private String time;


    @Override
    public int setLayout() {
        return R.layout.activity_take_look;
    }

    @Override
    public void initEvent() {

    }

    @Override
    public void initData() {
        Bundle extras = getIntent().getExtras();
        house_id = extras.getString("house_id");
        room_id = extras.getString("room_id");
        type = extras.getString("type");
        member_id = extras.getString("member_id");

        RetrofitHelper.getApi().getTakeLookInfo(room_id, type, house_id, member_id, Constant.uid)
                .compose(RxScheduler.<TakeLookInfoResponse>defaultScheduler())
                .subscribe(new BaseObserver<TakeLookInfoResponse>(mActivity) {
                    @Override
                    public void error(Throwable e) {

                    }

                    @Override
                    public void next(TakeLookInfoResponse takeLookInfoResponse) {
                        TakeLookInfoResponse.DataBean.ListBean bean = takeLookInfoResponse.getData().getList();
                        Glide.with(mActivity).load(bean.getHouse_photo()).into(iv_house);
                        tv_house_name.setText(bean.getHouse_title());
                        tv_house_location.setText(bean.getHouse_address());
                        tv_house_info.setText(bean.getHouse_info());
                        tv_house_rent.setText(bean.getHouse_rental()+"元/月");
                        showThreeTag(bean, ll_tag_container);

                        tv_tel.setText(bean.getPhone_number());
                    }

                    @Override
                    public void complete() {

                    }
                });



    }

    private void showThreeTag(TakeLookInfoResponse.DataBean.ListBean bean, LinearLayout ll_tag_container) {

        for (int i = 0; i < 3&&bean.getHouse_label()!=null&&bean.getHouse_label().size()>i; i++) {
            TextView childAt = ((TextView) ll_tag_container.getChildAt(i));
            childAt.setVisibility(View.VISIBLE);
            childAt.setText(bean.getHouse_label().get(i));
        }
    }

    @OnClick({R.id.tv_submit_look,R.id.tv_pick_time})
    public void onClick(final View view){
        switch (view.getId()) {
            case R.id.tv_submit_look:
                RetrofitHelper.getApi().lanchTakeLookResponse(room_id,type,house_id,member_id,Constant.uid,et_username.getText().toString(),time)
                        .compose(RxScheduler.<LaunchTakeLookResponse>defaultScheduler())
                        .subscribe(new Observer<LaunchTakeLookResponse>() {
                            @Override
                            public void onSubscribe(Disposable d) {

                            }

                            @Override
                            public void onNext(LaunchTakeLookResponse launchTakeLookResponse) {
                                if (launchTakeLookResponse.getCode()==0) {
                                    Intent intent = new Intent(mActivity, TakeLookProgressActivity.class);
                                    intent.putExtra("id",launchTakeLookResponse.getData().getId());
                                    startActivity(intent);
                                }else {
                                    Toast.makeText(mActivity, launchTakeLookResponse.getMsg(), Toast.LENGTH_SHORT).show();
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
            case R.id.tv_pick_time:
                TimePickerView pvTime = new TimePickerView.Builder(this, new TimePickerView.OnTimeSelectListener() {
                    @Override
                    public void onTimeSelect(Date date, View v) {//选中事件回调
                        time = getTime(date);
                        ((TextView) view).setText(TakeLookActivity.this.time);
                    }
                }).build();

                pvTime.setDate(Calendar.getInstance());
                //注：根据需求来决定是否使用该方法（一般是精确到秒的情况），此项可以在弹出选择器的时候重新设置当前时间，避免在初始化之后由于时间已经设定，导致选中时间与当前时间不匹配的问题。
                pvTime.show();
                break;
        }
    }

    private String getTime(Date date) {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        return format.format(date);

    }
}
