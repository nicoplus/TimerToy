package com.cc.geekband.timertoy;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by think on 2016-01-31.
 */
public class ColockView extends View {

    private int mFirstColor;

    private int mSecondColor;

    private int mCircleWidth;

    private Paint mPaint;

    private RectF mOval;

    private int mMillSecond;

    private int mSecond;

    private int mMinute;

    private  Rect mRect;

    private TimeNumber mTimeNumber;

    public ColockView(Context context) {
        this(context, null);
    }

    public ColockView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public ColockView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context, attrs);

    }

    private void init(Context context, AttributeSet attrs) {

        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.CustomProgressBar);
        mFirstColor = typedArray.getColor(R.styleable.CustomProgressBar_firstColor, Color.WHITE);
        mSecondColor = typedArray.getColor(R.styleable.CustomProgressBar_secondColor, Color.RED);
        mCircleWidth = typedArray.getDimensionPixelSize(R.styleable.CustomProgressBar_circleWidth, 10);
        typedArray.recycle();

        mPaint = new Paint();
        mOval = new RectF();
        mRect = new Rect();

        mTimeNumber = new TimeNumber();


    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        /*
        画第一个圆
         */
        int center = getWidth() / 2;
        int radius = center - mCircleWidth / 2;
        mPaint.setColor(mFirstColor);
        mPaint.setStrokeWidth(mCircleWidth);/*空心线宽*/
        mPaint.setAntiAlias(true);
        mPaint.setStyle(Paint.Style.STROKE);
        canvas.drawCircle(center, center, radius, mPaint);

        /*
        画圆弧
         */
        mOval.left = center - radius;
        mOval.top = center - radius;
        mOval.right = center + radius;
        mOval.bottom = center + radius;
        mPaint.setColor(mSecondColor);
        float progress =  ((mMillSecond + mSecond * 100.0f)*1.0f / 6000.0f * 360.0f);
        canvas.drawArc(mOval, 270,progress, false, mPaint);

        /*
        画数字
         */
        mPaint.setColor(mFirstColor);
        mPaint.setStrokeWidth(0);
        mPaint.setTextSize(getWidth() / 7);
        mMillSecond = (int)TimeNumber.getmMillSecond();
        mSecond = (int)TimeNumber.getmSecond();
        mMinute = (int)TimeNumber.getmMinute();

        String millSecond = "" + (mMillSecond<10 ? "0"+mMillSecond : mMillSecond);
        String second = "" + (mSecond < 10 ? "0" + mSecond : mSecond);
        String minute = "" + mMinute;

        //先画秒
        mPaint.getTextBounds(second,0,second.length(), mRect);
        canvas.drawText(second,getWidth()/2 - mRect.width()/2, getWidth()/2 + mRect.height()/2, mPaint );
        int distance = mRect.width();

        //再画分
        mPaint.getTextBounds(minute, 0, minute.length(), mRect);
        canvas.drawText(minute, getWidth()/2 - mRect.width() - distance, getWidth()/2 + mRect.height()/2, mPaint);

        //画毫秒
        mPaint.setTextSize(getWidth()/10);
        canvas.drawText(millSecond, getWidth()/2 + distance, getWidth()/2 + mRect.height()/2, mPaint);

        invalidate();
    }
}
