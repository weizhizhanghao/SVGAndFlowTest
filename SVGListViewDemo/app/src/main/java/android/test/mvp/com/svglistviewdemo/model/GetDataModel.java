package android.test.mvp.com.svglistviewdemo.model;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by HuangMei on 2017/6/27.
 */

public class GetDataModel {

    public List<String> getDataModel(int pageNum){
        List<String > stringList = new ArrayList<>(10);
        for (int i = pageNum * 10; i < (pageNum + 1) * 10; i ++){
            stringList.add("test string number i = " + i);
        }

        return stringList;
    }
}
