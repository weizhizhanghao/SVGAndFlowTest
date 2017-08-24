package android.test.mvp.com.svglistviewdemo.utils;

import android.util.Log;

/**
 * Created by HuangMei on 2017/8/21.
 */

public class SynchronizeTest {
    //no matter is have static ,the number that logged out are ordered
    public static synchronized void testA(String str){
        for (int i = 0; i < 5; i ++){
            Log.i(str, " " + i + " ");
        }
    }
}
