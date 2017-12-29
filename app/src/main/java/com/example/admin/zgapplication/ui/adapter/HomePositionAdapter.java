package com.example.admin.zgapplication.ui.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.example.admin.zgapplication.Constant;
import com.example.admin.zgapplication.R;
import com.example.admin.zgapplication.base.EventCenter;
import com.example.admin.zgapplication.mvp.module.RegionResponse;
import com.example.admin.zgapplication.ui.adapter.ZhyBaseRecycleAdapter.CommonAdapter;
import com.example.admin.zgapplication.ui.adapter.ZhyBaseRecycleAdapter.MultiItemTypeAdapter;
import com.example.admin.zgapplication.ui.adapter.ZhyBaseRecycleAdapter.base.ViewHolder;

import org.greenrobot.eventbus.EventBus;

import java.util.List;

/**
 * Created by fushuang on 2017/9/27.
 */

public class HomePositionAdapter extends CommonAdapter<RegionResponse.BaseRegion> implements MultiItemTypeAdapter.OnItemClickListener {

    private final List<RegionResponse.BaseRegion> datas;

    public boolean open=false;
    public int firstLevelPosition;

    public HomePositionAdapter(Context context, int layoutId, List<RegionResponse.BaseRegion> datas) {
        super(context, layoutId, datas);
        this.datas = datas;
        setOnItemClickListener(this);
    }

    @Override
    protected void convert(ViewHolder holder, RegionResponse.BaseRegion bean, int position) {
        if (position==0&&open){
             holder.getView(R.id.item_tv).setBackgroundResource(R.drawable.green_border_bg);
        }
        TextView view = (TextView) holder.getView(R.id.item_tv);
        view.setText(bean.getName());
    }


    @Override
    public void onItemClick(View view, RecyclerView.ViewHolder holder, int position) {
        if (open){
            if (position ==0){
//                datas.clear();
                open=false;
            }else {
                EventBus.getDefault().post(new EventCenter<RegionResponse.BaseRegion>(Constant.SECOND_LEVEL_REGION,datas.get(position)));  //发送给homeActivity刷新数据
                view.setBackgroundResource(R.drawable.green_border_bg);
            }
        }else {
            RegionResponse.DataBean.ListBean region = (RegionResponse.DataBean.ListBean) datas.get(position);
            firstLevelPosition = position;
            datas.clear();
            datas.add(region);
            datas.addAll(region.getSub());
            notifyDataSetChanged();
            open=true;
        }

    }

    @Override
    public boolean onItemLongClick(View view, RecyclerView.ViewHolder holder, int position) {
        return false;
    }
}
