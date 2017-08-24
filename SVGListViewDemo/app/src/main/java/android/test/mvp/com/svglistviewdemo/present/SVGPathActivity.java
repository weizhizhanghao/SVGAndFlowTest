package android.test.mvp.com.svglistviewdemo.present;

import android.graphics.drawable.Animatable;
import android.os.Bundle;
import android.test.mvp.com.svglistviewdemo.BaseActivity;
import android.test.mvp.com.svglistviewdemo.R;
import android.test.mvp.com.svglistviewdemo.model.GetDataModel;
import android.test.mvp.com.svglistviewdemo.view.RefreshView;
import android.test.mvp.com.svglistviewdemo.view.adapter.ListViewAdapter;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class SVGPathActivity extends BaseActivity {


    private ImageView image;
    private ListView listView;
    private RefreshView freshView;
    private List<String> stringList = new ArrayList<>();
    private ListViewAdapter listViewAdapter;
    private GetDataModel getDataModel = new GetDataModel();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_svgpath);
//
//        listView = (ListView) findViewById(R.id.list_view);
//        freshView = (RefreshView) findViewById(R.id.refresh_view);
//
//        listViewAdapter = new ListViewAdapter(this);
//        listViewAdapter.setmDatas(getDataModel.getDataModel(0));
//
////        freshView.scrollBy(0, 100);
//
//        listView.setAdapter(listViewAdapter);



        //   image = (ImageView) findViewById(R.id.image);
//        Animatable animatable = (Animatable) image.getDrawable();
//        animatable.start();
    }

    public void onStartRun(final View view){
        ImageView imageView = (ImageView)view;
        Animatable animatable = (Animatable) imageView.getDrawable();
        animatable.start();
    }

    private void setListViewData(int num){
        for (int i = 0; i < num; i ++){
            stringList.add(i + "test number i = " + i);
        }
    }
}
