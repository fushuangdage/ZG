package com.example.admin.zgapplication.ui.activity;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.TextView;

import com.example.admin.zgapplication.R;
import com.example.admin.zgapplication.base.BaseActivity;
import com.example.admin.zgapplication.ui.adapter.ZhyBaseRecycleAdapter.CommonAdapter;
import com.example.admin.zgapplication.ui.adapter.ZhyBaseRecycleAdapter.base.ViewHolder;

import java.util.ArrayList;

import butterknife.BindView;

public class IntentDetailActivity extends BaseActivity {


    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;
    @BindView(R.id.tv_title)
    TextView tv_title;

    @Override
    public int setLayout() {
        return R.layout.activity_intent_detail;
    }

    @Override
    public void initEvent() {
        ArrayList<String> strings = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            strings.add("");
        }

        tv_title.setText("意向记录详情");
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(new CommonAdapter<String>(this,R.layout.item_recommend_dami,strings) {
            @Override
            protected void convert(ViewHolder holder, String o, int position) {

            }

        });
    }

    @Override
    public void initData() {

    }
}
