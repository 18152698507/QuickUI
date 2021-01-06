package cn.zybwz.quickuimodule.widget.dialog;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;

import com.bumptech.glide.Glide;

import cn.zybwz.quickuimodule.R;

public class ConfirmDialog extends Dialog {
    private TextView title;
    private TextView cancel;
    private TextView confirm;
    private EventListener eventListener=null;
    public static final int TYPE_DELETE=2;
    public static final int TYPE_CONFIRM=1;
    private int dialogType=TYPE_CONFIRM;

    public ConfirmDialog(@NonNull Context context) {
        super(context);
    }

    public void setEventListener(EventListener eventListener) {
        this.eventListener = eventListener;
    }

    public void initView(int type, String content){
        View view = View.inflate(getContext(), R.layout.dialog_confirm,null);
        title=view.findViewById(R.id.dialog_confirm_tv_title);
        cancel=view.findViewById(R.id.dialog_confirm_tv_cancel);
        cancel.setOnClickListener(v -> {
            dismiss();
            if (eventListener!=null){
                eventListener.onCancel();
            }
        });
        confirm=view.findViewById(R.id.dialog_confirm_tv_confirm);
        confirm.setOnClickListener(v -> {
            dismiss();
            if (eventListener!=null){
                eventListener.onConfirm();
            }
        });
        dialogType=type;
        if (dialogType==TYPE_CONFIRM){
            confirm.setText("确认");
            confirm.setTextColor(getContext().getResources().getColor(R.color.normal));
        }
        title.setText(content);
        getWindow().clearFlags(WindowManager.LayoutParams.FLAG_DIM_BEHIND);
        setContentView(view);
    }

    public interface EventListener{
        public void onCancel();
        public void onConfirm();
    }
}
