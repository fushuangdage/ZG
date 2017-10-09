package com.example.admin.zgapplication.ui.view;

import android.content.Context;
import android.support.design.widget.CoordinatorLayout;
import android.support.v4.view.ViewCompat;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;

import com.example.admin.zgapplication.R;

/**
 * Created by fushuang on 2017/9/29.
 */

public class MyScrollBehavior extends CoordinatorLayout.Behavior<View> {

    public MyScrollBehavior(Context context, AttributeSet attrs) {
        super(context, attrs);  //必须重写此构造方法,coordinatorLayout 通过反射获取该方法
    }

    @Override
    public boolean onLayoutChild(CoordinatorLayout parent, View child, int layoutDirection) {

        if (child.getId()== R.id.rl_bottom_sheet) {
            child.layout(0,0,parent.getWidth(),parent.getHeight());
            child.setTranslationY(1000);
            return true;
        }

        return super.onLayoutChild(parent, child, layoutDirection);
    }

    @Override
    public boolean onInterceptTouchEvent(CoordinatorLayout parent, View child, MotionEvent ev) {
        return true;
    }

    @Override
    public boolean onTouchEvent(CoordinatorLayout parent, View child, MotionEvent ev) {
        Log.d("6666666666", "onTouchEvent: "+ev.getRawX());


        return true;
    }

    @Override
    public boolean onStartNestedScroll(CoordinatorLayout coordinatorLayout, View child, View directTargetChild, View target, int nestedScrollAxes) {
        return (nestedScrollAxes & ViewCompat.SCROLL_AXIS_VERTICAL) != 0;
    }

    @Override
    public void onNestedPreScroll(CoordinatorLayout coordinatorLayout, View child, View target, int dx, int dy, int[] consumed) {
        super.onNestedPreScroll(coordinatorLayout, child, target, dx, dy, consumed);
        if(dy < 0){
            return;
        }

        float transY =  child.getTranslationY() - dy;
        Log.i("66666666666","transY:"+transY+"++++child.getTranslationY():"+child.getTranslationY()+"---->dy:"+dy);
        if(transY > 0){
            child.setTranslationY(transY);
            consumed[1]= dy;
        }
    }


}
