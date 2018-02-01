package com.example.admin.zgapplication.ui.activity;

import android.Manifest;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.net.Uri;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.admin.zgapplication.Constant;
import com.example.admin.zgapplication.R;
import com.example.admin.zgapplication.base.BaseActivity;
import com.example.admin.zgapplication.mvp.module.ModifyIconResponse;
import com.example.admin.zgapplication.mvp.module.SelfInfo;
import com.example.admin.zgapplication.retrofit.RetrofitHelper;
import com.example.admin.zgapplication.retrofit.rx.BaseObserver;
import com.example.admin.zgapplication.retrofit.rx.RxScheduler;
import com.example.admin.zgapplication.utils.SPUtil;
import com.example.admin.zgapplication.utils.ZgUtil;
import com.example.admin.zgapplication.utils.img.GlideRoundTransform;
import com.tbruyelle.rxpermissions2.RxPermissions;
import com.zhihu.matisse.Matisse;
import com.zhihu.matisse.MimeType;
import com.zhihu.matisse.engine.impl.GlideEngine;

import java.io.File;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;

public class SelfInfoActivity extends BaseActivity {


    @BindView(R.id.iv_user_icon)
    ImageView iv_user_icon;


    @BindView(R.id.tv_user_name)
    TextView tv_user_name;

    @BindView(R.id.tv_user_tel)
    TextView tv_user_tel;
    private Uri headUri;


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
                        Glide.with(mActivity).load(selfInfo.getData().getAvatar()).transform(new GlideRoundTransform(mActivity)).into(iv_user_icon);

                    }

                    @Override
                    public void complete() {

                    }
                });
    }

    @OnClick({R.id.tv_login_exit,R.id.ll_nick_name,R.id.iv_user_icon})
    public void onClick(View view){
        switch (view.getId()) {
            case R.id.tv_login_exit:
                startActivity(LoginActivity.class);
                break;
            case R.id.iv_left:
                finish();
                break;
            case R.id.ll_nick_name:
                startActivity(ModifyUserNameActivity.class);
                break;
            case R.id.iv_user_icon:
                RxPermissions rxPermissions = new RxPermissions(getmActivity());
                rxPermissions.request(Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.CAMERA)
                        .subscribe(new Observer<Boolean>() {
                            @Override
                            public void onSubscribe(Disposable d) {

                            }

                            @Override
                            public void onNext(Boolean aBoolean) {
                                if (aBoolean) {
                                    Matisse.from(mActivity)
                                            .choose(MimeType.allOf())
                                            .countable(true)
                                            .maxSelectable(1)
//                                          .addFilter(new GifSizeFilter(320, 320, 5 * Filter.K * Filter.K))
                                            .gridExpectedSize(getResources().getDimensionPixelSize(R.dimen.grid_expected_size))
                                            .restrictOrientation(ActivityInfo.SCREEN_ORIENTATION_UNSPECIFIED)
                                            .thumbnailScale(0.85f)
                                            .imageEngine(new GlideEngine())
                                            .forResult(998);

                                } else {
                                    Toast.makeText(mActivity, "权限未获取", Toast.LENGTH_LONG)
                                            .show();
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

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 998 && resultCode == RESULT_OK) {
            List<Uri> c = Matisse.obtainResult(data);
            for (Uri uri : c) {
                headUri = uri;
            }


            MultipartBody.Builder builder = new MultipartBody.Builder().setType(MultipartBody.FORM);
            builder.addFormDataPart("uid", Constant.uid);
            if (headUri != null) {
                File file = new File(ZgUtil.uriToUrl(headUri, mActivity));
                RequestBody requestBody = RequestBody.create(MediaType.parse("multipart/form-data"), file);
                MultipartBody.Part part = MultipartBody.Part.createFormData("file", file.getName(), requestBody);
                builder.addPart(part);
            }
            MultipartBody multipartBody = builder.build();
            RetrofitHelper.getApi().modifyHeadIcon(multipartBody)
                    .compose(RxScheduler.<ModifyIconResponse>defaultScheduler())
                    .subscribe(new Observer<ModifyIconResponse>() {
                        @Override
                        public void onSubscribe(Disposable d) {
                            showProgressDialog();
                        }

                        @Override
                        public void onNext(ModifyIconResponse addEvaluationResponse) {
                            if (addEvaluationResponse.getCode()==0){
                                Glide.with(mActivity).load(addEvaluationResponse.getData().getAvatar()).transform(new GlideRoundTransform(mActivity)).into(iv_user_icon);
                                Constant.
                                        avatar=addEvaluationResponse.getData().getAvatar();
                                SPUtil.put(mActivity,"avatar",Constant.avatar);
                            }

                            Toast.makeText(mActivity, addEvaluationResponse.getMsg(), Toast.LENGTH_SHORT).show();
                        }

                        @Override
                        public void onError(Throwable e) {

                        }

                        @Override
                        public void onComplete() {
                            dismissProgressDialog();
                        }
                    });
        }

    }
}
