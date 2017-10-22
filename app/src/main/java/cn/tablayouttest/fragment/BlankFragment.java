package cn.tablayouttest.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

import cn.tablayouttest.R;
import cn.tablayouttest.model.FileAnd;

/**
 * Created by yu on 2017/10/16.
 */

public class BlankFragment extends Fragment {
    private RecyclerView mRecyclerView;
    private ArrayList<FileAnd> fileAnds;

    public BlankFragment() {

    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        int i = getArguments().getInt("key");
        Log.e( "onCreate: ",i+"-=-=-=" );

    }

    public static BlankFragment newInstance(String text) {

        Log.e("666666",text);

        Bundle args = new Bundle();
        args.putString("text",text);

        //上面那样子传递数值的。

        BlankFragment fragment = new BlankFragment();
        fragment.setArguments(args);

        return fragment;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_blank, container, false);
        mRecyclerView = (RecyclerView) view.findViewById(R.id.recycler_view);


//        mRecyclerView.setHasFixedSize(true);
//        mRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
//
//
//        Bundle bundleTop = getArguments();
//        ArrayList<FileAnd> mFileAnds = bundleTop.getParcelableArrayList("mFileAnds");
//        Log.e("aaaaaaaaaaa", "onCreateView: " + mFileAnds);
//
//
//        AllAdpater mAllAdpater = new AllAdpater(getContext(), mFileAnds);
//        //        mShowPicAdapter = new ShowPicAdapter(this,null);
//        mRecyclerView.setAdapter(mAllAdpater);



        return view;
    }



    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        TextView text = (TextView) view.findViewById(R.id.fg_text);
        String str = getArguments().getString("text");
        text.setText(str);
    }


}