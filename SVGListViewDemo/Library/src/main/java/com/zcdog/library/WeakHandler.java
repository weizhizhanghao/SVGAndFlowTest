package com.zcdog.library;

import android.os.Handler;
import android.os.Looper;
import android.os.Message;

import java.lang.ref.WeakReference;

/**
 * 使用hook操作宿主方法，规避代码不当造成的内存泄漏问题
 * Created by zhjh on 2017/6/28.
 */
public class WeakHandler<Hook> extends Handler {
    private WeakReference<Hook> mWeakReference;

    public WeakHandler(Hook hook, Looper looper) {
        super(looper);
        mWeakReference = new WeakReference<Hook>(hook);
    }

    public WeakHandler(Hook hook) {
        mWeakReference = new WeakReference<Hook>(hook);
    }

    @Override
    public final void handleMessage(Message msg) {
        if (mWeakReference == null) {
            return;
        }

        Hook hook = mWeakReference.get();
        if (hook != null) {
            HandleMessage(hook, msg);
        }
    }

    public void HandleMessage(Hook hook, Message msg) {

    }


    public void recycle() {
        if (mWeakReference != null) {
            mWeakReference.clear();
        }

        mWeakReference = null;
    }

}
