package com.example.admin.zgapplication.ui.activity;

import android.content.Intent;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.admin.zgapplication.R;
import com.example.admin.zgapplication.base.BaseActivity;
import com.example.admin.zgapplication.mvp.module.BaseResponse;
import com.example.admin.zgapplication.mvp.module.CollectionListResponse;
import com.example.admin.zgapplication.retrofit.RetrofitHelper;
import com.example.admin.zgapplication.retrofit.rx.BaseObserver;
import com.example.admin.zgapplication.retrofit.rx.FinishLoadConsumer;
import com.example.admin.zgapplication.retrofit.rx.RxScheduler;
import com.example.admin.zgapplication.ui.adapter.ZhyBaseRecycleAdapter.CommonAdapter;
import com.example.admin.zgapplication.ui.adapter.ZhyBaseRecycleAdapter.MultiItemTypeAdapter;
import com.example.admin.zgapplication.ui.adapter.ZhyBaseRecycleAdapter.base.ViewHolder;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnLoadmoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.OnClick;

public class ShopCarActivity extends BaseActivity implements MultiItemTypeAdapter.OnItemClickListener {

    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;

    @BindView(R.id.tv_title)
    TextView tv_title;


    @BindView(R.id.refreshLayout)
    RefreshLayout refreshLayout;


    public Integer page = 1;

    private CommonAdapter<CollectionListResponse.DataBean.ListBean> adapter;
    private ArrayList<CollectionListResponse.DataBean.ListBean> data = new ArrayList<>();

    @Override
    public int setLayout() {
        return R.layout.activity_shop_car;
    }

    @Override
    public void initEvent() {

        tv_title.setText("我的收藏");


        refreshLayout.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(RefreshLayout refreshlayout) {
                page = 1;
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
        adapter = new CommonAdapter<CollectionListResponse.DataBean.ListBean>(this, R.layout.item_shopping_car, data) {
            @Override
            protected void convert(ViewHolder holder, final CollectionListResponse.DataBean.ListBean bean, final int position) {
                Glide.with(mActivity).load(bean.getHouse_photo()).into((ImageView) holder.getView(R.id.iv_house));
                ((TextView) holder.getView(R.id.tv_house_name)).setText(bean.getHouse_title());
                ((TextView) holder.getView(R.id.tv_house_location)).setText(bean.getHouse_address());
                ((TextView) holder.getView(R.id.tv_house_info)).setText(bean.getHouse_info());
                ((TextView) holder.getView(R.id.tv_house_rent)).setText(bean.getHouse_rental() + "元/月");
                LinearLayout ll_tag_container = holder.getView(R.id.ll_tag_container);
                showThreeTag(bean, ll_tag_container);
                holder.setOnClickListener(R.id.iv_del, new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        RetrofitHelper.getApiWithUid().delCollectionRecord(bean.getId())
                                .compose(RxScheduler.<BaseResponse>defaultScheduler())
                                .subscribe(new BaseObserver<BaseResponse>(mActivity) {

                                    @Override
                                    public void error(Throwable e) {

                                    }

                                    @Override
                                    public void next(BaseResponse baseResponse) {
                                        Toast.makeText(ShopCarActivity.this, baseResponse.getMsg(), Toast.LENGTH_SHORT).show();
                                        if (baseResponse.getCode() == 0) {
                                            data.remove(position);
                                            adapter.notifyDataSetChanged();
                                        }
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

    private void showThreeTag(CollectionListResponse.DataBean.ListBean bean, LinearLayout ll_tag_container) {
        for (int i = 0; i < 3 && bean.getHouse_label() != null && bean.getHouse_label().size() > i; i++) {
            TextView childAt = ((TextView) ll_tag_container.getChildAt(i));
            childAt.setVisibility(View.VISIBLE);
            childAt.setText(bean.getHouse_label().get(i));
        }
    }

    @Override
    public void initData() {
        RetrofitHelper.getApiWithUid().getCollectList(page)
                .compose(RxScheduler.<CollectionListResponse>defaultScheduler())
                .doOnNext(new FinishLoadConsumer<CollectionListResponse>(refreshLayout, page))
                .subscribe(new BaseObserver<CollectionListResponse>(mActivity) {
                    @Override
                    public void error(Throwable e) {

                    }

                    @Override
                    public void next(CollectionListResponse collectionListResponse) {
                        if (page==1)
                        data.clear();
                        data.addAll(collectionListResponse.getData().getList());
                        adapter.notifyDataSetChanged();
                    }

                    @Override
                    public void complete() {

                    }
                });
    }

    @OnClick({R.id.iv_left})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.iv_left:
                finish();
                break;
        }
    }

    @Override
    public void onItemClick(View view, RecyclerView.ViewHolder holder, int position) {
        CollectionListResponse.DataBean.ListBean listBean = data.get(position);
        Intent intent = new Intent(mActivity, HouseDetailActivity.class);
        intent.putExtra("house_id", listBean.getHouse_id());
        intent.putExtra("room_id", listBean.getRoom_id());
        intent.putExtra("type", listBean.getType());
        startActivity(intent);
    }

    @Override
    public boolean onItemLongClick(View view, RecyclerView.ViewHolder holder, int position) {
        return false;
    }


}
