package com.example.admin.zgapplication.ui.activity;

import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.TextView;

import com.example.admin.zgapplication.R;
import com.example.admin.zgapplication.base.BaseActivity;
import com.example.admin.zgapplication.ui.adapter.ZhyBaseRecycleAdapter.CommonAdapter;
import com.example.admin.zgapplication.ui.adapter.ZhyBaseRecycleAdapter.base.ViewHolder;

import java.util.ArrayList;

import butterknife.BindView;

public class EvaluateActivity extends BaseActivity {

    @BindView(R.id.tv_title)
    TextView tv_title;
    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;
    private CommonAdapter<String> adapter;

    @Override
    public int setLayout() {
        return R.layout.activity_evaluate;
    }

    @Override
    public void initEvent() {
        tv_title.setText("评价详情");
        ArrayList<String> list = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            list.add("");
        }

        adapter = new CommonAdapter<String>(this, R.layout.item_evaluate,list) {
            @Override
            protected void convert(ViewHolder holder, String o, int position) {

            }
        };

        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.addItemDecoration(new DividerItemDecoration(this,DividerItemDecoration.VERTICAL));
        recyclerView.setAdapter(adapter);

    }

    @Override
    public void initData() {

    }

}
