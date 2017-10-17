package com.example.admin.zgapplication.ui.fragment;

import android.content.Intent;
import android.view.View;

import com.example.admin.zgapplication.R;
import com.example.admin.zgapplication.base.BaseSupportFragment;
import com.example.admin.zgapplication.ui.activity.CompanyDetailActivity;
import com.example.admin.zgapplication.ui.view.RoomPickDialog;

import butterknife.OnClick;

public class HouseDetailFragment extends BaseSupportFragment {

    @Override
    protected int setLayout() {
        return R.layout.fragment_house_detail;
    }

    @Override
    protected void init() {

    }

    @OnClick({R.id.ll_choose_room,R.id.ll_goto_company})
    public void onClick(View view){
        switch (view.getId()) {
            case R.id.ll_choose_room:
                RoomPickDialog roomPickDialog = new RoomPickDialog(getContext(),R.style.room_pick_dialog);
                roomPickDialog.show();
                break;
            case R.id.ll_goto_company:
                startActivity(new Intent(getContext(), CompanyDetailActivity.class));
                break;

        }
    }
}
