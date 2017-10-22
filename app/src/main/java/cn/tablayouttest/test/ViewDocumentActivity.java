package cn.tablayouttest.test;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import java.io.File;

import cn.tablayouttest.R;
import cn.tablayouttest.test.tbs.SuperFileView2;

public class ViewDocumentActivity extends AppCompatActivity {

    private SuperFileView2 mSuperFileView2;
    private String documentPath = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_document);

        //绑定控件
        mSuperFileView2 = (SuperFileView2) findViewById(R.id.displayDocumentView);

        //获取从适配器拿里传送过来的文件下载的路径。
        getFilePath();


        Toast.makeText(this, "getFilePath" + documentPath, Toast.LENGTH_SHORT).show();

        //再将路径封装成File对象
        File file = new File(documentPath);
        mSuperFileView2.displayFile(file);

    }

    public void getFilePath() {
        Intent intent = getIntent();
        if (intent != null) {
            documentPath = intent.getStringExtra("documentPath");
        }
    }
}
