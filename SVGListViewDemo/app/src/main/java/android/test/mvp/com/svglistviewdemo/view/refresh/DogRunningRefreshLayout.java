package android.test.mvp.com.svglistviewdemo.view.refresh;

import android.content.Context;
import android.graphics.drawable.AnimationDrawable;
import android.test.mvp.com.svglistviewdemo.R;
import android.test.mvp.com.svglistviewdemo.anim.IPullRefreshRule;
import android.test.mvp.com.svglistviewdemo.utils.DogRunAnimHelper;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.chanven.commonpulltorefresh.PtrDefaultHandler;
import com.chanven.commonpulltorefresh.PtrFrameLayout;
import com.chanven.commonpulltorefresh.PtrUIHandler;
import com.chanven.commonpulltorefresh.indicator.PtrIndicator;

/**
 * Created by HuangMei on 2017/8/1.
 */

public class DogRunningRefreshLayout extends PtrFrameLayout implements PtrUIHandler, IPullRefreshRule{

    private AnimationDrawable animationDrawable;
    private static final int DOG_RUNNING_CLOSE = 80;
    private static final int DURATION_CLOSE_HEADER = 500;
    private static final String TEXT_PULL_REFRESH = "下拉刷新";
    private static final String TEXT_RELEASE_REFRESH="松开即可刷新";
    private static final String TEXT_REFRESHING = "正在刷新...";

    private final int[] ids = new int[]{
            R.drawable.anim1,  R.drawable.anim3,
            R.drawable.anim5,  R.drawable.anim7,  R.drawable.anim9,R.drawable.anim11,  R.drawable.anim13,
            R.drawable.anim15,  R.drawable.anim17,  R.drawable.anim19
    };
    private TextView refreshDesc;

    public DogRunningRefreshLayout(Context context) {
        super(context);
    }

    public DogRunningRefreshLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public DogRunningRefreshLayout(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    private void init(Context context){
        View header = View.inflate(context, R.layout.layout_mall_refreshheader, null);
        ImageView imageView = (ImageView) header.findViewById(R.id.img);
        refreshDesc = (TextView) header.findViewById(R.id.refresh_desc);
        refreshDesc.setText(TEXT_PULL_REFRESH);
        animationDrawable = DogRunAnimHelper.makeAnActor(getResources(), ids, 100);
        setLoadingMinTime(900);
        imageView.setBackground(animationDrawable);
        setHeaderView(header);
        setResistance(RefreshConstants.FACTOR);
        setRatioOfHeaderHeightToRefresh(1);
        setDurationToClose(DURATION_CLOSE_HEADER);
        addPtrUIHandler(this);
        setPtrHandler(new PtrDefaultHandler() {
            @Override
            public void onRefreshBegin(PtrFrameLayout frame) {
                if (refreshListener != null){
                    postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            if (refreshListener != null){
                                refreshListener.onRefresh();
                            }
                        }
                    }, DURATION_CLOSE_HEADER);
                }
            }
        });
        disableWhenHorizontalMove(true);
    }

    @Override
    public void onUIReset(PtrFrameLayout frame) {
        refreshDesc.setText(TEXT_PULL_REFRESH);
    }

    @Override
    public void onUIRefreshPrepare(PtrFrameLayout frame) {

    }

    @Override
    public void onUIRefreshBegin(PtrFrameLayout frame) {
        startRun();
        refreshDesc.setText(TEXT_REFRESHING);
    }

    @Override
    public void onUIRefreshComplete(PtrFrameLayout frame) {
        stopRun();
    }

    @Override
    public void onUIPositionChange(PtrFrameLayout frame, boolean isUnderTouch, byte status, PtrIndicator ptrIndicator) {
        final int mOffsetToRefresh = frame.getOffsetToRefresh();
        final int currentPos = ptrIndicator.getCurrentPosY();
        final int lastPos = ptrIndicator.getLastPosY();

        if (currentPos < mOffsetToRefresh && lastPos >= mOffsetToRefresh){
            if (isUnderTouch && status == PtrFrameLayout.PTR_STATUS_PREPARE){
                refreshDesc.setText(TEXT_PULL_REFRESH);
            }
        } else if (currentPos > mOffsetToRefresh && lastPos <= mOffsetToRefresh){
            if (isUnderTouch && status == PtrFrameLayout.PTR_STATUS_PREPARE){
                refreshDesc.setText(TEXT_PULL_REFRESH);
            }
        }
    }

    private OnRefreshListener refreshListener;
    @Override
    public void setOnRefreshListener(OnRefreshListener refreshListener) {
        this.refreshListener = refreshListener;
    }

    public void setRefreshing(){
        setRefreshing(true);
    }

    @Override
    public void setRefreshing(boolean b) {
        if (b){
            setUIRefreshing();
        } else {
            refreshComplete();
        }
    }

    private void startRun(){
        if (animationDrawable != null){
            postDelayed(new Runnable() {
                @Override
                public void run() {
                    animationDrawable.start();
                }
            }, DURATION_CLOSE_HEADER);
        }
    }

    private void stopRun(){
        if (animationDrawable != null && animationDrawable.isRunning()){
            animationDrawable.stop();
        }
    }
}
