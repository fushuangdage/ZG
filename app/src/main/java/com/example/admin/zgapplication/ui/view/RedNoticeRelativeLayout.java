package com.example.admin.zgapplication.ui.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.widget.RelativeLayout;

import com.example.admin.zgapplication.utils.system.ScreenUtil;

/**
 * Created by fushuang on 2017/11/6.
 */

public class RedNoticeRelativeLayout extends RelativeLayout {

    private Paint paint;
    private int radius;
    private int width;
    private int height;

    public RedNoticeRelativeLayout(Context context) {
        super(context);
        init(context);
    }

    private void init(Context context) {
        paint = new Paint(Paint.ANTI_ALIAS_FLAG);
        paint.setColor(Color.parseColor("#ff0000"));
        radius = ScreenUtil.dp2px(context, 3);
    }

    public RedNoticeRelativeLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        width = getMeasuredWidth();
        height = getMeasuredHeight();
    }

    @Override
    protected void onDraw(Canvas canvas){
        super.onDraw(canvas);
        canvas.drawCircle(width-radius,radius,radius,paint);
    }
}
