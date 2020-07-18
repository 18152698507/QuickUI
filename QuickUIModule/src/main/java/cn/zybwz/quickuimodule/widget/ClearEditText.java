package cn.zybwz.quickuimodule.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.drawable.Drawable;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.EditText;

import cn.zybwz.quickuimodule.R;


public class ClearEditText extends androidx.appcompat.widget.AppCompatEditText implements View.OnFocusChangeListener, TextWatcher {
    private Drawable mClearDrawable;
    private Drawable mLeftDrawable;
    private int mClearDrawableWidth=0;
    private int mLeftDrawableWidth=0;

    public ClearEditText(Context context) {
        super(context);
        init();
    }

    public ClearEditText(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
    }

    private int dip2px(Context context, float dpValue) {
        float scale = context.getResources().getDisplayMetrics().density;
        return (int) (dpValue * scale + 0.5f);
    }

    private void init(){

        mClearDrawable = getCompoundDrawables()[2];
        mLeftDrawable = getCompoundDrawables()[0];
        if (mClearDrawable == null) {
//        	throw new NullPointerException("You can add drawableRight attribute in XML");
            mClearDrawable = getResources().getDrawable(R.drawable.delete);
        }
        if (mLeftDrawable == null) {
//        	throw new NullPointerException("You can add drawableRight attribute in XML");
            mLeftDrawable = getResources().getDrawable(R.drawable.user);
        }
        mClearDrawableWidth=dip2px(getContext(),20);
        mLeftDrawableWidth=dip2px(getContext(),30);
        mClearDrawable.setBounds(-5, 0, mClearDrawableWidth-5, mClearDrawableWidth);
        mLeftDrawable.setBounds(10,0,mLeftDrawableWidth,mLeftDrawableWidth-10);
        setOnFocusChangeListener(this);
        addTextChangedListener(this);
        setCompoundDrawables(mLeftDrawable,
                getCompoundDrawables()[1], getCompoundDrawables()[2], getCompoundDrawables()[3]);
    }
    protected void setClearIconVisible(boolean visible) {
        Drawable right = visible ? mClearDrawable : null;
        setCompoundDrawables(getCompoundDrawables()[0],
                getCompoundDrawables()[1], right, getCompoundDrawables()[3]);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if (getCompoundDrawables()[2] != null) {
            if (event.getAction() == MotionEvent.ACTION_UP) {
                boolean xTouchable = event.getX() > (getWidth() - getPaddingRight() -mClearDrawableWidth)
                        && (event.getX() < (getWidth() - getPaddingRight()));

                boolean yTouchable = event.getY() > (getHeight() -  mClearDrawableWidth) / 2
                        && event.getY() < (getHeight() + mClearDrawableWidth) / 2;

                //清除文本
                if (xTouchable && yTouchable) {
                    setText("");
                }
            }
        }
        return super.onTouchEvent(event);
    }

    @Override
    public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

    }

    @Override
    public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
        setClearIconVisible(charSequence.length()>0);
    }

    @Override
    public void afterTextChanged(Editable editable) {

    }

    @Override
    public void onFocusChange(View view, boolean b) {
        if (!b){
            setClearIconVisible(false);
        }
        else {

            if (getText().toString().length()>0){
                setClearIconVisible(true);
            }
        }
    }
}
