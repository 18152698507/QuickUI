package cn.zybwz.quickui;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import cn.zybwz.quickuimodule.base.BaseActivity;
import cn.zybwz.quickuimodule.page.LoginActivity;

public class MainActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
//        Intent intent=new Intent(this, LoginActivity.class);
//        startActivity(intent);
    }


}