package com.example.admin.zgapplication.ui.fragment;


import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;

import com.example.admin.zgapplication.R;
import com.example.admin.zgapplication.base.BaseSupportFragment;
import com.example.admin.zgapplication.ui.adapter.ZhyBaseRecycleAdapter.CommonAdapter;
import com.example.admin.zgapplication.ui.adapter.ZhyBaseRecycleAdapter.base.ViewHolder;
import com.zhy.view.flowlayout.FlowLayout;
import com.zhy.view.flowlayout.TagAdapter;
import com.zhy.view.flowlayout.TagFlowLayout;

import java.util.ArrayList;

import butterknife.BindView;

/**
 * A simple {@link Fragment} subclass.
 */
public class HouseEvaluteFragment extends BaseSupportFragment {


    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;

    @Override
    protected int setLayout() {
        return R.layout.fragment_house_evalute;
    }

    @Override
    protected void init() {
        final ArrayList<String> strings = new ArrayList<>();
        for (int i = 0; i < 7; i++) {
            strings.add("");
        }

        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setAdapter(new CommonAdapter<String>(getContext(),R.layout.item_evaluate,strings) {

            @Override
            protected void convert(ViewHolder holder, String s, int position) {
                TagFlowLayout flowLayout = (TagFlowLayout) holder.getView(R.id.fl_pic);
                flowLayout.setAdapter(new TagAdapter<String>(strings) {
                    @Override
                    public View getView(FlowLayout parent, int position, String o) {
                        View view = LayoutInflater.from(getContext()).inflate(R.layout.item_flowlayout_evaluation_pic, parent, false);
                        return view;
                    }
                });
            }
        });


    }

}
