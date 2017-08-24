package android.test.mvp.com.svglistviewdemo.anim;

/**
 * Created by HuangMei on 2017/8/6.
 */

public interface IPullRefreshRule {
    void setOnRefreshListener(OnRefreshListener refreshListener);
    void setRefreshing(boolean b);

    interface OnRefreshListener{
        void onRefresh();
    }
}
