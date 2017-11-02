package com.example.admin.zgapplication.ui.activity;

import com.example.admin.zgapplication.R;
import com.example.admin.zgapplication.base.BaseActivity;
import com.example.admin.zgapplication.ui.fragment.ConversationListFragment;


public class EaseConversationListActivity extends BaseActivity {


    private ConversationListFragment conversationListFragment;

    @Override
    public int setLayout() {
        return R.layout.activity_ease_conversation_list;
    }

    @Override
    public void initEvent() {
        conversationListFragment = new ConversationListFragment();

        getSupportFragmentManager().beginTransaction()
                .add(R.id.container,conversationListFragment,"conversationListFragment")
                .show(conversationListFragment).commit();
    }

    @Override
    public void initData() {

    }
}
