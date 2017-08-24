package android.test.mvp.com.svglistviewdemo.view.viewholder;

import android.test.mvp.com.svglistviewdemo.R;
import android.view.View;
import android.widget.TextView;

/**
 * Created by HuangMei on 2017/6/26.
 */

public class ListViewHolder extends BaseViewHolder{

    private TextView textView;

    public ListViewHolder(View covertView) {
        super(covertView);
        textView = (TextView) covertView.findViewById(R.id.name_tv);
    }

    @Override
    public void render(Object o) {
        String name = (String)o;
        textView.setText(name);
    }
}
