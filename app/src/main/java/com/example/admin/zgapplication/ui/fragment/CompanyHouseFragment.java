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
import com.example.admin.zgapplication.Constant;
import com.example.admin.zgapplication.R;
import com.example.admin.zgapplication.base.BaseSupportFragment;
import com.example.admin.zgapplication.mvp.module.CompanyHouseListResponse;
import com.example.admin.zgapplication.mvp.module.RegionResponse;
import com.example.admin.zgapplication.retrofit.RetrofitHelper;
import com.example.admin.zgapplication.retrofit.rx.BaseObserver;
import com.example.admin.zgapplication.retrofit.rx.RxScheduler;
import com.example.admin.zgapplication.ui.activity.HouseDetailActivity;
import com.example.admin.zgapplication.ui.adapter.ZhyBaseRecycleAdapter.CommonAdapter;
import com.example.admin.zgapplication.ui.adapter.ZhyBaseRecycleAdapter.MultiItemTypeAdapter;
import com.example.admin.zgapplication.ui.adapter.ZhyBaseRecycleAdapter.base.ViewHolder;
import com.example.admin.zgapplication.ui.view.BidirectionalSeekBar;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * A simple {@link Fragment} subclass.
 */
public class CompanyHouseFragment extends BaseSupportFragment implements MultiItemTypeAdapter.OnItemClickListener {

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

    private ArrayList<CompanyHouseListResponse.DataBean.ListBean> list = new ArrayList<>();
    private ArrayList<String> arrayList = new ArrayList<>();
    private PopupWindow region_panel;
    private PopupWindow rent_panel;
    private PopupWindow sort_panel;
    private PopupWindow filter_panel;
    private CommonAdapter<CompanyHouseListResponse.DataBean.ListBean> adapter;
    private int company_id;

    private ArrayList<RegionResponse.BaseRegion> list1 = new ArrayList<>();
    private ArrayList<RegionResponse.BaseRegion> list2 = new ArrayList<>();

    private Integer leftProgress;
    private Integer rightProgress;
    private String order;
    private String sort;
    private String param_house_config;
    private String param_rent_type;
    private String param_room_num;
    private String param_house_type;
    private Integer areaid;
    private Integer page = 1;
    private HashMap<String, Integer> configMap;
    private CommonAdapter<RegionResponse.BaseRegion> regionListAdapter1;
    private CommonAdapter<RegionResponse.BaseRegion> regionListAdapter2;


    @Override
    protected int setLayout() {
        return R.layout.fragment_company_house;
    }

