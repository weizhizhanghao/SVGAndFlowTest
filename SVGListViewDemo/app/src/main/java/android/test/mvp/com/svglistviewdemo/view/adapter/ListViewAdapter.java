package android.test.mvp.com.svglistviewdemo.view.adapter;

import android.content.Context;
import android.test.mvp.com.svglistviewdemo.R;
import android.test.mvp.com.svglistviewdemo.view.viewholder.ListViewHolder;
import android.view.ViewGroup;

/**
 * Created by HuangMei on 2017/6/26.
 */

public class ListViewAdapter extends MBaseAdapter<String, ListViewHolder>{

    public ListViewAdapter(Context mContext) {
        super(mContext);
    }

    @Override
    public ListViewHolder onCreateViewHolder(int position, ViewGroup parent) {
        return new ListViewHolder(mInflater.inflate(R.layout.list_item, null));
    }

    @Override
    public void onBindViewHolder(ListViewHolder holder, int position) {
        String name = getData(position);
        holder.render(name);
    }
}
