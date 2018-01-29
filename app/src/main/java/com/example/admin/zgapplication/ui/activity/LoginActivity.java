package com.example.admin.zgapplication.ui.activity;

import android.app.Dialog;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.admin.zgapplication.Constant;
import com.example.admin.zgapplication.R;
import com.example.admin.zgapplication.base.BaseActivity;
import com.example.admin.zgapplication.mvp.module.LoginResponse;
import com.example.admin.zgapplication.mvp.module.MsgCodeResponse;
import com.example.admin.zgapplication.retrofit.RetrofitHelper;
import com.example.admin.zgapplication.retrofit.rx.RxScheduler;
import com.example.admin.zgapplication.utils.NumCode;
import com.example.admin.zgapplication.utils.SPUtil;
import com.example.admin.zgapplication.utils.string.StringUtils;

import butterknife.BindView;
import butterknife.OnClick;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

public class LoginActivity extends BaseActivity implements View.OnClickListener {


    private View inflate;
    private Dialog dialog;
    private EditText editText;
    private ImageView ivNumCode;
    private String numCode;

    @BindView(R.id.act_login_phone)
    EditText act_login_phone;

    @BindView(R.id.et_message_code)
    EditText et_message_code;
    private String net_code;  //服务器给的短信验证码
    private String user_phoneNumber;

    @Override
    public int setLayout() {
        return R.layout.activity_login_regist;
    }

    @Override
    public void initEvent() {
        inflate = LayoutInflater.from(mActivity).inflate(R.layout.dialog_register_getcede, null, false);

        inflate.findViewById(R.id.btn_clear_pic).setOnClickListener(this);
        inflate.findViewById(R.id.iv_shutdown).setOnClickListener(this);

        inflate.findViewById(R.id.tv_post).setOnClickListener(this);
        ivNumCode = (ImageView) inflate.findViewById(R.id.iv_num_code);
        ivNumCode.setOnClickListener(this);
        editText = (EditText) inflate.findViewById(R.id.et_num_code);
        ivNumCode.setImageBitmap(NumCode.getInstance().getBitmap());
        numCode = NumCode.getInstance().getCode();
    }

    @Override
    public void initData() {
    }

    @OnClick({R.id.tv_reget_message_code, R.id.btn_clear_phone, R.id.act_login_submit})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.tv_reget_message_code:
                user_phoneNumber = act_login_phone.getText().toString();
                if (StringUtils.isMobile(user_phoneNumber)) {
                    dialog = new Dialog(mActivity);
                    dialog.setContentView(inflate);
                    dialog.show();
                } else {
                    Toast.makeText(mActivity, "手机号格式错误", Toast.LENGTH_SHORT).show();
                }
                break;

            case R.id.btn_clear_pic:
                editText.setText("");
                break;
            case R.id.iv_shutdown:
                dialog.hide();
                break;
            case R.id.iv_num_code:
                ivNumCode.setImageBitmap(NumCode.getInstance().getBitmap());
                numCode = NumCode.getInstance().getCode();
                break;
            case R.id.tv_post:
                String trim = editText.getText().toString().trim();
                if (trim.equals(numCode)) {
                    RetrofitHelper.getApi().getMsgCodeResponse(user_phoneNumber)
                            .compose(RxScheduler.<MsgCodeResponse>defaultScheduler())
                            .subscribe(new Observer<MsgCodeResponse>() {
                                @Override
                                public void onSubscribe(Disposable d) {

                                }

                                @Override
                                public void onNext(MsgCodeResponse lifeRentBillResponse) {
                                    if (lifeRentBillResponse.getCode() == 0) {
                                        net_code = lifeRentBillResponse.getData().getVerificationCode() + "";
                                    } else {
                                        Toast.makeText(mActivity, lifeRentBillResponse.getMsg(), Toast.LENGTH_SHORT).show();
                                    }
                                }

                                @Override
                                public void onError(Throwable e) {

                                }

                                @Override
                                public void onComplete() {
                                    dialog.hide();
                                }
                            });
                } else {
                    Toast.makeText(mActivity, "验证码错误", Toast.LENGTH_SHORT).show();
                }
                break;
            case R.id.btn_clear_phone:
                act_login_phone.setText("");
                break;

            case R.id.act_login_submit:

                RetrofitHelper.getApi().login(user_phoneNumber, net_code)
                        .compose(RxScheduler.<LoginResponse>defaultScheduler())
                        .subscribe(new Observer<LoginResponse>() {
                            @Override
                            public void onSubscribe(Disposable d) {

                            }

                            @Override
                            public void onNext(LoginResponse msgCodeResponse) {
                                if (msgCodeResponse.getCode() == 0) {
                                    Constant.uid = msgCodeResponse.getData().getId() + "";
                                    Constant.hx_username = msgCodeResponse.getData().getHx_username();
                                    Constant.hx_password = msgCodeResponse.getData().getHx_password();
                                    Constant.username = msgCodeResponse.getData().getUsername();
                                    Constant.password = msgCodeResponse.getData().getPassword();
                                    Constant.avatar=msgCodeResponse.getData().getAvatar();

                                    SPUtil.put(mActivity,"uid",Constant.uid);
                                    SPUtil.put(mActivity,"hx_username",Constant.hx_username);
                                    SPUtil.put(mActivity,"hx_password",Constant.hx_password);
                                    SPUtil.put(mActivity,"username",Constant.username);
                                    SPUtil.put(mActivity,"password",Constant.password);
                                    SPUtil.put(mActivity,"avatar",Constant.avatar);

                                    Intent intent = new Intent(mActivity, HomeActivity.class);
                                    intent.putExtra("loginBean",msgCodeResponse.getData());
                                    startActivity(intent);
                                } else {
                                    Toast.makeText(mActivity, msgCodeResponse.getMsg(), Toast.LENGTH_SHORT).show();
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
        }
    }
}
