package com.example.admin.zgapplication.ui.activity;

import android.Manifest;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.net.Uri;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.admin.zgapplication.R;
import com.example.admin.zgapplication.base.BaseActivity;
import com.tbruyelle.rxpermissions2.RxPermissions;
import com.zhihu.matisse.Matisse;
import com.zhihu.matisse.MimeType;
import com.zhihu.matisse.engine.impl.GlideEngine;
import com.zhy.view.flowlayout.FlowLayout;
import com.zhy.view.flowlayout.TagAdapter;
import com.zhy.view.flowlayout.TagFlowLayout;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import butterknife.BindView;
import butterknife.OnClick;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

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

    private ArrayList<String> pic;
    private TagAdapter<Uri> takePhotoTagAdapter;
    private List<Uri> uris=new ArrayList<>();
    private TextView tv_add_pic_count;

    @Override
    public int setLayout() {
        return R.layout.activity_make_evaluate;
    }

    @Override
    public void initEvent() {

        bar_title.setText("我要评价");

        ArrayList<String> strings = new ArrayList<>();
        for (int i = 0; i < 6; i++) {
            strings.add("第几条"+i);
        }

//        tfl_pic.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                // TODO: 2017/10/23 用于取消原本TagFlowLayout 绑定的事件
//                Log.d(TAG, "onClick: ");
//            }
//        });

        tfl_agent.setAdapter(new TagAdapter<String>(strings) {
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

        tfl_company.setAdapter(new TagAdapter<String>(strings) {
            @Override
            public View getView(FlowLayout parent, int position, String o) {
                TextView view = (TextView) LayoutInflater.from(MakeEvaluateActivity.this).inflate(R.layout.flow_item, tfl_company, false);
                view.setText(o);
                return view;
            }
        });

        tfl_house.setAdapter(new TagAdapter<String>(strings) {
            @Override
            public View getView(FlowLayout parent, int position, String o) {
                TextView view = (TextView) LayoutInflater.from(MakeEvaluateActivity.this).inflate(R.layout.flow_item, tfl_house, false);
                view.setText(o);
                return view;
            }
        });

        tfl_agent.setOnSelectListener(new TagFlowLayout.OnSelectListener() {
            @Override
            public void onSelected(Set<Integer> selectPosSet) {
                Log.d("6666666", "onSelected: "+selectPosSet.toString());
            }
        });

        tfl_company.setOnSelectListener(new TagFlowLayout.OnSelectListener() {
            @Override
            public void onSelected(Set<Integer> selectPosSet) {
                Log.d("6666666", "onSelected: "+selectPosSet.toString());
            }
        });

        tfl_house.setOnSelectListener(new TagFlowLayout.OnSelectListener() {
            @Override
            public void onSelected(Set<Integer> selectPosSet) {
                Log.d("6666666", "onSelected: "+selectPosSet.toString());
            }
        });


        uris.add(null);
        takePhotoTagAdapter = new TagAdapter<Uri>(uris) {
            @Override
            public View getView(FlowLayout parent, final int position, final Uri uri) {

                if (uri==null) {
                    View view = LayoutInflater.from(mActivity).inflate(R.layout.item_img_pick_choose_layout, parent, false);
                    tv_add_pic_count = ((TextView) view.findViewById(R.id.tv_add));
                    return view;
                }

                View view =  LayoutInflater.from(getmActivity()).inflate(R.layout.item_img_pic, parent, false);
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
                tv_add_pic_count.setText(uris.size()-1+"/9");
            }
        };
        tfl_pic.setOnTagClickListener(new TagFlowLayout.OnTagClickListener() {
            @Override
            public boolean onTagClick(View view, int position, FlowLayout parent) {
                if (position==uris.size()-1){
                    RxPermissions rxPermissions = new RxPermissions(getmActivity());
                    rxPermissions.request(Manifest.permission.WRITE_EXTERNAL_STORAGE,Manifest.permission.CAMERA)
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
    }

    @Override
    public void initData() {

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == 998 && resultCode == RESULT_OK) {
            List<Uri> c = Matisse.obtainResult(data);
            for (Uri uri : c) {
                uris.add(0,uri);
            }
            takePhotoTagAdapter.notifyDataChanged();
        }
        super.onActivityResult(requestCode, resultCode, data);
    }

    @OnClick({R.id.iv_left})
    public void onClick(View view){
        switch (view.getId()) {
            case R.id.iv_left:
                finish();
                break;

        }
    }
}
