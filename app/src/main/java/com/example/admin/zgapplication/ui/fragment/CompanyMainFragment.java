package com.example.admin.zgapplication.ui.fragment;


import android.support.v4.app.Fragment;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.example.admin.zgapplication.R;
import com.example.admin.zgapplication.base.BaseSupportFragment;

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

        webView.setWebViewClient(new WebViewClient(){
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String request) {
                view.loadUrl(request);
                return true;
            }
        });
        webView.loadUrl("http://www.zhagen.com/");
    }
}
