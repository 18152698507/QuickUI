package cn.zybwz.quickui;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import com.google.gson.Gson;

import java.util.List;

import cn.zybwz.components.net.APIService;
import cn.zybwz.components.net.demo.DemoBean;
import cn.zybwz.components.net.demo.DemoService;
import cn.zybwz.quickuimodule.base.BaseActivity;
import cn.zybwz.quickuimodule.page.MainActivityShop;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class MainActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        Intent intent=new Intent(this, MainActivityShop.class);
        startActivity(intent);
        //init at application
        APIService.init("https://jsonplaceholder.typicode.com/");

        DemoService service = APIService.createService(DemoService.class);
        service.getDemo("1").subscribeOn(Schedulers.io())//IO线程加载数据
                .observeOn(AndroidSchedulers.mainThread())//主线程显示数据
                .subscribe(new Subscriber<List<DemoBean>>() {
                    @Override
                    public void onCompleted() {
                        Log.e("TAG", "onCompleted: ");
                    }

                    @Override
                    public void onError(Throwable e) {
                        e.printStackTrace();
                    }

                    @Override
                    public void onNext(List<DemoBean> resp) {
                        Log.e("TAG", "response == " + new Gson().toJson(resp));
                    }
                });
    }


}