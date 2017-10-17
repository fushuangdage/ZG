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
import com.zhy.view.flowlayout.TagFlowLayout;

/**
 * Created by fushuang on 2017/10/16.
 */

public class RoomPickDialog extends Dialog {

    private Context context;
    private TagFlowLayout fl_pay_way;
    private TagFlowLayout fl_room_num;

    public RoomPickDialog(@NonNull Context context) {
        super(context);
        this.context = context;
    }

    public RoomPickDialog(@NonNull Context context, @StyleRes int themeResId) {
        super(context, themeResId);
        this.context = context;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        View view = LayoutInflater.from(getContext()).inflate(R.layout.room_pick_dialog, null, false);
        fl_pay_way = ((TagFlowLayout) view.findViewById(R.id.fl_pay_way));
        fl_room_num = ((TagFlowLayout) view.findViewById(R.id.fl_room_num));

        setContentView(view);
        Window window = getWindow();
        WindowManager.LayoutParams params  = window.getAttributes();
        window.setGravity(Gravity.BOTTOM);
        params.width= ((Activity) context).getWindowManager().getDefaultDisplay().getWidth();
        window.setAttributes(params);
    }

}
