package com.example.admin.zgapplication.base;


import com.example.admin.zgapplication.retrofit.rx.NetManager;

import java.lang.ref.WeakReference;

import io.reactivex.disposables.Disposable;


/**
 * Created by hjn on 2016/11/3.
 */
public abstract class MVPBasePresenter<V extends BaseContract.View,M extends BaseContract.Model> {
    protected V mView;

    public void attach(V mView)
    {
        this.mView = new WeakReference<>(mView).get();
    }

    public void detach() {
        if (mView!=null){
            mView=null;
            NetManager.getInstance().cancelRequests(getClass().getName());
        }
    }

    public boolean isAttach() {
        return mView != null;
    }


    /**
     * 记录该页面的所有网络请求
     * @param call
     */
    public void recordRequest(Disposable call){
        NetManager.getInstance().recordRequest(getClass().getName(),call);
    }

}