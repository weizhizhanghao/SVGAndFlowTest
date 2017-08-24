package android.test.mvp.com.svglistviewdemo.present;

import android.os.Bundle;
import android.test.mvp.com.svglistviewdemo.BaseActivity;
import android.test.mvp.com.svglistviewdemo.R;
import android.test.mvp.com.svglistviewdemo.utils.SynchronizeTest;

public class TestActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);

        SynchronizeTest.testA("one");
        SynchronizeTest.testA("two");
//
//        SynchronizeTest one = new SynchronizeTest();
//        SynchronizeTest two = new SynchronizeTest();
//
//        one.testA("one");
//        two.testA("two");
    }
}
