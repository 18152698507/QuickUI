package cn.zybwz.quickuimodule.page.ui.notifications;

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

import java.util.LinkedList;
import java.util.List;

import cn.zybwz.quickuimodule.R;
import cn.zybwz.quickuimodule.ShopingCarAdapter;
import cn.zybwz.quickuimodule.TimelineAdapter;
import cn.zybwz.quickuimodule.widget.TimeLine;

public class NotificationsFragment extends Fragment {
    private List<Object> objectList=new LinkedList<>();
    private NotificationsViewModel notificationsViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater,
            ViewGroup container, Bundle savedInstanceState) {
        notificationsViewModel =
                ViewModelProviders.of(this).get(NotificationsViewModel.class);
        View root = inflater.inflate(R.layout.fragment_notifications, container, false);

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
        TimeLine timeLine=root.findViewById(R.id.rv_timeline);
        initGoodsData();
        timeLine.setData(objectList);
    }
}