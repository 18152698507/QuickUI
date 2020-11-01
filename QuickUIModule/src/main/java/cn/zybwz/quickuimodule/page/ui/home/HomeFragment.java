package cn.zybwz.quickuimodule.page.ui.home;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.RoundedCorners;
import com.bumptech.glide.request.RequestOptions;
import com.youth.banner.Banner;
import com.youth.banner.adapter.BannerImageAdapter;
import com.youth.banner.holder.BannerImageHolder;
import com.youth.banner.indicator.CircleIndicator;

import java.util.LinkedList;
import java.util.List;

import cn.zybwz.quickuimodule.GoodsAdapter;
import cn.zybwz.quickuimodule.R;
import cn.zybwz.quickuimodule.page.GoodsDetailActivity;

public class HomeFragment extends Fragment {

    private HomeViewModel homeViewModel;
    private List<Integer> drawList=new LinkedList<>();
    private List<Object> objectList=new LinkedList<>();

    public View onCreateView(@NonNull LayoutInflater inflater,
            ViewGroup container, Bundle savedInstanceState) {
        homeViewModel =
                ViewModelProviders.of(this).get(HomeViewModel.class);
        View root = inflater.inflate(R.layout.fragment_home, container, false);
        return root;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initView(view);

    }

    public void initView(View root){
        initBanner(root);
        initGoodsList(root);
    }



    public void initBanner(View root){
        drawList.add(R.drawable.main1);
        drawList.add(R.drawable.main2);
        drawList.add(R.drawable.main3);
        Banner banner = root.findViewById(R.id.fragment_home_banner);
        banner.setAdapter(new BannerImageAdapter<Integer>(drawList) {
            @Override
            public void onBindView(BannerImageHolder holder, Integer data, int position, int size) {
                //图片加载自己实现
                Glide.with(holder.itemView)
                        .load(data)
                        .apply(RequestOptions.bitmapTransform(new RoundedCorners(30)))
                        .into(holder.imageView);
            }
        })
                .addBannerLifecycleObserver(this)//添加生命周期观察者
                .setIndicator(new CircleIndicator(getContext()));
    }

    private void initGoodsData(){
        for (int i=0;i<10;i++){
            objectList.add(new Object());
        }
    }

    private void initGoodsList(View root){
        RecyclerView recyclerView=root.findViewById(R.id.fragment_home_rv_goods);
        GoodsAdapter goodsAdapter=new GoodsAdapter();
        initGoodsData();
        goodsAdapter.setList(objectList);
        goodsAdapter.setOnClickListener(new GoodsAdapter.OnClickListener() {
            @Override
            public void onItemClick(int pos) {
                Intent intent=new Intent(getContext(), GoodsDetailActivity.class);
                startActivity(intent);
            }
        });
        recyclerView.setLayoutManager(new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL));
        recyclerView.setAdapter(goodsAdapter);
    }

}