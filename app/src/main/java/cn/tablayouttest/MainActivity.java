package cn.tablayouttest;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.daimajia.numberprogressbar.NumberProgressBar;

import java.io.File;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;

import butterknife.ButterKnife;
import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.datatype.BmobFile;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.FindListener;
import cn.bmob.v3.listener.SaveListener;
import cn.bmob.v3.listener.UploadFileListener;
import cn.tablayouttest.fragment.BlankFragment;
import cn.tablayouttest.model.FileAnd;
import cn.tablayouttest.utils.FileUtils;

public class MainActivity extends AppCompatActivity {


    private NumberProgressBar mNumberProgressBar;
    private Button mButtom;
    private static final int FILE_SELECT_CODE = 0;
    private static final String TAG = "ChooseFile";
    private String mPath;
    private BmobFile mBmobFile;
    private TabLayout mTabLayout;
    private ArrayList<FileAnd> mFileAnds = new ArrayList<>();
    private String fileSize = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);


        initTabLayout();

        initViews();

        initData();

    }

    private void initData() {
//        Bundle bundleTop = new Bundle();
//        bundleTop.putParcelableArayList("mFileAnds",mFileAnds);
//        BlankFragment f0 = new BlankFragment();
//        f0.setArguments(bundleTop);

        BlankFragment bf = new BlankFragment();
        Bundle bundle1 = new Bundle();
        bundle1.putInt("key", 1);
        bf.setArguments(bundle1);

        BlankFragment f1 = new BlankFragment();
        BlankFragment f2 = new BlankFragment();
        BlankFragment f3 = new BlankFragment();

    }

    private void initQuery() {

        BmobQuery<FileAnd> query = new BmobQuery<FileAnd>();

        query.findObjects(new FindListener<FileAnd>() {
            @Override
            public void done(List<FileAnd> list, BmobException e) {
                for (FileAnd one : list) {
                    FileAnd mFileAnd = new FileAnd();
                    mFileAnd.setFileName(one.getFileName());
                    mFileAnd.setFileType(one.getFileType());
                    mFileAnd.setBmobFile(one.getBmobFile());
                    mFileAnd.setFileSize(one.getFileSize());
                    mFileAnds.add(mFileAnd);
                }

            }
        });
    }

    private void initViews() {
        mButtom = (Button) findViewById(R.id.btn_upload);
        mNumberProgressBar = (NumberProgressBar) findViewById(R.id.number_progress_bar);
        mNumberProgressBar.setMax(100);


        mButtom.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showFileChooser();
            }
        });

    }

    //1.请求选择文件
    private void showFileChooser() {
        Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
        intent.setType("*/*");
        intent.addCategory(Intent.CATEGORY_OPENABLE);
        try {
            startActivityForResult(Intent.createChooser(intent, "Select a File to Upload"), FILE_SELECT_CODE);
        } catch (ActivityNotFoundException ex) {
            // Potentially direct the user to the Market with a Dialog
            Toast.makeText(this, "Please install a File Manager.", Toast.LENGTH_SHORT).show();
        }
    }

    //处理选中文件的回调
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        switch (requestCode) {
            case FILE_SELECT_CODE:
                if (resultCode == RESULT_OK) {
                    // Get the Uri of the selected file
                    Uri uri = data.getData();
                    Log.d(TAG, "File Uri: " + uri.toString());
                    // Get the path
                    mPath = null;

                    try {
                        mPath = FileUtils.getPath(this, uri);

                        Toast.makeText(this, "文件大小 = " + FileUtils.getFileSize(mPath), Toast.LENGTH_SHORT).show();
                        fileSize = FileUtils.getFileSize(mPath);
                    } catch (URISyntaxException e) {
                        e.printStackTrace();
                    }
                    Log.d(TAG, "File Path: " + mPath);
                    // Get the file instance
                    // File file = new File(path);
                    // Initiate the upload


                    //这里执行操作。
                    //1.首先判断文件类型（用一个变量存储），获取文件类型以后上传
                    //(1)常见的文档类型：txt doc hlp wps rtf html pdf
                    //(2)常见的图片类型：bmp gif jpg pic png tif
                    //(3)常见的视频类型：。。。
                    //(4)常见的音乐类型：mp3
                    //(5)其他的类型：。。。

                    //获取文件类型
                    int indexOfType = mPath.lastIndexOf(".");
                    indexOfType = indexOfType + 1;
                    String mType = mPath.substring(indexOfType);
                    Log.e("-------------", "《-》" + mType);

                    //获取文件名字
                    int indexOfNmae = mPath.lastIndexOf("/");
                    indexOfNmae = indexOfNmae + 1;
                    String mName = mPath.substring(indexOfNmae);
                    Log.e("-------------", "《-》" + mName);

                    classifyType(mType, mName);

                }
                break;
        }
        super.onActivityResult(requestCode, resultCode, data);
    }


    private void classifyType(final String mType, final String mName) {
        //(1)常见的文档类型：txt doc hlp wps rtf html pdf
        //(2)常见的图片类型：bmp gif jpg pic png tif
        switch (mType) {
            case "txt":
            case "doc":
            case "hlp":
            case "wps":
            case "rtf":
            case "html":
            case "pdf":
                System.out.println("文档类型");

                //2.第二步
                // (1)上传文档到Bmob
                if (mPath == null) {
                    Log.e("path", "null");
                    return;
                } else {
                    mBmobFile = null;
                    mBmobFile = new BmobFile(new File(mPath));
//                    Log.e("88888",mBmobFile.getFilename());
                    mBmobFile.uploadblock(new UploadFileListener() {
                        @Override
                        public void done(BmobException e) {
                            if (e == null) {
                                //创建方法传递参数。
                                Toast.makeText(MainActivity.this, "文档文件上传成功", Toast.LENGTH_SHORT).show();
                                // (2)获取BmobFile以及文件类型生成一张表。
                                saveAlbumPic(mBmobFile, mType, mName, fileSize);


                            } else {
                                Toast.makeText(MainActivity.this, "文档文件上传失败", Toast.LENGTH_SHORT).show();
                            }

                        }

                        @Override
                        public void onProgress(Integer value) {
                            // 返回的上传进度（百分比）
                            if (value == 100) {
                                mNumberProgressBar.setVisibility(View.GONE);
                            }
                            mNumberProgressBar.setProgress(value);
                        }
                    });

                }
                break;

            case "bmp":
            case "gif":
            case "jpg":
            case "pic":
            case "png":
            case "tif":
                System.out.println("图片类型");


//                if(mBmobFile==null){
                mBmobFile = new BmobFile(new File(mPath));
//                }
                mBmobFile.uploadblock(new UploadFileListener() {
                    @Override
                    public void done(BmobException e) {
                        if (e == null) {
                            //创建方法传递参数。
                            Toast.makeText(MainActivity.this, "图片文件上传成功", Toast.LENGTH_SHORT).show();
                            saveAlbumPic(mBmobFile, mType, mName, fileSize);
                        } else {
                            Toast.makeText(MainActivity.this, "图片文件上传失败", Toast.LENGTH_SHORT).show();
                        }

                    }

                    @Override
                    public void onProgress(Integer value) {
                        // 返回的上传进度（百分比）
                        if (value == 100) {
                            mNumberProgressBar.setVisibility(View.GONE);
                        }
                        mNumberProgressBar.setProgress(value);
                    }
                });

                break;
            default:

                break;
        }
    }


    private void saveAlbumPic(BmobFile bmobFile, final String mType, String mName, String fileSize) {
        FileAnd mFileAnd = new FileAnd();
        //注意：不能调用gameScore.setObjectId("")方法
        mFileAnd.setBmobFile(bmobFile);
        mFileAnd.setFileType(mType);
        mFileAnd.setFileName(mName);
        mFileAnd.setFileSize(fileSize);
        mFileAnd.save(new SaveListener<String>() {

            @Override
            public void done(String objectId, BmobException e) {
                if (e == null) {
                    Toast.makeText(MainActivity.this, mType + " ：save_success", Toast.LENGTH_SHORT).show();
                } else {
                    Log.i("bmob", "失败：" + e.getMessage() + "," + e.getErrorCode());
                }
            }
        });
    }


    private void initTabLayout() {
//        ViewPager vp = (ViewPager) findViewById(R.id.main_vp);
//
//        mTabLayout = (TabLayout) findViewById(R.id.main_tab_layout);

        List<String> list = new ArrayList<>();

        list.add("文档");
        list.add("图片");
        list.add("视频");
        list.add("音乐");
        list.add("其他");


//        List<BlankFragment> fragments=new ArrayList<BlankFragment>();
//        BlankFragment f1 = new BlankFragment();
//        BlankFragment f2 = new BlankFragment();
//        BlankFragment f3 = new BlankFragment();
//        BlankFragment f4 = new BlankFragment();
//        BlankFragment f5 = new BlankFragment();

//        Bundle mBundle = new Bundle();
//        mBundle.putString("fuck","我艹，又报错");
//        f1 = new BlankFragment();
//        f1.setArguments(mBundle);

//        fragments.add(f1);
//        fragments.add(f2);
//        fragments.add(f3);
//        fragments.add(f4);
//        fragments.add(f5);

//        vp.setAdapter(new MyAdapter(getSupportFragmentManager(), list));
//
//        mTabLayout.setupWithViewPager(vp);


        /**
         * 选择tablayout的监听，一般是用不到的
         */

        mTabLayout.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                if (tab.getText().equals("文档")) {
                    Toast.makeText(getApplicationContext(), "滑动或点击了文档", Toast.LENGTH_SHORT).show();
                }
                if (tab.getPosition() == 1) {
                    Toast.makeText(getApplicationContext(), "滑动或点击了图片", Toast.LENGTH_SHORT).show();
                }
                if (tab.getPosition() == 2) {
                    Toast.makeText(getApplicationContext(), "滑动或点击了视频", Toast.LENGTH_SHORT).show();
                }
                if (tab.getPosition() == 3) {
                    Toast.makeText(getApplicationContext(), "滑动或点击了音乐", Toast.LENGTH_SHORT).show();
                }
                if (tab.getPosition() == 4) {
                    Toast.makeText(getApplicationContext(), "滑动或点击了其他", Toast.LENGTH_SHORT).show();
                }

            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {
                if (tab.isSelected()) {
                    Toast.makeText(getApplicationContext(), "切换了Pager", Toast.LENGTH_SHORT).show();
                }
                Log.i(TAG, "tab.getTag()" + tab.getTag());
            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
    }


}
