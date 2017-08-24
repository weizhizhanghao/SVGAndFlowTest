package android.test.mvp.com.svglistviewdemo.utils;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.support.annotation.Nullable;
import android.test.mvp.com.svglistviewdemo.R;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by HuangMei on 2017/8/23.
 */

public class AutoLinefeedTextView extends View{

    private Rect mBound;
    private Paint mPaint;
    private int mTextSize;
    private int mTextColor;
    private String mTextStr;
    private List<String> mStrList;

    public AutoLinefeedTextView(Context context) {
        this(context, null);
    }

    public AutoLinefeedTextView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public AutoLinefeedTextView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        mStrList = new ArrayList<>();
        TypedArray typedArray = context.getTheme().obtainStyledAttributes(attrs, R.styleable.NormalTextView, defStyleAttr, 0);
        mTextStr = typedArray.getString(R.styleable.NormalTextView_mText);
        mTextColor = typedArray.getColor(R.styleable.NormalTextView_mTextColor, Color.BLACK);
        mTextSize = typedArray.getDimensionPixelSize(R.styleable.NormalTextView_mTextSize, 100);
        typedArray.recycle();
        mPaint = new Paint();
        mPaint.setTextSize(mTextSize);
        mPaint.setColor(mTextColor);
        mBound = new Rect();
        mPaint.getTextBounds(mTextStr, 0, mTextStr.length(), mBound);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        for (int i = 0; i < mStrList.size(); i ++){
            mPaint.getTextBounds(mStrList.get(i), 0, mStrList.get(i).length(), mBound);
            canvas.drawText(mStrList.get(i), (getWidth() / 2 - mBound.width() / 2), (getPaddingTop() + (mBound.height() * i)), mPaint);
        }
    }

    boolean isOpenLines = true;
    float lineNum;
    float splineNum;

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        int widthMode = MeasureSpec.getMode(widthMeasureSpec);
        int heightMode = MeasureSpec.getMode(heightMeasureSpec);
        int widthSize = MeasureSpec.getSize(widthMeasureSpec);
        int heightSize = MeasureSpec.getSize(heightMeasureSpec);

        float textWidth = mBound.width();
        if (mStrList.size() == 0){
            int padding = getPaddingLeft() + getPaddingRight();
            int specWidth = widthSize - padding;
            if (textWidth < specWidth){
                lineNum = 1;
                mStrList.add(mTextStr);
            } else {
                isOpenLines = false;
                splineNum = textWidth / specWidth;
                String splineNumStr = splineNum + "";
                if (splineNumStr.contains(".")){
                    lineNum = Integer.parseInt(splineNumStr.substring(0, splineNumStr.indexOf("."))) + 1;
                } else {
                    lineNum = splineNum;
                }
                int lineLength = (int)(mTextStr.length() / splineNum);
                for (int i = 0; i < lineNum; i ++){
                    String lineStr;
                    if (mTextStr.length() < lineLength){
                        lineStr = mTextStr.substring(0, mTextStr.length());
                    } else {
                        lineStr = mTextStr.substring(0, lineLength);
                    }

                    mStrList.add(lineStr);

                    if (!TextUtils.isEmpty(lineStr)){
                        if (mTextStr.length() < lineLength){
                            mTextStr = mTextStr.substring(0, mTextStr.length());
                        } else {
                            mTextStr = mTextStr.substring(0, lineLength);
                        }
                    } else {
                        break;
                    }
                }
            }
        }

        int width;
        int height;

        if (widthMode == MeasureSpec.EXACTLY){
            width = widthSize;
        } else {
            if (isOpenLines){
                width = (int) (getPaddingLeft() + textWidth + getPaddingRight());
            } else {
                width = widthSize;
            }
        }

        if (heightMode == MeasureSpec.EXACTLY){
            height = heightSize;
        } else {
            float textHeight = mBound.height();
            if (isOpenLines){
                height = (int) (getPaddingTop() + textHeight + getPaddingBottom());
            } else {
                height = (int) (getPaddingTop() + textHeight * lineNum + getPaddingBottom());
            }
        }

        setMeasuredDimension(width, height);
    }
}
