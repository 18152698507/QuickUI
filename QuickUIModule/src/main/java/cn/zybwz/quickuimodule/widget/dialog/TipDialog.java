package cn.zybwz.quickuimodule.widget.dialog;

import android.app.Dialog;
import android.content.Context;
import android.view.View;
import android.view.WindowManager;
import android.widget.TextView;

import androidx.annotation.NonNull;

import cn.zybwz.quickuimodule.R;

public class TipDialog extends Dialog {

    public TipDialog(@NonNull Context context) {
        super(context);
    }

    public void initView(String content){
        View view = View.inflate(getContext(), R.layout.dialog_tip,null);
        TextView title = view.findViewById(R.id.dialog_tip_tv_title);
        TextView know = view.findViewById(R.id.dialog_tip_tv_cancel);
        know.setOnClickListener(v -> {
            dismiss();
        });
        title.setText(content);
        getWindow().clearFlags(WindowManager.LayoutParams.FLAG_DIM_BEHIND);
        setContentView(view);
    }

}
