package cn.tablayouttest.test.widget;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
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

import com.white.progressview.CircleProgressView;

import java.io.File;

import cn.bmob.v3.datatype.BmobFile;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.DownloadFileListener;
import cn.tablayouttest.R;
import cn.tablayouttest.test.model.Other;


/**
 * Created by yu on 2017/10/9.
 */

public class ChildFiveItemView extends RelativeLayout {


    private ImageView mFilePic;
    private TextView mFileName;
    private TextView mFileMB;
    private TextView mFileUpdateTime;
    private Button mSee;
    private String objectId;
    private BmobFile mBmobFile;
    private CircleProgressView mCircleProgressView;

    public ChildFiveItemView(Context context) {
        this(context, null);
    }

    public ChildFiveItemView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    private void init() {
        View view = LayoutInflater.from(getContext()).inflate(R.layout.child_five_item_view, this);


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


    public void bindView(Other other) {

        this.objectId = other.getOid();
        this.mBmobFile = other.getBmobFile();

        Resources res = getResources();
        Bitmap orther = BitmapFactory.decodeResource(res, R.drawable.orther);

        mFilePic.setImageBitmap(orther);
        mFileName.setText(other.getOtherName());
        mFileMB.setText(other.getOtherSize());
        mFileUpdateTime.setText(other.getOtherTime());
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
                //mArrowDownloadButton.setVisibility(VISIBLE);
            }

            @Override
            public void done(String savePath,BmobException e) {
                if(e==null){

                    Toast.makeText(getContext(), "下载成功" +  savePath, Toast.LENGTH_SHORT).show();

                }else{
                    Toast.makeText(getContext(), "下载失败", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onProgress(Integer value, long newworkSpeed) {
                Log.i("bmob++++++++","下载进度："+value+","+newworkSpeed);

//                mArrowDownloadButton.startAnimating();
//                mArrowDownloadButton.setProgress(value);
//                if(value == 100){
//                    mArrowDownloadButton.setVisibility(GONE);
//                    mSee.setVisibility(VISIBLE);
//                }



                mCircleProgressView.setProgress(value);
                if(value == 100){
                    mCircleProgressView.setVisibility(GONE);
                    mSee.setVisibility(VISIBLE);
                }



            }

        });

    }

}
