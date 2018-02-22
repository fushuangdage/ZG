package com.example.admin.zgapplication.ui.adapter;

import android.app.Activity;
import android.support.annotation.LayoutRes;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.admin.zgapplication.R;
import com.example.admin.zgapplication.mvp.module.CityResponse;

import java.util.List;

/**
 * Created by fushuang on 2018/2/7.
 */

public class CityChooseAdapter extends RecyclerView.Adapter<CityChooseAdapter.ViewHolder> {


    private final Activity activity;
    private final  @LayoutRes  int layout_id;
    private final List<CityResponse.DataBean.ListBean> cityList;
    private OnItemClickCallBack onItemClickListener;

    public CityChooseAdapter(Activity activity, @LayoutRes int layout_Id, List<CityResponse.DataBean.ListBean> cityList) {
        this.activity = activity;
        layout_id = layout_Id;
        this.cityList = cityList;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(activity).inflate(layout_id, null, false);
        ViewHolder viewHolder = new ViewHolder(itemView);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {
            holder.tv_content.setText(cityList.get(position).getName());
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    onItemClickListener.onItemClick(holder.itemView,holder,position);
                }
            });
    }

    @Override
    public int getItemCount() {
        return cityList.size();
    }

    public void setOnItemClickListener(OnItemClickCallBack onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }




    public static class ViewHolder extends RecyclerView.ViewHolder {

        public   TextView tv_content;

        public ViewHolder(View itemView) {
            super(itemView);
            tv_content = (TextView) itemView.findViewById(R.id.tv_content);
        }
    }

    public interface  OnItemClickCallBack{
        void onItemClick(View view, RecyclerView.ViewHolder holder, int position);
    }
}
