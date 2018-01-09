package com.example.admin.zgapplication.ui.activity;

import android.content.Intent;
import android.support.design.widget.TabLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.admin.zgapplication.R;
import com.example.admin.zgapplication.base.BaseActivity;
import com.example.admin.zgapplication.mvp.module.TakeLookListResponse;
import com.example.admin.zgapplication.retrofit.RetrofitHelper;
import com.example.admin.zgapplication.retrofit.rx.BaseObserver;
import com.example.admin.zgapplication.retrofit.rx.FinishLoadConsumer;
import com.example.admin.zgapplication.retrofit.rx.RxScheduler;
import com.example.admin.zgapplication.ui.adapter.ZhyBaseRecycleAdapter.CommonAdapter;
import com.example.admin.zgapplication.ui.adapter.ZhyBaseRecycleAdapter.MultiItemTypeAdapter;
import com.example.admin.zgapplication.ui.adapter.ZhyBaseRecycleAdapter.base.ViewHolder;
import com.example.admin.zgapplication.utils.date.TimeUtil;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnLoadmoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;

import java.util.ArrayList;

import butterknife.BindView;

public class TakeLookListActivity extends BaseActivity {

    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;

    @BindView(R.id.refreshLayout)
    RefreshLayout refreshLayout;

    @BindView(R.id.tabLayout)
    TabLayout tabLayout;
    @BindView(R.id.tv_title)
    TextView tv_title;

    private ArrayList<TakeLookListResponse.DataBean.ListBean> data = new ArrayList<>();
    public Integer currentPager = 1;
    public Integer status = 1;
    private CommonAdapter<TakeLookListResponse.DataBean.ListBean> adapter;

    @Override
    public int setLayout() {
        return R.layout.activity_take_look_list;
    }

