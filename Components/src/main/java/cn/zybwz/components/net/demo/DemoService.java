package cn.zybwz.components.net.demo;

import java.util.List;

import retrofit2.http.GET;
import retrofit2.http.Query;
import rx.Observable;

public interface DemoService {
    //https://jsonplaceholder.typicode.com/
    @GET("posts")
    Observable<List<DemoBean>> getDemo(@Query("_page") String page);
}
