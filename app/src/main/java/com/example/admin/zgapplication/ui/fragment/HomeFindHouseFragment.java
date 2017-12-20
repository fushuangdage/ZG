package com.example.admin.zgapplication.ui.fragment;


import android.content.Intent;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.admin.zgapplication.R;
import com.example.admin.zgapplication.base.BaseSupportFragment;
import com.example.admin.zgapplication.mvp.module.HouseResourseListBean;
import com.example.admin.zgapplication.retrofit.RetrofitHelper;
import com.example.admin.zgapplication.retrofit.rx.BaseObserver;
import com.example.admin.zgapplication.retrofit.rx.RxScheduler;
import com.example.admin.zgapplication.ui.activity.HouseDetailActivity;
import com.example.admin.zgapplication.ui.adapter.ZhyBaseRecycleAdapter.CommonAdapter;
import com.example.admin.zgapplication.ui.adapter.ZhyBaseRecycleAdapter.MultiItemTypeAdapter;
import com.example.admin.zgapplication.ui.adapter.ZhyBaseRecycleAdapter.base.ViewHolder;
import com.example.admin.zgapplication.ui.view.BidirectionalSeekBar;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnLoadmoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * A simple {@link Fragment} subclass.
 */
public class HomeFindHouseFragment extends BaseSupportFragment {


    @BindView(R.id.ll_region)
    LinearLayout ll_region;

    @BindView(R.id.ll_rent)
    LinearLayout ll_rent;

    @BindView(R.id.ll_sort)
    LinearLayout ll_sort;

    @BindView(R.id.ll_filter)
    LinearLayout ll_filter;


    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;

    @BindView(R.id.refreshLayout)
    RefreshLayout refreshLayout;

    private ArrayList<HouseResourseListBean.DataBean.ListBean> houseList =new ArrayList<>();
    private ArrayList<String> list=new ArrayList<>();

    private PopupWindow region_panel;
    private PopupWindow rent_panel;
    private PopupWindow sort_panel;
    private PopupWindow filter_panel;
    private CommonAdapter<HouseResourseListBean.DataBean.ListBean> adapter;
    private Integer leftProgress;
    private Integer rightProgress;
    private String order;
    private String sort;
    private String param_house_config;
    private String param_rent_type;
    private String param_room_num;
    private String param_house_type;


    @Override
    protected int setLayout() {
        return R.layout.fragment_home_find_house;
    }

