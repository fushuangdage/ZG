package com.example.admin.zgapplication.ui.view;

import android.animation.ValueAnimator;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.support.annotation.Nullable;
import android.text.TextPaint;
import android.util.AttributeSet;
import android.view.View;
import android.view.animation.LinearInterpolator;

import com.example.admin.zgapplication.R;
import com.example.admin.zgapplication.utils.date.TimeUtil;

import java.text.SimpleDateFormat;
import java.util.Date;


/**
 * Created by fushuang on 2017/9/28.
 */

public class CustomProgressView extends View{

    private int measuredWidth;
    private int measuredHeight;
    private TextPaint mTextPaint;
    private Paint mCirclePaint;
    private float progress=0.5f;
    private int radius;
    private String time="02:30";  //记录抢单剩余时间
    private Paint bgPaint;
    private String count="3";   //记录抢单经纪人数
    private RectF rectF;
    private SimpleDateFormat simpleDateFormat;
    private Date date;
    private boolean isCountDown;
    private boolean drawGrayBg;
    private boolean drawCenterPic;
    private Bitmap runBitmap;


    public float getProgress() {
        return progress;
    }

    public void setProgress(float progress) {
        this.progress = progress;
    }

    public CustomProgressView(Context context) {
        super(context);
    }

    public CustomProgressView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        TypedArray ta = context.obtainStyledAttributes(attrs, R.styleable.CustomProgressView);
        isCountDown = ta.getBoolean(R.styleable.CustomProgressView_clock_wise, false);
        drawGrayBg = ta.getBoolean(R.styleable.CustomProgressView_draw_gray_bg, false);
        drawCenterPic = ta.getBoolean(R.styleable.CustomProgressView_draw_center_pic, false);
        init(context);

    }

    private void init(Context context) {
        mTextPaint = new TextPaint();
        mTextPaint.setTextAlign(Paint.Align.CENTER);
        mTextPaint.setStyle(Paint.Style.STROKE);
        mTextPaint.setAntiAlias(true);
        mCirclePaint = new Paint();
        mCirclePaint.setStyle(Paint.Style.STROKE);
        mCirclePaint.setAntiAlias(true);
        mCirclePaint.setColor(Color.parseColor("#4dad01"));
        mCirclePaint.setStrokeWidth(5);
        bgPaint = new Paint();
        bgPaint.setColor(Color.WHITE);
        bgPaint.setAntiAlias(true);
        bgPaint.setStyle(Paint.Style.FILL);
        simpleDateFormat = new SimpleDateFormat("mm:ss");
        if (drawCenterPic){
            runBitmap = BitmapFactory.decodeResource(context.getResources(), R.drawable.person_run);
        }
//        if (isCountDown){
//            startReversal();
//        }else {
//            start();
//        }
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        measuredWidth = getMeasuredWidth();
        measuredHeight = getMeasuredHeight();
        radius= (int) (measuredWidth*0.4);
        mTextPaint.setTextSize(30);
        rectF = new RectF(((float) (measuredWidth * 0.05)),
                ((float) (measuredHeight * 0.05)),
                ((float) (measuredWidth * 0.95)),
                ((float) (measuredHeight * 0.95)));
    }


    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        mTextPaint.setColor(Color.parseColor("#4dad01"));

        if (drawGrayBg){
            mTextPaint.setColor(Color.parseColor("#dddddd"));
            mTextPaint.setStrokeWidth(5);
            canvas.drawArc(rectF,0,360,false,mTextPaint);
        }else {
            canvas.drawCircle(measuredWidth/2,measuredHeight/2,measuredHeight/2,bgPaint);
        }

        if (drawCenterPic){
            canvas.drawBitmap(runBitmap,getMeasuredWidth()/2-runBitmap.getWidth()/2,getMeasuredHeight()/2-runBitmap.getWidth()/2,bgPaint);
        }else {
            canvas.drawText(time,0,time.length(),measuredHeight/2, (float) (measuredHeight*0.4),mTextPaint);
            mTextPaint.setColor(Color.BLACK);
            canvas.drawText(count,0,count.length(),measuredHeight/2, (float) (measuredHeight*0.7),mTextPaint);
        }

        canvas.drawArc(rectF,-90,360*progress,false,mCirclePaint);

    }


    public void start(){

//        new Thread(new Runnable() {
//            @Override
//            public void run() {
//                int t=6000;
//                while (t>0){
//                    date = new Date(t);
//                    time = simpleDateFormat.format(CustomProgressView.this.date);
//                    postInvalidate();
//                    try {
//                        Thread.sleep(100);
//                        t=t-100;
//                    } catch (InterruptedException e) {
//                        e.printStackTrace();
//                    }
//                }
//            }
//        }).start();
        ValueAnimator valueAnimator = ValueAnimator.ofFloat(0, 300);
        valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                float value = (float) animation.getAnimatedValue();
                time=TimeUtil.formatData(TimeUtil.dateFormatms, (long) value);
                progress=  (value / 300f);
                postInvalidate();
            }
        });
        valueAnimator.setInterpolator(new LinearInterpolator());
        valueAnimator.setDuration(300000);
        valueAnimator.start();
    }

    public void startReversal(){

//        new Thread(new Runnable() {
//            @Override
//            public void run() {
//                int t=6000;
//                while (t>0){
//                    date = new Date(t);
//                    time = simpleDateFormat.format(CustomProgressView.this.date);
//                    postInvalidate();
//                    try {
//                        Thread.sleep(100);
//                        t=t-100;
//                    } catch (InterruptedException e) {
//                        e.printStackTrace();
//                    }
//                }
//            }
//        }).start();

        ValueAnimator valueAnimator = ValueAnimator.ofFloat(300, 0);
        valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                float value = (float) animation.getAnimatedValue();
//                time="00:"+ ((int) value);
                time = TimeUtil.formatData(TimeUtil.dateFormatms, (long) value);
                progress=  (value / 300f);
                postInvalidate();
            }
        });
        valueAnimator.setInterpolator(new LinearInterpolator());
        valueAnimator.setDuration(300000);
        valueAnimator.start();
    }



}
