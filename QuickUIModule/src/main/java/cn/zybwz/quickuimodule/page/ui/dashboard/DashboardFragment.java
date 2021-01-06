package cn.zybwz.quickuimodule.page.ui.dashboard;

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
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import com.youth.banner.Banner;

import java.util.LinkedList;
import java.util.List;

import cn.zybwz.quickuimodule.GoodsAdapter;
import cn.zybwz.quickuimodule.R;
import cn.zybwz.quickuimodule.ShopingCarAdapter;


public class DashboardFragment extends Fragment {
    private List<Object> objectList=new LinkedList<>();

    private DashboardViewModel dashboardViewModel;
    public View onCreateView(@NonNull LayoutInflater inflater,
            ViewGroup container, Bundle savedInstanceState) {
        dashboardViewModel =
                ViewModelProviders.of(this).get(DashboardViewModel.class);
        View root = inflater.inflate(R.layout.fragment_dashboard, container, false);
        return root;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initView(view);
    }

    public void initView(View root){
        initGoodsList(root);
    }

    private void initGoodsData(){
        for (int i=0;i<10;i++){
            objectList.add(new Object());
        }
    }

    private void initGoodsList(View root){
        RecyclerView recyclerView=root.findViewById(R.id.fragment_dashboard_rv_car_item);
        ShopingCarAdapter shopingCarAdapter=new ShopingCarAdapter();
        initGoodsData();
        shopingCarAdapter.setList(objectList);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setAdapter(shopingCarAdapter);
    }

}