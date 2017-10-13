package com.example.admin.zgapplication.ui.fragment;


import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.example.admin.zgapplication.R;
import com.example.admin.zgapplication.base.BaseSupportFragment;
import com.example.admin.zgapplication.ui.adapter.ZhyBaseRecycleAdapter.CommonAdapter;
import com.example.admin.zgapplication.ui.adapter.ZhyBaseRecycleAdapter.base.ViewHolder;

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

    private ArrayList<String> strings;
    private PopupWindow region_panel;
    private PopupWindow rent_panel;
    private PopupWindow sort_panel;
    private PopupWindow filter_panel;
    private CommonAdapter<String> adapter;

    @Override
    protected int setLayout() {
        return R.layout.fragment_home_find_house;
    }

    @Override
    protected void init() {

        strings = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            strings.add("第几条 "+i);
        }
        initRegionPanel();

        initRentPanel();

        initSortPanel();

        initFilterPanel();

        for (int i = 0; i < 10; i++) {
            strings.add("");
        }
        adapter = new CommonAdapter<String>(getActivity(), R.layout.item_recommend_house, strings) {
            @Override
            protected void convert(ViewHolder holder, String o, int position) {

            }
        };
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.setAdapter(adapter);


    }

    private void initFilterPanel() {
        View rent_pick_panel = LayoutInflater.from(getActivity()).inflate(R.layout.filter_pick_panel, null, false);
        filter_panel = new PopupWindow(rent_pick_panel, ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT, true);
        filter_panel.getContentView().measure(View.MeasureSpec.EXACTLY, View.MeasureSpec.UNSPECIFIED);

    }

    private void initRentPanel() {
        View rent_pick_panel = LayoutInflater.from(getActivity()).inflate(R.layout.rent_pick_panel, null, false);
        rent_panel = new PopupWindow(rent_pick_panel, ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT, true);
        rent_panel.getContentView().measure(View.MeasureSpec.EXACTLY, View.MeasureSpec.UNSPECIFIED);

    }

    private void initSortPanel() {
        View sort_pick_panel = LayoutInflater.from(getActivity()).inflate(R.layout.sort_pick_panel, null, false);
        sort_panel = new PopupWindow(sort_pick_panel, ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT, true);
        sort_panel.getContentView().measure(View.MeasureSpec.EXACTLY, View.MeasureSpec.UNSPECIFIED);
    }

    private void initRegionPanel() {
        View region_pick_panel = LayoutInflater.from(getActivity()).inflate(R.layout.region_pick_panel, null, false);
        region_panel = new PopupWindow(region_pick_panel, ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT, true);
        region_panel.getContentView().measure(View.MeasureSpec.EXACTLY, View.MeasureSpec.EXACTLY);

        RecyclerView rv_region1 = (RecyclerView) region_pick_panel.findViewById(R.id.region1);
        RecyclerView rv_region2 = (RecyclerView) region_pick_panel.findViewById(R.id.region2);
        RecyclerView rv_region3 = (RecyclerView) region_pick_panel.findViewById(R.id.region3);

        rv_region1.setLayoutManager(new LinearLayoutManager(getActivity(),LinearLayoutManager.VERTICAL,false));
        rv_region2.setLayoutManager(new LinearLayoutManager(getActivity(),LinearLayoutManager.VERTICAL,false));
        rv_region3.setLayoutManager(new LinearLayoutManager(getActivity(),LinearLayoutManager.VERTICAL,false));

        rv_region1.setAdapter(new CommonAdapter<String>(getActivity(),R.layout.item_region_pick_panel,strings) {

            @Override
            protected void convert(ViewHolder holder, String s, int position) {
                ((TextView) holder.getView(R.id.region_name)).setText(s);
            }
        });
        rv_region2.setAdapter(new CommonAdapter<String>(getActivity(),R.layout.item_region_pick_panel,strings) {

            @Override
            protected void convert(ViewHolder holder, String s, int position) {
                ((TextView) holder.getView(R.id.region_name)).setText(s);
            }
        });
        rv_region3.setAdapter(new CommonAdapter<String>(getActivity(),R.layout.item_region_pick_panel,strings) {

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
