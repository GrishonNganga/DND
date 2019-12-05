package com.project.balmer.dnd.ui.home;

import android.content.Context;
import android.net.Uri;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.annotation.NonNull;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.RecyclerView;

import com.project.balmer.dnd.Model.GoodInfo;
import com.project.balmer.dnd.Model.ShopInfo;
import com.project.balmer.dnd.R;
import com.project.balmer.dnd.ui.shop.HomeShopSharedViewModel;
import com.squareup.picasso.Picasso;

import java.util.List;

public class HomeAdapter extends RecyclerView.Adapter<HomeAdapter.ShopHolder> {

    private List<ShopInfo> shops;
    private Context context;
    private Context context1;

    public HomeAdapter(Context mContext, List<ShopInfo> mShops) {
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
        final ShopInfo shopInfo = shops.get(position);
        List<GoodInfo> goodInfoList = shopInfo.getGoodInfo();
        String name = shopInfo.getName();
        holder.shopName.setText(name);
        Picasso.get().setLoggingEnabled(true);
        holder.spinner.setVisibility(View.VISIBLE);
        Picasso.get()
                .load(shopInfo.getImage().trim())
                .resize(300, 100)
                .centerCrop()
                .into(holder.shopImage);
        holder.spinner.setVisibility(View.GONE);
        if (shopInfo.getGoodInfo() == null) {
            holder.shopGoods.setText("");
        } else if (shopInfo.getGoodInfo().get(position).getName() == null) {
            holder.shopGoods.setText("");
        } else holder.shopGoods.setText(goodInfoList.get(position).getName());

    }

    @Override
    public int getItemCount() {
        return shops.size();
    }


    public class ShopHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        public TextView shopName, shopGoods;
        public ImageView shopImage;
        public ProgressBar spinner;

        public ShopHolder(@NonNull final View itemView) {
            super(itemView);
            shopName = itemView.findViewById(R.id.shopName);
            shopGoods = itemView.findViewById(R.id.goodInfo);
            shopImage = itemView.findViewById(R.id.homeImage);
            spinner = itemView.findViewById(R.id.spinner);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {

            Log.d("Adapter", shops.get(getAdapterPosition()).getName());
            HomeFragmentDirections.ActionNavHomeToShopFragment action = HomeFragmentDirections.actionNavHomeToShopFragment(shops.get(getAdapterPosition()));
            Navigation.findNavController(view).navigate(action);
        }
    }
}
