package com.example.admin.zgapplication.ui.activity;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.admin.zgapplication.R;
import com.example.admin.zgapplication.base.BaseActivity;
import com.example.admin.zgapplication.mvp.module.BaseResponse;
import com.example.admin.zgapplication.mvp.module.RentIntentListResponse;
import com.example.admin.zgapplication.retrofit.RetrofitHelper;
import com.example.admin.zgapplication.retrofit.rx.BaseObserver;
import com.example.admin.zgapplication.retrofit.rx.FinishLoadConsumer;
import com.example.admin.zgapplication.retrofit.rx.RxScheduler;
import com.example.admin.zgapplication.ui.adapter.ZhyBaseRecycleAdapter.CommonAdapter;
import com.example.admin.zgapplication.ui.adapter.ZhyBaseRecycleAdapter.MultiItemTypeAdapter;
import com.example.admin.zgapplication.ui.adapter.ZhyBaseRecycleAdapter.base.ViewHolder;
import com.scwang.smartrefresh.layout.api.RefreshLayout;

import java.util.ArrayList;

import butterknife.BindView;

public class IntentListActivity extends BaseActivity implements MultiItemTypeAdapter.OnItemClickListener {


    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;
    @BindView(R.id.tv_title)
    TextView tv_title;
    @BindView(R.id.refreshLayout)
    RefreshLayout refreshLayout;

    private CommonAdapter<RentIntentListResponse.DataBean.ListBean> adapter;
    private int currentPage=1;
    private ArrayList<RentIntentListResponse.DataBean.ListBean> data= new ArrayList<>();

    @Override
    public int setLayout() {
        return R.layout.activity_intent_list;
    }

    @Override
    public void initEvent() {
        tv_title.setText("意向记录");

        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter = new CommonAdapter<RentIntentListResponse.DataBean.ListBean>(this, R.layout.item_intent_list, data) {
            @Override
            protected void convert(ViewHolder holder, final RentIntentListResponse.DataBean.ListBean o, final int position) {
                ((TextView) holder.getView(R.id.tv_intent_region)).setText(String.format("意向区域：%s-%s",o.getParent(),o.getDistrict()));
                ((TextView) holder.getView(R.id.tv_intent_money)).setText(o.getOutset()
                        .equals("0")&&o.getCutoff()
                        .equals("0")?String.format("意向价格：%s-%s",
                        o.getOutset(),o.getCutoff()):"意向价格：不限");
                ((TextView) holder.getView(R.id.tv_crab_count)).setText(o.getSum());
                holder.setOnClickListener(R.id.tv_del, new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
//                        RetrofitHelper.getApiWithUid()
                        final RentIntentListResponse.DataBean.ListBean remove = data.get(position);

                        RetrofitHelper.getApiWithUid().delIntentItem(remove.getId())
                                .compose(RxScheduler.<BaseResponse>defaultScheduler())
                                .subscribe(new BaseObserver<BaseResponse>(mActivity) {
                                    @Override
                                    public void error(Throwable e) {

                                    }

                                    @Override
                                    public void next(BaseResponse baseResponse) {
                                        Toast.makeText(IntentListActivity.this, baseResponse.getMsg(), Toast.LENGTH_SHORT).show();
                                        data.remove(position);
                                        adapter.notifyItemRemoved(position);
                                    }

                                    @Override
                                    public void complete() {

                                    }
                                });
                    }
                });

            }

        };
        adapter.setOnItemClickListener(this);
        recyclerView.setAdapter(adapter);


    }

    @Override
    public void initData() {
        RetrofitHelper.getApiWithUid().getIntentList(currentPage)
                .compose(RxScheduler.<RentIntentListResponse>defaultScheduler())
                .doOnNext(new FinishLoadConsumer<RentIntentListResponse>(refreshLayout,currentPage))
                .subscribe(new BaseObserver<RentIntentListResponse>(mActivity) {
                    @Override
                    public void error(Throwable e) {

                    }

                    @Override
                    public void next(RentIntentListResponse rentIntentListResponse) {
                        /**
                         * 集中在返回数据时处理上拉加载和刷新的分页问题
                         */
                        if (rentIntentListResponse.getData().getPage()==1){
                            data.clear();
                            data.addAll(rentIntentListResponse.getData().getList());
                        }else {
                            data.addAll(rentIntentListResponse.getData().getList());
                        }
                        adapter.notifyDataSetChanged();
                    }

                    @Override
                    public void complete() {

                    }
                });


    }

    @Override
    public void onItemClick(View view, RecyclerView.ViewHolder holder, int position) {
        startActivity(IntentDetailActivity.class);
    }

    @Override
    public boolean onItemLongClick(View view, RecyclerView.ViewHolder holder, int position) {
        return false;
    }
}
