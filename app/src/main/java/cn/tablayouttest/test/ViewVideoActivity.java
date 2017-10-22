package cn.tablayouttest.test;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import cn.tablayouttest.R;
import fm.jiecao.jcvideoplayer_lib.JCVideoPlayer;
import fm.jiecao.jcvideoplayer_lib.JCVideoPlayerStandard;

public class ViewVideoActivity extends AppCompatActivity {

    private JCVideoPlayerStandard mJCVideoPlayerStandard;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_video);

        Intent intent = getIntent();
        String downloadpath = intent.getStringExtra("BmobFile");

        mJCVideoPlayerStandard = (JCVideoPlayerStandard) findViewById(R.id.jiecao_Player);
        mJCVideoPlayerStandard.setUp(
                downloadpath, JCVideoPlayerStandard.SCREEN_LAYOUT_NORMAL,
                "颤抖吧");
    }



    @Override
    public void onBackPressed() {
        if (JCVideoPlayer.backPress()){
            return;
        }
        super.onBackPressed();
    }

    @Override
    protected void onPause() {
        super.onPause();
        JCVideoPlayer.releaseAllVideos();
    }
}
