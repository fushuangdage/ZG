package com.example.admin.zgapplication.ui.activity;

import android.content.Intent;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import com.example.admin.zgapplication.R;
import com.example.admin.zgapplication.base.BaseActivity;
import com.example.admin.zgapplication.mvp.module.Dami;
import com.example.admin.zgapplication.ui.adapter.ZhyBaseRecycleAdapter.CommonAdapter;
import com.example.admin.zgapplication.ui.adapter.ZhyBaseRecycleAdapter.MultiItemTypeAdapter;
import com.example.admin.zgapplication.ui.adapter.ZhyBaseRecycleAdapter.base.ViewHolder;
import com.zhy.view.flowlayout.FlowLayout;
import com.zhy.view.flowlayout.TagAdapter;
import com.zhy.view.flowlayout.TagFlowLayout;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

public class ChooseAgentActivity extends BaseActivity {

    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;

    public List<Dami> list=new ArrayList<>();
    public List<String> tagList=new ArrayList<>();
    private String from;

    @Override
    public int setLayout() {
        return R.layout.activity_choose_agent;
    }

    @Override
    public void initEvent() {
        from = getIntent().getStringExtra("from");

        for (int i = 0; i < 10; i++) {
            list.add(new Dami());
        }

        for (int i = 0; i < 5; i++) {
            tagList.add("第几条:"+i);
        }

        CommonAdapter<Dami> adapter = new CommonAdapter<Dami>(mActivity, R.layout.item_choose_agent, list) {
            @Override
            protected void convert(ViewHolder holder, Dami dami, int position) {
                TagFlowLayout flowLayout = (TagFlowLayout) holder.getView(R.id.flow_layout);
                if (from!=null) {
                    //代表是从咨询经纪人跳转过来,显示三个按钮
                    View view = holder.getView(R.id.ll_chat_agent_show);
                    view.setVisibility(View.VISIBLE);
                    view.findViewById(R.id.iv_take_look).setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            startActivity(TakeLookActivity.class);
                        }
                    });
                    view.findViewById(R.id.iv_chat).setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            // TODO: 2017/11/1 跳转到单聊

                        }
                    });
                    view.findViewById(R.id.iv_tel).setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {

                        }
                    });

                }

                flowLayout.setAdapter(new TagAdapter<String>(tagList) {
                    @Override
                    public View getView(FlowLayout parent, int position, String o) {
                        TextView view = ((TextView) LayoutInflater.from(mActivity).inflate(R.layout.flow_item, parent, false));
                        view.setText(o);
                        return view;
                    }
                });
            }
        };
        adapter.setOnItemClickListener(new MultiItemTypeAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, RecyclerView.ViewHolder holder, int position) {

                Intent intent = new Intent(mActivity, OrderDetailActivity.class);
                startActivity(intent);
            }

            @Override
            public boolean onItemLongClick(View view, RecyclerView.ViewHolder holder, int position) {
                return false;
            }
        });
        recyclerView.setLayoutManager(new LinearLayoutManager(mActivity));
        recyclerView.setAdapter(adapter);
    }

    @Override
    public void initData() {

    }
}
