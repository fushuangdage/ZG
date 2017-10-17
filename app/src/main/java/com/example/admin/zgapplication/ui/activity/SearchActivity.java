package com.example.admin.zgapplication.ui.activity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.view.View;

import com.example.admin.zgapplication.R;
import com.example.admin.zgapplication.base.BaseActivity;
import com.zhy.view.flowlayout.TagFlowLayout;

import butterknife.BindView;
import butterknife.OnClick;

public class SearchActivity extends BaseActivity {


    @BindView(R.id.fl_history)
    TagFlowLayout fl_history;

    @BindView(R.id.fl_hot_search)
    TagFlowLayout fl_hot_search;

    @OnClick({R.id.iv_del})
    public void onClick(View view){
        switch (view.getId()) {
            case  R.id.iv_del:
                AlertDialog.Builder builder = new AlertDialog.Builder(this);
                builder.setTitle("确认删除全部搜索历史").setNegativeButton("取消", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                }).setPositiveButton("确认", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                }).show();
                break;
        }
    }


    @Override
    public int setLayout() {
        return R.layout.activity_search;
    }

    @Override
    public void initEvent() {

    }

    @Override
    public void initData() {

    }
}
