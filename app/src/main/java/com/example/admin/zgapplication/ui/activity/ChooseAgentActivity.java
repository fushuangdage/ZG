package com.example.admin.zgapplication.ui.activity;

import android.content.Intent;
import android.net.Uri;
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
import com.example.admin.zgapplication.mvp.module.AgentListResponse;
import com.example.admin.zgapplication.mvp.module.SelectOrderInfoBean;
import com.example.admin.zgapplication.retrofit.RetrofitHelper;
import com.example.admin.zgapplication.retrofit.rx.BaseObserver;
import com.example.admin.zgapplication.retrofit.rx.RxScheduler;
import com.example.admin.zgapplication.ui.adapter.ZhyBaseRecycleAdapter.CommonAdapter;
import com.example.admin.zgapplication.ui.adapter.ZhyBaseRecycleAdapter.MultiItemTypeAdapter;
import com.example.admin.zgapplication.ui.adapter.ZhyBaseRecycleAdapter.base.ViewHolder;
import com.example.admin.zgapplication.ui.view.StartBar;
import com.hyphenate.easeui.EaseConstant;
import com.zhy.view.flowlayout.FlowLayout;
import com.zhy.view.flowlayout.TagAdapter;
import com.zhy.view.flowlayout.TagFlowLayout;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

public class ChooseAgentActivity extends BaseActivity {

    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;

    public Integer page;

    public List<AgentListResponse.DataBean.ListBean> data = new ArrayList<>();
    public List<String> tagList = new ArrayList<>();
    private String from;
    private CommonAdapter<AgentListResponse.DataBean.ListBean> adapter;

    @Override
    public int setLayout() {
        return R.layout.activity_choose_agent;
    }

    @Override
    public void initEvent() {
        from = getIntent().getStringExtra("from");
        //代表是从咨询经纪人跳转过来,显示三个按钮
        // TODO: 2017/11/1 跳转到单聊
        adapter = new CommonAdapter<AgentListResponse.DataBean.ListBean>(mActivity, R.layout.item_choose_agent, data) {
            @Override
            protected void convert(ViewHolder holder, final AgentListResponse.DataBean.ListBean bean, int position) {

                holder.setText(R.id.tv_name, bean.getUsername());
                holder.setText(R.id.tv_company, bean.getCompany_name() + " ");
                holder.setText(R.id.tv_house_count_num, bean.getHouse_sum());
                holder.setText(R.id.tv_trade_area, "负责商圈 " + bean.getDistrict() + " ");
                holder.setText(R.id.tv_visit_count, bean.getVisit_sum());
                holder.setText(R.id.tv_deal_count, bean.getOrder_sum());
                Glide.with(mActivity).load(bean.getAvatar())
                        .into((ImageView) holder.getView(R.id.icon));

                ((StartBar) holder.getView(R.id.rating_bar)).setRating((int) bean.getScore());


                TagFlowLayout flowLayout = (TagFlowLayout) holder.getView(R.id.flow_layout);
                if (from != null) {
                    //代表是从咨询经纪人跳转过来,显示三个按钮
                    View view = holder.getView(R.id.ll_chat_agent_show);
                    view.setVisibility(View.VISIBLE);
                    view.findViewById(R.id.iv_take_look).setOnClickListener(new View.OnClickListener() {

                        @Override
                        public void onClick(View v) {
                            Bundle extras = getIntent().getExtras();
                            extras.putString("member_id", bean.getId());  //经纪人id
                            startActivity(TakeLookActivity.class, extras);
                        }
                    });
                    view.findViewById(R.id.iv_chat).setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            // TODO: 2017/11/1 跳转到单聊
                            Intent intent = new Intent(mActivity, ChatActivity.class);
                            Bundle bundle = new Bundle();
                            bundle.putString(EaseConstant.EXTRA_USER_ID,bean.getHx_username());
                            bundle.putString(EaseConstant.NICK_NAME,bean.getUsername());
                            bundle.putString(EaseConstant.HEADIMAGEURL,bean.getAvatar());
                            bundle.putInt(EaseConstant.EXTRA_CHAT_TYPE, EaseConstant.CHATTYPE_SINGLE);
                            intent.putExtras(bundle);
                            startActivity(intent);
                        }
                    });
                    view.findViewById(R.id.iv_tel).setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            Intent intent = new Intent(Intent.ACTION_DIAL, Uri.parse("tel:"+bean.getTelephone()));
                            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                            startActivity(intent);
                        }
                    });

                } else {
                    //选择经纪人,填写订单
                    adapter.setOnItemClickListener(new MultiItemTypeAdapter.OnItemClickListener() {
                        @Override
                        public void onItemClick(View view, RecyclerView.ViewHolder holder, int position) {

                            Intent intent = new Intent(mActivity, OrderDetailActivity.class);
                            SelectOrderInfoBean extra = (SelectOrderInfoBean) getIntent().getSerializableExtra("bean");
                            extra.member_id = data.get(position).getId();
                            intent.putExtra("bean", extra);
                            startActivity(intent);
                        }

                        @Override
                        public boolean onItemLongClick(View view, RecyclerView.ViewHolder holder, int position) {
                            return false;
                        }
                    });
                }
                tagList.clear();
                tagList.addAll(bean.getLabel());
                flowLayout.setAdapter(new TagAdapter<String>(tagList) {
                    @Override
                    public View getView(FlowLayout parent, int position, String o) {
                        TextView view = ((TextView) LayoutInflater.from(mActivity).inflate(R.layout.flow_item, parent, false));
                        view.setText(o);
                        return view;
                    }
                });
            }
        };

        recyclerView.setLayoutManager(new LinearLayoutManager(mActivity));
        recyclerView.setAdapter(adapter);
    }

    @Override
    public void initData() {
        Bundle bundle = getIntent().getExtras();
        String house_id = bundle.getString("house_id");
        String room_id = bundle.getString("room_id");
        String type = bundle.getString("type");
        RetrofitHelper.getApiWithUid().getAgentList(room_id, type, house_id, page)
                .compose(RxScheduler.<AgentListResponse>defaultScheduler())
                .subscribe(new BaseObserver<AgentListResponse>(mActivity) {
                    @Override
                    public void error(Throwable e) {

                    }

                    @Override
                    public void next(AgentListResponse agentListResponse) {
                        List<AgentListResponse.DataBean.ListBean> list = agentListResponse.getData().getList();
                        data.clear();
                        data.addAll(list);
                        adapter.notifyDataSetChanged();
                    }

                    @Override
                    public void complete() {

                    }
                });

    }
}