    @Override
    protected void init() {


        initConfigMap();

        initRegionPanel();

        initRentPanel();

        initSortPanel();

        initFilterPanel();

        company_id = getActivity().getIntent().getIntExtra("company_id", 0);

        adapter = new CommonAdapter<CompanyHouseListResponse.DataBean.ListBean>(getActivity(), R.layout.item_recommend_house, list) {
            @Override
            protected void convert(ViewHolder holder, CompanyHouseListResponse.DataBean.ListBean bean, int position) {
                Glide.with(mActivity).load(bean.getHouse_photo()).into((ImageView) holder.getView(R.id.iv_house));
                ((TextView) holder.getView(R.id.tv_house_name)).setText(bean.getHouse_title());
                ((TextView) holder.getView(R.id.tv_house_location)).setText(bean.getHouse_address());
                ((TextView) holder.getView(R.id.tv_house_info)).setText(bean.getHouse_info());
                ((TextView) holder.getView(R.id.tv_house_rent)).setText(bean.getHouse_rental() + "元/月");
                LinearLayout ll_tag_container = holder.getView(R.id.ll_tag_container);
                showThreeTag(bean, ll_tag_container);

            }
        };
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        adapter.setOnItemClickListener(new MultiItemTypeAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, RecyclerView.ViewHolder holder, int position) {
                CompanyHouseListResponse.DataBean.ListBean listBean = list.get(position);

                Intent intent = new Intent(getActivity(), HouseDetailActivity.class);
                intent.putExtra("house_id", listBean.getHouse_id());
                intent.putExtra("type", listBean.getType());
                intent.putExtra("room_id", listBean.getHouse_id());
                startActivity(intent);
            }

            @Override
            public boolean onItemLongClick(View view, RecyclerView.ViewHolder holder, int position) {
                return false;
            }
        });
        recyclerView.setAdapter(adapter);


        loadCompanyHouseList();
        loadRegionList();
    }

    private void loadRegionList() {
        RetrofitHelper.getApi().getRegionResponse(Constant.city_id)
                .compose(RxScheduler.<RegionResponse>defaultScheduler())
                .subscribe(new BaseObserver<RegionResponse>(mActivity) {
                    @Override
                    public void error(Throwable e) {

                    }

                    @Override
                    public void next(RegionResponse regionResponse) {
                        list1.clear();
                        list2.clear();
                        List<RegionResponse.DataBean.ListBean> list = regionResponse.getData().getList();
                        list1.addAll(list);
                        list2.addAll(list.get(0).getSub());
                        regionListAdapter1.notifyDataSetChanged();
                        regionListAdapter2.notifyDataSetChanged();
                    }

                    @Override
                    public void complete() {

                    }
                });
    }

    private void loadCompanyHouseList() {
        RetrofitHelper.getApi().getCompanyHouse(company_id, leftProgress, rightProgress,
                param_room_num, param_rent_type, order,
                sort, areaid, null, page,
                param_house_type,param_house_config)
                .compose(RxScheduler.<CompanyHouseListResponse>defaultScheduler())
                .subscribe(new BaseObserver<CompanyHouseListResponse>(mActivity) {
                    @Override
                    public void error(Throwable e) {

                    }

                    @Override
                    public void next(CompanyHouseListResponse companyHouseListResponse) {

                        CompanyHouseListResponse.DataBean data = companyHouseListResponse.getData();
                        if (data.getPage() == 1) {
                            list.clear();
                        }
                        list.addAll(data.getList());

                        adapter.notifyDataSetChanged();

                    }

                    @Override
                    public void complete() {

                    }
                });
    }

    private void showThreeTag(CompanyHouseListResponse.DataBean.ListBean bean, LinearLayout ll_tag_container) {

        for (int i = 0; i < 3 && bean.getHouse_label() != null && bean.getHouse_label().size() > i; i++) {
            TextView childAt = ((TextView) ll_tag_container.getChildAt(i));
            childAt.setVisibility(View.VISIBLE);
            childAt.setText(bean.getHouse_label().get(i));
        }
    }

    public void setChildCheck(LinearLayout linearLayout){
        for (int i = 0; i < linearLayout.getChildCount(); i++) {
            CheckBox childAt = (CheckBox) linearLayout.getChildAt(i);
            childAt.setChecked(false);
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
        rent_pick_panel.findViewById(R.id.tv_reset).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setChildCheck(ll_house_config);
                setChildCheck(ll_house_rent_type);
                setChildCheck(ll_house_source_type);
                setChildCheck(ll_room_num);
            }
        });

        filter_panel = new PopupWindow(rent_pick_panel, ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT, true);
        filter_panel.getContentView().measure(View.MeasureSpec.EXACTLY, View.MeasureSpec.UNSPECIFIED);
        filter_panel.setOnDismissListener(new PopupWindow.OnDismissListener() {
            @Override
            public void onDismiss() {
                param_house_config="";
                param_house_type="";
                param_rent_type="";
                param_room_num="";

                for (int i = 0; i < ll_house_config.getChildCount(); i++) {
                    String ck_string = ((CheckBox) ll_house_config.getChildAt(i)).getText().toString();
                    Integer integer = configMap.get(ck_string);
                    param_house_config=param_house_config+integer+",";
                }
                param_house_config=param_house_config.substring(0,param_house_config.length()-2);


                param_house_type = getRequestParam(ll_house_source_type);
                param_rent_type = getRequestParam(ll_house_rent_type);
                param_room_num = getRequestParam(ll_room_num);

                loadCompanyHouseList();
            }
        });
    }

    private String getRequestParam(LinearLayout ll_house_config) {
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < ll_house_config.getChildCount(); i++) {
            CheckBox childAt = ((CheckBox) ll_house_config.getChildAt(i));
            if (childAt.isChecked()) {
                builder.append(i+',');
            }
        }
        String  s = builder.toString().trim();
        if (s.length()>0){
            s = s.substring(0, s.length()-2);  //从0开始
        }
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
                CompanyHouseFragment.this.leftProgress = leftProgress;
                CompanyHouseFragment.this.rightProgress = rightProgress;
                rent_region.setText(String.format("￥%d - ¥%d",leftProgress,rightProgress));
            }
        });
        rent_panel = new PopupWindow(rent_pick_panel, ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT, true);
        rent_panel.getContentView().measure(View.MeasureSpec.EXACTLY, View.MeasureSpec.UNSPECIFIED);
        rent_panel.setOnDismissListener(new PopupWindow.OnDismissListener() {
            @Override
            public void onDismiss() {
                loadCompanyHouseList();
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
                loadCompanyHouseList();
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
                loadCompanyHouseList();
            }
        });

        RecyclerView rv_region1 = (RecyclerView) region_pick_panel.findViewById(R.id.region1);
        RecyclerView rv_region2 = (RecyclerView) region_pick_panel.findViewById(R.id.region2);

        rv_region1.setLayoutManager(new LinearLayoutManager(getActivity(),LinearLayoutManager.VERTICAL,false));
        rv_region2.setLayoutManager(new LinearLayoutManager(getActivity(),LinearLayoutManager.VERTICAL,false));

        regionListAdapter1 = new CommonAdapter<RegionResponse.BaseRegion>(getActivity(), R.layout.item_region_pick_panel, list1) {

            @Override
            protected void convert(ViewHolder holder, RegionResponse.BaseRegion s, int position) {
                ((TextView) holder.getView(R.id.region_name)).setText(s.getName());
            }
        };
        rv_region1.setAdapter(regionListAdapter1);

        regionListAdapter2 = new CommonAdapter<RegionResponse.BaseRegion>(getActivity(), R.layout.item_region_pick_panel, list2) {
            @Override
            protected void convert(ViewHolder holder, RegionResponse.BaseRegion s, int position) {
                ((TextView) holder.getView(R.id.region_name)).setText(s.getName());
            }
        };

        rv_region2.setAdapter(regionListAdapter2);

        regionListAdapter1.setOnItemClickListener(this);
        regionListAdapter2.setOnItemClickListener(this);
    }

    @OnClick({R.id.ll_region, R.id.ll_rent, R.id.ll_sort, R.id.ll_filter})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.ll_region:
                if (region_panel.isShowing()) {
                    region_panel.dismiss();
                } else {
                    region_panel.showAsDropDown(ll_region);
                }
                break;
            case R.id.ll_rent:
                if (rent_panel.isShowing()) {
                    rent_panel.dismiss();
                } else {
                    rent_panel.showAsDropDown(ll_rent);
                }
                break;

            case R.id.ll_sort:
                if (sort_panel.isShowing()) {
                    sort_panel.dismiss();
                } else {
                    sort_panel.showAsDropDown(ll_sort);
                }
                break;
            case R.id.ll_filter:
                if (filter_panel.isShowing()) {
                    filter_panel.dismiss();
                } else {
                    filter_panel.showAsDropDown(ll_filter);
                }
                break;

        }
    }

    @Override
    public void onItemClick(View view, RecyclerView.ViewHolder holder, int position) {
        switch (((View) view.getParent()).getId()) {
            case R.id.region1:
                list2.clear();
                list2.addAll(((RegionResponse.DataBean.ListBean) list1.get(position)).getSub());
                regionListAdapter2.notifyDataSetChanged();
                break;
            case R.id.region2:
                RegionResponse.BaseRegion baseRegion = list2.get(position);
                areaid = baseRegion.getId();
                region_panel.dismiss();
                break;
        }
    }

    @Override
    public boolean onItemLongClick(View view, RecyclerView.ViewHolder holder, int position) {
        return false;
    }

    private void initConfigMap() {

        configMap = new HashMap<>();
        configMap.put("床",1);
        configMap.put("wifi",2);
        configMap.put("空调",3);
        configMap.put("书桌",4);
        configMap.put("卫生间",5);
        configMap.put("衣柜",6);
        configMap.put("沙发",7);
        configMap.put("电视",8);
        configMap.put("洗衣机",9);
        configMap.put("冰箱",10);
        configMap.put("热水器",11);
        configMap.put("电磁炉",12);
        configMap.put("微波炉",13);
        configMap.put("燃气灶",14);
        configMap.put("油烟机",15);
        configMap.put("阳台",16);

    }

}
