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
import com.example.admin.zgapplication.mvp.module.HouseEvaluationRespose;
import com.example.admin.zgapplication.retrofit.RetrofitHelper;
import com.example.admin.zgapplication.retrofit.rx.BaseObserver;
import com.example.admin.zgapplication.retrofit.rx.FinishLoadConsumer;
import com.example.admin.zgapplication.retrofit.rx.RxScheduler;
import com.example.admin.zgapplication.ui.adapter.ZhyBaseRecycleAdapter.CommonAdapter;
import com.example.admin.zgapplication.ui.adapter.ZhyBaseRecycleAdapter.base.ViewHolder;
import com.example.admin.zgapplication.utils.date.TimeUtil;
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
public class HouseEvaluteFragment extends BaseSupportFragment {


    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;
    @BindView(R.id.refreshLayout)
    RefreshLayout refreshLayout;

    public Integer page=1;
    private ArrayList<HouseEvaluationRespose.DataBean.ListBean> data;
    private CommonAdapter<HouseEvaluationRespose.DataBean.ListBean> adapter;
    private String house_id;
    private String type;
    private String room_id;

    @Override
    protected int setLayout() {
        return R.layout.fragment_house_evalute;
    }

    @Override
    protected void init() {

        data = new ArrayList<>();

        Intent intent = getActivity().getIntent();
        house_id = intent.getStringExtra("house_id");
        type = intent.getStringExtra("type");
        room_id = intent.getStringExtra("room_id");

        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        adapter = new CommonAdapter<HouseEvaluationRespose.DataBean.ListBean>(getContext(), R.layout.item_evaluate, data) {
            @Override
            protected void convert(ViewHolder holder, HouseEvaluationRespose.DataBean.ListBean s, int position) {

                if (s.getHide().equals("1")){
//                    Glide.with(mActivity).load(getResources().getDrawable(R.drawable.user_sit)).into((ImageView) holder.getView(R.id.user_icon));
                    holder.setText(R.id.tv_user_name,"匿名评价");
                }else {
                    Glide.with(mActivity).load(s.getAvatar()).into((ImageView) holder.getView(R.id.user_icon));
                    holder.setText(R.id.tv_user_name,s.getReal_name());
                }

                holder.setText(R.id.tv_publish_time, TimeUtil.formatData(TimeUtil.dateFormatMDofChinese,s.getCreate_atX()));


                TagFlowLayout picFlowLayout = holder.getView(R.id.fl_pic);
                picFlowLayout.setAdapter(new TagAdapter<String>(s.getAlbum()) {
                    @Override
                    public View getView(FlowLayout parent, int position, String o) {
                        ImageView view = ((ImageView) LayoutInflater.from(getContext()).inflate(R.layout.item_flowlayout_evaluation_pic, null, false));
                        Glide.with(mActivity).load(o).into(view);
                        return view;
                    }
                });


                TagFlowLayout tagFlowLayout = holder.getView(R.id.flow_layout);
                tagFlowLayout.setAdapter(new TagAdapter<String>(s.getLabel()) {
                    @Override
                    public View getView(FlowLayout parent, int position, String o) {
                        TextView tv = (TextView) LayoutInflater.from(mActivity).inflate(R.layout.item_agent_tag, null, false);
                        tv.setText(o);
                        return tv;
                    }
                });


            }


        };
        recyclerView.setAdapter(adapter);

        loadData();

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

    private void loadData() {
        RetrofitHelper.getApi().getHouseEvaluation(house_id,type,room_id)
                .compose(RxScheduler.<HouseEvaluationRespose>defaultScheduler())
                .doOnNext(new FinishLoadConsumer<HouseEvaluationRespose>(refreshLayout,page))
                .subscribe(new BaseObserver<HouseEvaluationRespose>(mActivity) {
                    @Override
                    public void error(Throwable e) {

                    }

                    @Override
                    public void next(HouseEvaluationRespose houseEvaluationRespose) {
                         if (page==1){
                             data.clear();
                         }
                         data.addAll(houseEvaluationRespose.getData().getList());
                         adapter.notifyDataSetChanged();
                    }

                    @Override
                    public void complete() {

                    }
                });
    }

}
