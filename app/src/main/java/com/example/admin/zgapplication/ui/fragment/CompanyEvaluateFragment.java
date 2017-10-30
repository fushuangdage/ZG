package com.example.admin.zgapplication.ui.fragment;


import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.example.admin.zgapplication.R;
import com.example.admin.zgapplication.base.BaseSupportFragment;
import com.example.admin.zgapplication.ui.adapter.ZhyBaseRecycleAdapter.CommonAdapter;
import com.example.admin.zgapplication.ui.adapter.ZhyBaseRecycleAdapter.base.ViewHolder;

import java.util.ArrayList;

import butterknife.BindView;

/**
 * A simple {@link Fragment} subclass.
 */
public class CompanyEvaluateFragment extends BaseSupportFragment {

    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;

    @Override
    protected int setLayout() {
        return R.layout.fragment_company_evaluate;
    }

    @Override
    protected void init() {
        ArrayList<String> strings = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            strings.add("");
        }
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setAdapter(new CommonAdapter<String>(getContext(),R.layout.item_evaluate,strings) {

            @Override
            protected void convert(ViewHolder holder, String s, int position) {

            }
        });
    }
}
