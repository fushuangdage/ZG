package com.example.admin.zgapplication.ui.activity;

import android.content.Intent;
import android.support.design.widget.TabLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.admin.zgapplication.R;
import com.example.admin.zgapplication.base.BaseActivity;
import com.example.admin.zgapplication.base.EvaluationListResponse;
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

import java.util.ArrayList;

import butterknife.BindView;


public class EvaluationListActivity extends BaseActivity {


    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;
    @BindView(R.id.radio_group)
    RadioGroup radio_group;
    @BindView(R.id.tabLayout)
    TabLayout tabLayout;
    @BindView(R.id.refreshLayout)
    RefreshLayout refreshLayout;

    public int method = 2; //1 成交  2 带看
    private int reviewed;  //0为待评价，1为已评价
    public int page = 1;
    private ArrayList<EvaluationListResponse.DataBean.ListBean> data;
    private CommonAdapter<EvaluationListResponse.DataBean.ListBean> adapter;

    @Override
    public int setLayout() {
        return R.layout.activity_evaluation_list;
    }

    @Override
    public void initEvent() {
        data = new ArrayList<>();

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


        radio_group.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                if (checkedId == R.id.rb_findPerson) {
                    method = 2;
                } else {
                    method = 1;
                }
                initData();
            }
        });

        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                reviewed = tab.getPosition();
                initData();
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });


        recyclerView.setLayoutManager(new LinearLayoutManager(mActivity));
        adapter = new CommonAdapter<EvaluationListResponse.DataBean.ListBean>(mActivity, R.layout.item_order_list, data) {
            @Override
            protected void convert(ViewHolder holder, final EvaluationListResponse.DataBean.ListBean bean, int position) {
                holder.getView(R.id.rl_my_evaluation_item_show).setVisibility(View.VISIBLE);
                holder.getView(R.id.tv_contact_agent).setVisibility(View.VISIBLE);


                Glide.with(mActivity).load(bean.getHouse_photo()).into((ImageView) holder.getView(R.id.iv_house));
                Glide.with(mActivity).load(bean.getAvatar()).into((ImageView) holder.getView(R.id.iv_userhead));


                ((TextView) holder.getView(R.id.tv_user_name)).setText(bean.getAgent());
                ((TextView) holder.getView(R.id.tv_company)).setText(bean.getCompany_name());


                ((TextView) holder.getView(R.id.tv_house_name)).setText(bean.getHouse_title());
                ((TextView) holder.getView(R.id.tv_house_location)).setText(bean.getHouse_address());
                ((TextView) holder.getView(R.id.tv_house_info)).setText(bean.getHouse_info());
                ((TextView) holder.getView(R.id.tv_house_rent)).setText(bean.getRent_money()+"元/月");
                LinearLayout ll_tag_container = holder.getView(R.id.ll_tag_container);
                showThreeTag(bean, ll_tag_container);

                if (method==1){
                    //成交
                    holder.setText(R.id.tv_user_tag,bean.getCompany_name()+" "+bean.getAgent());
                    holder.setText(R.id.tv_total_price,"¥"+bean.getPayment());
                    holder.getView(R.id.rl_pay_info).setVisibility(View.VISIBLE);
                }else {
                    //带看
                    holder.setText(R.id.tv_user_tag, TimeUtil.formatData(TimeUtil.dateFormat,bean.getExpect_time()));
                    holder.getView(R.id.rl_pay_info).setVisibility(View.GONE);
                }

                if (reviewed==0){
                    ((TextView) holder.getView(R.id.tv_go_pay)).setText("去评价");
                }else {
                    ((TextView) holder.getView(R.id.tv_go_pay)).setText("查看评价");
                }
                holder.setOnClickListener(R.id.tv_go_pay, new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent= new Intent(mActivity,MakeEvaluateActivity.class);
                        intent.putExtra("id",bean.getId());
                        intent.putExtra("method",bean.getMethod());
                        intent.putExtra("evaluated",reviewed==1?true:false);
                        startActivity(intent);
                    }
                });

//                ((TextView) holder.getView(R.id.tv_friend_pay)).setText("联系大秘");
            }
        };
        recyclerView.setAdapter(adapter);

    }

    private void showThreeTag(EvaluationListResponse.DataBean.ListBean bean, LinearLayout ll_tag_container) {
                for (int i = 0; i < 3&&bean.getHouse_label()!=null&&bean.getHouse_label().size()>i; i++) {
            TextView childAt = ((TextView) ll_tag_container.getChildAt(i));
            childAt.setVisibility(View.VISIBLE);
            childAt.setText(bean.getHouse_label().get(i));
        }
    }

    @Override
    public void initData() {
        RetrofitHelper.getApiWithUid().getEvaluationList(method, reviewed, page)
                .compose(RxScheduler.<EvaluationListResponse>defaultScheduler())
                .doOnNext(new FinishLoadConsumer<EvaluationListResponse>(refreshLayout, page))
                .subscribe(new BaseObserver<EvaluationListResponse>(mActivity) {
                    @Override
                    public void error(Throwable e) {

                    }

                    @Override
                    public void next(EvaluationListResponse evaluationListResponse) {
                        if (page == 1)
                            data.clear();
                        data.addAll(evaluationListResponse.getData().getList());
                        adapter.notifyDataSetChanged();
                    }

                    @Override
                    public void complete() {

                    }
                });
    }
}
