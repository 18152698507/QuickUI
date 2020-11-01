package cn.zybwz.quickuimodule.page;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import cn.zybwz.quickuimodule.R;
import cn.zybwz.quickuimodule.widget.dialog.LoadingDialog;

public class GoodsDetailActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_goods_detail);
        LoadingDialog loadingDialog = new LoadingDialog(this);
        loadingDialog.show();
    }
}