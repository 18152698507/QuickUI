package cn.zybwz.quickuimodule.widget.seekbar;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.View;

import androidx.annotation.Nullable;

import cn.zybwz.quickuimodule.utils.Utils;

public class CircleSeekBar extends View {
    private Paint baseCirclePaint=new Paint();
    private Paint seekCirclePaint=new Paint();
    private Paint seekTextPaint=new Paint();
    private int borderWidth= Utils.Companion.dip2px(getContext(),10);
    private int progress=0;
    private int textSize=Utils.Companion.dip2px(getContext(),10);
    private Path wavePath=new Path();
    public CircleSeekBar(Context context) {
        super(context);
    }

    public CircleSeekBar(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        initView();
    }

    public void setProgress(int progress){
        this.progress=progress;
        postInvalidate();
    }

    public CircleSeekBar(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView();
    }

    public CircleSeekBar(Context context, @Nullable AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }

    private void initView(){
        baseCirclePaint.setAntiAlias(true);
        baseCirclePaint.setStyle(Paint.Style.STROKE);
        baseCirclePaint.setStrokeWidth(borderWidth);
        baseCirclePaint.setColor(Color.parseColor("#33000000"));
        seekCirclePaint.setAntiAlias(true);
        seekCirclePaint.setStyle(Paint.Style.STROKE);
        seekCirclePaint.setStrokeWidth(borderWidth);
        seekCirclePaint.setColor(Color.parseColor("#52c41a"));
        seekTextPaint.setAntiAlias(true);
        seekTextPaint.setTextSize(textSize);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        int width = getWidth();
        int height = getHeight();
        int min= Math.min(width, height);
        canvas.drawCircle(width/2,height/2,(min-borderWidth)/2,baseCirclePaint);
        canvas.drawText(progress+"%",width/2-textSize*2/3,height/2+textSize/2,seekTextPaint);
        canvas.drawArc(new RectF(borderWidth/2, height/2-min/2+borderWidth/2, min-borderWidth/2, height/2+min/2-borderWidth/2), -90, 360*progress/100, false, seekCirclePaint);
    }
}
