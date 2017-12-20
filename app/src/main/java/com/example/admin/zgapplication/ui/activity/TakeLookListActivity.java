package com.example.admin.zgapplication.ui.activity;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.example.admin.zgapplication.R;
import com.example.admin.zgapplication.base.BaseActivity;
import com.example.admin.zgapplication.ui.adapter.ZhyBaseRecycleAdapter.CommonAdapter;
import com.example.admin.zgapplication.ui.adapter.ZhyBaseRecycleAdapter.base.ViewHolder;

import java.util.ArrayList;

import butterknife.BindView;

public class TakeLookListActivity extends BaseActivity {

    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;

    @Override
    public int setLayout() {
        return R.layout.activity_take_look_list;
    }

    @Override
    public void initEvent() {

        ArrayList<String> strings = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            strings.add("");

        }
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(new CommonAdapter<String>(this,R.layout.item_order_list,strings) {
            @Override
            protected void convert(ViewHolder holder, String o, int position) {
                View view = holder.getView(R.id.rl_my_evaluation_item_show);
                view.setVisibility(View.VISIBLE);
                ((TextView) holder.getView(R.id.tv_go_pay)).setText("确认带看");
                ((TextView) holder.getView(R.id.tv_friend_pay)).setText("取消带看");

            }
        });
    }

    @Override
    public void initData() {

    }
}
