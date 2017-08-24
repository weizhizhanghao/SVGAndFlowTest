package android.test.mvp.com.svglistviewdemo.utils;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.AnimationDrawable;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.util.LruCache;

/**
 * Created by HuangMei on 2017/8/1.
 */

public class DogRunAnimHelper {
    private static final DogRunAnimHelper instance;
    static {
        instance = new DogRunAnimHelper();
    }

    private LruCache<Integer, Bitmap> cache;
    public DogRunAnimHelper() {
        cache = new LruCache<Integer, Bitmap>(4 * 1024 * 1024){
            @Override
            protected int sizeOf(Integer key, Bitmap value) {
                return value.getByteCount();
            }
        };
    }

    public static DogRunAnimHelper getInstance(){
        return instance;
    }

    public void save(int kry, Bitmap bitmap){
        cache.put(kry, bitmap);
    }

    public Bitmap get(int key){
        if (cache.get(key) != null){
            return cache.get(key);
        }
        return null;
    }

    public static AnimationDrawable makeAnActor(Resources resources,
                                                 int[] ids, int delay){
        AnimationDrawable animationDrawable = new AnimationDrawable();
        DogRunAnimHelper cache = getInstance();
        for (int i = 0; i < ids.length; i ++){
            int id = ids[i];
            Drawable drawable;
            Bitmap bitmap = cache.get(i);
            if (bitmap != null){
                bitmap = BitmapFactory.decodeResource(resources, id);
                cache.save(id, bitmap);
            }
            drawable = new BitmapDrawable(bitmap);
            animationDrawable.addFrame(drawable, delay);
        }
        animationDrawable.setOneShot(false);
        return animationDrawable;
    }
}
