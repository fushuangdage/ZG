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
import com.example.admin.zgapplication.Constant;
import com.example.admin.zgapplication.R;
import com.example.admin.zgapplication.base.BaseActivity;
import com.example.admin.zgapplication.mvp.module.IntentionDetailResponse;
import com.example.admin.zgapplication.mvp.module.RestartIntentResponse;
import com.example.admin.zgapplication.retrofit.RetrofitHelper;
import com.example.admin.zgapplication.retrofit.rx.RxScheduler;
import com.example.admin.zgapplication.ui.adapter.ZhyBaseRecycleAdapter.CommonAdapter;
import com.example.admin.zgapplication.ui.adapter.ZhyBaseRecycleAdapter.base.ViewHolder;
import com.example.admin.zgapplication.utils.date.TimeUtil;
import com.hyphenate.easeui.EaseConstant;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnLoadmoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;
import com.zhy.view.flowlayout.FlowLayout;
import com.zhy.view.flowlayout.TagAdapter;
import com.zhy.view.flowlayout.TagFlowLayout;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.OnClick;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

import static com.umeng.socialize.utils.ContextUtil.getContext;

public class IntentDetailActivity extends BaseActivity {


    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;
    @BindView(R.id.tv_title)
    TextView tv_title;
    @BindView(R.id.refreshLayout)
    RefreshLayout refreshLayout;
    @BindView(R.id.tv_time)
    TextView tv_time;
    @BindView(R.id.tv_intent_region)
    TextView tv_intent_region;

    @BindView(R.id.tv_crab_count)
    TextView tv_crab_count;


    public int page=1;
    private ArrayList<IntentionDetailResponse.DataBean.ListBean> data;
    private String iid;
    private CommonAdapter<IntentionDetailResponse.DataBean.ListBean> adapter;

    @Override
    public int setLayout() {
        return R.layout.activity_intent_detail;
    }

    @Override
    public void initEvent() {
        data = new ArrayList<>();
        tv_title.setText("意向记录详情");
        iid = getIntent().getStringExtra("iid");
        refreshLayout.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(RefreshLayout refreshlayout) {
                page=1;
                initData();
            }
        });

        refreshLayout.setOnLoadmoreListener(new OnLoadmoreListener() {
            @Override
            public void onLoadmore(RefreshLayout refreshlayout) {
                page++;
                initData();
            }
        });

        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter = new CommonAdapter<IntentionDetailResponse.DataBean.ListBean>(this, R.layout.item_recommend_dami, data) {
            @Override
            protected void convert(ViewHolder holder, final IntentionDetailResponse.DataBean.ListBean bean, int position) {
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
                        bundle.putString(EaseConstant.CHAT_HX_NAME,bean.getHx_username());
                        bundle.putString(EaseConstant.NICK_NAME,bean.getUsername());
                        bundle.putInt(EaseConstant.EXTRA_CHAT_TYPE, EaseConstant.CHATTYPE_SINGLE);

//                        bundle.putString(EaseConstant.STR,str);
//                        bundle.putString(EaseConstant.AGENT_ID,bean.getId());
//                        bundle.putString(EaseConstant.CHAT_HX_NAME,bean.getHx_username());
//                        bundle.putString(EaseConstant.NICK_NAME,bean.getUsername());
//                        bundle.putInt(EaseConstant.EXTRA_CHAT_TYPE, EaseConstant.CHATTYPE_SINGLE);

                        intent.putExtras(bundle);
                        startActivity(intent);
                    }
                });
            }
        };
        recyclerView.setAdapter(adapter);

    }

    @Override
    public void initData() {

        RetrofitHelper.getApiWithUid().getIntentDetail(iid,page)
                .compose(RxScheduler.<IntentionDetailResponse>defaultScheduler())
                .subscribe(new Observer<IntentionDetailResponse>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(IntentionDetailResponse intentionDetailResponse) {
                        IntentionDetailResponse.DataBean bean = intentionDetailResponse.getData();
                        tv_time.setText(TimeUtil.formatData(TimeUtil.dateFormatYMDHM,bean.getCreate_at()));

                        String[] split = bean.getRoom_id().split(",");
                        StringBuilder builder = new StringBuilder();
                        for (String s : split) {
                            builder.append(s+"室");
                        }
                        String rent_method="整/合租";
                        if (bean.getMethod()==2){
                            rent_method="整租";
                        }else if (bean.getMethod()==3){
                            rent_method="合租";
                        }

                        String room_string = builder.toString();
                        tv_intent_region.setText(String.format("意向区域：%s-%s\n意向价格：%s\n出租方式：%s\n意向户型：%s",
                                bean.getParent(),bean.getDistrict(),bean.getPrice(),rent_method,room_string));
                        if (page==1)
                            data.clear();

                        tv_crab_count.setText(bean.getList().size()+"");

                        data.addAll(bean.getList());
                        adapter.notifyDataSetChanged();
                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });


    }

    @OnClick({R.id.tv_restart_intent})
    public void onClick(View view){
        switch (view.getId()) {

            case R.id.tv_restart_intent:
                RetrofitHelper.getApi().restartIntent(Constant.uid,iid)
                        .compose(RxScheduler.<RestartIntentResponse>defaultScheduler())
                        .subscribe(new Observer<RestartIntentResponse>() {
                            @Override
                            public void onSubscribe(Disposable d) {

                            }

                            @Override
                            public void onNext(RestartIntentResponse restartIntentResponse) {
                                if (restartIntentResponse.getCode()==0) {
                                    Intent intent = new Intent(mActivity, WaitCrabActivity.class);
                                    intent.putExtra("iid",restartIntentResponse.getData().getId());
                                    startActivity(intent);
                                }
                            }

                            @Override
                            public void onError(Throwable e) {

                            }

                            @Override
                            public void onComplete() {

                            }
                        });
                break;


        }
    }

}
