package com.project.balmer.dnd.ui.order;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.RecyclerView;

import com.project.balmer.dnd.Model.OrderInfo;
import com.project.balmer.dnd.R;

import java.util.List;

public class OrderAdapter extends RecyclerView.Adapter<OrderAdapter.OrderHolder> {

    private Context context;
    private List<OrderInfo> orders;
    private Fragment fragment;

    @NonNull
    @Override
    public OrderHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        View view = LayoutInflater.from(context).inflate(R.layout.order_list, parent, false);
         OrderHolder holder = new OrderHolder(view);
        return holder;
    }
    public OrderAdapter(Context mContext, List<OrderInfo> mOrders, Fragment fragment){
        context = mContext;
        orders = mOrders;
        this.fragment = fragment;
    }

    @Override
    public void onBindViewHolder(@NonNull OrderHolder holder, int position) {
        OrderInfo orderInfo = orders.get(position);
        holder.goodOrderName.setText(orderInfo.getGoodInfo().getName());
        holder.goodOrderDescription.setText(orderInfo.getGoodInfo().getDescription());
        holder.goodOrderPrice.setText(orderInfo.getGoodInfo().getPrice());
        holder.goodQuantity.setText(orderInfo.getQuantity());
        holder.goodQuantity.append(" X");
    }


    @Override
    public int getItemCount() {

        return orders.size();
    }

    public class OrderHolder extends RecyclerView.ViewHolder{

        private TextView goodOrderName, goodOrderDescription, goodOrderPrice, goodQuantity;
        public OrderHolder(@NonNull View itemView) {
            super(itemView);
            goodOrderName = itemView.findViewById(R.id.goodOrderName);
            goodOrderDescription = itemView.findViewById(R.id.goodOrderDescription);
            goodOrderPrice = itemView.findViewById(R.id.goodOrderPrice);
            goodQuantity = itemView.findViewById(R.id.howMany);
            itemView.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View view) {
                    OrderViewModel orderViewModel = ViewModelProviders.of(fragment).get(OrderViewModel.class);
                    orderViewModel.removeOrder(orders.get(getAdapterPosition()));
                    return false;
                }
            });
        }
    }
}

