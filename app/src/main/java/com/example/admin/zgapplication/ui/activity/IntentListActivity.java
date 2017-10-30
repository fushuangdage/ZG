package com.example.admin.zgapplication.ui.activity;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.example.admin.zgapplication.R;
import com.example.admin.zgapplication.base.BaseActivity;
import com.example.admin.zgapplication.ui.adapter.ZhyBaseRecycleAdapter.CommonAdapter;
import com.example.admin.zgapplication.ui.adapter.ZhyBaseRecycleAdapter.MultiItemTypeAdapter;
import com.example.admin.zgapplication.ui.adapter.ZhyBaseRecycleAdapter.base.ViewHolder;

import java.util.ArrayList;

import butterknife.BindView;

public class IntentListActivity extends BaseActivity implements MultiItemTypeAdapter.OnItemClickListener {


    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;
    @BindView(R.id.tv_title)
    TextView tv_title;
    private CommonAdapter<String> adapter;

    @Override
    public int setLayout() {
        return R.layout.activity_intent_list;
    }

    @Override
    public void initEvent() {
        tv_title.setText("意向记录");

        ArrayList<String> strings = new ArrayList<>();
        for (int i = 0; i <10 ; i++) {
            strings.add("");
        }

        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter = new CommonAdapter<String>(this, R.layout.item_intent_list, strings) {
            @Override
            protected void convert(ViewHolder holder, String o, int position) {

            }

        };
        adapter.setOnItemClickListener(this);
        recyclerView.setAdapter(adapter);


    }

    @Override
    public void initData() {

    }

    @Override
    public void onItemClick(View view, RecyclerView.ViewHolder holder, int position) {
        startActivity(IntentDetailActivity.class);
    }

    @Override
    public boolean onItemLongClick(View view, RecyclerView.ViewHolder holder, int position) {
        return false;
    }
}
