package com.example.admin.zgapplication.ui.activity;

import android.content.Intent;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.admin.zgapplication.R;
import com.example.admin.zgapplication.base.BaseActivity;
import com.example.admin.zgapplication.mvp.module.ContractListResponse;
import com.example.admin.zgapplication.retrofit.RetrofitHelper;
import com.example.admin.zgapplication.retrofit.rx.BaseObserver;
import com.example.admin.zgapplication.retrofit.rx.FinishLoadConsumer;
import com.example.admin.zgapplication.retrofit.rx.RxScheduler;
import com.example.admin.zgapplication.ui.adapter.ZhyBaseRecycleAdapter.CommonAdapter;
import com.example.admin.zgapplication.ui.adapter.ZhyBaseRecycleAdapter.MultiItemTypeAdapter;
import com.example.admin.zgapplication.ui.adapter.ZhyBaseRecycleAdapter.base.ViewHolder;
import com.scwang.smartrefresh.layout.api.RefreshLayout;

import java.util.ArrayList;

import butterknife.BindView;

public class MyContractActivity extends BaseActivity implements MultiItemTypeAdapter.OnItemClickListener {


    @BindView(R.id.tv_title)
    TextView tv_title;
    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;

    @BindView(R.id.refreshLayout)
    RefreshLayout refreshLayout;

    public int currentPage=1;

    private CommonAdapter<ContractListResponse.DataBean.ListBean> adapter;
    private ArrayList<ContractListResponse.DataBean.ListBean> list=new ArrayList<>();

    @Override
    public int setLayout() {
        return R.layout.activity_my_contract;
    }

    @Override
    public void initEvent() {



        adapter = new CommonAdapter<ContractListResponse.DataBean.ListBean>(this, R.layout.item_my_contract_list, list) {
            @Override
            protected void convert(ViewHolder holder, ContractListResponse.DataBean.ListBean o, int position) {
                ((TextView) holder.getView(R.id.tv_room_name)).setText(o.getTitle());
                ((TextView) holder.getView(R.id.tv_status)).setText(o.getStatus());
                ((TextView) holder.getView(R.id.tv_order_no)).setText(o.getOrder());
                ((TextView) holder.getView(R.id.tv_data)).setText(o.getDate());
                ((TextView) holder.getView(R.id.tv_price)).setText(String.format("%s元/月 %s",o.getRent(),o.getPay()));
                Glide.with(mActivity).load(o.getImg()).into((ImageView) holder.getView(R.id.iv_room));


                holder.getView(R.id.tv_fix_online).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        startActivity(FixOnlineActivity.class);
                    }
                });
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
        RetrofitHelper.getApiWithUid().getContractList()
                .compose(RxScheduler.<ContractListResponse>defaultScheduler())
                .doOnNext(new FinishLoadConsumer<ContractListResponse>(refreshLayout,currentPage))
                .subscribe(new BaseObserver<ContractListResponse>(mActivity) {
                    @Override
                    public void error(Throwable e) {

                    }

                    @Override
                    public void next(ContractListResponse contractListResponse) {
                        ContractListResponse.DataBean data = contractListResponse.getData();
                        list.clear();
                        list.addAll(data.getList());
                        adapter.notifyDataSetChanged();
                    }

                    @Override
                    public void complete() {

                    }
                });
    }

    @Override
    public void onItemClick(View view, RecyclerView.ViewHolder holder, int position) {
        Intent intent = new Intent(this, ContractDetailActivity.class);
        intent.putExtra("contract_id",list.get(position).getOrder());
        startActivity(intent);
    }

    @Override
    public boolean onItemLongClick(View view, RecyclerView.ViewHolder holder, int position) {
        return false;
    }
}
