package com.example.admin.zgapplication.ui.view;

import android.animation.Animator;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.AccelerateDecelerateInterpolator;

/**
 * Created by fushuang on 2017/9/13.
 */

public class LoadingView extends View {

    private Paint mPaint;
    private float mProgress;
    private int mWidth;
    private int mHeight;
    private int mRadius;
    private int mLineWidth = 20;
    private RectF rectF;
    private float mStartAngle = -90f;
    private Paint mEraserPaint;


    public LoadingView(Context context) {
        super(context, null);
    }

    public LoadingView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        return super.onTouchEvent(event);
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent event) {
        return super.dispatchTouchEvent(event);
    }

    private void init() {
        mPaint = new Paint();
        mPaint.setAntiAlias(true);
        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setStrokeWidth(mLineWidth);


        mEraserPaint = new Paint();
        mEraserPaint.setAntiAlias(true);
        mEraserPaint.setStyle(Paint.Style.STROKE);
        mEraserPaint.setStrokeWidth(mLineWidth+5);
        mEraserPaint.setColor(Color.WHITE);

        ValueAnimator valueAnimator = ObjectAnimator.ofFloat(60,300);
        valueAnimator.setInterpolator(new AccelerateDecelerateInterpolator());
        valueAnimator.setRepeatCount(-1);
        valueAnimator.setDuration(3000).addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                mProgress = (float) animation.getAnimatedValue();
                mStartAngle++;
                postInvalidate();
            }
        });
        valueAnimator.addListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animation) {

            }

            @Override
            public void onAnimationEnd(Animator animation) {

            }

            @Override
            public void onAnimationCancel(Animator animation) {

            }

            @Override
            public void onAnimationRepeat(Animator animation) {

                    mStartAngle=mStartAngle-120;
                    mStartAngle=mStartAngle%360;
            }
        });
        valueAnimator.start();


    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        mWidth = getMeasuredWidth();
        mHeight = getMeasuredHeight();
        mRadius = mWidth > mHeight ? mHeight / 2 : mWidth / 2;

        rectF = new RectF(mWidth / 2 - mRadius + mLineWidth,
                mHeight / 2 - mRadius + mLineWidth,
                mWidth / 2 + mRadius - mLineWidth,
                mHeight / 2 + mRadius - mLineWidth);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        mPaint.setColor(Color.GREEN);
        canvas.drawArc(rectF,mStartAngle,mProgress,false,mPaint);
        canvas.drawArc(rectF,mStartAngle+mProgress,mProgress, false, mEraserPaint);
    }


}
