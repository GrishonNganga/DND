package com.project.balmer.dnd.ui.order;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.project.balmer.dnd.Model.OrderInfo;
import com.project.balmer.dnd.R;

import java.util.List;

public class OrderAdapter extends RecyclerView.Adapter<OrderAdapter.OrderHolder> {

    private Context context;
    private List<OrderInfo> orders;

    @NonNull
    @Override
    public OrderHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        View view = LayoutInflater.from(context).inflate(R.layout.order_list, parent, false);
         OrderHolder holder = new OrderHolder(view);
        return holder;
    }
    public OrderAdapter(Context mContext, List<OrderInfo> mOrders){
        context = mContext;
        orders = mOrders;
    }

    @Override
    public void onBindViewHolder(@NonNull OrderHolder holder, int position) {
        OrderInfo orderInfo = orders.get(position);
        holder.goodOrderName.setText(orderInfo.getGoodInfo().getName());
        holder.goodOrderDescription.setText(orderInfo.getGoodInfo().getDescription());
        holder.goodOrderPrice.setText(orderInfo.getGoodInfo().getPrice());
    }


    @Override
    public int getItemCount() {

        return orders.size();
    }

    public class OrderHolder extends RecyclerView.ViewHolder{

        private TextView goodOrderName, goodOrderDescription, goodOrderPrice;
        public OrderHolder(@NonNull View itemView) {
            super(itemView);
            goodOrderName = itemView.findViewById(R.id.goodOrderName);
            goodOrderDescription = itemView.findViewById(R.id.goodOrderDescription);
            goodOrderPrice = itemView.findViewById(R.id.goodOrderPrice);
        }
    }
}

