package com.example.admin.zgapplication.retrofit.rx;

import android.support.v7.widget.RecyclerView;

import com.example.admin.zgapplication.mvp.module.BaseResponse;
import com.example.admin.zgapplication.ui.adapter.ZhyBaseRecycleAdapter.CommonAdapter;
import com.example.admin.zgapplication.ui.adapter.ZhyBaseRecycleAdapter.wrapper.HeaderAndFooterWrapper;
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

        RecyclerView recyclerView = (RecyclerView) ((SmartRefreshLayout) refreshLayout).getChildAt(0);
        if (t.getCode()!=0){

            RecyclerView.Adapter rv_adapter = recyclerView.getAdapter();
            if (rv_adapter instanceof CommonAdapter) {
                CommonAdapter adapter = (CommonAdapter) rv_adapter;
                adapter.getDatas().clear();
                adapter.getDatas().add(null);
                adapter.notifyDataSetChanged();
            }else if (rv_adapter instanceof HeaderAndFooterWrapper){
                HeaderAndFooterWrapper headerAndFooterWrapper = (HeaderAndFooterWrapper) rv_adapter;
                headerAndFooterWrapper.mInnerAdapter.getDatas().clear();
                headerAndFooterWrapper.mInnerAdapter.getDatas().add(null);
                headerAndFooterWrapper.notifyDataSetChanged();
            }

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
