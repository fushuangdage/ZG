package com.example.admin.zgapplication.ui.adapter;

import android.content.Context;
import android.widget.TextView;

import com.example.admin.zgapplication.R;
import com.example.admin.zgapplication.ui.adapter.ZhyBaseRecycleAdapter.CommonAdapter;
import com.example.admin.zgapplication.ui.adapter.ZhyBaseRecycleAdapter.base.ViewHolder;

import java.util.List;

/**
 * Created by fushuang on 2017/9/27.
 */

public class HomePositionAdapter extends CommonAdapter<String>{

    private final List<String> datas;

    public HomePositionAdapter(Context context, int layoutId, List<String> datas) {
        super(context, layoutId, datas);
        this.datas = datas;
    }

    @Override
    protected void convert(ViewHolder holder, String homePosition, int position) {
        TextView view = (TextView) holder.getView(R.id.item_tv);
        view.setText("测试"+position);
    }
}
