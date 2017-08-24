package android.test.mvp.com.svglistviewdemo.view.adapter;

import android.content.Context;
import android.test.mvp.com.svglistviewdemo.view.viewholder.BaseViewHolder;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by HuangMei on 2017/6/27.
 */

public abstract class MBaseAdapter<T, K extends BaseViewHolder> extends BaseAdapter implements AdapterLifeCycle {

    protected Context mContext;
    protected List<T> mDatas;
    protected LayoutInflater mInflater;
    protected DataWatcher mDataWatcher;

    public MBaseAdapter(Context mContext) {
        this.mContext = mContext;
        mInflater = (LayoutInflater)mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    public void setmDatas(List<T> datas) {
        if (datas != null){
            mDatas = new ArrayList<>(datas.size());
            mDatas.addAll(datas);
        } else {
            mDatas = null;
        }
        notifyDataSetChanged();
    }

    public void setmDatas(List<T> datas, boolean isRefresh){
        if (mDatas == null || isRefresh){
            mDatas = new ArrayList<>();
        }
        if (datas != null){
            mDatas.addAll(datas);
        }
        notifyDataSetChanged();
    }

    public T getData(int position){
        return mDatas.get(position);
    }

    public void removeData(T data){
        mDatas.remove(data);
        notifyDataSetChanged();
    }

    @Override
    public void notifyDataSetChanged() {
        if (mDataWatcher != null){
            mDataWatcher.onDataChanged(getCount());
        }
        super.notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        return mDatas.size();
    }

    @Override
    public Object getItem(int position) {
        return mDatas.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        K holder;
        if (convertView == null){
            holder = onCreateViewHolder(position, parent);
            convertView = holder.rootView;
            convertView.setTag(holder);
        } else {
            holder = (K) convertView.getTag();
        }

        onBindViewHolder(holder, position);
        return convertView;
    }

    public abstract K onCreateViewHolder(int position, ViewGroup parent);

    public abstract void onBindViewHolder(K holder, int position);

    public interface DataWatcher{
        void onDataChanged(int count);
    }

    public void setDataWatcher(DataWatcher dataWatcher){
        this.mDataWatcher = dataWatcher;
    }

    @Override
    public void onAdapterPause() {

    }

    @Override
    public void onAdapterResume() {

    }
}
