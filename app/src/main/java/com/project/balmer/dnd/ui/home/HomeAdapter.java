package com.project.balmer.dnd.ui.home;

import android.content.Context;
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

public class HomeAdapter extends RecyclerView.Adapter<HomeAdapter.ShopHolder> {

    private List<ShopInfo> shops;
    private Context context;
    private Context context1;

    public HomeAdapter(Context mContext, List<ShopInfo> mShops){
        context = mContext;
        shops = mShops;

    }

    @NonNull
    @Override
    public ShopHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        context1 = parent.getContext();
        View view = LayoutInflater.from(context).inflate(R.layout.list_home_items, parent, false);
        ShopHolder viewHolder = new ShopHolder(view);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ShopHolder holder, int position) {
        ShopInfo shopInfo = shops.get(position);
        GoodInfo goodInfo = shopInfo.getGoodInfo();
        String name = shopInfo.getName();
        holder.shopName.setText(name);
        int resourceId = context.getResources().getIdentifier(shopInfo.getImage(), "drawable", context.getPackageName());
        holder.shopImage.setImageResource(resourceId);
        if (shopInfo.getGoodInfo() == null){
            holder.shopGoods.setText("");
        }else if (shopInfo.getGoodInfo().getName() == null){
            holder.shopGoods.setText("");
        }else holder.shopGoods.setText(goodInfo.getName());

    }

    @Override
    public int getItemCount() {
        return shops.size();
    }

    public class ShopHolder extends RecyclerView.ViewHolder{

        public TextView shopName, shopGoods;
        public ImageView shopImage;

        public ShopHolder(@NonNull final View itemView) {
            super(itemView);
            shopName = itemView.findViewById(R.id.shopName);
            shopGoods = itemView.findViewById(R.id.goodInfo);
            shopImage = itemView.findViewById(R.id.homeImage);

            itemView.setOnClickListener(
                    Navigation.createNavigateOnClickListener(R.id.action_nav_home_to_shopFragment));
        }
    }
}
