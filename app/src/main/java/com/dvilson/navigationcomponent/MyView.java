package com.dvilson.navigationcomponent;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.view.View;

import androidx.annotation.Nullable;

/**
 * TODO: document your custom view class.
 */
public class MyView extends View{
    private Rect mRectSquare;
    private Paint mPaintSquare;


    public MyView(Context context) {
        super(context);
        init(null);
    }

    public MyView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init(attrs);
    }

    public MyView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(attrs);
    }

    public MyView(Context context, @Nullable AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        init(attrs);
    }

    private void init(@Nullable AttributeSet set){

        mRectSquare = new Rect();
        mPaintSquare = new Paint();
        mPaintSquare.setColor(Color.GREEN);
        postInvalidate();

    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        mRectSquare.left = 50;
        mRectSquare.top = 50;
        mRectSquare.right = mRectSquare.left +100;
        mRectSquare.bottom = mRectSquare.top+100;

        canvas.drawRect(mRectSquare,mPaintSquare);
    }

    private void swapColor(){
        mPaintSquare.setColor(mPaintSquare.getColor()== Color.GREEN ? Color.RED:Color.GREEN);

    }
}