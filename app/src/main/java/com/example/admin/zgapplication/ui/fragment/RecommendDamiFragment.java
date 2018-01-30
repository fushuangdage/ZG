package com.example.admin.zgapplication.ui.fragment;


import android.content.Intent;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.admin.zgapplication.R;
import com.example.admin.zgapplication.base.BaseSupportFragment;
import com.example.admin.zgapplication.mvp.module.RecommendAgentsListResponse;
import com.example.admin.zgapplication.retrofit.RetrofitHelper;
import com.example.admin.zgapplication.retrofit.rx.BaseObserver;
import com.example.admin.zgapplication.retrofit.rx.FinishLoadConsumer;
import com.example.admin.zgapplication.retrofit.rx.RxScheduler;
import com.example.admin.zgapplication.ui.activity.AgentActivity;
import com.example.admin.zgapplication.ui.activity.ChatActivity;
import com.example.admin.zgapplication.ui.adapter.ZhyBaseRecycleAdapter.CommonAdapter;
import com.example.admin.zgapplication.ui.adapter.ZhyBaseRecycleAdapter.MultiItemTypeAdapter;
import com.example.admin.zgapplication.ui.adapter.ZhyBaseRecycleAdapter.base.ViewHolder;
import com.example.admin.zgapplication.ui.view.StartBar;
import com.hyphenate.chat.EMMessage;
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

/**
 * A simple {@link Fragment} subclass.
 */
public class RecommendDamiFragment extends BaseSupportFragment {

    @BindView(R.id.rv_recommend_dami)
    RecyclerView recyclerView;

    @BindView(R.id.refreshLayout)
    RefreshLayout refreshLayout;

    public List<RecommendAgentsListResponse.DataBean.ListBean> list=new ArrayList<>();
    public List<String> tagList=new ArrayList<>();

    public int page=1;
    private CommonAdapter<RecommendAgentsListResponse.DataBean.ListBean> adapter;
    private int iid;

    @Override
    protected int setLayout() {
        return R.layout.fragment_recommend_dami;
    }

    @Override
    protected void init() {
        iid = getActivity().getIntent().getIntExtra("iid",0);

        refreshLayout.setOnLoadmoreListener(new OnLoadmoreListener() {
            @Override
            public void onLoadmore(RefreshLayout refreshlayout) {
                page++;
                loadData();
            }
        });


        refreshLayout.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(RefreshLayout refreshlayout) {
                page=1;
                loadData();
            }
        });

        adapter = new CommonAdapter<RecommendAgentsListResponse.DataBean.ListBean>(getContext(), R.layout.item_recommend_dami, list) {
            @Override
            protected void convert(ViewHolder holder, final RecommendAgentsListResponse.DataBean.ListBean bean, int position) {
                TagFlowLayout flowLayout = (TagFlowLayout) holder.getView(R.id.flow_layout);

                Glide.with(mActivity).load(bean.getAvatar()).into((ImageView) holder.getView(R.id.icon));
                holder.setText(R.id.tv_name,bean.getUsername());
                ((StartBar) holder.getView(R.id.rating_bar)).setRating((int) bean.getScore());
                holder.setText(R.id.tv_company,bean.getCompany_name());
                holder.setText(R.id.tv_house_count_num,bean.getHouse_sum()+"套");
                holder.setText(R.id.tv_trade_area,"负责商圈 : "+bean.getDistrict());

                holder.setOnClickListener(R.id.bt_chat, new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(mActivity, ChatActivity.class);
                        intent.putExtra(EaseConstant.EXTRA_USER_ID,bean.getHx_username());
                        intent.putExtra(EaseConstant.EXTRA_CHAT_TYPE,EMMessage.ChatType.Chat);

//                        Bundle bundle = new Bundle();
//                        bundle.putString(EaseConstant.EXTRA_USER_ID,bean.getHx_username());
//                        bundle.pu(EaseConstant.EXTRA_CHAT_TYPE, EMMessage.ChatType.Chat);
//                        intent.putExtras(bundle);

                        startActivity(intent);
                    }
                });

                holder.setText(R.id.tv_visit_count,bean.getVisit_sum());
                holder.setText(R.id.tv_deal_count,bean.getOrder_sum());




                flowLayout.setAdapter(new TagAdapter<String>(bean.getLabel()) {
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

                Intent intent = new Intent(getContext(), AgentActivity.class);
                RecommendAgentsListResponse.DataBean.ListBean listBean = list.get(position);
                intent.putExtra("aid",listBean.getId());
                startActivity(intent);
            }

            @Override
            public boolean onItemLongClick(View view, RecyclerView.ViewHolder holder, int position) {
                return false;
            }
        });
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setAdapter(adapter);


        loadData();
    }

    private void loadData() {
        RetrofitHelper.getApiWithUid().getRecommendAgents(iid,page)
                .compose(RxScheduler.<RecommendAgentsListResponse>defaultScheduler())
                .doOnNext(new FinishLoadConsumer<RecommendAgentsListResponse>(refreshLayout,page))
                .subscribe(new BaseObserver<RecommendAgentsListResponse>(mActivity) {
                    @Override
                    public void error(Throwable e) {

                    }

                    @Override
                    public void next(RecommendAgentsListResponse recommendHouseListResponse) {
                        if (recommendHouseListResponse.getData().getPage()==1){
                            list.clear();
                        }
                        list.addAll(recommendHouseListResponse.getData().getList());
                        adapter.notifyDataSetChanged();
                    }

                    @Override
                    public void complete() {

                    }
                });
    }

}
