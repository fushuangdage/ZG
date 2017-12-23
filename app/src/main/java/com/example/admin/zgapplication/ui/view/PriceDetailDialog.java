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
import android.widget.TextView;

import com.example.admin.zgapplication.R;
import com.example.admin.zgapplication.mvp.module.OrderDetailResponse;

import butterknife.BindView;

/**
 * Created by fushuang on 2017/10/19.
 */

public class PriceDetailDialog extends Dialog{

    private Context context;
    private OrderDetailResponse.OrderDetailDataBean.ListBean bean;

    @BindView(R.id.tv_rent_count)
    TextView tv_rent_count;
    @BindView(R.id.tv_deposit_count)
    TextView tv_deposit_count;
    @BindView(R.id.middle_count)
    TextView middle_count;
    @BindView(R.id.rent_money)
    TextView rent_money;
    @BindView(R.id.deposit_money)
    TextView deposit_money;
    @BindView(R.id.middle_money)
    TextView middle_money;

    @BindView(R.id.service_count)
    TextView service_count;

    @BindView(R.id.service_money)
    TextView service_money;

    public PriceDetailDialog(@NonNull Context context) {
        super(context);
        this.context = context;
    }

    public PriceDetailDialog(@NonNull Context context, @StyleRes int themeResId) {
        super(context, themeResId);
        this.context = context;
    }

    public OrderDetailResponse.OrderDetailDataBean.ListBean getBean() {
        return bean;
    }

    public void setBean(OrderDetailResponse.OrderDetailDataBean.ListBean bean) {
        this.bean = bean;
        rent_money.setText("¥"+bean.getRent()+"");
        tv_rent_count.setText(bean.getRent_pay()+"");
        deposit_money.setText("¥"+bean.getPay()+"");
        tv_deposit_count.setText(bean.getDeposit());
        middle_count.setText(bean.getMiddle_count());
        middle_money.setText("¥"+bean.getMiddle_money());
        service_count.setText(bean.getService_count());
        service_money.setText("¥"+bean.getService_money());
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        View view = LayoutInflater.from(context).inflate(R.layout.price_detail_panel, null, false);
        tv_rent_count= (TextView) view.findViewById(R.id.tv_rent_count);
        tv_deposit_count= (TextView) view.findViewById(R.id.tv_deposit_count);
        middle_count= (TextView) view.findViewById(R.id.middle_count);
        rent_money= (TextView) view.findViewById(R.id.rent_money);
        deposit_money= (TextView) view.findViewById(R.id.deposit_money);
        middle_money= (TextView) view.findViewById(R.id.middle_money);
        service_count= (TextView) view.findViewById(R.id.service_count);
        service_money= (TextView) view.findViewById(R.id.service_money);

        setContentView(view);
        Window window = getWindow();
        WindowManager.LayoutParams params  = window.getAttributes();
        window.setGravity(Gravity.CENTER);
        params.width= ((Activity) context).getWindowManager().getDefaultDisplay().getWidth();
        window.setAttributes(params);

    }
}
