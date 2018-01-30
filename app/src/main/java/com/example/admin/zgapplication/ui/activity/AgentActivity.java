package com.example.admin.zgapplication.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.admin.zgapplication.R;
import com.example.admin.zgapplication.base.BaseActivity;
import com.example.admin.zgapplication.mvp.module.AgentDetailResponse;
import com.example.admin.zgapplication.retrofit.RetrofitHelper;
import com.example.admin.zgapplication.retrofit.rx.BaseObserver;
import com.example.admin.zgapplication.retrofit.rx.FinishLoadConsumer;
import com.example.admin.zgapplication.retrofit.rx.RxScheduler;
import com.example.admin.zgapplication.ui.adapter.ZhyBaseRecycleAdapter.CommonAdapter;
import com.example.admin.zgapplication.ui.adapter.ZhyBaseRecycleAdapter.MultiItemTypeAdapter;
import com.example.admin.zgapplication.ui.adapter.ZhyBaseRecycleAdapter.base.ViewHolder;
import com.example.admin.zgapplication.ui.adapter.ZhyBaseRecycleAdapter.wrapper.HeaderAndFooterWrapper;
import com.example.admin.zgapplication.ui.view.StartBar;
import com.hyphenate.easeui.EaseConstant;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnLoadmoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;
import com.zhy.view.flowlayout.FlowLayout;
import com.zhy.view.flowlayout.TagAdapter;
import com.zhy.view.flowlayout.TagFlowLayout;

import java.util.ArrayList;
import java.util.LinkedList;

import butterknife.BindView;
import butterknife.OnClick;


public class AgentActivity extends BaseActivity implements View.OnClickListener {

    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;
    @BindView(R.id.iv_left)
    ImageView iv_left;
    @BindView(R.id.refreshLayout)
    RefreshLayout refreshLayout;
    public int page=1;

    private CommonAdapter<AgentDetailResponse.DataBean.ListBean> houseAdapter;
    private ArrayList<AgentDetailResponse.DataBean.ListBean> list= new ArrayList<>();
    private ArrayList<String> tagList=new ArrayList<>();
    private View list_head;
    private TagAdapter<String> tag;
    private String aid;
    private String company_id;
    private AgentDetailResponse.DataBean data;

    @Override
    public int setLayout() {
        return R.layout.activity_agent;
    }

