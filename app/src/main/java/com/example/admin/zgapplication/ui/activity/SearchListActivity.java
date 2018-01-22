package com.example.admin.zgapplication.ui.activity;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.admin.zgapplication.R;
import com.example.admin.zgapplication.base.BaseActivity;
import com.example.admin.zgapplication.mvp.module.CompanyListResponse;
import com.example.admin.zgapplication.mvp.module.RecommendHouseListResponse;
import com.example.admin.zgapplication.retrofit.RetrofitHelper;
import com.example.admin.zgapplication.retrofit.rx.BaseObserver;
import com.example.admin.zgapplication.retrofit.rx.RxScheduler;
import com.example.admin.zgapplication.ui.adapter.ZhyBaseRecycleAdapter.CommonAdapter;
import com.example.admin.zgapplication.ui.adapter.ZhyBaseRecycleAdapter.base.ViewHolder;
import com.scwang.smartrefresh.layout.api.RefreshLayout;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

public class SearchListActivity extends BaseActivity {


    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;

    @BindView(R.id.refreshLayout)
    RefreshLayout refreshLayout;
    private int type;
    private String keyword;

    @BindView(R.id.tv_title)
    TextView tv_title;
    public int page=1;

    public List<CompanyListResponse.DataBean.ListBean> company_list = new ArrayList<>();
    public List<RecommendHouseListResponse.DataBean.ListBean> house_list = new ArrayList<>();
    private CommonAdapter<CompanyListResponse.DataBean.ListBean> companyAdapter;
    private CommonAdapter<RecommendHouseListResponse.DataBean.ListBean> houseAdapter;

    @Override
    public int setLayout() {
        return R.layout.activity_search_list;
    }

    @Override
    public void initEvent() {


        // 0公司  1房源
        type = getIntent().getIntExtra("type", 0);


        keyword = getIntent().getStringExtra("keyword");
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        if (type == 0) {
            tv_title.setText("公司搜索列表");
            companyAdapter = new CommonAdapter<CompanyListResponse.DataBean.ListBean>(mActivity, R.layout.item_search_company, company_list) {
                @Override
                protected void convert(ViewHolder holder, CompanyListResponse.DataBean.ListBean bean, int position) {


                }
            };
            recyclerView.setAdapter(companyAdapter);
        } else {
            tv_title.setText("房源搜索列表");
            houseAdapter = new CommonAdapter<RecommendHouseListResponse.DataBean.ListBean>(mActivity, R.layout.item_recommend_house_white, house_list) {
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
            recyclerView.setAdapter(houseAdapter);
        }

    }

    private void showThreeTag(RecommendHouseListResponse.DataBean.ListBean bean, LinearLayout ll_tag_container) {
        for (int i = 0; i < 3 && bean.getHouse_label() != null && bean.getHouse_label().size() > i; i++) {
            TextView childAt = ((TextView) ll_tag_container.getChildAt(i));
            childAt.setVisibility(View.VISIBLE);
            childAt.setText(bean.getHouse_label().get(i));
        }
    }

    @Override
    public void initData() {
        if (type == 0) {
            //请求搜索全部公司
            RetrofitHelper.getApi().getSearchMoreCompany(keyword, 0,page)
                    .compose(RxScheduler.<CompanyListResponse>defaultScheduler())
                    .subscribe(new BaseObserver<CompanyListResponse>(mActivity) {
                        @Override
                        public void error(Throwable e) {

                        }

                        @Override
                        public void next(CompanyListResponse companyListResponse) {
                            if (page==1){
                                company_list.clear();
                            }
                            company_list.addAll(companyListResponse.getData().getList());
                            companyAdapter.notifyDataSetChanged();
                        }

                        @Override
                        public void complete() {

                        }
                    });
        } else {
            //请求搜索全部房源

            RetrofitHelper.getApi().getSearchMoreHouse(keyword, 1,page)
                    .compose(RxScheduler.<RecommendHouseListResponse>defaultScheduler())
                    .subscribe(new BaseObserver<RecommendHouseListResponse>(mActivity) {
                        @Override
                        public void error(Throwable e) {

                        }

                        @Override
                        public void next(RecommendHouseListResponse response) {
                            if (page==1){
                                house_list.clear();
                            }
                            house_list.addAll(response.getData().getList());
                            houseAdapter.notifyDataSetChanged();
                        }

                        @Override
                        public void complete() {

                        }
                    });
        }
    }
}
