package cn.zybwz.quickui;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import com.google.gson.Gson;

import java.util.List;

import cn.zybwz.components.net.APIService;
import cn.zybwz.components.net.demo.DemoBean;
import cn.zybwz.components.net.demo.DemoService;
import cn.zybwz.components.videoplayer.VideoPlayActivity;
import cn.zybwz.quickuimodule.base.BaseActivity;
import cn.zybwz.quickuimodule.page.MainActivityShop;
import cn.zybwz.quickuimodule.widget.dialog.ConfirmDialog;
import cn.zybwz.quickuimodule.widget.dialog.TipDialog;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class MainActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        ConfirmDialog confirmDialog=new ConfirmDialog(this);
        confirmDialog.initView(ConfirmDialog.TYPE_CONFIRM,"您确定要删除此文件，删除后无法找回");
        confirmDialog.show();
        confirmDialog.setEventListener(new ConfirmDialog.EventListener() {
            @Override
            public void onCancel() {

            }

            @Override
            public void onConfirm() {
                TipDialog tipDialog=new TipDialog(MainActivity.this);
                tipDialog.initView("您确定要删除此文件，删除后无法找回");
                tipDialog.show();
            }
        });
//        Intent intent=new Intent(this, VideoPlayActivity.class);
//        startActivity(intent);
        //init at application
//        APIService.init("https://jsonplaceholder.typicode.com/");
//
//        DemoService service = APIService.createService(DemoService.class);
//        service.getDemo("1").subscribeOn(Schedulers.io())//IO线程加载数据
//                .observeOn(AndroidSchedulers.mainThread())//主线程显示数据
//                .subscribe(new Subscriber<List<DemoBean>>() {
//                    @Override
//                    public void onCompleted() {
//                        Log.e("TAG", "onCompleted: ");
//                    }
//
//                    @Override
//                    public void onError(Throwable e) {
//                        e.printStackTrace();
//                    }
//
//                    @Override
//                    public void onNext(List<DemoBean> resp) {
//                        Log.e("TAG", "response == " + new Gson().toJson(resp));
//                    }
//                });
    }


}