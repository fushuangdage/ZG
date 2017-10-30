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


public class EvaluationListActivity extends BaseActivity {


    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;
    @Override
    public int setLayout() {
        return R.layout.activity_evaluation_list;
    }

    @Override
    public void initEvent() {
        ArrayList<String> strings = new ArrayList<>();

        for (int i = 0; i < 10; i++) {
             strings.add("");
        }

        recyclerView.setLayoutManager(new LinearLayoutManager(mActivity));
        recyclerView.setAdapter(new CommonAdapter<String>(mActivity,R.layout.item_order_list,strings) {
            @Override
            protected void convert(ViewHolder holder, String o, int position) {
                holder.getView(R.id.rl_my_evaluation_item_show).setVisibility(View.VISIBLE);
                ((TextView) holder.getView(R.id.tv_go_pay)).setText("去评价");
                ((TextView) holder.getView(R.id.tv_friend_pay)).setText("联系大秘");
            }
        });

    }

    @Override
    public void initData() {

    }
}
