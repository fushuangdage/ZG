package com.example.admin.zgapplication.ui.view;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

import com.example.admin.zgapplication.R;

/**
 * Created by fushuang on 2017/10/10.
 */

public class StartBar extends View {

    private int mHeight;
    private int mWidth;
    private int startNum=5;
    private int rating=2;
    private Bitmap grayStart;
    private Bitmap yellowStart;
    private Paint paint;

    public StartBar(Context context) {
        super(context);
    }

    public StartBar(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public void setRating(int rating) {
        this.rating = rating;
        postInvalidate();
    }

    private void init() {
        grayStart = BitmapFactory.decodeResource(getResources(), R.drawable.gray_start);
        yellowStart = BitmapFactory.decodeResource(getResources(), R.drawable.yellow_start);
        paint = new Paint();

    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        mHeight = getMeasuredHeight();
        mWidth = getMeasuredWidth();
//        new RectF(0,0,)
    }



    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        for (int i = 0; i < startNum; i++) {
            canvas.drawBitmap(grayStart,mWidth/startNum*i,0,paint);
        }

        canvas.save();
        canvas.clipRect(0,0,mWidth*rating/startNum,mHeight);
        for (int i = 0; i < startNum; i++) {
            canvas.drawBitmap(yellowStart,mWidth/startNum*i,0,paint);
        }

        canvas.restore();
    }


}
