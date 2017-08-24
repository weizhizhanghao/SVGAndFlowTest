package android.test.mvp.com.svglistviewdemo;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.test.mvp.com.svglistviewdemo.model.GetDataModel;
import android.test.mvp.com.svglistviewdemo.present.PendulumActivity;
import android.test.mvp.com.svglistviewdemo.present.SVGPathActivity;
import android.test.mvp.com.svglistviewdemo.present.SearchActivity;
import android.test.mvp.com.svglistviewdemo.present.SmileFaceActivity;
import android.test.mvp.com.svglistviewdemo.present.TestActivity;
import android.test.mvp.com.svglistviewdemo.view.RefreshView;
import android.test.mvp.com.svglistviewdemo.view.adapter.ListViewAdapter;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends Activity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void toSVG(View view){
        jumpToNewAct(new SVGPathActivity());
    }

    public void toSmile(View view){
        jumpToNewAct(new SmileFaceActivity());
    }

    public void toPendulum(View view){
        jumpToNewAct(new PendulumActivity());
    }

    public void toSearch(View view){
        jumpToNewAct(new SearchActivity());
    }

    public void toTest(View view){
        jumpToNewAct(new TestActivity());
    }

    private void jumpToNewAct(BaseActivity activity){
        Intent intent = new Intent(this, activity.getClass());
        startActivity(intent);
    }
}
