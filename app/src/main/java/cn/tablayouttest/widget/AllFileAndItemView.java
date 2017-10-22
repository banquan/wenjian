package cn.tablayouttest.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.RelativeLayout;
import android.widget.TextView;

import butterknife.BindView;
import cn.tablayouttest.R;
import cn.tablayouttest.model.FileAnd;


/**
 * Created by yu on 2017/10/9.
 */

public class AllFileAndItemView extends RelativeLayout {


    @BindView(R.id.FileName)
    TextView mFileName;
    @BindView(R.id.File_MB)
    TextView mFileMB;
    @BindView(R.id.FileCreateTime)
    TextView mFileCreateTime;

    public AllFileAndItemView(Context context) {
        this(context, null);
    }

    public AllFileAndItemView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    private void init() {
        LayoutInflater.from(getContext()).inflate(R.layout.all_item_view, this);
    }


    public void bindView(FileAnd fileAnd) {
        mFileCreateTime.setText(fileAnd.getCreatedAt());
        mFileMB.setText(fileAnd.getFileSize());
        mFileName.setText(fileAnd.getFileName());
    }
}
