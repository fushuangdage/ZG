package com.example.admin.zgapplication.ui.view;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.StyleRes;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

import com.example.admin.zgapplication.R;

/**
 * Created by fushuang on 2017/10/19.
 */

public class PriceDetailDialog extends Dialog{

    private Context context;

    public PriceDetailDialog(@NonNull Context context) {
        super(context);
        this.context = context;
    }

    public PriceDetailDialog(@NonNull Context context, @StyleRes int themeResId) {
        super(context, themeResId);
        this.context = context;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        View view = LayoutInflater.from(context).inflate(R.layout.price_detail_panel, null, false);
        setContentView(view);
        Window window = getWindow();
        WindowManager.LayoutParams params  = window.getAttributes();
        window.setGravity(Gravity.CENTER);
        params.width= ((Activity) context).getWindowManager().getDefaultDisplay().getWidth();
        window.setAttributes(params);

    }
}
