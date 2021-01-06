package cn.zybwz.quickuimodule.widget.seekbar;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.View;

import androidx.annotation.Nullable;

public class CircleSeekBar extends View {
    private Paint baseCirclePaint=new Paint();
    private Paint seekCirclePaint=new Paint();
    private Paint seekTextPaint=new Paint();
    public CircleSeekBar(Context context) {
        super(context);
    }

    public CircleSeekBar(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        initView();
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
        baseCirclePaint.setStrokeWidth(20);
        baseCirclePaint.setColor(Color.parseColor("#99000000"));
        seekCirclePaint.setAntiAlias(true);
        seekCirclePaint.setStyle(Paint.Style.STROKE);
        seekCirclePaint.setStrokeWidth(20);
        seekCirclePaint.setTextSize(60);
//        seekCirclePaint.setColor(Color.RED);
        seekCirclePaint.setColor(Color.parseColor("#52c41a"));

        seekTextPaint.setAntiAlias(true);
        seekTextPaint.setTextSize(60);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        int width = getWidth();
        int height = getHeight();
        int min= Math.min(width, height);
//        width-=30;
//        height-=30;
//        min-=30;
        canvas.drawCircle(width/2,height/2,(min-60)/2,baseCirclePaint);
        canvas.drawText("80%",width/2-60,height/2,seekTextPaint);
//        canvas.drawCircle(300,300,200,seekCirclePaint);
        canvas.drawArc(new RectF(30, height/2-min/2+30, min-30, height/2+min/2-30), -90, 180, false, seekCirclePaint);
//        seekCirclePaint.setAlpha(0);
//        seekCirclePaint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.SRC_IN));
//        canvas.drawCircle(300, 300, 150, seekCirclePaint);

    }
}
