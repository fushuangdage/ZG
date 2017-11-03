package com.example.admin.zgapplication.ui.view;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;

import com.example.admin.zgapplication.R;
import com.example.admin.zgapplication.utils.system.ScreenUtil;
import com.zhy.view.flowlayout.FlowLayout;
import com.zhy.view.flowlayout.TagAdapter;
import com.zhy.view.flowlayout.TagFlowLayout;

import java.util.ArrayList;

/**
 * Created by fushuang on 2017/11/3.
 */

public class HouseFilterDialog extends Dialog {

    private final Context context;

    public HouseFilterDialog(@NonNull Context context) {
        super(context);
        this.context = context;
    }

    public HouseFilterDialog(@NonNull Context context, int themeResId) {
        super(context, themeResId);
        this.context = context;
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        View view = LayoutInflater.from(context).inflate(R.layout.dialog_house_filter, null, false);
        TagFlowLayout tfl_rent_way = (TagFlowLayout) view.findViewById(R.id.tfl_rent_way);
        TagFlowLayout tfl_house_type = (TagFlowLayout) view.findViewById(R.id.tfl_house_type);
        ArrayList<String> rent_way = new ArrayList<>();
        rent_way.add("整租");
        rent_way.add("合租");
        ArrayList<String> house_type = new ArrayList<>();
        house_type.add("一居室");
        house_type.add("二居室");
        house_type.add("三居室");
        house_type.add("四居室");
        house_type.add("更大户型");
        tfl_rent_way.setAdapter(new TagAdapter<String>(rent_way) {
            @Override
            public View getView(FlowLayout parent, int position, String o) {
                TextView view = ((TextView) LayoutInflater.from(getContext()).inflate(R.layout.flow_item, parent, false));
                view.setText(o);
                return view;
            }
        });

        tfl_house_type.setAdapter(new TagAdapter<String>(house_type) {
            @Override
            public View getView(FlowLayout parent, int position, String o) {
                TextView view = ((TextView) LayoutInflater.from(getContext()).inflate(R.layout.flow_item, parent, false));
                view.setText(o);
                return view;
            }
        });
        setContentView(view);
        Window window = getWindow();
        window.setGravity(Gravity.BOTTOM);
        WindowManager.LayoutParams params  = window.getAttributes();
        params.width= ((Activity) context).getWindowManager().getDefaultDisplay().getWidth()- ScreenUtil.dp2px(context,20);
        window.setAttributes(params);
    }
}