    @Override
    public void initEvent() {

        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                status = tab.getPosition();
                loadListData();
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });

        refreshLayout.setOnRefreshListener(new OnRefreshListener() {

            @Override
            public void onRefresh(RefreshLayout refreshlayout) {
                currentPager = 1;
                loadListData();
            }
        });

        refreshLayout.setOnLoadmoreListener(new OnLoadmoreListener() {
            @Override
            public void onLoadmore(RefreshLayout refreshlayout) {
                currentPager++;
            }
        });

        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        //                ((TextView) holder.getView(R.id.tv_friend_pay)).setText("取消带看");
        adapter = new CommonAdapter<TakeLookListResponse.DataBean.ListBean>(this, R.layout.item_order_list, data) {
            @Override
            protected void convert(ViewHolder holder, TakeLookListResponse.DataBean.ListBean bean, int position) {

                View view = holder.getView(R.id.rl_my_evaluation_item_show);
                view.setVisibility(View.VISIBLE);
                switch (bean.getStatus()) {
                    case "1":
                        ((TextView) holder.getView(R.id.tv_go_pay)).setText("确认带看");
                        holder.setText(R.id.tv_state,"租客待确认");
                        ((TextView) holder.getView(R.id.tv_go_pay)).setBackgroundResource(R.drawable.green_border_bg);
                        ((TextView) holder.getView(R.id.tv_go_pay)).setTextColor(getResources().getColor(R.color.green));
                        break;
                    case "2":
                        ((TextView) holder.getView(R.id.tv_go_pay)).setText("联系大秘");
                        holder.setText(R.id.tv_state,"大秘待确认");
                        ((TextView) holder.getView(R.id.tv_go_pay)).setBackgroundResource(R.drawable.green_border_bg);
                        ((TextView) holder.getView(R.id.tv_go_pay)).setTextColor(getResources().getColor(R.color.green));
                        break;
                    case "3":
                        ((TextView) holder.getView(R.id.tv_go_pay)).setText("联系大秘");
                        holder.setText(R.id.tv_state,"预约中");
                        ((TextView) holder.getView(R.id.tv_go_pay)).setBackgroundResource(R.drawable.green_border_bg);
                        ((TextView) holder.getView(R.id.tv_go_pay)).setTextColor(getResources().getColor(R.color.green));
                        break;
                    case "4":
                        ((TextView) holder.getView(R.id.tv_go_pay)).setText("去评价");
                        holder.setText(R.id.tv_state,"已完成");
                        ((TextView) holder.getView(R.id.tv_go_pay)).setBackgroundResource(R.drawable.green_border_bg);
                        ((TextView) holder.getView(R.id.tv_go_pay)).setTextColor(getResources().getColor(R.color.green));
                        break;
                    case "5":
                        holder.setText(R.id.tv_state,"已取消");
                        ((TextView) holder.getView(R.id.tv_go_pay)).setText("删除");
                        ((TextView) holder.getView(R.id.tv_go_pay)).setBackgroundResource(R.drawable.gray_rect_boder_no_radius);
                        ((TextView) holder.getView(R.id.tv_go_pay)).setTextColor(getResources().getColor(R.color.gray666));
                        break;
                    default:
                        break;
                }
                holder.setText(R.id.tv_user_tag, TimeUtil.formatData(TimeUtil.dateFormatYMDHM,bean.getExpect_time()));
                Glide.with(mActivity).load(bean.getAvatar()).into((ImageView) holder.getView(R.id.iv_userhead));
                holder.setText(R.id.tv_user_name,bean.getUsername());
                holder.setText(R.id.tv_company,bean.getCompany_name());


                Glide.with(mActivity).load(bean.getHouse_photo()).into((ImageView) holder.getView(R.id.iv_house));
                ((TextView) holder.getView(R.id.tv_house_name)).setText(bean.getHouse_title());
                ((TextView) holder.getView(R.id.tv_house_location)).setText(bean.getHouse_address());
                ((TextView) holder.getView(R.id.tv_house_info)).setText(bean.getHouse_info());
                ((TextView) holder.getView(R.id.tv_house_rent)).setText(bean.getHouse_rental() + "元/月");
                LinearLayout ll_tag_container = holder.getView(R.id.ll_tag_container);
                showThreeTag(bean, ll_tag_container);


//                ((TextView) holder.getView(R.id.tv_friend_pay)).setText("取消带看");

            }
        };
        adapter.setOnItemClickListener(new MultiItemTypeAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, RecyclerView.ViewHolder holder, int position) {
                Intent intent = new Intent(mActivity, TakeLookProgressActivity.class);
                intent.putExtra("id",data.get(position).getId());
                startActivity(intent);
            }

            @Override
            public boolean onItemLongClick(View view, RecyclerView.ViewHolder holder, int position) {
                return false;
            }
        });
        recyclerView.setAdapter(adapter);
        tv_title.setText("带看记录");
    }

    private void showThreeTag(TakeLookListResponse.DataBean.ListBean bean, LinearLayout ll_tag_container) {
        for (int i = 0; i < 3 && bean.getHouse_label() != null && bean.getHouse_label().size() > i; i++) {
            TextView childAt = ((TextView) ll_tag_container.getChildAt(i));
            childAt.setVisibility(View.VISIBLE);
            childAt.setText(bean.getHouse_label().get(i));
        }
    }

    private void loadListData() {
        RetrofitHelper.getApiWithUid().getTakeLookList(currentPager, status)
                .compose(RxScheduler.<TakeLookListResponse>defaultScheduler())
                .doOnNext(new FinishLoadConsumer<TakeLookListResponse>(refreshLayout, currentPager))
                .subscribe(new BaseObserver<TakeLookListResponse>(mActivity) {
                    @Override
                    public void error(Throwable e) {

                    }

                    @Override
                    public void next(TakeLookListResponse takeLookListResponse) {
                        data.clear();
                        data.addAll(takeLookListResponse.getData().getList());
                        adapter.notifyDataSetChanged();
                    }

                    @Override
                    public void complete() {

                    }
                });
    }

    @Override
    public void initData() {
        loadListData();

    }
}
