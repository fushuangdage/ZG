package com.example.admin.zgapplication.ui.fragment;


import android.content.Intent;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.admin.zgapplication.R;
import com.example.admin.zgapplication.base.BaseFragment;
import com.example.admin.zgapplication.ui.activity.SecondActivity;
import com.example.admin.zgapplication.utils.dialog.LoadingDialog;
import com.hyphenate.EMCallBack;
import com.hyphenate.chat.EMClient;
import com.hyphenate.exceptions.HyphenateException;
import com.umeng.socialize.ShareAction;
import com.umeng.socialize.bean.SHARE_MEDIA;
import com.umeng.socialize.media.UMImage;

import butterknife.BindView;

/**
 * A simple {@link Fragment} subclass.
 */
public class HomeFragment extends BaseFragment implements View.OnClickListener {

    @BindView(R.id.bt_register)
    Button bt_register;
    @BindView(R.id.bt_login)
    Button bt_login;
    @BindView(R.id.et_password)
    EditText et_password;
    @BindView(R.id.et_username)
    EditText et_username;
    @BindView(R.id.bt_logout)
    Button bt_logout;
    @BindView(R.id.bt_share)
    Button share;

    @Override
    protected int setLayout() {
        return R.layout.fragment_home;
    }

    @Override
    protected void init() {
        bt_login.setOnClickListener(this);
        bt_register.setOnClickListener(this);
        bt_logout.setOnClickListener(this);
        share.setOnClickListener(this);
    }


    @Override
    public void onClick(View v) {
        LoadingDialog.getInstance().showLoadingDialog(getActivity());
        switch (v.getId()) {

            case R.id.bt_register:

                    new Thread(new Runnable() {
                        @Override
                        public void run() {
                            try {
                                EMClient.getInstance().createAccount(et_username.getText().toString(),et_password.getText().toString());
                            } catch (HyphenateException e) {
                                e.printStackTrace();
                            }
                        }
                    }).start();

                break;
            case R.id.bt_login:
                EMClient.getInstance().login(et_username.getText().toString(), et_password.getText().toString(), new EMCallBack() {
                    @Override
                    public void onSuccess() {
                        Intent intent = new Intent(getActivity(), SecondActivity.class);
                        startActivity(intent);

                    }

                    @Override
                    public void onError(int i, String s) {
                        Log.d("888888888888888888888", "onError: "+s);
                    }

                    @Override
                    public void onProgress(int i, String s) {

                    }
                });
                break;

            case R.id.bt_logout:
                EMClient.getInstance().logout(true, new EMCallBack() {
                    @Override
                    public void onSuccess() {
                        Toast.makeText(mActivity, "退出成功", Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onError(int i, String s) {
                        Toast.makeText(mActivity, "退出失败"+s, Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onProgress(int i, String s) {

                    }
                });
                break;

            case R.id.bt_share:
                UMImage umImage = new UMImage(mActivity, "https://www.baidu.com/img/bd_logo1.png");

                new ShareAction(getActivity()).withText("nihao").withMedia(umImage).setPlatform(SHARE_MEDIA.QQ).share();
                break;

        }
    }


}
