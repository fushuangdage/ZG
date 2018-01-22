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
import com.example.admin.zgapplication.ui.fragment.HomeFindPersonFragment;

import org.greenrobot.eventbus.EventBus;

import java.util.List;

/**
 * Created by fushuang on 2017/9/27.
 */

public class HomePositionAdapter extends CommonAdapter<RegionResponse.BaseRegion> implements MultiItemTypeAdapter.OnItemClickListener {

    private final List<RegionResponse.BaseRegion> datas;
    private final HomeFindPersonFragment homeFindPersonFragment;

    public boolean isSecondLevel =false;
    public int firstLevelPosition;
    private int secondLevelSelected;

    public HomePositionAdapter(Context context, int layoutId, List<RegionResponse.BaseRegion> datas, HomeFindPersonFragment homeFindPersonFragment) {
        super(context, layoutId, datas);
        this.datas = datas;
        this.homeFindPersonFragment = homeFindPersonFragment;
        setOnItemClickListener(this);
    }

    @Override
    protected void convert(ViewHolder holder, RegionResponse.BaseRegion bean, int position) {
        if (isSecondLevel&&(position==secondLevelSelected||position==0)){
             holder.getView(R.id.item_tv).setBackgroundResource(R.drawable.green_border_bg);
            ((TextView) holder.getView(R.id.item_tv)).setTextColor(mContext.getResources().getColor(R.color.green));
        }else {
            holder.getView(R.id.item_tv).setBackgroundResource(R.drawable.gray_rect_white_boder);
            ((TextView) holder.getView(R.id.item_tv)).setTextColor(mContext.getResources().getColor(R.color.gray666));
        }
        TextView view = (TextView) holder.getView(R.id.item_tv);
        view.setText(bean.getName());
    }


    @Override
    public void onItemClick(View view, RecyclerView.ViewHolder holder, int position) {
        if (isSecondLevel){
            if (position ==0){
//                datas.clear();
                homeFindPersonFragment.reloadFirstLevelData();
                isSecondLevel =false;
            }else {
                secondLevelSelected = position;
                notifyDataSetChanged();
                EventBus.getDefault().post(new EventCenter<RegionResponse.BaseRegion>(Constant.SECOND_LEVEL_REGION,datas.get(position)));  //发送给fragment刷新数据
            }
        }else {
            RegionResponse.DataBean.ListBean region = (RegionResponse.DataBean.ListBean) datas.get(position);
            firstLevelPosition = position;
            datas.clear();
            datas.add(region);
            datas.addAll(region.getSub());
            notifyDataSetChanged();
            isSecondLevel =true;
        }

    }

    @Override
    public boolean onItemLongClick(View view, RecyclerView.ViewHolder holder, int position) {
        return false;
    }
}