    @Override
    protected void init() {

        for (int i = 0; i < 10; i++) {
            list.add("123456");
        }

        loadFindHouseList();

        initRegionPanel();

        initRentPanel();

        initSortPanel();

        initFilterPanel();

        refreshLayout.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(RefreshLayout refreshlayout) {

                refreshlayout.finishRefresh(2000);
            }
        });
        refreshLayout.setEnableLoadmore(true);
        refreshLayout.setOnLoadmoreListener(new OnLoadmoreListener() {
            @Override
            public void onLoadmore(RefreshLayout refreshlayout) {

                refreshlayout.finishLoadmore(2000);
            }
        });

        adapter = new CommonAdapter<HouseResourseListBean.DataBean.ListBean>(getActivity(), R.layout.item_recommend_house, houseList) {
            @Override
            protected void convert(ViewHolder holder, HouseResourseListBean.DataBean.ListBean bean, int position) {
                Glide.with(mActivity).load(bean.getHouse_photo()).into((ImageView) holder.getView(R.id.iv_house));
                ((TextView) holder.getView(R.id.tv_house_name)).setText(bean.getHouse_title());
                ((TextView) holder.getView(R.id.tv_house_location)).setText(bean.getHouse_address());
                ((TextView) holder.getView(R.id.tv_house_info)).setText(bean.getHouse_info());
                ((TextView) holder.getView(R.id.tv_house_rent)).setText(bean.getHouse_rental()+"元/月");
                LinearLayout ll_tag_container = holder.getView(R.id.ll_tag_container);
                showThreeTag(bean, ll_tag_container);
            }
        };

        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        adapter.setOnItemClickListener(new MultiItemTypeAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, RecyclerView.ViewHolder holder, int position) {
                startActivity(new Intent(getActivity(), HouseDetailActivity.class));
            }

            @Override
            public boolean onItemLongClick(View view, RecyclerView.ViewHolder holder, int position) {
                return false;
            }
        });
        recyclerView.setAdapter(adapter);


    }

    private void loadFindHouseList() {
        RetrofitHelper.getApiWithUid().getApartmentList(leftProgress,rightProgress,param_room_num,param_rent_type,order,sort,null,null,null,param_house_type)
                .compose(RxScheduler.<HouseResourseListBean>defaultScheduler())
                .subscribe(new BaseObserver<HouseResourseListBean>(mActivity,mActivity.getClass().getName()) {

                    @Override
                    public void error(Throwable e) {

                    }

                    @Override
                    public void next(HouseResourseListBean houseResourseListBean) {
                        houseList.clear();
                        houseList.addAll(houseResourseListBean.getData().getList());
                        adapter.notifyDataSetChanged();
                    }

                    @Override
                    public void complete() {

                    }
                });
    }

    private void  showThreeTag(HouseResourseListBean.DataBean.ListBean bean, LinearLayout ll_tag_container) {
        for (int i = 0; i < 3&&bean.getHouse_label()!=null&&bean.getHouse_label().size()>i; i++) {
            TextView childAt = ((TextView) ll_tag_container.getChildAt(i));
            childAt.setVisibility(View.VISIBLE);
            childAt.setText(bean.getHouse_label().get(i));
        }
    }

    private void initFilterPanel() {
        View rent_pick_panel = LayoutInflater.from(getActivity()).inflate(R.layout.filter_pick_panel, null, false);
        final LinearLayout ll_house_config = (LinearLayout) rent_pick_panel.findViewById(R.id.ll_house_config);
        final LinearLayout ll_house_rent_type = (LinearLayout) rent_pick_panel.findViewById(R.id.ll_house_rent_type);
        final LinearLayout ll_house_source_type = (LinearLayout) rent_pick_panel.findViewById(R.id.ll_house_source_type);
        final LinearLayout ll_room_num = (LinearLayout) rent_pick_panel.findViewById(R.id.ll_room_num);
        rent_pick_panel.findViewById(R.id.tv_confirm).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                filter_panel.dismiss();
            }
        });

        filter_panel = new PopupWindow(rent_pick_panel, ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT, true);
        filter_panel.getContentView().measure(View.MeasureSpec.EXACTLY, View.MeasureSpec.UNSPECIFIED);
        filter_panel.setOnDismissListener(new PopupWindow.OnDismissListener() {
            @Override
            public void onDismiss() {
                param_house_config = getRequstParam(ll_house_config);
                param_house_type = getRequstParam(ll_house_source_type);
                param_rent_type = getRequstParam(ll_house_rent_type);
                param_room_num = getRequstParam(ll_room_num);

                loadFindHouseList();
            }
        });
    }

    private String getRequstParam(LinearLayout ll_house_config) {
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < ll_house_config.getChildCount(); i++) {
            CheckBox childAt = ((CheckBox) ll_house_config.getChildAt(i));
            if (childAt.isChecked()) {
                builder.append(i+',');
            }
        }
        String  s = builder.toString();
        s = s.substring(0, s.length()-2);
        return s;
    }

    private void initRentPanel() {
        View rent_pick_panel = LayoutInflater.from(getActivity()).inflate(R.layout.rent_pick_panel, null, false);
        final BidirectionalSeekBar bidirectionalSeekBar = (BidirectionalSeekBar) rent_pick_panel.findViewById(R.id.bidirectionalSeekBar);
        final TextView rent_region = (TextView) rent_pick_panel.findViewById(R.id.tv_text_show);
        rent_pick_panel.findViewById(R.id.tv_rent_reload).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                bidirectionalSeekBar.dataReLoad();
            }
        });
        rent_pick_panel.findViewById(R.id.tv_rent_confirm).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                rent_panel.dismiss();
            }
        });

        bidirectionalSeekBar.setOnSeekBarChangeListener(new BidirectionalSeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(int leftProgress, int rightProgress) {
                HomeFindHouseFragment.this.leftProgress = leftProgress;
                HomeFindHouseFragment.this.rightProgress = rightProgress;
                rent_region.setText(String.format("￥%d - ¥%d",leftProgress,rightProgress));
            }
        });
        rent_panel = new PopupWindow(rent_pick_panel, ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT, true);
        rent_panel.getContentView().measure(View.MeasureSpec.EXACTLY, View.MeasureSpec.UNSPECIFIED);
        rent_panel.setOnDismissListener(new PopupWindow.OnDismissListener() {
            @Override
            public void onDismiss() {
                loadFindHouseList();
            }
        });
    }

    private void initSortPanel() {
        final View sort_pick_panel = LayoutInflater.from(getActivity()).inflate(R.layout.sort_pick_panel, null, false);

        sort_pick_panel.findViewById(R.id.tv_unlimited).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                order=null;
                sort=null;
               sort_panel.dismiss();
            }
        });

        sort_pick_panel.findViewById(R.id.tv_rent_asc).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                order="rental";
                sort="ASC";
                sort_panel.dismiss();
            }
        });

        sort_pick_panel.findViewById(R.id.tv_rent_desc).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                order="rental";
                sort="DESC";
                sort_panel.dismiss();
            }
        });


        sort_pick_panel.findViewById(R.id.tv_area_asc).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                order="space_area";
                sort="ASC";
                sort_panel.dismiss();
            }
        });

        sort_pick_panel.findViewById(R.id.tv_area_desc).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                order="space_area";
                sort="DESC";
                sort_panel.dismiss();
            }
        });

        sort_panel = new PopupWindow(sort_pick_panel, ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT, true);
        sort_panel.getContentView().measure(View.MeasureSpec.EXACTLY, View.MeasureSpec.UNSPECIFIED);
        sort_panel.setOnDismissListener(new PopupWindow.OnDismissListener() {
            @Override
            public void onDismiss() {
                loadFindHouseList();
            }
        });

    }

    private void initRegionPanel() {
        View region_pick_panel = LayoutInflater.from(getActivity()).inflate(R.layout.region_pick_panel, null, false);
        region_panel = new PopupWindow(region_pick_panel, ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT, true);
        region_panel.getContentView().measure(View.MeasureSpec.EXACTLY, View.MeasureSpec.EXACTLY);
        region_panel.setOnDismissListener(new PopupWindow.OnDismissListener() {
            @Override
            public void onDismiss() {
                loadFindHouseList();
            }
        });

        RecyclerView rv_region1 = (RecyclerView) region_pick_panel.findViewById(R.id.region1);
        RecyclerView rv_region2 = (RecyclerView) region_pick_panel.findViewById(R.id.region2);
        RecyclerView rv_region3 = (RecyclerView) region_pick_panel.findViewById(R.id.region3);

        rv_region1.setLayoutManager(new LinearLayoutManager(getActivity(),LinearLayoutManager.VERTICAL,false));
        rv_region2.setLayoutManager(new LinearLayoutManager(getActivity(),LinearLayoutManager.VERTICAL,false));
        rv_region3.setLayoutManager(new LinearLayoutManager(getActivity(),LinearLayoutManager.VERTICAL,false));

        rv_region1.setAdapter(new CommonAdapter<String>(getActivity(),R.layout.item_region_pick_panel, list) {

            @Override
            protected void convert(ViewHolder holder, String s, int position) {
                ((TextView) holder.getView(R.id.region_name)).setText(s);
            }
        });
        rv_region2.setAdapter(new CommonAdapter<String>(getActivity(),R.layout.item_region_pick_panel, list) {

            @Override
            protected void convert(ViewHolder holder, String s, int position) {
                ((TextView) holder.getView(R.id.region_name)).setText(s);
            }
        });
        rv_region3.setAdapter(new CommonAdapter<String>(getActivity(),R.layout.item_region_pick_panel, list) {

            @Override
            protected void convert(ViewHolder holder, String s, int position) {
                ((TextView) holder.getView(R.id.region_name)).setText(s);
            }
        });

    }

    @OnClick({R.id.ll_region,R.id.ll_rent,R.id.ll_sort,R.id.ll_filter})
    public void onClick(View view){
        switch (view.getId()) {
            case R.id.ll_region:
                if (region_panel.isShowing()){
                    region_panel.dismiss();
                }else {
                    region_panel.showAsDropDown(ll_region);
                }
                break;
            case R.id.ll_rent:
                if(rent_panel.isShowing()){
                    rent_panel.dismiss();
                }else {
                    rent_panel.showAsDropDown(ll_rent);
                }
                break;

            case R.id.ll_sort:
                 if (sort_panel.isShowing()){
                     sort_panel.dismiss();
                 }else {
                     sort_panel.showAsDropDown(ll_sort);
                 }
                break;
            case R.id.ll_filter:
                if(filter_panel.isShowing()){
                    filter_panel.dismiss();
                }else {
                    filter_panel.showAsDropDown(ll_filter);
                }
                break;

        }
    }


}
