package com.example.admin.zgapplication.ui.activity;

import android.Manifest;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.admin.zgapplication.Constant;
import com.example.admin.zgapplication.R;
import com.example.admin.zgapplication.base.BaseActivity;
import com.example.admin.zgapplication.mvp.module.AddEvaluationResponse;
import com.example.admin.zgapplication.mvp.module.EvaluationSuccessDetail;
import com.example.admin.zgapplication.mvp.module.MakeEvaluationDetailResponse;
import com.example.admin.zgapplication.retrofit.RetrofitHelper;
import com.example.admin.zgapplication.retrofit.rx.BaseObserver;
import com.example.admin.zgapplication.retrofit.rx.RxScheduler;
import com.example.admin.zgapplication.ui.view.StartBar;
import com.example.admin.zgapplication.utils.ZgUtil;
import com.tbruyelle.rxpermissions2.RxPermissions;
import com.zhihu.matisse.Matisse;
import com.zhihu.matisse.MimeType;
import com.zhihu.matisse.engine.impl.GlideEngine;
import com.zhy.view.flowlayout.FlowLayout;
import com.zhy.view.flowlayout.TagAdapter;
import com.zhy.view.flowlayout.TagFlowLayout;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import butterknife.BindView;
import butterknife.OnClick;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;

public class MakeEvaluateActivity extends BaseActivity {

    private static final String TAG = "6666666666";
    @BindView(R.id.tfl_agent)
    TagFlowLayout tfl_agent;

    @BindView(R.id.tfl_company)
    TagFlowLayout tfl_company;

    @BindView(R.id.tfl_house)
    TagFlowLayout tfl_house;

    @BindView(R.id.tv_title)
    TextView bar_title;

    @BindView(R.id.tfl_pic)
    TagFlowLayout tfl_pic;

    @BindView(R.id.ll_company_impression)
    LinearLayout ll_company_impression;

    @BindView(R.id.iv_icon)
    ImageView iv_icon;
    @BindView(R.id.tv_agent_name)
    TextView tv_agent_name;
    @BindView(R.id.tv_company)
    TextView tv_company;
    @BindView(R.id.tv_house_name)
    TextView tv_house_name;
    @BindView(R.id.tv_house_area)
    TextView tv_house_area;
    @BindView(R.id.iv_company_icon)
    ImageView iv_company_icon;
    @BindView(R.id.tv_company_name)
    TextView tv_company_name;

    @BindView(R.id.cb_anonymous)
    CheckBox choose_checkbox;

    @BindView(R.id.sb_agent)
    StartBar sb_agent;

    @BindView(R.id.sb_company)
    StartBar sb_company;

    @BindView(R.id.sb_house)
    StartBar sb_house;

    @BindView(R.id.tv_submit)
    TextView tv_submit;

    @BindView(R.id.et_company)
    EditText et_company;


    @BindView(R.id.et_agent_content)
    EditText et_agent_contnet;
    @BindView(R.id.et_house_content)
    EditText et_house_content;

    @BindView(R.id.ll_foot)
    View ll_foot;  //提交按钮


    private ArrayList<String> pic;
    private TagAdapter<Uri> takePhotoTagAdapter;
    private List<Uri> uris = new ArrayList<>();
    private TextView tv_add_pic_count;
    private String id;
    private String method;
    private MakeEvaluationDetailResponse.DataBean.ListBean bean;

    public String member_label = "";
    public String house_label = "";
    public String company_label = "";
    private boolean anonymous;
    private boolean evaluated;

    @Override
    public int setLayout() {
        return R.layout.activity_make_evaluate;
    }

