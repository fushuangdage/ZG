package com.example.admin.zgapplication.ui.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.admin.zgapplication.R;
import com.hyphenate.easeui.ui.EaseChatFragment;

public class ChatActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat);

        EaseChatFragment chatFragment=new EaseChatFragment();
        chatFragment.setArguments(getIntent().getExtras());
        getSupportFragmentManager().beginTransaction().add(R.id.fl_content,chatFragment).commit();
    }
}
