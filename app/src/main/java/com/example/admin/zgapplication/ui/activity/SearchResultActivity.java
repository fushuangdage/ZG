package com.example.admin.zgapplication.ui.activity;

import android.content.Intent;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.admin.zgapplication.R;
import com.example.admin.zgapplication.base.BaseActivity;
import com.example.admin.zgapplication.mvp.module.SearchResultResponse;
import com.example.admin.zgapplication.retrofit.RetrofitHelper;
import com.example.admin.zgapplication.retrofit.rx.RxScheduler;
import com.example.admin.zgapplication.ui.adapter.ZhyBaseRecycleAdapter.MultiItemTypeAdapter;
import com.example.admin.zgapplication.ui.adapter.ZhyBaseRecycleAdapter.base.ItemViewDelegate;
import com.example.admin.zgapplication.ui.adapter.ZhyBaseRecycleAdapter.base.ViewHolder;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnLoadmoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

public class SearchResultActivity extends BaseActivity implements MultiItemTypeAdapter.OnItemClickListener {

    private String keyword;
    public Integer page = 1;


    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;
    @BindView(R.id.refreshLayout)
    RefreshLayout refreshLayout;
    @BindView(R.id.tv_title)
    TextView tv_title;
    public List<Object> list = new ArrayList<>();
    private MultiItemTypeAdapter<Object> adapter;


    @Override
    public int setLayout() {
        return R.layout.activity_search_result;
    }

