package cn.zybwz.quickuimodule;

import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.LinkedList;
import java.util.List;

public class ShopingCarAdapter extends RecyclerView.Adapter<ShopingCarAdapter.ViewHolder> {
    private List<Object> list=new LinkedList<>();
    private OnEvent onEvent;
    private int count=0;
    private double totalPrice=0.00;
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View inflate = View.inflate(parent.getContext(), R.layout.item_shopping_cart, null);
        return new ViewHolder(inflate);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

    }


    @Override
    public int getItemCount() {
        return list.size();
    }

    public void setList(List<?> objects){
        list.clear();
        list.addAll(objects);
        notifyDataSetChanged();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
        }
    }

    public void setOnEvent(OnEvent onEvent){
        this.onEvent=onEvent;
    }

    private void addShop(Object o){
        list.add(o);
        count++;
        totalPrice++;
        notifyDataSetChanged();
    }

    private void removeShop(Object o){
        list.remove(o);
        //if(select==true)
        count--;
        totalPrice--;
        notifyDataSetChanged();
    }

    interface OnEvent{
       void onSelectItemChange(int count,double totalPrice);
       void onItemClick(int pos);
    }

    public void selectAll(boolean select){

    }
}
