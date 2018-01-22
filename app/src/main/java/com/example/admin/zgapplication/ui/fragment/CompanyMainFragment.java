package com.example.admin.zgapplication.ui.fragment;


import android.support.v4.app.Fragment;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.example.admin.zgapplication.R;
import com.example.admin.zgapplication.base.BaseSupportFragment;
import com.example.admin.zgapplication.mvp.module.CompanyHomePageResponse;
import com.example.admin.zgapplication.retrofit.RetrofitHelper;
import com.example.admin.zgapplication.retrofit.rx.BaseObserver;
import com.example.admin.zgapplication.retrofit.rx.RxScheduler;

import butterknife.BindView;

/**
 * A simple {@link Fragment} subclass.
 */
public class CompanyMainFragment extends BaseSupportFragment{

    @BindView(R.id.webView)
    WebView webView;



    @Override
    protected int setLayout() {
        return R.layout.fragment_company_main;
    }

    @Override
    protected void init() {

        int company_id = getActivity().getIntent().getIntExtra("company_id", 0);
        RetrofitHelper.getApi().getCompanyHomePage(company_id)
                .compose(RxScheduler.<CompanyHomePageResponse>defaultScheduler())
                .subscribe(new BaseObserver<CompanyHomePageResponse>(mActivity) {
                    @Override
                    public void error(Throwable e) {

                    }

                    @Override
                    public void next(CompanyHomePageResponse companyHomePageResponse) {
                        if (companyHomePageResponse.getCode()==0) {
                            String link = companyHomePageResponse.getData().getLink();
                            webView.loadUrl(link);
                        }
                    }

                    @Override
                    public void complete() {

                    }
                });

        webView.setWebViewClient(new WebViewClient(){
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String request) {
                view.loadUrl(request);
                return true;
            }
        });
    }
}
