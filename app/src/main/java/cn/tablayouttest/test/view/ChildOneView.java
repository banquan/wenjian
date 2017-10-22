package cn.tablayouttest.test.view;

import java.util.ArrayList;

import cn.tablayouttest.test.model.Document;

/**
 * Created by yu on 2017/10/19.
 */

public interface ChildOneView {
    void getDocumentListSuccess(ArrayList<Document> arrayList);

    void getDocumentListFailed();
}
