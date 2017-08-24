package com.zcdog.jni.utils;

import android.util.Log;

import java.util.Arrays;

/**
 * Created by lihoubin on 15/8/17.
 */
public class CommonUtils {
    public static byte[] key;
    private static final String TAG ="CommonUtils";
    static {
        System.loadLibrary("ZcdogJni");
        key=getByteArrayFromNative();
    }
    public static native String getMd5StringFromNative(String info);
    public static native byte[] getByteArrayFromNative();

}
