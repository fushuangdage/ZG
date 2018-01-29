package com.example.admin.zgapplication.retrofit.rx;

import android.content.Context;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.example.admin.zgapplication.R;
import com.example.admin.zgapplication.mvp.module.BaseResponse;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;

import java.util.List;

import io.reactivex.functions.Consumer;


/**
 * Created by fushuang on 2017/12/22.
 */

public class   FinishLoadConsumer<T extends BaseResponse> implements Consumer<T> {

    private final RefreshLayout refreshLayout;
    private Integer currentPage;
    private List<Object> list;

    public FinishLoadConsumer(RefreshLayout refreshLayout) {
        this.refreshLayout = refreshLayout;
    }

    public FinishLoadConsumer(RefreshLayout refreshLayout, Integer currentPage) {
        this.refreshLayout = refreshLayout;
        this.currentPage = currentPage;
    }


    @Override
    public void accept(T t) throws Exception {

        if (t.getCode()!=0){

            int rl_width = refreshLayout.getLayout().getWidth();
            int rl_height = refreshLayout.getLayout().getHeight();
            View recyclerView = ((SmartRefreshLayout) refreshLayout).getChildAt(0);
            recyclerView.setVisibility(View.GONE);
            Context context = recyclerView.getContext();
            ImageView imageView = new ImageView(context);
//            imageView.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
            imageView.setLayoutParams(new LinearLayout.LayoutParams(1000, 1000));
            imageView.setBackgroundResource(R.drawable.ic_launcher);
            ((SmartRefreshLayout) refreshLayout).addView(imageView,new LinearLayout.LayoutParams(1000, 1000));

        }else {

            refreshLayout.setEnableLoadmore(true);
            refreshLayout.setEnableLoadmore(true);

            if (currentPage!=null&&currentPage>=t.getData().getSum_page()){
                refreshLayout.setEnableLoadmore(false);
            }else {
                refreshLayout.setEnableLoadmore(true);
            }
            refreshLayout.finishLoadmore();
            refreshLayout.finishRefresh();
        }

    }
}
