package com.example.admin.zgapplication.ui.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.example.admin.zgapplication.Constant;
import com.example.admin.zgapplication.R;
import com.example.admin.zgapplication.mvp.module.EmptyResponse;
import com.example.admin.zgapplication.retrofit.RetrofitHelper;
import com.example.admin.zgapplication.retrofit.rx.RxScheduler;
import com.example.admin.zgapplication.ui.fragment.ChatFragment;
import com.hyphenate.easeui.EaseConstant;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

public class ChatActivity extends AppCompatActivity {

    private String toChatUsername;
    private String aid;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat);



        toChatUsername = getIntent().getExtras().getString(Constant.CHAT_HX_NAME);
        Bundle extras = getIntent().getExtras();
        aid = extras.getString(EaseConstant.AGENT_ID, "0");

        RetrofitHelper.getApi()
                .addHXFriend(aid,Constant.uid)
                .compose(RxScheduler.<EmptyResponse>defaultScheduler())
                .subscribe(new Observer<EmptyResponse>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(EmptyResponse baseObserver) {
//                        Toast.makeText(ChatActivity.this, "添加成功", Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });

        extras.putString(EaseConstant.MY_HEAD, Constant.avatar);
        extras.putString(EaseConstant.USER_ID, Constant.uid);
        extras.putString(EaseConstant.MY_NAME,Constant.username);
        extras.putString(EaseConstant.AGENT_ID,aid);
        ChatFragment chatFragment=new ChatFragment();
        chatFragment.setArguments(extras);
        getSupportFragmentManager().beginTransaction().add(R.id.fl_content,chatFragment).commit();
    }
}
