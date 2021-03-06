package com.example.admin.zgapplication.ui.view;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;

import com.example.admin.zgapplication.Constant;
import com.example.admin.zgapplication.R;
import com.example.admin.zgapplication.base.EventCenter;
import com.example.admin.zgapplication.base.EventRegionSelect;
import com.example.admin.zgapplication.utils.system.ScreenUtil;
import com.zhy.view.flowlayout.FlowLayout;
import com.zhy.view.flowlayout.TagAdapter;
import com.zhy.view.flowlayout.TagFlowLayout;

import org.greenrobot.eventbus.EventBus;

import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.Set;

/**
 * Created by fushuang on 2017/11/3.
 */

public class HouseFilterDialog extends Dialog implements DialogInterface.OnDismissListener {

    private final Context context;
    private Set<Integer> rent_way_set=new LinkedHashSet<>();
    private Set<Integer> house_type_set=new LinkedHashSet<>();
    private int leftProgress;
    private int rightProgress;
    private BidirectionalSeekBar seekBar;
    private int leftBallX;
    private int rightBallX=0;

    public HouseFilterDialog(@NonNull Context context) {
        super(context);
        this.context = context;
    }

    public HouseFilterDialog(@NonNull Context context, int themeResId) {
        super(context, themeResId);
        this.context = context;
        setOnDismissListener(this);
    }


    public void reloadStarBar(){
        if (seekBar.getRightBallX()!=0) {
            seekBar.setLeftBallX(leftBallX);
            seekBar.setRightBallX(rightBallX);
        }
    }



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        View view = LayoutInflater.from(context).inflate(R.layout.dialog_house_filter, null, false);
        view.findViewById(R.id.btn_confirm).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
            }
        });
        seekBar = (BidirectionalSeekBar) view.findViewById(R.id.seekBar);
        final TextView tv_text_show = (TextView) view.findViewById(R.id.tv_text_show);
        seekBar.setOnSeekBarChangeListener(new BidirectionalSeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(int leftProgress, int rightProgress) {
                HouseFilterDialog.this.leftProgress = leftProgress;
                HouseFilterDialog.this.rightProgress = rightProgress;
                tv_text_show.setText(String.format("￥%d - ¥%d",leftProgress,rightProgress));
            }
        });
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
        tfl_rent_way.setOnSelectListener(new TagFlowLayout.OnSelectListener() {
            @Override
            public void onSelected(Set<Integer> selectPosSet) {
                rent_way_set.clear();
                for (Integer integer : selectPosSet) {
                    rent_way_set.add(integer+2);   //可恶的后台小姐姐第一个整租用2 表示,合租用3表示
                }
            }
        });
        tfl_house_type.setOnSelectListener(new TagFlowLayout.OnSelectListener() {
            @Override
            public void onSelected(Set<Integer> selectPosSet) {
                house_type_set.clear();
                for (Integer integer : selectPosSet) {
                    house_type_set.add(integer+1);
                }
            }
        });
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

    @Override
    public void onDismiss(DialogInterface dialog) {
        EventBus.getDefault().post(new EventCenter<EventRegionSelect>(Constant.REGION_SELECT,new EventRegionSelect(rent_way_set,house_type_set,leftProgress,rightProgress)));
        leftBallX = seekBar.getLeftBallX();
        rightBallX = seekBar.getRightBallX();
    }
}
