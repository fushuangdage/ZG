package com.example.admin.zgapplication.ui.activity;

import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.admin.zgapplication.R;
import com.example.admin.zgapplication.base.BaseActivity;
import com.example.admin.zgapplication.mvp.module.AgentEvaluationResponse;
import com.example.admin.zgapplication.retrofit.RetrofitHelper;
import com.example.admin.zgapplication.retrofit.rx.BaseObserver;
import com.example.admin.zgapplication.retrofit.rx.FinishLoadConsumer;
import com.example.admin.zgapplication.retrofit.rx.RxScheduler;
import com.example.admin.zgapplication.ui.adapter.ZhyBaseRecycleAdapter.CommonAdapter;
import com.example.admin.zgapplication.ui.adapter.ZhyBaseRecycleAdapter.base.ViewHolder;
import com.example.admin.zgapplication.ui.view.StartBar;
import com.example.admin.zgapplication.utils.date.TimeUtil;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnLoadmoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;
import com.zhy.view.flowlayout.FlowLayout;
import com.zhy.view.flowlayout.TagAdapter;
import com.zhy.view.flowlayout.TagFlowLayout;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.OnClick;

public class EvaluateShowActivity extends BaseActivity {

    @BindView(R.id.tv_title)
    TextView tv_title;

    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;

    @BindView(R.id.refreshLayout)
    RefreshLayout refreshLayout;

    private CommonAdapter<AgentEvaluationResponse.DataBean.ListBean> adapter;
    private int page=1;

    private ArrayList<AgentEvaluationResponse.DataBean.ListBean> list=new ArrayList<>();
    private String aid;

    @Override
    public int setLayout() {
        return R.layout.activity_evaluate;
    }

    @Override
    public void initEvent() {
        tv_title.setText("评价详情");

        adapter = new CommonAdapter<AgentEvaluationResponse.DataBean.ListBean>(this, R.layout.item_evaluate, list) {
            @Override
            protected void convert(ViewHolder holder, AgentEvaluationResponse.DataBean.ListBean listBean, int position) {
                Glide.with(mActivity).load(listBean.getAvatar()).into((ImageView) holder.getView(R.id.user_icon));
                holder.setText(R.id.tv_user_name,listBean.getNick_name());
                holder.setText(R.id.tv_evaluate_content,listBean.getContent());
                holder.setText(R.id.tv_evaluate_content, TimeUtil.formatData(TimeUtil.dateFormatMDofChinese,listBean.getCreate_at()));
                ((StartBar) holder.getView(R.id.start_bar)).setRating((int) listBean.getScore());
                holder.setText(R.id.tv_evaluate_content,listBean.getContent());


                ((StartBar) holder.getView(R.id.start_bar)).setRating((int) listBean.getScore());
                ((TagFlowLayout) holder.getView(R.id.flow_layout)).setAdapter(new TagAdapter<String>(listBean.getLabel()) {
                    @Override
                    public View getView(FlowLayout parent, int position, String o) {
                        View view = LayoutInflater.from(mActivity).inflate(R.layout.item_agent_tag, null, false);
                        ((TextView) view).setText(o);
                        return view;
                    }
                });

            }
        };

        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.addItemDecoration(new DividerItemDecoration(this,DividerItemDecoration.VERTICAL));
        recyclerView.setAdapter(adapter);
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

    }

    @Override
    public void initData() {
        aid = getIntent().getStringExtra("aid");
        loadData();
    }

    private void loadData() {
        RetrofitHelper.getApiWithUid().getAgentEvaluation(aid,page)
                .compose(RxScheduler.<AgentEvaluationResponse>defaultScheduler())
                .doOnNext(new FinishLoadConsumer<AgentEvaluationResponse>(refreshLayout,page))
                .subscribe(new BaseObserver<AgentEvaluationResponse>(mActivity) {
                    @Override
                    public void error(Throwable e) {

                    }

                    @Override
                    public void next(AgentEvaluationResponse agentEvaluationResponse) {
                        if (agentEvaluationResponse.getData().getPage()==1){
                            list.clear();
                        }
                        list.addAll(agentEvaluationResponse.getData().getList());
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
