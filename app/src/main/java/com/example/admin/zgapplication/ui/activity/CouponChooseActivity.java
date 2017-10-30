package com.example.admin.zgapplication.ui.activity;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import com.example.admin.zgapplication.R;
import com.example.admin.zgapplication.base.BaseActivity;
import com.example.admin.zgapplication.ui.adapter.ZhyBaseRecycleAdapter.CommonAdapter;
import com.example.admin.zgapplication.ui.adapter.ZhyBaseRecycleAdapter.MultiItemTypeAdapter;
import com.example.admin.zgapplication.ui.adapter.ZhyBaseRecycleAdapter.base.ViewHolder;
import com.example.admin.zgapplication.ui.adapter.ZhyBaseRecycleAdapter.wrapper.HeaderAndFooterWrapper;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.OnClick;


public class CouponChooseActivity extends BaseActivity implements View.OnClickListener {

    @BindView(R.id.tv_title)
    TextView tv_title;
    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;
    private CommonAdapter<String> adapter;
    private HeaderAndFooterWrapper wrapper;

    @Override
    public int setLayout() {
        return R.layout.activity_coupon_choose;
    }

    @Override
    public void initEvent() {
        final String title = getIntent().getStringExtra("title");
        tv_title.setText(title);

        ArrayList<String> strings = new ArrayList<>();
        for (int i = 0; i < 3; i++) {
            strings.add("");
        }
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter = new CommonAdapter<String>(this, R.layout.item_coupon, strings) {
            @Override
            protected void convert(ViewHolder holder, String s, int position) {
                if (title.equals("选择经纪公司优惠券")) {
                    holder.itemView.setBackgroundResource(R.drawable.jingjigongsiweishiyong);
                }else {
                    holder.itemView.setBackgroundResource(R.drawable.zhagenweishiyong);
                }
            }
        };

        wrapper = new HeaderAndFooterWrapper(adapter);
        View head = LayoutInflater.from(this).inflate(R.layout.item_coupon_head, null, false);
        head.setOnClickListener(this);
        wrapper.addHeaderView(head);
        adapter.setOnItemClickListener(new MultiItemTypeAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, RecyclerView.ViewHolder holder, int position) {
                finish();
            }

            @Override
            public boolean onItemLongClick(View view, RecyclerView.ViewHolder holder, int position) {
                return false;
            }
        });
        recyclerView.setAdapter(wrapper);
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
            case R.id.choose_coupon_head:
                finish();
                break;
        }
    }
}
