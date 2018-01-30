package com.example.admin.zgapplication.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.admin.zgapplication.R;
import com.example.admin.zgapplication.base.BaseActivity;
import com.example.admin.zgapplication.mvp.module.CrabListResponse;
import com.example.admin.zgapplication.retrofit.RetrofitHelper;
import com.example.admin.zgapplication.retrofit.rx.BaseObserver;
import com.example.admin.zgapplication.retrofit.rx.RxScheduler;
import com.example.admin.zgapplication.ui.adapter.ZhyBaseRecycleAdapter.CommonAdapter;
import com.example.admin.zgapplication.ui.adapter.ZhyBaseRecycleAdapter.MultiItemTypeAdapter;
import com.example.admin.zgapplication.ui.adapter.ZhyBaseRecycleAdapter.base.ViewHolder;
import com.hyphenate.easeui.EaseConstant;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnLoadmoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;
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
    @BindView(R.id.refreshLayout)
    RefreshLayout refreshLayout;
    public List<CrabListResponse.DataBean.ListBean> list=new ArrayList<>();
    public List<String> tagList=new ArrayList<>();
    private int page=1;
    private CommonAdapter<CrabListResponse.DataBean.ListBean> adapter;
    private int iid;


    @Override
    public int setLayout() {
        return R.layout.activity_grab_list;
    }

    @Override
    public void initEvent() {
        refreshLayout.setOnLoadmoreListener(new OnLoadmoreListener() {
            @Override
            public void onLoadmore(RefreshLayout refreshlayout) {
                page++;
                initData();
            }
        });

        refreshLayout.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(RefreshLayout refreshlayout) {
                page=1;
                initData();

            }
        });


        adapter = new CommonAdapter<CrabListResponse.DataBean.ListBean>(getContext(), R.layout.item_recommend_dami, list) {
            @Override
            protected void convert(ViewHolder holder, final CrabListResponse.DataBean.ListBean bean, int position) {

                ((TagFlowLayout) holder.getView(R.id.flow_layout)).setAdapter(new TagAdapter<String>(bean.getLabel()) {
                     @Override
                     public View getView(FlowLayout parent, int position, String o) {
                         TextView view = ((TextView) LayoutInflater.from(getContext()).inflate(R.layout.flow_item, parent, false));
                         view.setText(o);
                         return view;
                     }
                 });
                Glide.with(mActivity).load(bean.getAvatar()).into((ImageView) holder.getView(R.id.icon));
                holder.setText(R.id.tv_name,bean.getUsername());
                holder.setText(R.id.tv_company,bean.getCompany_name());
                holder.setText(R.id.tv_house_count_num,bean.getHouse_sum()+"套");
                holder.setText(R.id.tv_chat_spead,bean.getChat_intention());
                holder.setText(R.id.tv_trade_area,"负责商圈  "+bean.getChat_intention()+" ");
                holder.setText(R.id.tv_visit_count,bean.getVisit_sum());
                holder.setText(R.id.tv_deal_count,bean.getOrder_sum());

                holder.setOnClickListener(R.id.bt_chat, new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(mActivity, ChatActivity.class);
                        Bundle bundle = new Bundle();
                        bundle.putString(EaseConstant.AGENT_ID,bean.getId());
                        bundle.putString(EaseConstant.EXTRA_USER_ID,bean.getHx_username());
                        bundle.putString(EaseConstant.NICK_NAME,bean.getUsername());
                        bundle.putInt(EaseConstant.EXTRA_CHAT_TYPE, EaseConstant.CHATTYPE_SINGLE);
                        intent.putExtras(bundle);
                        startActivity(intent);
                    }
                });

            }
        };
        adapter.setOnItemClickListener(new MultiItemTypeAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, RecyclerView.ViewHolder holder, int position) {
                Intent intent = new Intent(mActivity, AgentActivity.class);
                intent.putExtra("aid",list.get(position).getId());
                startActivity(intent);
            }

            @Override
            public boolean onItemLongClick(View view, RecyclerView.ViewHolder holder, int position) {
                return false;
            }
        });
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setAdapter(adapter);
        iid = getIntent().getIntExtra("iid", 0);

    }

    
    @Override
    public void initData() {
        RetrofitHelper.getApiWithUid().getCrabList(iid,page)
                .compose(RxScheduler.<CrabListResponse>defaultScheduler())
                .subscribe(new BaseObserver<CrabListResponse>(mActivity) {
                    @Override
                    public void error(Throwable e) {

                    }

                    @Override
                    public void next(CrabListResponse crabListResponse) {
                        if (page==1) {
                            list.clear();
                        }
                        list.addAll(crabListResponse.getData().getList());
                        adapter.notifyDataSetChanged();
                    }

                    @Override
                    public void complete() {

                    }
                });
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
