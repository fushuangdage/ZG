package com.example.admin.zgapplication.ui.fragment;

import android.content.Intent;
import android.view.View;

import com.example.admin.zgapplication.ui.activity.HouseDetailActivity;
import com.hyphenate.chat.EMClient;
import com.hyphenate.chat.EMMessage;
import com.hyphenate.easeui.EaseConstant;
import com.hyphenate.easeui.ui.EaseChatFragment;
import com.hyphenate.easeui.widget.chatrow.EaseCustomChatRowProvider;

/**
 * Created by fushuang on 2017/11/1.
 */

public class ChatFragment extends EaseChatFragment {

    private String aid;



    @Override
    protected void setUpView() {
        super.setUpView();
        aid=getArguments().getString(EaseConstant.AGENT_ID,"0");
        setChatFragmentHelper(new EaseChatFragmentHelper() {
            @Override
            public void onSetMessageAttributes(EMMessage message) {

            }

            @Override
            public void onEnterToChatDetails() {

            }

            @Override
            public void onAvatarClick(String username) {
                if (!username.equals(EMClient.getInstance().getCurrentUser())){
                    //说明点击的是经纪人的头像
                    Intent intent = new Intent("com.fs.agentActivity");
                    intent.putExtra("aid",aid);
                    startActivity(intent);
                }
            }

            @Override
            public void onAvatarLongClick(String username) {

            }

            @Override
            public boolean onMessageBubbleClick(EMMessage message) {


                String houseId = message.getStringAttribute("houseId", "");
                if (!"".equals(houseId)){
                    Intent intent = new Intent(getActivity(), HouseDetailActivity.class);
                    intent.putExtra("house_id",message.getStringAttribute("houseId",""));
                    intent.putExtra("room_id", message.getStringAttribute("roomId",""));
                    intent.putExtra("type", message.getStringAttribute("type",""));
                    startActivity(intent);
                    return true;
                }

                return false;
            }

            @Override
            public void onMessageBubbleLongClick(EMMessage message) {

            }

            @Override
            public boolean onExtendMenuItemClick(int itemId, View view) {
                return false;
            }

            @Override
            public EaseCustomChatRowProvider onSetCustomChatRowProvider() {
                return null;
            }
        });
    }
}
