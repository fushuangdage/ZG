package com.example.admin.zgapplication.ui.fragment;


import android.content.Intent;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.admin.zgapplication.R;
import com.example.admin.zgapplication.base.BaseSupportFragment;
import com.example.admin.zgapplication.mvp.module.RecommendHouseListResponse;
import com.example.admin.zgapplication.retrofit.RetrofitHelper;
import com.example.admin.zgapplication.retrofit.rx.BaseObserver;
import com.example.admin.zgapplication.retrofit.rx.FinishLoadConsumer;
import com.example.admin.zgapplication.retrofit.rx.RxScheduler;
import com.example.admin.zgapplication.ui.activity.HouseDetailActivity;
import com.example.admin.zgapplication.ui.adapter.ZhyBaseRecycleAdapter.CommonAdapter;
import com.example.admin.zgapplication.ui.adapter.ZhyBaseRecycleAdapter.MultiItemTypeAdapter;
import com.example.admin.zgapplication.ui.adapter.ZhyBaseRecycleAdapter.base.ViewHolder;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnLoadmoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * A simple {@link Fragment} subclass.
 */
public class RecommendHouseFragment extends BaseSupportFragment {

    @BindView(R.id.rv_recommend_house)
    RecyclerView recyclerView;

    @BindView(R.id.refreshLayout)
    RefreshLayout refreshLayout;


    public List<RecommendHouseListResponse.DataBean.ListBean> list = new ArrayList<>();
    private CommonAdapter<RecommendHouseListResponse.DataBean.ListBean> adapter;
    private int currentPage = 1;
    private int iid;


    @Override
    protected int setLayout() {
        return R.layout.fragment_recommend_house;
    }

    @Override
    protected void init() {

        refreshLayout.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(RefreshLayout refreshlayout) {
                currentPage=1;
                loadData();

            }
        });

        refreshLayout.setOnLoadmoreListener(new OnLoadmoreListener() {
            @Override
            public void onLoadmore(RefreshLayout refreshlayout) {
                     currentPage++;
                     loadData();
            }
        });


        iid = getActivity().getIntent().getIntExtra("iid",0);
        adapter = new CommonAdapter<RecommendHouseListResponse.DataBean.ListBean>(getContext(), R.layout.item_recommend_house_white, list) {
            @Override
            protected void convert(ViewHolder holder, RecommendHouseListResponse.DataBean.ListBean bean, int position) {
                Glide.with(mActivity).load(bean.getHouse_photo()).into((ImageView) holder.getView(R.id.iv_house));
                ((TextView) holder.getView(R.id.tv_house_name)).setText(bean.getHouse_title());
                ((TextView) holder.getView(R.id.tv_house_location)).setText(bean.getHouse_address());
                ((TextView) holder.getView(R.id.tv_house_info)).setText(bean.getHouse_info());
                ((TextView) holder.getView(R.id.tv_house_rent)).setText(bean.getHouse_rental() + "元/月");
                LinearLayout ll_tag_container = holder.getView(R.id.ll_tag_container);
                showThreeTag(bean, ll_tag_container);
            }
        };

        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.addItemDecoration(new DividerItemDecoration(getContext(), DividerItemDecoration.VERTICAL));
        recyclerView.setAdapter(adapter);
        adapter.setOnItemClickListener(new MultiItemTypeAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, RecyclerView.ViewHolder holder, int position) {
                RecommendHouseListResponse.DataBean.ListBean listBean = list.get(position);
                Intent intent = new Intent(mActivity, HouseDetailActivity.class);
                intent.putExtra("house_id",listBean.getHouse_id());
                intent.putExtra("type",listBean.getType());
                intent.putExtra("room_id",listBean.getRoom_id());
                startActivity(intent);
            }

            @Override
            public boolean onItemLongClick(View view, RecyclerView.ViewHolder holder, int position) {
                return false;
            }
        });
        loadData();
    }

    private void loadData() {
        RetrofitHelper.getApiWithUid().getRecommendHouse(iid, currentPage)
                .compose(RxScheduler.<RecommendHouseListResponse>defaultScheduler())
                .doOnNext(new FinishLoadConsumer<RecommendHouseListResponse>(refreshLayout,currentPage))
                .subscribe(new BaseObserver<RecommendHouseListResponse>(mActivity) {
                    @Override
                    public void error(Throwable e) {

                    }

                    @Override
                    public void next(RecommendHouseListResponse recommendHouseListResponse) {
                        if (currentPage==1) {
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

    private void showThreeTag(RecommendHouseListResponse.DataBean.ListBean bean, LinearLayout ll_tag_container) {
        for (int i = 0; i < 3 && bean.getHouse_label() != null && bean.getHouse_label().size() > i; i++) {
            TextView childAt = ((TextView) ll_tag_container.getChildAt(i));
            childAt.setVisibility(View.VISIBLE);
            childAt.setText(bean.getHouse_label().get(i));
        }
    }

}
