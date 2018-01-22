package com.example.admin.zgapplication.ui.fragment;


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
import com.example.admin.zgapplication.base.CompanyEvaluationList;
import com.example.admin.zgapplication.retrofit.RetrofitHelper;
import com.example.admin.zgapplication.retrofit.rx.BaseObserver;
import com.example.admin.zgapplication.retrofit.rx.FinishLoadConsumer;
import com.example.admin.zgapplication.retrofit.rx.RxScheduler;
import com.example.admin.zgapplication.ui.adapter.ZhyBaseRecycleAdapter.CommonAdapter;
import com.example.admin.zgapplication.ui.adapter.ZhyBaseRecycleAdapter.base.ViewHolder;
import com.example.admin.zgapplication.ui.view.StartBar;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnLoadmoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;
import com.zhy.view.flowlayout.FlowLayout;
import com.zhy.view.flowlayout.TagAdapter;
import com.zhy.view.flowlayout.TagFlowLayout;

import java.util.ArrayList;

import butterknife.BindView;

/**
 * A simple {@link Fragment} subclass.
 */
public class CompanyEvaluateFragment extends BaseSupportFragment {

    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;

    @BindView(R.id.refreshLayout)
    RefreshLayout refreshLayout;




    public int page=1;
    private ArrayList<CompanyEvaluationList.DataBean.ListBean> list;
    private int company_id;
    private CommonAdapter<CompanyEvaluationList.DataBean.ListBean> adapter;


    @Override
    protected int setLayout() {
        return R.layout.fragment_company_evaluate;
    }

    @Override
    protected void init() {

        company_id = getActivity().getIntent().getIntExtra("company_id", 0);


        list = new ArrayList<>();

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


        company_id = getActivity().getIntent().getIntExtra("company_id", 0);

        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        adapter = new CommonAdapter<CompanyEvaluationList.DataBean.ListBean>(getContext(), R.layout.item_evaluate, list) {

            @Override
            protected void convert(ViewHolder holder, CompanyEvaluationList.DataBean.ListBean s, int position) {
                Glide.with(mActivity).load(s.getAvatar()).into((ImageView) holder.getView(R.id.user_icon));
                holder.setText(R.id.tv_user_name,s.getReal_name());
                holder.setText(R.id.tv_evaluate_content,s.getContent());
                ((StartBar) holder.getView(R.id.start_bar)).setRating((int) s.getScore());
                ((TagFlowLayout) holder.getView(R.id.flow_layout)).setAdapter(new TagAdapter<String>(s.getLabel()) {
                    @Override
                    public View getView(FlowLayout parent, int position, String o) {
                        TextView view = (TextView) LayoutInflater.from(mActivity).inflate(R.layout.item_agent_tag, null, false);
                        view.setText(o);
                        return view;
                    }
                });

            }
        };
        recyclerView.setAdapter(adapter);

        loadData();
    }

    private void loadData() {
        RetrofitHelper.getApi().getCompanyEvaluation(company_id)
                .compose(RxScheduler.<CompanyEvaluationList>defaultScheduler())
                .doOnNext(new FinishLoadConsumer<CompanyEvaluationList>(refreshLayout,page))
                .subscribe(new BaseObserver<CompanyEvaluationList>(mActivity) {
                    @Override
                    public void error(Throwable e) {

                    }

                    @Override
                    public void next(CompanyEvaluationList companyEvaluationList) {
                        if (page==1){
                            list.clear();
                        }
                        list.addAll(companyEvaluationList.getData().getList());
                        adapter.notifyDataSetChanged();
                    }

                    @Override
                    public void complete() {

                    }
                });
    }
}
