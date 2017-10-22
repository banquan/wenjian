package cn.tablayouttest.test.view;

import java.util.ArrayList;

import cn.tablayouttest.test.model.Music;

/**
 * Created by yu on 2017/10/19.
 */

public interface ChildFourView {
    void getMusicListSuccess(ArrayList<Music> arrayList);

    void getMusicListFailed();
}
