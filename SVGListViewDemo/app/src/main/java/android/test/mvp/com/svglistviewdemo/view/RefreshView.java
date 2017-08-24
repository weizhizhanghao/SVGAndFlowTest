package android.test.mvp.com.svglistviewdemo.view;

import android.content.Context;
import android.test.mvp.com.svglistviewdemo.R;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewTreeObserver;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

/**
 * Created by HuangMei on 2017/6/24.
 */

public class RefreshView extends FrameLayout {

    private static final CharSequence TEXT_LOAD_START = "下拉推荐";
    private static final CharSequence TEXT_LOAD = "推荐中";
    private static final CharSequence TEXT_LOAD_FINISH = "松开推荐";
    private static final int STATE_NONE = 0;
    private static final int STATE_DOWN = 1;
    private static final int STATE_UP = 2;
    private static final int STATE_REFRESHING = 3;

    private LinearLayout headView;
    private int headH;
    private TextView textView;
    private ImageView imgView;

    private int downY, moveY, lastMoveY, upY;
    private int diff, moveDiff;
    private int state = STATE_NONE;
    private int textViewHeight;
    private int imgViewHeight;

    public RefreshView(Context context) {
        super(context);
        init();
    }

    public RefreshView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public RefreshView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    public void init(){
        headView = (LinearLayout) View.inflate(getContext(), R.layout.refresh_header_view, null);
        addView(headView, LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT);

        textView = (TextView)headView.findViewById(R.id.text);
        imgView = (ImageView) headView.findViewById(R.id.img);

        setViewHeight();
        setRefreshState(state);
    }

    //测量各个View的高度
    void setViewHeight(){
        ViewTreeObserver vto = imgView.getViewTreeObserver();
        vto.addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                imgView.getViewTreeObserver().removeGlobalOnLayoutListener(this);
                imgViewHeight = imgView.getHeight();
                imgView.getWidth();
            }
        });

        ViewTreeObserver vto1 = textView.getViewTreeObserver();
        vto1.addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                textView.getViewTreeObserver().removeGlobalOnLayoutListener(this);
                textViewHeight = textView.getHeight();
                textView.getWidth();
            }
        });

        ViewTreeObserver viewTreeObserver = textView.getViewTreeObserver();
        viewTreeObserver.addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                headView.getViewTreeObserver().removeGlobalOnLayoutListener(this);
                headH = headView.getHeight();
                headView.getWidth();
            }
        });
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        return onTouchEvent(ev);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        Log.i("Height Info", "textViewHeight = " + textViewHeight + "   headH = " + headH + " imgViewHeight = " + imgViewHeight);
        switch (event.getAction()){
            case MotionEvent.ACTION_DOWN:
                downY = lastMoveY = (int)event.getY();
                state = STATE_NONE;
                setRefreshState(state);
                break;
            case MotionEvent.ACTION_MOVE:
                moveY = (int) event.getY();
                moveDiff = moveY - lastMoveY;
                lastMoveY = moveY;
                diff = moveY - downY;

                if (diff < 0){
                    state = STATE_NONE;
                } else if (diff < textViewHeight + imgViewHeight){
                    state = STATE_DOWN;
                } else if (diff >= textViewHeight + imgViewHeight){
                    state = STATE_REFRESHING;
                }
                setRefreshState(state);
                break;
            case MotionEvent.ACTION_UP:
                upY = (int) event.getY();
                diff = upY - downY;
                if (diff >= textViewHeight + imgViewHeight){
                    state = STATE_UP;
                } else {
                    state = STATE_NONE;
                }
                setRefreshState(state);
                break;
        }
        Log.i("Height Info", "textViewHeight = " + textViewHeight + " headH = " + headH + " imgViewHeight = " + imgViewHeight);
        Log.i("Info", "Diff = " + diff);
        return true;
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        headH = headView.getMeasuredHeight();
        textViewHeight = textView.getMeasuredHeight();
        imgViewHeight = imgView.getMeasuredHeight();
    }

    private void setRefreshState(int refreshState){
        switch (refreshState){
            case STATE_DOWN:
                headView.scrollTo(0, headH - diff);
                Log.i("Info Difff", "headH = " + headH + "Diff = " + diff);
                headView.setVisibility(VISIBLE);
                textView.setText(TEXT_LOAD_START);
            case STATE_UP:
                headView.setVisibility(VISIBLE);
                headView.scrollTo(0, 0);
                textView.setText(TEXT_LOAD);
                break;
            case STATE_REFRESHING:
                headView.setVisibility(VISIBLE);
                headView.scrollTo(0, headH - diff);
                textView.setText(TEXT_LOAD_FINISH);
                break;
            case STATE_NONE:
                headView.scrollTo(0, headH);
                headView.setVisibility(VISIBLE);
                break;
        }
    }

//    @Override
//    protected void onLayout(boolean changed, int l, int t, int r, int b) {
//        layoutChildren();
//    }
//
//    private void layoutChildren(){
//      //  int offsetY = 0;
//        int paddingLeft = getPaddingLeft();
//        int paddingTop = getPaddingTop();
//
//        if (headView != null){
//            MarginLayoutParams lp = (MarginLayoutParams)headView.getLayoutParams();
//            final int left = paddingLeft + lp.leftMargin;
//            final int top = paddingTop + lp.rightMargin;
//            final int right = left + headView.getMeasuredWidth();
//            final int bottom = top + headView.getMeasuredHeight();
//
//            headView.layout(left, top, right, bottom);
//        }
//    }
}
