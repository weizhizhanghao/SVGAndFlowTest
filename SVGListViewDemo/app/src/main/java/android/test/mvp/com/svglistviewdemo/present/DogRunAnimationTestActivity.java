package android.test.mvp.com.svglistviewdemo.present;

import android.os.Bundle;
import android.test.mvp.com.svglistviewdemo.BaseActivity;
import android.test.mvp.com.svglistviewdemo.R;
import android.test.mvp.com.svglistviewdemo.view.refresh.DogRunningRefreshLayout;
import android.widget.ListView;

import java.util.List;

public class DogRunAnimationTestActivity extends BaseActivity {

    private DogRunningRefreshLayout mVRefreshLayout;
    private ListView mVList;
    private List<String> stringList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dog_run_animation_test);

        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(3000);
                }catch (InterruptedException e){
                    e.printStackTrace();
                }
            }
        }).start();
    }
}
