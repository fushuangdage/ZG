package com.example.admin.zgapplication.ui.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.example.admin.zgapplication.R;
import com.example.admin.zgapplication.ui.fragment.ChatFragment;

public class ChatActivity extends AppCompatActivity {

    private String toChatUsername;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat);
        toChatUsername = getIntent().getExtras().getString("userId");

        ChatFragment chatFragment=new ChatFragment();
        chatFragment.setArguments(getIntent().getExtras());
        getSupportFragmentManager().beginTransaction().add(R.id.fl_content,chatFragment).commit();
    }
}
