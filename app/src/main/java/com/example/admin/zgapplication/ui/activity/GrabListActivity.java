package com.example.admin.zgapplication.ui.activity;

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
import butterknife.OnClick;

import static com.umeng.socialize.utils.ContextUtil.getContext;

public class GrabListActivity extends BaseActivity {


    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;
    public List<Dami> list=new ArrayList<>();
    public List<String> tagList=new ArrayList<>();


    @Override
    public int setLayout() {
        return R.layout.activity_grab_list;
    }

    @Override
    public void initEvent() {

        for (int i = 0; i < 10; i++) {
            list.add(new Dami());
        }
        for (int i = 0; i < 5; i++) {
            tagList.add("第几条:"+i);
        }
        CommonAdapter<Dami> adapter = new CommonAdapter<Dami>(getContext(), R.layout.item_recommend_dami, list) {
            @Override
            protected void convert(ViewHolder holder, Dami dami, int position) {
                TagFlowLayout flowLayout = (TagFlowLayout) holder.getView(R.id.flow_layout);
                flowLayout.setAdapter(new TagAdapter<String>(tagList) {
                    @Override
                    public View getView(FlowLayout parent, int position, String o) {
                        TextView view = ((TextView) LayoutInflater.from(getContext()).inflate(R.layout.flow_item, parent, false));
                        view.setText(o);
                        return view;
                    }
                });
            }
        };
        adapter.setOnItemClickListener(new MultiItemTypeAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, RecyclerView.ViewHolder holder, int position) {
                startActivity(AgentActivity.class);
            }

            @Override
            public boolean onItemLongClick(View view, RecyclerView.ViewHolder holder, int position) {
                return false;
            }
        });
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setAdapter(adapter);
    }

    
    @Override
    public void initData() {

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
