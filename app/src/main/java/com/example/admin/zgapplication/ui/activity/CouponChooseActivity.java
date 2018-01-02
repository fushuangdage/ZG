package com.example.admin.zgapplication.ui.activity;

import android.content.Intent;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import com.example.admin.zgapplication.R;
import com.example.admin.zgapplication.base.BaseActivity;
import com.example.admin.zgapplication.mvp.module.DiscountListResponse;
import com.example.admin.zgapplication.retrofit.RetrofitHelper;
import com.example.admin.zgapplication.retrofit.rx.BaseObserver;
import com.example.admin.zgapplication.retrofit.rx.RxScheduler;
import com.example.admin.zgapplication.ui.adapter.ZhyBaseRecycleAdapter.CommonAdapter;
import com.example.admin.zgapplication.ui.adapter.ZhyBaseRecycleAdapter.MultiItemTypeAdapter;
import com.example.admin.zgapplication.ui.adapter.ZhyBaseRecycleAdapter.base.ViewHolder;
import com.example.admin.zgapplication.ui.adapter.ZhyBaseRecycleAdapter.wrapper.HeaderAndFooterWrapper;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;


public class CouponChooseActivity extends BaseActivity implements View.OnClickListener {

    @BindView(R.id.tv_title)
    TextView tv_title;
    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;
    public int currentPage=1;
    private CommonAdapter<DiscountListResponse.DataBean.ListBean> adapter;
    private HeaderAndFooterWrapper wrapper;
    private ArrayList<DiscountListResponse.DataBean.ListBean> data=new ArrayList<>();

    @Override
    public int setLayout() {
        return R.layout.activity_coupon_choose;
    }

    @Override
    public void initEvent() {
        final String title = getIntent().getStringExtra("title");
        tv_title.setText(title);

        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter = new CommonAdapter<DiscountListResponse.DataBean.ListBean>(this, R.layout.item_coupon, data) {
            @Override
            protected void convert(ViewHolder holder, DiscountListResponse.DataBean.ListBean s, int position) {
                if (title.equals("选择经纪公司优惠券")) {
                    holder.itemView.setBackgroundResource(R.drawable.jingjigongsiweishiyong);
                }else {
                    holder.itemView.setBackgroundResource(R.drawable.zhagenweishiyong);
                }


            }
        };

        wrapper = new HeaderAndFooterWrapper(adapter);
        View head = LayoutInflater.from(this).inflate(R.layout.item_coupon_head, null, false);
        head.setOnClickListener(this);
        wrapper.addHeaderView(head);
        adapter.setOnItemClickListener(new MultiItemTypeAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, RecyclerView.ViewHolder holder, int position) {
                Intent intent = new Intent();
                intent.putExtra("user_coupon_id",data.get(position).getId());
                intent.putExtra("user_coupon_money",data.get(position).getMoney());

                setResult(1,intent);
                finish();
            }

            @Override
            public boolean onItemLongClick(View view, RecyclerView.ViewHolder holder, int position) {
                return false;
            }
        });
        recyclerView.setAdapter(wrapper);
    }

    @Override
    public void initData() {
        RetrofitHelper.getApiWithUid().getDiscountList(1,currentPage)
                .compose(RxScheduler.<DiscountListResponse>defaultScheduler())
                .subscribe(new BaseObserver<DiscountListResponse>(mActivity) {
                    @Override
                    public void error(Throwable e) {

                    }

                    @Override
                    public void next(DiscountListResponse discountListResponse) {
                        List<DiscountListResponse.DataBean.ListBean> list = discountListResponse.getData().getList();
                        data.clear();
                        data.addAll(list);
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
            case R.id.choose_coupon_head:
                finish();
                break;
        }
    }
}
