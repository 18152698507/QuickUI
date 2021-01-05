package cn.zybwz.components.videoplayer;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import cn.zybwz.components.R;

public class VideoPlayActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_video_play);
        VideoView videoView=findViewById(R.id.video_view);
        videoView.initPlayer();
        videoView.setDataSource("http://vfx.mtime.cn/Video/2019/03/09/mp4/190309153658147087.mp4");
    }
}