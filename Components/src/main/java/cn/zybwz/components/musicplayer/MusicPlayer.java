package cn.zybwz.components.musicplayer;

import android.media.MediaPlayer;

import java.io.IOException;

public class MusicPlayer {
    private static MusicPlayer musicPlayer=null;
    private MediaPlayer mediaPlayer=new MediaPlayer();
    private PlayListener playListener=null;

    public void setPlayListener(PlayListener playListener) {
        this.playListener = playListener;
        initPlayer();
    }


    public void initPlayer(){
        mediaPlayer.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
            @Override
            public void onPrepared(MediaPlayer mediaPlayer) {

            }
        });

        mediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mediaPlayer) {
                if (playListener!=null)
                    playListener.onComplete();
            }
        });

        mediaPlayer.setOnErrorListener(new MediaPlayer.OnErrorListener() {
            @Override
            public boolean onError(MediaPlayer mediaPlayer, int i, int i1) {
                if (playListener!=null)
                    playListener.onError();
                return false;
            }
        });

    }

    public static MusicPlayer getInstance(){
        if (musicPlayer==null){
            synchronized (MusicPlayer.class){
                if (musicPlayer==null){
                    musicPlayer=new MusicPlayer();
                }
            }
        }
        return musicPlayer;
    }

    public void setDataSource(String path){
        try {
            mediaPlayer.setDataSource(path);
            mediaPlayer.prepareAsync();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void play(){
        mediaPlayer.start();
    }

    public void pause(){
        mediaPlayer.pause();
    }

    public void stop(){
        mediaPlayer.stop();
    }

    public void release(){
        mediaPlayer.release();
    }

    public void destroy(){
        mediaPlayer.stop();
        mediaPlayer.release();
        mediaPlayer=null;

    }
    public interface PlayListener{
        void onError();
        void onComplete();
    }

}
