package cn.tablayouttest.test.widget;

import android.content.Context;
import android.os.Environment;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.white.progressview.CircleProgressView;

import java.io.File;

import cn.bmob.v3.datatype.BmobFile;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.DownloadFileListener;
import cn.tablayouttest.R;
import cn.tablayouttest.test.model.Picture;


/**
 * Created by yu on 2017/10/9.
 */

public class ChildTwoItemView extends RelativeLayout {

    private ImageView mFilePic;
    private TextView mFileName;
    private TextView mFileMB;
    private TextView mFileUpdateTime;
    private Button mSee;
    private String objectId;
    private BmobFile mBmobFile;
    private CircleProgressView mCircleProgressView;

    public ChildTwoItemView(Context context) {
        this(context, null);
    }

    public ChildTwoItemView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    private void init() {
        View view = LayoutInflater.from(getContext()).inflate(R.layout.child_two_item_view, this);

        mFilePic = (ImageView) view.findViewById(R.id.FilePic);
        mFileName = (TextView) view.findViewById(R.id.FileName);
        mFileMB = (TextView) view.findViewById(R.id.File_MB);
        mFileUpdateTime = (TextView) view.findViewById(R.id.FileUpdateTime);
        mSee = (Button) view.findViewById(R.id.See);
        mSee.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.e("ChildOneItemView", objectId + " ");
                downloadFile(mBmobFile);
            }
        });

        mCircleProgressView = (CircleProgressView) view.findViewById(R.id.circle_progress_normal);
        mCircleProgressView.setProgress(0);

    }


    public void bindView(Picture picture) {

        this.objectId = picture.getOid();
        this.mBmobFile = picture.getBmobFile();

        Glide.with(getContext()).load(mBmobFile.getUrl()).into(mFilePic);
        mFileName.setText(picture.getPicName());
        mFileMB.setText(picture.getPicSize());
        mFileUpdateTime.setText(picture.getPicTime());
    }


    private void downloadFile(BmobFile file){
        //允许设置下载文件的存储路径，默认下载文件的目录为：context.getApplicationContext().getCacheDir()+"/bmob/"
        File saveFile = new File(Environment.getExternalStorageDirectory(), file.getFilename());
        file.download(saveFile, new DownloadFileListener() {

            @Override
            public void onStart() {
                Toast.makeText(getContext(), "开始下载", Toast.LENGTH_SHORT).show();
                mCircleProgressView.setVisibility(VISIBLE);
                mSee.setVisibility(GONE);
            }

            @Override
            public void done(String savePath,BmobException e) {
                if(e==null){
                    Toast.makeText(getContext(), "下载成功" +  savePath, Toast.LENGTH_SHORT).show();
                    Log.e("savePath",savePath);
                }else{
                    Toast.makeText(getContext(), "下载失败", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onProgress(Integer value, long newworkSpeed) {
                Log.i("bmob","下载进度："+value+","+newworkSpeed);

                mCircleProgressView.setProgress(value);
                if(value == 100){
                    mCircleProgressView.setVisibility(GONE);
                    mSee.setVisibility(VISIBLE);
                }

            }

        });
    }
}
