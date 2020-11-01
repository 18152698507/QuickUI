package cn.zybwz.components.net;

import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class APIService {
    private static Retrofit retrofit;

    public static void init(String baseUrl){
        if (retrofit==null){
            synchronized (APIService.class){
                if (retrofit==null)
                    retrofit = new Retrofit.Builder()
                            .baseUrl(baseUrl)//基础URL 建议以 / 结尾
                            .addConverterFactory(GsonConverterFactory.create())//设置 Json 转换器
                            .addCallAdapterFactory(RxJavaCallAdapterFactory.create())//RxJava 适配器
                            .build();
            }
        }
    }

    public static  <T> T createService(final Class<T> service){
        if (retrofit==null)
            throw new RuntimeException("please init service first");
        return  retrofit.create(service);

    }

}
