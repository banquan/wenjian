package cn.tablayouttest.test.view;

import java.util.ArrayList;

import cn.tablayouttest.test.model.Other;

/**
 * Created by yu on 2017/10/19.
 */

public interface ChildFiveView {
    void test();

    void getOtherListSuccess(ArrayList<Other> arrayList);

    void getOtherListFailed();
}
