package cn.zybwz.quickuimodule.widget;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.media.MediaPlayer;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

import androidx.annotation.Nullable;

import java.util.LinkedList;
import java.util.List;

import cn.zybwz.quickuimodule.domain.LrcEntity;
import cn.zybwz.quickuimodule.utils.TextUtils;


public class LrcView extends View {
    private List<String> strings=new LinkedList<>();
    private boolean isRun=true;
    private boolean isScroll=false;
    private float historyY=0f;
//    private MediaPlayer mediaPlayer = getMediaPlayer.getInstance();
    private List<LrcEntity> lrcEntities;
    private int lineIndex,oldLineIndex=0;
    private boolean firstIn=false;
    private boolean seeking=false;
    private long currentTime=0;
    public LrcView(Context context) {
        super(context);
    }

    public LrcView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public void setLrcRows(List<LrcEntity> lrcEntities) {
        this.lrcEntities = lrcEntities;
        if (lrcEntities!=null){
            for (int i=0;i<lrcEntities.size();i++){
                if (lrcEntities.get(i).getTimeLong()>currentTime){
                    lineIndex=i;
                    break;
                }
            }
        }
        postInvalidate();
    }

//    @Override
//    public boolean dispatchTouchEvent(MotionEvent event){
//        switch (event.getAction()){
//            case MotionEvent.ACTION_DOWN:
//                oldLineIndex=lineIndex;
//                break;
//            case MotionEvent.ACTION_MOVE:
//                isScroll=true;
//                if (historyY!=event.getY()){
//                if (event.getY()-historyY>100){
//                    lineIndex=lineIndex+(int)(historyY-event.getY())/100;
//                    if (lineIndex>lrcEntities.size()-7)
//                        lineIndex=lrcEntities.size()-7;
//                    else if (lineIndex<0)
//                        lineIndex=0;
//                    historyY=event.getY();
//                    postInvalidate();
//                }else if (historyY-event.getY()>100){
//                    lineIndex=lineIndex+(int)(historyY-event.getY())/100;
//                    if (lineIndex>lrcEntities.size()-7)
//                        lineIndex=lrcEntities.size()-7;
//                    else if (lineIndex<0)
//                        lineIndex=0;
//                    historyY=event.getY();
//                    postInvalidate();
//                }
//            }
//            break;
//            case MotionEvent.ACTION_UP:
//                new Thread(new Runnable() {
//                    @Override
//                    public void run() {
//                        try {
//                            Thread.sleep(3000);
//                        } catch (InterruptedException e) {
//                            e.printStackTrace();
//                        }
//                        lineIndex=oldLineIndex;
//                        isScroll=false;
//                    }
//                }).start();
//                break;
//        }
//        return true;
//    }

    public void forSeek(long seekLong){
        if (lrcEntities!=null)
        for (int i=0;i<lrcEntities.size();i++){
            if (lrcEntities.get(i).getTimeLong()>seekLong){
                lineIndex=i;
                seeking=true;
                postInvalidate();
                break;
            }
        }
    }

    //@Override
    public void myPostInvalidate(){
        if (lrcEntities!=null)
        if (lineIndex<lrcEntities.size()){
            if (currentTime>(int) lrcEntities.get(lineIndex).getTimeLong()){
                postInvalidate();
                //Log.e(currentTime+"",lrcEntities.get(lineIndex).getTimeLong()+"");
                lineIndex++;
            }
        }
    }
    public void isResume(){
        strings.clear();
        if (lrcEntities!=null)
        if (lineIndex<lrcEntities.size()){
            if (currentTime>(int) lrcEntities.get(lineIndex).getTimeLong()){
                postInvalidate();
                //Log.e(currentTime+"",lrcEntities.get(lineIndex).getTimeLong()+"");
                lineIndex++;
            }
        }
        postInvalidate();
    }

    @Override
    protected void onDraw(Canvas canvas){
        super.onDraw(canvas);
        Paint paint = new Paint();
        Paint normalPaint = new Paint();
        int canvasHeight = canvas.getHeight();
        int canvasWidth = canvas.getWidth();
        paint.setColor(Color.BLACK);
        normalPaint.setColor(Color.GRAY);
        //写文字
        paint.setTextSize(60); //以px为单位
        normalPaint.setTextSize(40);
        if (lrcEntities==null||lrcEntities.size()==0){
            canvas.drawText("暂无歌词",(canvasWidth-4*60)/2,100,paint);
        }else{
            if (seeking||isScroll){
                strings.clear();
                seeking=false;
                isScroll=false;
            }
                if (lineIndex < lrcEntities.size() - 2) {
                    if (lineIndex <=3) {
                        for (int i = 0; i < 7; i++) {
                            strings.add(i, lrcEntities.get(i).getText());
                        }
                    }else if (strings.size()==0){
                        for (int i = 0; i < 7; i++) {
                            strings.add(i, lrcEntities.get(lineIndex-4+i).getText());
                        }
                    }
                    else  {
                        strings.remove(0);
                        strings.add(6, lrcEntities.get(lineIndex +2).getText());
                    }
                    for (int j = 0; j < 7; j++) {
                        int numberCount= TextUtils.numberCount(strings.get(j));
                        if (lineIndex <= 3 && j == lineIndex % 7) {
                            canvas.drawText(strings.get(j), (canvasWidth - ((strings.get(j).length()-numberCount) * 60)-numberCount*30) / 2, 100 + 100 * j, paint);
                        } else if (lineIndex > 3 && j == 3) {
                            canvas.drawText(strings.get(j), (canvasWidth - ((strings.get(j).length()-numberCount) * 60)-numberCount*30)  / 2, 100 + 100 * j, paint);
                        } else
                            canvas.drawText(strings.get(j), (canvasWidth - ((strings.get(j).length()-numberCount) * 40)-numberCount*20) / 2, 100 + 100 * j, normalPaint);
                    }
                }else {
                    strings.remove(0);
                    for (int j = 0; j < 4+(lrcEntities.size()-lineIndex); j++) {
                        int numberCount=TextUtils.numberCount(strings.get(j));
                        if (lineIndex <= 3 && j == lineIndex % 7) {
                            canvas.drawText(strings.get(j), (canvasWidth - ((strings.get(j).length()-numberCount) * 60)-numberCount*30) / 2, 100 + 100 * j, paint);
                        } else if (lineIndex > 3 && j == 3) {
                            canvas.drawText(strings.get(j), (canvasWidth - ((strings.get(j).length()-numberCount) * 60)-numberCount*30)  / 2, 100 + 100 * j, paint);
                        } else
                            canvas.drawText(strings.get(j), (canvasWidth - ((strings.get(j).length()-numberCount) * 40)-numberCount*20) / 2, 100 + 100 * j, normalPaint);
                    }
                }

        }
    }

}
