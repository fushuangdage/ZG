package com.example.admin.zgapplication.ui.fragment;


import android.support.v4.app.Fragment;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.example.admin.zgapplication.R;
import com.example.admin.zgapplication.base.BaseSupportFragment;
import com.example.admin.zgapplication.mvp.module.Dami;
import com.example.admin.zgapplication.ui.adapter.ZhyBaseRecycleAdapter.CommonAdapter;
import com.example.admin.zgapplication.ui.adapter.ZhyBaseRecycleAdapter.base.ViewHolder;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * A simple {@link Fragment} subclass.
 */
public class RecommendHouseFragment extends BaseSupportFragment {

    @BindView(R.id.rv_recommend_house)
    RecyclerView recyclerView;

    public List<Dami> list=new ArrayList<>();


    @Override
    protected int setLayout() {
        return R.layout.fragment_recommend_house;
    }

    @Override
    protected void init() {

        for (int i = 0; i < 15; i++) {
            list.add(new Dami());
        }
        CommonAdapter<Dami> adapter = new CommonAdapter<Dami>(getContext(), R.layout.item_recommend_house, list) {
            @Override
            protected void convert(ViewHolder holder, Dami dami, int position) {

            }
        };
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.addItemDecoration(new DividerItemDecoration(getContext(),DividerItemDecoration.VERTICAL));
        recyclerView.setAdapter(adapter);
    }

}
