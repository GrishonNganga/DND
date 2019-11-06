package com.project.balmer.dnd.ui.shop;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.RecyclerView;

import com.project.balmer.dnd.Model.GoodInfo;
import com.project.balmer.dnd.Model.ShopInfo;
import com.project.balmer.dnd.R;


import java.util.List;

public class ShopAdapter extends RecyclerView.Adapter<ShopAdapter.GoodHolder> {

    private List<GoodInfo> goods;
    private Context context, context1;

    public ShopAdapter(Context mContext, List<GoodInfo> mGoods){
        context = mContext;
         goods = mGoods;

    }

    @NonNull
    @Override
    public GoodHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        context1 = parent.getContext();
        View view = LayoutInflater.from(context).inflate(R.layout.list_shop_items, parent, false);
        GoodHolder viewHolder = new GoodHolder(view);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull GoodHolder holder, int position) {
        GoodInfo goodInfo = goods.get(position);
        ShopInfo shopInfo = goodInfo.getShopInfo();
        String name = goodInfo.getName();
        holder.goodName.setText(name);
        if (goodInfo.getDescription() == null){
            holder.goodPrice.setText("");
        }holder.goodPrice.setText(goodInfo.getDescription());
        int drawableId = context.getResources().getIdentifier(goodInfo.getImage(), "drawable", context.getPackageName());
        holder.goodImage.setImageResource(drawableId);

    }

    @Override
    public int getItemCount() {
        return goods.size();
    }

    public class GoodHolder extends RecyclerView.ViewHolder{

        public TextView goodName, goodPrice;
        public ImageView goodImage, shopImage;

        public GoodHolder(@NonNull final View itemView) {
            super(itemView);
            goodName = itemView.findViewById(R.id.goodName);
            goodPrice = itemView.findViewById(R.id.goodPrice);
            goodImage = itemView.findViewById(R.id.shopGoodImage);
            itemView.setOnClickListener(Navigation.createNavigateOnClickListener(R.id.action_shopFragment_to_shopOrder));
        }
    }
}