    @Override
    public void initEvent() {

        tv_title.setText("搜索结果");

        refreshLayout.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(RefreshLayout refreshlayout) {
                page = 1;
                loadData();
            }
        });
        refreshLayout.setOnLoadmoreListener(new OnLoadmoreListener() {
            @Override
            public void onLoadmore(RefreshLayout refreshlayout) {
                page++;
                loadData();
            }
        });

        recyclerView.setLayoutManager(new LinearLayoutManager(mActivity));
        adapter = new MultiItemTypeAdapter<>(this, list);
        adapter.addItemViewDelegate(1, new ItemViewDelegate<Object>() {

            @Override
            public int getItemViewLayoutId() {
                return R.layout.item_search_text;
            }

            @Override
            public boolean isForViewType(Object item, int position) {
                if (item instanceof String) {
                    return true;
                }
                return false;
            }

            @Override
            public void convert(ViewHolder holder, Object s, int position) {
                if (((String) s).length() > 4) {
                    holder.getView(R.id.tv_head).setVisibility(View.GONE);
                    holder.getView(R.id.tv_foot).setVisibility(View.VISIBLE);
                    ((TextView) holder.getView(R.id.tv_foot)).setText(((String) s));
                } else {
                    holder.getView(R.id.tv_head).setVisibility(View.VISIBLE);
                    holder.getView(R.id.tv_foot).setVisibility(View.GONE);
                    ((TextView) holder.getView(R.id.tv_head)).setText(((String) s));
                }

            }
        });

        adapter.addItemViewDelegate(2, new ItemViewDelegate<Object>() {

            @Override
            public int getItemViewLayoutId() {
                return R.layout.item_recommend_house_white;
            }

            @Override
            public boolean isForViewType(Object item, int position) {
                if (item instanceof SearchResultResponse.DataBean.ListBean) {
                    return true;
                }
                return false;
            }

            @Override
            public void convert(ViewHolder holder, Object s, int position) {
                SearchResultResponse.DataBean.ListBean bean = (SearchResultResponse.DataBean.ListBean) s;
                Glide.with(mActivity).load(bean.getHouse_photo()).into((ImageView) holder.getView(R.id.iv_house));
                ((TextView) holder.getView(R.id.tv_house_name)).setText(bean.getHouse_title());
                ((TextView) holder.getView(R.id.tv_house_location)).setText(bean.getHouse_address());
                ((TextView) holder.getView(R.id.tv_house_info)).setText(bean.getHouse_info());
                ((TextView) holder.getView(R.id.tv_house_rent)).setText(bean.getHouse_rental() + "元/月");
                LinearLayout ll_tag_container = holder.getView(R.id.ll_tag_container);
                showThreeTag(bean, ll_tag_container);
            }
        });

        adapter.addItemViewDelegate(3, new ItemViewDelegate<Object>() {

            @Override
            public int getItemViewLayoutId() {
                return R.layout.item_search_company;
            }

            @Override
            public boolean isForViewType(Object item, int position) {
                if (item instanceof SearchResultResponse.DataBean.CompanyBean) {
                    return true;
                }
                return false;
            }

            @Override
            public void convert(ViewHolder holder, Object s, int position) {
                SearchResultResponse.DataBean.CompanyBean bean = (SearchResultResponse.DataBean.CompanyBean) s;
                Glide.with(mActivity).load(bean.getLogo()).into((ImageView) holder.getView(R.id.iv_house));
                holder.setText(R.id.tv_company_name, bean.getCompany_name());
                holder.setText(R.id.tv_house_count, bean.getCount_house() + "套");
                holder.setText(R.id.tv_agent_count, bean.getCount_house() + "人");
                holder.setText(R.id.tv_score_count, bean.getCount_house() + "");

            }
        });


        recyclerView.setAdapter(adapter);
        adapter.setOnItemClickListener(this);
    }

    private void showThreeTag(SearchResultResponse.DataBean.ListBean bean, LinearLayout ll_tag_container) {
        for (int i = 0; i < 3 && bean.getHouse_label() != null && bean.getHouse_label().size() > i; i++) {
            TextView childAt = ((TextView) ll_tag_container.getChildAt(i));
            childAt.setVisibility(View.VISIBLE);
            childAt.setText(bean.getHouse_label().get(i));
        }
    }

    @Override
    public void initData() {
        keyword = getIntent().getStringExtra("keyword");
        loadData();
    }

    private void loadData() {
        RetrofitHelper.getApiWithUid().getSearchResult(keyword, page)
                .compose(RxScheduler.<SearchResultResponse>defaultScheduler())
                .subscribe(new Observer<SearchResultResponse>() {

                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(SearchResultResponse searchResultResponse) {
                        list.clear();
                        SearchResultResponse.DataBean data = searchResultResponse.getData();
                        list.add("房源");
                        for (int i = 0; i < Math.min(3, data.getList().size()); i++) {
                            list.add(data.getList().get(i));
                        }
                        list.add("查看更多房源");

                        list.add("经纪公司");
                        for (int i = 0; i < Math.min(3, data.getCompany().size()); i++) {
                            list.add(data.getCompany().get(i));
                        }
                        list.add("查看更多公司");
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

    @Override
    public void onItemClick(View view, RecyclerView.ViewHolder holder, int position) {
        Object o = list.get(position);
        Intent intent;
        if (o instanceof String && ((String) o).length() > 4) {
            intent = new Intent(mActivity, SearchListActivity.class);
            intent.putExtra("type", ((String) o).equals("查看更多房源") ? 1 : 0);
            intent.putExtra("keyword", keyword);
            startActivity(intent);
        } else if (o instanceof SearchResultResponse.DataBean.ListBean) {
            SearchResultResponse.DataBean.ListBean bean = (SearchResultResponse.DataBean.ListBean) o;
            intent = new Intent(mActivity, HouseDetailActivity.class);
            intent.putExtra("house_id", bean.getHouse_id());
            intent.putExtra("type", bean.getType());
            intent.putExtra("room_id", bean.getRoom_id());
            startActivity(intent);
        } else if (o instanceof SearchResultResponse.DataBean.CompanyBean) {
            SearchResultResponse.DataBean.CompanyBean bean = (SearchResultResponse.DataBean.CompanyBean) o;
            intent = new Intent(mActivity, CompanyDetailActivity.class);
            intent.putExtra("company_id",bean.getCompany_id());
            startActivity(intent);
        }
    }

    @Override
    public boolean onItemLongClick(View view, RecyclerView.ViewHolder holder, int position) {
        return false;
    }
}
