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
import butterknife.OnClick;

public class RentBillListActivity extends BaseActivity {

    @BindView(R.id.tv_title)
    TextView tv_title;
    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;
    private CommonAdapter<String> adapter;
    @BindView(R.id.ll_pay_all)
    View ll_pay_all;
    @BindView(R.id.iv_right)
    View iv_right;
    @Override
    public int setLayout() {
        return R.layout.activity_rent_bill_list;
    }

    @Override
    public void initEvent() {
        String title = getIntent().getStringExtra("title");
        tv_title.setText(title);

        ArrayList<String> strings = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            strings.add("");
        }

        if (title.equals("房租账单")) {
            ll_pay_all.setVisibility(View.GONE);
            iv_right.setVisibility(View.GONE);
            adapter = new CommonAdapter<String>(this, R.layout.item_rent_bill, strings) {
                @Override
                protected void convert(ViewHolder holder, String s, int position) {

                }
            };
        }else {
          adapter = new CommonAdapter<String>(this, R.layout.item_life_bill, strings) {
                @Override
                protected void convert(ViewHolder holder, String s, int position) {
                        holder.getView(R.id.tv_go_pay).setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                startActivity(PayOnlineActivity.class);
                            }
                        });
                }
            };
        }
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);

    }

    @Override
    public void initData() {

    }

    @OnClick({R.id.iv_left,R.id.tv_go_pay})
    public void onClick(View view){
        switch (view.getId()) {
            case R.id.tv_go_pay:
                startActivity(PayOnlineActivity.class);
                break;
            case R.id.iv_left:
                finish();
                break;
        }
    }
}