    @Override
    public void initEvent() {
        LinkedList<String> strings = new LinkedList<>();
        strings.add("");
        strings.add(1,"");
        houseAdapter = new CommonAdapter<AgentDetailResponse.DataBean.ListBean>(this, R.layout.item_recommend_house, list) {
            @Override
            protected void convert(ViewHolder holder, AgentDetailResponse.DataBean.ListBean bean, int position) {
                Glide.with(mActivity).load(bean.getHouse_photo()).into((ImageView) holder.getView(R.id.iv_house));
                ((TextView) holder.getView(R.id.tv_house_name)).setText(bean.getHouse_title());
                ((TextView) holder.getView(R.id.tv_house_location)).setText(bean.getHouse_address());
                ((TextView) holder.getView(R.id.tv_house_info)).setText(bean.getHouse_info());
                ((TextView) holder.getView(R.id.tv_house_rent)).setText(bean.getHouse_rental()+"元/月");
                LinearLayout ll_tag_container = holder.getView(R.id.ll_tag_container);
                showThreeTag(bean, ll_tag_container);
            }
        };

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

        HeaderAndFooterWrapper wrapper = new HeaderAndFooterWrapper(houseAdapter);
        list_head = LayoutInflater.from(this).inflate(R.layout.agent_list_head, ((ViewGroup) getRootView()), false);
        list_head.findViewById(R.id.tv_look_evaluate).setOnClickListener(this);
        list_head.findViewById(R.id.rl_company).setOnClickListener(this);

        TagFlowLayout flowLayout = (TagFlowLayout) list_head.findViewById(R.id.flow_layout);
        iv_left.setOnClickListener(this);
        wrapper.addHeaderView(list_head);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(wrapper);
        tag = new TagAdapter<String>(tagList) {
            @Override
            public View getView(FlowLayout parent, int position, String o) {
                TextView content = (TextView) LayoutInflater.from(mActivity).inflate(R.layout.item_agent_tag, null, false);
                content.setText(o);
                return content;
            }
        };
        flowLayout.setAdapter(tag);
        houseAdapter.setOnItemClickListener(new MultiItemTypeAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, RecyclerView.ViewHolder holder, int position) {
                Intent intent = new Intent(mActivity,HouseDetailActivity.class);
                intent.putExtra("house_id",list.get(position).getHouse_id());
                intent.putExtra("type",list.get(position).getType());
                intent.putExtra("room_id",list.get(position).getRoom_id());
                startActivity(intent);
            }

            @Override
            public boolean onItemLongClick(View view, RecyclerView.ViewHolder holder, int position) {
                return false;
            }
        });
    }

    private void showThreeTag(AgentDetailResponse.DataBean.ListBean bean, LinearLayout ll_tag_container) {

        for (int i = 0; i < 3&&bean.getHouse_label()!=null&&bean.getHouse_label().size()>i; i++) {
            TextView childAt = ((TextView) ll_tag_container.getChildAt(i));
            childAt.setVisibility(View.VISIBLE);
            childAt.setText(bean.getHouse_label().get(i));
        }

    }

    @Override
    public void initData() {
        aid = getIntent().getStringExtra("aid");
        loadData();
    }

    private void loadData() {
        RetrofitHelper.getApi().getAgentDetail(aid)
                .compose(RxScheduler.<AgentDetailResponse>defaultScheduler())
                .doOnNext(new FinishLoadConsumer<AgentDetailResponse>(refreshLayout,page))
                .subscribe(new BaseObserver<AgentDetailResponse>(mActivity) {
                    @Override
                    public void error(Throwable e) {

                    }

                    @Override
                    public void next(AgentDetailResponse agentDetailResponse) {
                        data = agentDetailResponse.getData();
                        company_id = data.getCompany_id();
                        Glide.with(mActivity).load(data.getAvatar()).into((ImageView) list_head.findViewById(R.id.iv_agent_icon));
                        ((TextView) list_head.findViewById(R.id.tv_agent_name)).setText(data.getUsername());
                        ((StartBar) list_head.findViewById(R.id.start_bar)).setRating((int) data.getScore());
                        ((TextView) list_head.findViewById(R.id.tv_district)).setText(data.getDistrict());
                        ((TextView) list_head.findViewById(R.id.tv_take_time)).setText(data.getVisit_sum());
                        ((TextView) list_head.findViewById(R.id.tv_deal_time)).setText(data.getOrder_sum());
                        ((TextView) list_head.findViewById(R.id.tv_agent_grade)).setText(data.getScore()+"");
                        Glide.with(mActivity).load(data.getLogo()).into((ImageView) list_head.findViewById(R.id.company_icon));
                        ((TextView) list_head.findViewById(R.id.tv_company_name)).setText(data.getCompany_name());
                        ((TextView) list_head.findViewById(R.id.tv_agent_count)).setText(data.getAgent_count());
                        ((TextView) list_head.findViewById(R.id.tv_house_count)).setText(data.getHouse_count());
                        ((TextView) list_head.findViewById(R.id.tv_score)).setText(data.getScore()+"");
                        tagList.clear();
                        tagList.addAll(data.getLabel());
                        tag.notifyDataChanged();

                        if (page==1)
                            list.clear();
                        list.addAll(data.getList());
                        houseAdapter.notifyDataSetChanged();

                    }

                    @Override
                    public void complete() {

                    }
                });
    }

    @Override
    @OnClick({R.id.tv_contact_agent})
    public void onClick(View v) {

        Intent intent;
        switch (v.getId()) {
            case R.id.tv_look_evaluate:
                intent = new Intent(mActivity, EvaluateShowActivity.class);
                intent.putExtra("aid",aid);
                startActivity(intent);
                break;
            case R.id.iv_left:
                finish();
                break;
            case R.id.rl_company:
                intent = new Intent(mActivity, CompanyDetailActivity.class);
                intent.putExtra("company_id",company_id);
                startActivity(intent);
                break;
            case R.id.tv_contact_agent:
                intent = new Intent(mActivity, ChatActivity.class);
                Bundle bundle = new Bundle();
                bundle.putString(EaseConstant.EXTRA_USER_ID,data.getHx_username());
                bundle.putString(EaseConstant.NICK_NAME,data.getUsername());
                bundle.putInt(EaseConstant.EXTRA_CHAT_TYPE, EaseConstant.CHATTYPE_SINGLE);
                intent.putExtras(bundle);
                startActivity(intent);
                break;
        }
    }
}
