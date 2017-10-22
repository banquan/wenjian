package cn.tablayouttest.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.List;

import cn.tablayouttest.fragment.BlankFragment;

/**
 * Created by yu on 2017/10/16.
 */

public class MyAdapter extends FragmentPagerAdapter {
    private List<String> list;

    public MyAdapter(FragmentManager fm , List<String> list) {
        super(fm);
        this.list = list;
    }

    @Override
    public Fragment getItem(int position) {
        //这个方法就是让布局文件中的TextView显示部分中文内容。
        return new BlankFragment().newInstance(list.get(position));

    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return list.get(position);
    }


}
