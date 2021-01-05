package cn.zybwz.components.videoplayer;

import android.content.Context;
import android.graphics.Color;
import android.os.Handler;
import android.os.Looper;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.SeekBar;
import android.widget.TextView;

import androidx.annotation.NonNull;

import java.io.IOException;

import cn.zybwz.components.R;
import cn.zybwz.components.Utils;
import tv.danmaku.ijk.media.player.IMediaPlayer;
import tv.danmaku.ijk.media.player.IjkMediaPlayer;

public class VideoView extends LinearLayout {
    private final String TAG=VideoView.class.getSimpleName();
    private Handler mHandler=new Handler(Looper.getMainLooper());
    private IjkMediaPlayer ijkMediaPlayer = new IjkMediaPlayer();//视频控制类
    private SurfaceView surfView;
    private ImageView playStop;
    private TextView currentTime;
    private TextView duration;
    private SeekBar seekBar;
    private Runnable progressChangeRunnable=new Runnable() {
        @Override
        public void run() {
            if (!ijkMediaPlayer.isPlaying()){
                return;
            }
            currentTime.setText(Utils.sec2String((int)(ijkMediaPlayer.getCurrentPosition()/1000)));
            seekBar.setProgress((int)(ijkMediaPlayer.getCurrentPosition()/1000));
            mHandler.postDelayed(this,1000);
        }
    };
    public VideoView(Context context) {
        super(context);
        setBackgroundColor(Color.parseColor("#009966"));
        //.inflate(context,R.layout.view_video,null);
        LayoutInflater.from(context).inflate(R.layout.view_video, this, true);

        initView();
    }

    public VideoView(Context context, AttributeSet attrs) {
        super(context, attrs);
        LayoutInflater.from(context).inflate(R.layout.view_video, (ViewGroup) this,true);
        initView();
    }

    public VideoView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        LayoutInflater.from(context).inflate(R.layout.view_video, (ViewGroup) this,true);
        initView();
    }

    private void initView(){
        surfView = findViewById(R.id.view_video_sv_surf_view);
        playStop = findViewById(R.id.view_video_iv_play_stop);
        currentTime = findViewById(R.id.video_view_tv_current);
        duration = findViewById(R.id.video_view_tv_duration);
        seekBar = findViewById(R.id.video_view_sb_seek_bar);
        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                if (b){
                    ijkMediaPlayer.seekTo((i-1)*1000);
                }
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
        playStop.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                if (ijkMediaPlayer.isPlaying()){
                    playStop.setBackground(getResources().getDrawable(R.drawable.video_play));
                    ijkMediaPlayer.pause();
                }else {
                    playStop.setBackground(getResources().getDrawable(R.drawable.video_stop));
                    ijkMediaPlayer.start();
                }
            }
        });
    }

    public void initPlayer(){
        initPlayerListener();
        surfView.getHolder().addCallback(new SurfaceHolder.Callback() {
            @Override
            public void surfaceCreated(@NonNull SurfaceHolder surfaceHolder) {
                ijkMediaPlayer.setDisplay(surfaceHolder);
            }

            @Override
            public void surfaceChanged(@NonNull SurfaceHolder surfaceHolder, int i, int i1, int i2) {
                ijkMediaPlayer.setDisplay(surfaceHolder);
            }

            @Override
            public void surfaceDestroyed(@NonNull SurfaceHolder surfaceHolder) {

            }
        });
    }

    public void setDataSource(String path){
        try {
            ijkMediaPlayer.setDataSource(path);
            ijkMediaPlayer.prepareAsync();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void initPlayerListener(){
        ijkMediaPlayer.setOnVideoSizeChangedListener(new IMediaPlayer.OnVideoSizeChangedListener() {
            @Override
            public void onVideoSizeChanged(IMediaPlayer iMediaPlayer, int i, int i1, int i2, int i3) {
                Log.e(TAG, "onVideoSizeChanged: "+i +"  "+ i1+"  "+i2+"  "+i3 );
                ViewGroup.LayoutParams oldLP = surfView.getLayoutParams();
                oldLP.height=(getWidth()*i1)/i;
                surfView.setLayoutParams(oldLP);
            }
        });
        ijkMediaPlayer.setOnErrorListener(new IMediaPlayer.OnErrorListener() {
            @Override
            public boolean onError(IMediaPlayer iMediaPlayer, int i, int i1) {
                return false;
            }
        });

        ijkMediaPlayer.setOnCompletionListener(new IMediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(IMediaPlayer iMediaPlayer) {
                Log.e(TAG, "onCompletion: " );
                playStop.setBackground(getResources().getDrawable(R.drawable.video_play));
            }
        });

        ijkMediaPlayer.setOnInfoListener(new IMediaPlayer.OnInfoListener() {
            @Override
            public boolean onInfo(IMediaPlayer iMediaPlayer, int i, int i1) {
                if (iMediaPlayer.isPlaying())
                    mHandler.postDelayed(progressChangeRunnable,1000);
                long duration = iMediaPlayer.getDuration();
                Log.e(TAG, "onInfo: "+duration );
                return false;
            }
        });

        ijkMediaPlayer.setOnPreparedListener(new IMediaPlayer.OnPreparedListener() {
            @Override
            public void onPrepared(IMediaPlayer iMediaPlayer) {
                playStop.setBackground(getResources().getDrawable(R.drawable.video_stop));
                duration.setText(Utils.sec2String( (int) (ijkMediaPlayer.getDuration()/1000)));
                seekBar.setMax((int) (ijkMediaPlayer.getDuration()/1000));
            }
        });
    }

}
