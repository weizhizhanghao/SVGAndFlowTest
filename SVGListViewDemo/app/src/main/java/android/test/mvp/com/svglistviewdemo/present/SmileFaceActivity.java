
package android.test.mvp.com.svglistviewdemo.present;

import android.graphics.drawable.Animatable;
import android.os.Bundle;
import android.test.mvp.com.svglistviewdemo.BaseActivity;
import android.test.mvp.com.svglistviewdemo.R;
import android.view.View;
import android.widget.ImageView;

public class SmileFaceActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_smile_face);
    }

    public void onFaceClicked(View view){
        start(view);
    }

    private void start(View view){
        ImageView imageView = (ImageView) view;
        Animatable animatable = (Animatable)imageView.getDrawable();
        if (animatable.isRunning()){
            animatable.stop();
        }
        animatable.start();
    }
}
