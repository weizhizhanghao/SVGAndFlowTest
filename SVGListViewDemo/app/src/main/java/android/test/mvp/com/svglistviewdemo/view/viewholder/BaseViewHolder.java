package android.test.mvp.com.svglistviewdemo.view.viewholder;

import android.view.View;

/**
 * Created by HuangMei on 2017/6/26.
 */

public abstract class BaseViewHolder {

    public final View rootView;

    public BaseViewHolder(View covertView) {
        rootView = covertView;
    }

    protected abstract void render(Object o);
}
