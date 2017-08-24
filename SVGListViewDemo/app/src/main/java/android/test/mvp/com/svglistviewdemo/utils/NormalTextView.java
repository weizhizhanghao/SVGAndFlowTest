package android.test.mvp.com.svglistviewdemo.utils;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.support.annotation.Nullable;
import android.test.mvp.com.svglistviewdemo.R;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by HuangMei on 2017/8/23.
 */

public class NormalTextView extends View{

    private String mText;
    private int mTextColor;
    private int mTextSize;

    private Rect mBound;
    private Paint mPaint;

    public NormalTextView(Context context) {
        this(context, null);
    }

    public NormalTextView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public NormalTextView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        TypedArray typedArray = context.getTheme().obtainStyledAttributes(attrs, R.styleable.NormalTextView, defStyleAttr, 0);

        mText = typedArray.getString(R.styleable.NormalTextView_mText);
        mTextColor = typedArray.getColor(R.styleable.NormalTextView_mTextColor, Color.BLACK);
        mTextSize = typedArray.getDimensionPixelSize(R.styleable.NormalTextView_mTextSize, 100);
        typedArray.recycle();
        mPaint = new Paint();
        mPaint.setTextSize(mTextSize);
        mPaint.setColor(mTextColor);
        mBound = new Rect();
        mPaint.getTextBounds(mText, 0, mText.length(), mBound);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        canvas.drawText(mText, getWidth() / 2 - mBound.width() / 2, getHeight() / 2 + mBound.height() / 2, mPaint);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        int widthMode = MeasureSpec.getMode(widthMeasureSpec);
        int heightMode = MeasureSpec.getMode(heightMeasureSpec);
        int widthSize = MeasureSpec.getSize(widthMeasureSpec);
        int heightSize = MeasureSpec.getSize(heightMeasureSpec);

        int width;
        int height;
        if (widthMode == MeasureSpec.EXACTLY){
            width = widthSize;
        } else {
            float textWidth = mBound.width();
            width = (int) (getPaddingLeft() + textWidth + getPaddingRight());
        }

        if (heightMode == MeasureSpec.EXACTLY){
            height = heightSize;
        } else {
            float textHeight = mBound.height();
            height = (int) (getPaddingTop() + textHeight + getPaddingBottom());
        }

        setMeasuredDimension(width, height);
    }
}
