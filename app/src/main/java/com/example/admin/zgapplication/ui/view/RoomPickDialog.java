package com.example.admin.zgapplication.ui.view;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.StyleRes;
import android.support.v7.widget.AppCompatCheckBox;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.admin.zgapplication.R;
import com.example.admin.zgapplication.mvp.module.RoomDetailResponse;
import com.example.admin.zgapplication.mvp.module.SelectOrderInfoBean;
import com.zhy.view.flowlayout.FlowLayout;
import com.zhy.view.flowlayout.TagAdapter;
import com.zhy.view.flowlayout.TagFlowLayout;

import java.util.List;

/**
 * Created by fushuang on 2017/10/16.
 */

public class RoomPickDialog extends Dialog implements View.OnClickListener {

    private Context context;
    private TagFlowLayout fl_pay_way;
    private TagFlowLayout fl_room_num;
    ResultCallBack resultCallBack;
    private RoomDetailResponse.DataBean data;
    private TextView tv_price;
    private TextView tv_room_id;
    private TextView tv_room_pay_mode;
    private TextView tv_payment_info;
    private TagFlowLayout fl_rent_time;
    private List<RoomDetailResponse.DataBean.PayMethodBean> list_pay_method;
    private List<Integer> list_rent_time;
    private List<RoomDetailResponse.DataBean.RoomNBean> list_room;
    private RoomDetailResponse.DataBean.RoomNBean check_roomBean;
    private RoomDetailResponse.DataBean.PayMethodBean check_pay_way;
    private Integer check_rent_time;
    private ImageView imageView;
    private String type;


    public void setResultCallBack(ResultCallBack resultCallBack) {
        this.resultCallBack = resultCallBack;
    }

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
        imageView = (ImageView) view.findViewById(R.id.iv_room);
        fl_pay_way = ((TagFlowLayout) view.findViewById(R.id.fl_pay_way));
        fl_room_num = ((TagFlowLayout) view.findViewById(R.id.fl_room_num));
        fl_rent_time = (TagFlowLayout) view.findViewById(R.id.fl_rent_time);
        tv_price = (TextView) view.findViewById(R.id.tv_price);
        tv_room_id = (TextView) view.findViewById(R.id.tv_room_id);
        tv_room_pay_mode = (TextView) view.findViewById(R.id.tv_room_pay_mode);
        tv_payment_info = (TextView) view.findViewById(R.id.tv_payment_info);



        ((TextView) view.findViewById(R.id.tv_confirm)).setOnClickListener(this);
        setContentView(view);
        Window window = getWindow();
        WindowManager.LayoutParams params  = window.getAttributes();
        window.setGravity(Gravity.BOTTOM);
        params.width= ((Activity) context).getWindowManager().getDefaultDisplay().getWidth();
        window.setAttributes(params);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tv_confirm:

                resultCallBack.resultCallBack(new SelectOrderInfoBean(data.getHouse_id(),check_roomBean.getRoom_id(),
                        type,check_roomBean.getRental(),
                        check_rent_time,check_pay_way.getPay(),check_pay_way.getPledge(),check_roomBean.getRoom_number()
                        ));
                dismiss();
                break;
        }
    }

    public void setData(RoomDetailResponse.DataBean data) {
        this.data = data;
        type = data.getType();
        tv_room_id.setText("房源编号: "+data.getHouse_code());
        tv_price.setText("¥ "+data.getRental());
        tv_payment_info.setText(String.format("租金: %s元/月, 押金: %s元",data.getRoom_n().get(0).getRental(),data.getRoom_n().get(0).getRoom_deposit()));

        Glide.with(context).load(data.getHouse_picture()).into(imageView);

        list_room = data.getRoom_n();
        check_roomBean=data.getRoom_n().get(0);
        TagAdapter<RoomDetailResponse.DataBean.RoomNBean> adapter1 = new TagAdapter<RoomDetailResponse.DataBean.RoomNBean>(list_room) {
            @Override
            public View getView(FlowLayout parent, int position, RoomDetailResponse.DataBean.RoomNBean o) {
                AppCompatCheckBox checkBox = (AppCompatCheckBox) LayoutInflater.from(context).inflate(R.layout.item_tag_check_box, null, false);
//                if (position == 0)
//                    checkBox.setChecked(true);
                checkBox.setText(o.getRoom_number());
                return checkBox;
            }
        };
        adapter1.setSelectedList(0);
        fl_room_num.setAdapter(adapter1);


        list_rent_time = data.getDate();
        check_rent_time=data.getDate().get(0);
        TagAdapter<Integer> adapter2 = new TagAdapter<Integer>(list_rent_time) {
            @Override
            public View getView(FlowLayout parent, int position, Integer o) {
                AppCompatCheckBox checkBox = (AppCompatCheckBox) LayoutInflater.from(context).inflate(R.layout.item_tag_check_box, null, false);
//                if (position == 0)
//                    checkBox.setChecked(true);
                checkBox.setText(o + "");
                return checkBox;
            }
        };
        adapter2.setSelectedList(0);
        fl_rent_time.setAdapter(adapter2);

        list_pay_method = data.getPay_method();
        check_pay_way=data.getPay_method().get(0);
        TagAdapter<RoomDetailResponse.DataBean.PayMethodBean> adapter3 = new TagAdapter<RoomDetailResponse.DataBean.PayMethodBean>(list_pay_method) {
            @Override
            public View getView(FlowLayout parent, int position, RoomDetailResponse.DataBean.PayMethodBean o) {

                AppCompatCheckBox checkBox = (AppCompatCheckBox) LayoutInflater.from(context).inflate(R.layout.item_tag_check_box, null, false);
//                if (position == 1)
//                    checkBox.setChecked(true);

                checkBox.setText(o.getMethod_name());
                return checkBox;
            }
        };
        adapter3.setSelectedList(0);
        fl_pay_way.setAdapter(adapter3);

        fl_room_num.setOnTagClickListener(new TagFlowLayout.OnTagClickListener() {
            @Override
            public boolean onTagClick(View view, int position, FlowLayout parent) {
                check_roomBean = list_room.get(position);
                refreshChooseData();
                return false;
            }
        });

        fl_pay_way.setOnTagClickListener(new TagFlowLayout.OnTagClickListener() {
            @Override
            public boolean onTagClick(View view, int position, FlowLayout parent) {
                check_pay_way = list_pay_method.get(position);
                refreshChooseData();
                return false;
            }
        });

        fl_rent_time.setOnTagClickListener(new TagFlowLayout.OnTagClickListener() {
            @Override
            public boolean onTagClick(View view, int position, FlowLayout parent) {
                check_rent_time = list_rent_time.get(position);
                refreshChooseData();
                return false;
            }
        });

    }

    public void refreshChooseData(){
        tv_room_id.setText("房源编号: "+check_roomBean.getRoom_code());
        tv_price.setText("¥ "+(check_roomBean.getRental()*check_pay_way.getPay()+check_roomBean.getRoom_deposit()*check_pay_way.getPledge()));
        tv_payment_info.setText(String.format("租金: %d元/月, 押金: %d元",check_roomBean.getRental(),check_roomBean.getRoom_deposit()));
        tv_room_pay_mode.setText(String.format("已选: %s，%s",check_pay_way.getMethod_name(),check_roomBean.getRoom_number()));
    }

    public interface ResultCallBack{
         void resultCallBack(SelectOrderInfoBean bean);
    }

}
