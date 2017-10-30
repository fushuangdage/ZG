package com.example.admin.zgapplication.ui.activity;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.example.admin.zgapplication.R;
import com.example.admin.zgapplication.base.BaseActivity;
import com.example.admin.zgapplication.ui.adapter.ZhyBaseRecycleAdapter.AgentHouseAdapter;
import com.example.admin.zgapplication.ui.adapter.ZhyBaseRecycleAdapter.wrapper.HeaderAndFooterWrapper;

import java.util.ArrayList;

import butterknife.BindView;


public class AgentActivity extends BaseActivity implements View.OnClickListener {

    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;
    @BindView(R.id.iv_left)
    ImageView iv_left;
    private AgentHouseAdapter houseAdapter;

    @Override
    public int setLayout() {
        return R.layout.activity_agent;
    }

    @Override
    public void initEvent() {

        ArrayList<String> list = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            list.add("");
        }
        houseAdapter = new AgentHouseAdapter(this, R.layout.item_recommend_house, list);
        HeaderAndFooterWrapper<String> wrapper = new HeaderAndFooterWrapper<>(houseAdapter);
        View list_head = LayoutInflater.from(this).inflate(R.layout.agent_list_head, ((ViewGroup) getRootView()), false);
        list_head.findViewById(R.id.tv_look_evaluate).setOnClickListener(this);
        iv_left.setOnClickListener(this);
        wrapper.addHeaderView(list_head);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(wrapper);
    }

    @Override
    public void initData() {

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tv_look_evaluate:
                startActivity(EvaluateShowActivity.class);
                break;
            case R.id.iv_left:
                finish();
                break;
        }
    }
}
