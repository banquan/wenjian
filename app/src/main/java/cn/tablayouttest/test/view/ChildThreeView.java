package cn.tablayouttest.test.view;

import java.util.ArrayList;

import cn.tablayouttest.test.model.Video;

/**
 * Created by yu on 2017/10/19.
 */

public interface ChildThreeView {
    void getVideoListSuccess(ArrayList<Video> arrayList);

    void getVideoListFailed();
}
