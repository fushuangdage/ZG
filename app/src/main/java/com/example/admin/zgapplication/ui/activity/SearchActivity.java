package com.example.admin.zgapplication.ui.activity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.example.admin.zgapplication.Constant;
import com.example.admin.zgapplication.R;
import com.example.admin.zgapplication.base.BaseActivity;
import com.example.admin.zgapplication.utils.SPUtil;
import com.zhy.view.flowlayout.FlowLayout;
import com.zhy.view.flowlayout.TagAdapter;
import com.zhy.view.flowlayout.TagFlowLayout;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

public class SearchActivity extends BaseActivity {


    @BindView(R.id.fl_history)
    TagFlowLayout fl_history;

    @BindView(R.id.fl_hot_search)
    TagFlowLayout fl_hot_search;

    @BindView(R.id.et_input)
    EditText et_input;
    private TagAdapter adapter;

    private List<String> list=new ArrayList<>();

    @OnClick({R.id.iv_del})
    public void onClick(View view){
        switch (view.getId()) {
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
                    list.add(et_input.getText().toString());
                    adapter.notifyDataChanged();
                    return true;
                }
                return false;
            }
        });

        String s = (String) SPUtil.get(mActivity, Constant.SEARCH_HISTORY, "");
        String[] split = s.split(",");
        for (String s1 : split) {
            if (!s1.equals("")) {
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

    }

    @Override
    public void initData() {

    }
}