    @Override
    public void initEvent() {

        bar_title.setText("我要评价");

        evaluated = getIntent().getBooleanExtra("evaluated", false);


        choose_checkbox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                anonymous = isChecked;
            }
        });


        if (!evaluated) {
            tfl_agent.setOnSelectListener(new TagFlowLayout.OnSelectListener() {
                @Override
                public void onSelected(Set<Integer> selectPosSet) {
                    StringBuilder stringBuilder = new StringBuilder();
                    for (Integer integer : selectPosSet) {
                        stringBuilder.append(bean.getMember_label().get(integer) + ",");
                    }
                    if (stringBuilder.length() > 1)
                        member_label = stringBuilder.substring(0, stringBuilder.length() - 1);
                }
            });

            tfl_house.setOnSelectListener(new TagFlowLayout.OnSelectListener() {
                @Override
                public void onSelected(Set<Integer> selectPosSet) {
                    StringBuilder stringBuilder = new StringBuilder();
                    for (Integer integer : selectPosSet) {
                        stringBuilder.append(bean.getHouse_label().get(integer) + ",");
                    }
                    if (stringBuilder.length() > 1)
                        house_label = stringBuilder.substring(0, stringBuilder.length() - 1);
                }
            });

            tfl_company.setOnSelectListener(new TagFlowLayout.OnSelectListener() {
                @Override
                public void onSelected(Set<Integer> selectPosSet) {
                    StringBuilder stringBuilder = new StringBuilder();
                    for (Integer integer : selectPosSet) {
                        stringBuilder.append(bean.getCompany_label().get(integer) + ",");
                    }
                    if (stringBuilder.length() > 1)
                        company_label = stringBuilder.substring(0, stringBuilder.length() - 1);
                }
            });


            uris.add(null);
            takePhotoTagAdapter = new TagAdapter<Uri>(uris) {
                @Override
                public View getView(FlowLayout parent, final int position, final Uri uri) {

                    if (uri == null) {
                        View view = LayoutInflater.from(mActivity).inflate(R.layout.item_img_pick_choose_layout, parent, false);
                        tv_add_pic_count = ((TextView) view.findViewById(R.id.tv_add));
                        return view;
                    }

                    View view = LayoutInflater.from(getmActivity()).inflate(R.layout.item_img_pic, parent, false);
                    View iv_del = view.findViewById(R.id.iv_del);
                    iv_del.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            uris.remove(position);
                            notifyDataChanged();
                        }
                    });
                    ImageView imageView = (ImageView) view.findViewById(R.id.item_pick_iv);
                    Glide.with(getmActivity()).load(uri).centerCrop().into(imageView);
                    return view;
                }

                @Override
                public void notifyDataChanged() {
                    super.notifyDataChanged();
                    tv_add_pic_count.setText(uris.size() - 1 + "/9");
                }
            };
            tfl_pic.setOnTagClickListener(new TagFlowLayout.OnTagClickListener() {
                @Override
                public boolean onTagClick(View view, int position, FlowLayout parent) {
                    if (position == uris.size() - 1) {
                        RxPermissions rxPermissions = new RxPermissions(getmActivity());
                        rxPermissions.request(Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.CAMERA)
                                .subscribe(new Observer<Boolean>() {
                                    @Override
                                    public void onSubscribe(Disposable d) {

                                    }

                                    @Override
                                    public void onNext(Boolean aBoolean) {
                                        if (aBoolean) {
                                            Matisse.from(MakeEvaluateActivity.this)
                                                    .choose(MimeType.allOf())
                                                    .countable(true)
                                                    .maxSelectable(9)
//                                          .addFilter(new GifSizeFilter(320, 320, 5 * Filter.K * Filter.K))
                                                    .gridExpectedSize(getResources().getDimensionPixelSize(R.dimen.grid_expected_size))
                                                    .restrictOrientation(ActivityInfo.SCREEN_ORIENTATION_UNSPECIFIED)
                                                    .thumbnailScale(0.85f)
                                                    .imageEngine(new GlideEngine())
                                                    .forResult(998);

                                        } else {
                                            Toast.makeText(MakeEvaluateActivity.this, "权限未获取", Toast.LENGTH_LONG)
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
                    }
                    return false;
                }
            });
            tfl_pic.setAdapter(takePhotoTagAdapter);

        }else {
            bar_title.setText("查看评价");
            sb_agent.setCanStart(false);
            sb_company.setCanStart(false);
            sb_house.setCanStart(false);
        }

    }

    @Override
    public void initData() {
        id = getIntent().getStringExtra("id");
        method = getIntent().getStringExtra("method");

        if (evaluated) {
            //已经提交,展示详情
            ll_foot.setVisibility(View.GONE);

            RetrofitHelper.getApiWithUid().getEvaluationDetail(method, id)
                    .compose(RxScheduler.<EvaluationSuccessDetail>defaultScheduler()).subscribe(new BaseObserver<EvaluationSuccessDetail>(mActivity) {
                @Override
                public void error(Throwable e) {

                }

                @Override
                public void next(EvaluationSuccessDetail evaluationSuccessDetail) {
                    EvaluationSuccessDetail.DataBean.ListBean bean = evaluationSuccessDetail.getData().getList();
                    Glide.with(mActivity).load(bean.getAvatar()).into(iv_icon);
                    Glide.with(mActivity).load(bean.getLogo()).into(iv_company_icon);
                    sb_agent.setRating(Float.valueOf(bean.getMember_score()));
                    sb_company.setRating(Float.valueOf(bean.getCompany_score()));
                    sb_house.setRating(Float.valueOf(bean.getHouse_score()));
                    tv_agent_name.setText(bean.getUsername());
                    tv_company.setText(bean.getCompany_name());
                    tv_house_area.setText(bean.getHouse_area());
                    tv_company_name.setText(bean.getCompany_name());

                    tfl_agent.setAdapter(new TagAdapter<String>(bean.getMember_label()) {
                        @Override
                        public View getView(FlowLayout parent, int position, String o) {
                            TextView view = ((TextView) LayoutInflater.from(MakeEvaluateActivity.this).inflate(R.layout.flow_item, tfl_agent, false));
                            view.setText(o);
                            return view;
                        }

                    });


                    tfl_house.setAdapter(new TagAdapter<String>(bean.getHouse_label()) {
                        @Override
                        public View getView(FlowLayout parent, int position, String o) {
                            TextView view = (TextView) LayoutInflater.from(MakeEvaluateActivity.this).inflate(R.layout.flow_item, tfl_house, false);
                            view.setText(o);
                            return view;
                        }
                    });


                    tfl_pic.setAdapter(new TagAdapter<String>(bean.getAlbum()) {
                        @Override
                        public View getView(FlowLayout parent, int position, String o) {
                            View view = LayoutInflater.from(getmActivity()).inflate(R.layout.item_img_pic, parent, false);
                            View iv_del = view.findViewById(R.id.iv_del);
                            iv_del.setVisibility(View.GONE);
                            ImageView imageView = (ImageView) view.findViewById(R.id.item_pick_iv);
                            Glide.with(getmActivity()).load(o).centerCrop().into(imageView);
                            return view;
                        }
                    });


                    if (method.equals(1)) {

                        ll_company_impression.setVisibility(View.VISIBLE);

                        tfl_company.setAdapter(new TagAdapter<String>(bean.getCompany_label()) {
                            @Override
                            public View getView(FlowLayout parent, int position, String o) {
                                TextView view = (TextView) LayoutInflater.from(MakeEvaluateActivity.this).inflate(R.layout.flow_item, tfl_company, false);
                                view.setText(o);
                                return view;
                            }
                        });

                    }


                    et_agent_contnet.setText(bean.getMember_conent());
                    et_agent_contnet.setFocusable(false);

                    et_house_content.setText(bean.getHouse_conent());
                    et_house_content.setFocusable(false);

                    et_company.setText(bean.getCompany_conent());
                    et_company.setFocusable(false);

                }

                @Override
                public void complete() {

                }
            });
        } else {

            ll_foot.setVisibility(View.VISIBLE);
            RetrofitHelper.getApiWithUid().getEvaluationInfo(method, id)
                    .compose(RxScheduler.<MakeEvaluationDetailResponse>defaultScheduler())
                    .subscribe(new Observer<MakeEvaluationDetailResponse>() {
                        @Override
                        public void onSubscribe(Disposable d) {

                        }

                        @Override
                        public void onNext(MakeEvaluationDetailResponse makeEvaluationDetailResponse) {
                            bean = makeEvaluationDetailResponse.getData().getList();
                            Glide.with(mActivity).load(bean.getAvatar()).into(iv_icon);
                            Glide.with(mActivity).load(bean.getLogo()).into(iv_company_icon);
                            tv_agent_name.setText(bean.getUsername());
                            tv_company.setText(bean.getCompany_name());
                            tv_house_area.setText(bean.getHouse_area());
                            tv_company_name.setText(bean.getCompany_name());

                            tfl_agent.setAdapter(new TagAdapter<String>(bean.getMember_label()) {
                                @Override
                                public View getView(FlowLayout parent, int position, String o) {
                                    TextView view = ((TextView) LayoutInflater.from(MakeEvaluateActivity.this).inflate(R.layout.flow_item, tfl_agent, false));
                                    view.setText(o);
                                    return view;
                                }

                                @Override
                                public void onSelected(int position, View view) {
                                    super.onSelected(position, view);
                                }
                            });

                            tfl_company.setAdapter(new TagAdapter<String>(bean.getCompany_label()) {
                                @Override
                                public View getView(FlowLayout parent, int position, String o) {
                                    TextView view = (TextView) LayoutInflater.from(MakeEvaluateActivity.this).inflate(R.layout.flow_item, tfl_company, false);
                                    view.setText(o);
                                    return view;
                                }
                            });

                            tfl_house.setAdapter(new TagAdapter<String>(bean.getHouse_label()) {
                                @Override
                                public View getView(FlowLayout parent, int position, String o) {
                                    TextView view = (TextView) LayoutInflater.from(MakeEvaluateActivity.this).inflate(R.layout.flow_item, tfl_house, false);
                                    view.setText(o);
                                    return view;
                                }
                            });
                        }

                        @Override
                        public void onError(Throwable e) {

                        }

                        @Override
                        public void onComplete() {

                        }
                    });
        }
        if (method.equals("2")) {
            //带看没有公司评价信息
            ll_company_impression.setVisibility(View.GONE);
        }

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == 998 && resultCode == RESULT_OK) {
            List<Uri> c = Matisse.obtainResult(data);
            for (Uri uri : c) {
                uris.add(0, uri);
            }
            takePhotoTagAdapter.notifyDataChanged();
        }
        super.onActivityResult(requestCode, resultCode, data);
    }

    @OnClick({R.id.iv_left, R.id.tv_submit})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.iv_left:
                finish();
                break;
            case R.id.tv_submit:

                MultipartBody.Builder builder = new MultipartBody.Builder().setType(MultipartBody.FORM);
                builder.addFormDataPart("uid", Constant.uid);
                builder.addFormDataPart("single_id", id);
                builder.addFormDataPart("method", method);
                builder.addFormDataPart("member_label", member_label);
                builder.addFormDataPart("member_score", sb_agent.getRating() + "");
                builder.addFormDataPart("member_content", et_agent_contnet.getText().toString());
                builder.addFormDataPart("house_label", house_label);
                builder.addFormDataPart("house_score", sb_house.getRating() + "");
                builder.addFormDataPart("house_content", et_house_content.getText().toString());

                for (int i = 0; i < uris.size(); i++) {
                    Uri uri = uris.get(i);
                    if (uri != null) {
                        File file = new File(ZgUtil.uriToUrl(uri, mActivity));
                        RequestBody requestBody = RequestBody.create(MediaType.parse("multipart/form-data"), file);
                        MultipartBody.Part part = MultipartBody.Part.createFormData("album" + i, file.getName(), requestBody);
                        builder.addPart(part);
                    }
                }

                if (method.equals("1")) {
                    builder.addFormDataPart("company_label", company_label);
                    builder.addFormDataPart("company_score", sb_company.getRating() + "");
                    builder.addFormDataPart("company_content", et_company.getText().toString());
                }

                builder.addFormDataPart("hide", anonymous ? "1" : "0");

                RetrofitHelper.getApi().makeEvaluation(builder.build())
                        .compose(RxScheduler.<AddEvaluationResponse>defaultScheduler())
                        .subscribe(new Observer<AddEvaluationResponse>() {

                            @Override
                            public void onSubscribe(Disposable d) {
                                showProgressDialog();
                            }

                            @Override
                            public void onNext(AddEvaluationResponse baseResponse) {
                                if (baseResponse.getCode() == 0) {
                                    Toast.makeText(mActivity, baseResponse.getMsg(), Toast.LENGTH_SHORT).show();
                                    Intent intent = new Intent(mActivity, MakeEvaluateActivity.class);
                                    intent.putExtra("id", baseResponse.getData().getSingle_id());
                                    intent.putExtra("method", baseResponse.getData().getMethod());
                                    intent.putExtra("evaluated", true);
                                    startActivity(intent);
                                }
                            }

                            @Override
                            public void onError(Throwable e) {

                            }

                            @Override
                            public void onComplete() {
                                dismissProgressDialog();
                            }
                        });
                break;

        }
    }
}
