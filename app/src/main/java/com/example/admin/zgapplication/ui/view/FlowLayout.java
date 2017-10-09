package com.example.admin.zgapplication.ui.view;

import android.content.Context;
import android.support.annotation.LayoutRes;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by fushuang on 2017/10/9.
 */

public class FlowLayout extends ViewGroup {

    List<String> mList;
    List<List<View>> mViewList=new ArrayList<>();

    private LayoutInflater layoutInflater;

    public List<String> getList() {
        return mList;
    }

    public void setList(List<String> mList,@LayoutRes int itemLayoutID) {
        this.mList = mList;
        for (String s : mList) {
            View child = layoutInflater.inflate(itemLayoutID,null);
            addView(child);
        }
        postInvalidate();
    }

    public FlowLayout(Context context) {
        super(context,null);
    }

    public FlowLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
        layoutInflater = LayoutInflater.from(context);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {

        int sizeWidth = MeasureSpec.getSize(widthMeasureSpec);
        int modeWidth = MeasureSpec.getMode(widthMeasureSpec);

        int sizeHeight = MeasureSpec.getSize(heightMeasureSpec);
        int modeHeight = MeasureSpec.getMode(heightMeasureSpec);

        int wLeft = sizeWidth-getPaddingLeft() - getPaddingRight();
        int totalHeight=getPaddingTop()+getPaddingBottom();
        ArrayList<View> lineViews = new ArrayList<>();
        for (int i = 0; i < getChildCount(); i++) {

            View childAt = getChildAt(i);
            measureChild(childAt,widthMeasureSpec,heightMeasureSpec);
            MarginLayoutParams childlp = ((MarginLayoutParams) childAt.getLayoutParams());
            int childUsedWidth=childAt.getMeasuredWidth()+childlp.leftMargin+childlp.rightMargin;
            wLeft-=childUsedWidth;
            if (wLeft>0){
                //说明这一行能放下
                lineViews.add(childAt);
            }else {
                //说明该换行了
                mViewList.add(lineViews); //把装好的上一行放入最终结果集
                totalHeight+= childAt.getMeasuredHeight();

                //把当前这个放不下的控件放到下一行里
                wLeft = sizeWidth-getPaddingLeft() - getPaddingRight()-childUsedWidth;
                lineViews=new ArrayList<>();
                lineViews.add(childAt);
            }

        }

        super.onMeasure(widthMeasureSpec, resolveSize(totalHeight,heightMeasureSpec));
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        int userdWidth;
        int userHeight = 0;

        for (List<View> views : mViewList) {
            userdWidth=0;
            userHeight+=views.get(0).getMeasuredHeight();

            for (View view : views) {
                view.layout(userdWidth,userHeight,userdWidth+view.getMeasuredWidth(),userHeight+view.getMeasuredHeight());
                userdWidth+=view.getMeasuredWidth();
            }
        }

    }


}
