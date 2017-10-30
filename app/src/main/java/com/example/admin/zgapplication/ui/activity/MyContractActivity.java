package com.example.admin.zgapplication.ui.activity;

import android.support.v7.widget.DividerItemDecoration;
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

public class MyContractActivity extends BaseActivity implements MultiItemTypeAdapter.OnItemClickListener {


    @BindView(R.id.tv_title)
    TextView tv_title;
    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;
    private CommonAdapter<String> adapter;

    @Override
    public int setLayout() {
        return R.layout.activity_my_contract;
    }

    @Override
    public void initEvent() {
        ArrayList<String> strings = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            strings.add("");
        }
        adapter = new CommonAdapter<String>(this, R.layout.item_my_contract_list, strings) {
            @Override
            protected void convert(ViewHolder holder, String o, int position) {

            }
        };
        recyclerView.addItemDecoration(new DividerItemDecoration(this,DividerItemDecoration.VERTICAL));
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);
        adapter.setOnItemClickListener(this);
        tv_title.setText("我的合同");
    }

    @Override
    public void initData() {

    }

    @Override
    public void onItemClick(View view, RecyclerView.ViewHolder holder, int position) {
        startActivity(ContractDetailActivity.class);
    }

    @Override
    public boolean onItemLongClick(View view, RecyclerView.ViewHolder holder, int position) {
        return false;
    }
}
