package com.project.balmer.dnd.ui.shop;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.project.balmer.dnd.Model.GoodInfo;
import com.project.balmer.dnd.Model.ShopInfo;
import com.project.balmer.dnd.R;

import java.util.List;


public class ShopFragment extends Fragment {

    private ShopViewModel shopViewModel;
    private RecyclerView recyclerView;
    private ShopAdapter adapter;
    private ImageView shopImageView;
    private ShopInfo shopInfo;
    private TextView shopName;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        shopInfo = ShopFragmentArgs.fromBundle(getArguments()).getShop();
        Log.d(ShopFragment.class.getSimpleName(),shopInfo.getName());
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        shopViewModel =
                ViewModelProviders.of(this).get(ShopViewModel.class);
        final View root = inflater.inflate(R.layout.fragment_shop, container, false);
        shopImageView = root.findViewById(R.id.homeImage);
        shopName = root.findViewById(R.id.shopNameTitle);
        shopViewModel.init();
        recyclerView = root.findViewById(R.id.shopRecyclerView);
        GridLayoutManager layoutManager = new GridLayoutManager(root.getContext(), 1);
        recyclerView.setLayoutManager(layoutManager);
        adapter = new ShopAdapter(root.getContext(), shopViewModel.getGoods().getValue());
        recyclerView.setAdapter(adapter);

        int drawableId = root.getContext().getResources().getIdentifier(shopInfo.getImage(), "drawable", root.getContext().getPackageName());
        shopImageView.setImageResource(drawableId);
        shopName.setText(shopInfo.getName());

        shopViewModel.getGoods().observe(this, new Observer<List<GoodInfo>>() {
            @Override
            public void onChanged(List<GoodInfo> goodInfos) {

            }
        });

        return root;
    }


}
