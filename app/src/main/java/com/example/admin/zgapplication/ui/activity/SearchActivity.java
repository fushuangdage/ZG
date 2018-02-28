package com.example.admin.zgapplication.ui.activity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.example.admin.zgapplication.Constant;
import com.example.admin.zgapplication.R;
import com.example.admin.zgapplication.base.BaseActivity;
import com.example.admin.zgapplication.mvp.module.HotSearchListResponse;
import com.example.admin.zgapplication.retrofit.RetrofitHelper;
import com.example.admin.zgapplication.retrofit.rx.RxScheduler;
import com.example.admin.zgapplication.utils.SPUtil;
import com.zhy.view.flowlayout.FlowLayout;
import com.zhy.view.flowlayout.TagAdapter;
import com.zhy.view.flowlayout.TagFlowLayout;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import butterknife.BindView;
import butterknife.OnClick;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

public class SearchActivity extends BaseActivity {


    @BindView(R.id.fl_history)
    TagFlowLayout fl_history;

    @BindView(R.id.fl_hot_search)
    TagFlowLayout fl_hot_search;

    @BindView(R.id.et_input)
    EditText et_input;


    private TagAdapter adapter;

    private List<String> list=new ArrayList<>();
    private List<HotSearchListResponse.DataBean> hotSearchList;

    @OnClick({R.id.iv_del,R.id.tv_cancel})
    public void onClick(View view){
        switch (view.getId()) {
            case R.id.tv_cancel:
                finish();
                break;
            case  R.id.iv_del:
                AlertDialog.Builder builder = new AlertDialog.Builder(this);
                builder.setTitle("确认删除全部搜索历史").setNegativeButton("取消", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                }).setPositiveButton("确认", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        SPUtil.remove(mActivity,Constant.SEARCH_HISTORY);
                        list.clear();
                        adapter.notifyDataChanged();
                    }
                }).show();
                break;
        }
    }


    @Override
    public int setLayout() {
        return R.layout.activity_search;
    }

    @Override
    public void initEvent() {
        et_input.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                if (keyCode==KeyEvent.KEYCODE_ENTER&&event.getAction()== KeyEvent.ACTION_DOWN){
                    String s = (String) SPUtil.get(mActivity, Constant.SEARCH_HISTORY, "");
                    if (s.equals("")) {
                        s=et_input.getText().toString();
                    }else {
                        s+=","+et_input.getText();
                    }

                    SPUtil.put(mActivity,Constant.SEARCH_HISTORY,s);
                    if(!list.contains(s))
                    list.add(et_input.getText().toString());
                    adapter.notifyDataChanged();
                    Intent intent = new Intent(mActivity, SearchResultActivity.class);
                    intent.putExtra("keyword",s);
                    startActivity(intent);
                    return true;
                }
                return false;
            }
        });

        String s = (String) SPUtil.get(mActivity, Constant.SEARCH_HISTORY, "");
        String[] split = s.split(",");
        for (String s1 : split) {
            if (!s1.equals("")&& !list.contains(s)) {
                list.add(s1);
            }
        }
        adapter = new TagAdapter<String>(list) {
            @Override
            public View getView(FlowLayout parent, int position, String o) {
                TextView textView = (TextView) LayoutInflater.from(mActivity).inflate(R.layout.item_history_search, null, false);
                textView.setText(o);
                return textView;
            }
        };

        fl_history.setAdapter(adapter);
        fl_history.setOnTagClickListener(new TagFlowLayout.OnTagClickListener() {
            @Override
            public boolean onTagClick(View view, int position, FlowLayout parent) {
                Intent intent = new Intent(mActivity, SearchResultActivity.class);
                intent.putExtra("keyword",list.get(position));
                startActivity(intent);
                return false;
            }
        });

        fl_hot_search.setOnTagClickListener(new TagFlowLayout.OnTagClickListener() {
            @Override
            public boolean onTagClick(View view, int position, FlowLayout parent) {
                Intent intent = new Intent(mActivity, SearchResultActivity.class);
                intent.putExtra("keyword",hotSearchList.get(position).getCompany_name());
                startActivity(intent);
                return false;
            }
        });
        fl_history.setOnSelectListener(new TagFlowLayout.OnSelectListener() {
            @Override
            public void onSelected(Set<Integer> selectPosSet) {

            }
        });


        fl_hot_search.setOnSelectListener(new TagFlowLayout.OnSelectListener() {
            @Override
            public void onSelected(Set<Integer> selectPosSet) {

            }
        });

    }

    @Override
    public void initData() {
        RetrofitHelper.getApi().getHotSearch()
                .compose(RxScheduler.<HotSearchListResponse>defaultScheduler())
                .subscribe(new Observer<HotSearchListResponse>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(HotSearchListResponse hotSearchListResponse) {
                        hotSearchList = hotSearchListResponse.getData();
                        TagAdapter<HotSearchListResponse.DataBean> hotSearchAdapter = new TagAdapter<HotSearchListResponse.DataBean>(hotSearchListResponse.getData()) {
                            @Override
                            public View getView(FlowLayout parent, int position, HotSearchListResponse.DataBean o) {
                                TextView textView = (TextView) LayoutInflater.from(mActivity).inflate(R.layout.item_history_search, null, false);
                                textView.setText(o.getCompany_name());
                                return textView;
                            }
                        };

                        fl_hot_search.setAdapter(hotSearchAdapter);

                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });



    }
}
