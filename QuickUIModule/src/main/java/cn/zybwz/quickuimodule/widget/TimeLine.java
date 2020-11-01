package cn.zybwz.quickuimodule.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.LinkedList;
import java.util.List;

import cn.zybwz.quickuimodule.R;
import cn.zybwz.quickuimodule.TimelineAdapter;

public class TimeLine extends LinearLayout {
    private TimelineAdapter timelineAdapter;
    public TimeLine(Context context) {
        super(context);
        initView();
    }

    public TimeLine(Context context, AttributeSet attrs) {
        super(context, attrs);
        initView();
    }

    public TimeLine(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView();
    }

    public TimeLine(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        initView();
    }

    public void setData(List<Object> objects){
        timelineAdapter.setList(objects);
        Log.e("TAG", "setData: "+objects.size());
    }

    private void initView(){
        RecyclerView recyclerView= new RecyclerView(getContext());
        timelineAdapter=new TimelineAdapter();;
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setAdapter(timelineAdapter);
        LayoutParams layoutParams = new LinearLayout.LayoutParams(LayoutParams.MATCH_PARENT,LayoutParams.WRAP_CONTENT);
        addView(recyclerView,layoutParams);
    }
}
