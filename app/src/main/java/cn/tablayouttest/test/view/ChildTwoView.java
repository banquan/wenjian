package cn.tablayouttest.test.view;

import java.util.ArrayList;

import cn.tablayouttest.test.model.Picture;

/**
 * Created by yu on 2017/10/19.
 */

public interface ChildTwoView {
    void getPictureListSuccess(ArrayList<Picture> arrayList);

    void getDocumentListFailed();
}
