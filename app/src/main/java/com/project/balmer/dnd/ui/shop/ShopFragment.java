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
import android.widget.ProgressBar;
import android.widget.TextView;

import com.project.balmer.dnd.Model.GoodInfo;
import com.project.balmer.dnd.Model.ShopInfo;
import com.project.balmer.dnd.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;


public class ShopFragment extends Fragment {

    private ShopViewModel shopViewModel;
    private RecyclerView recyclerView;
    private ShopAdapter adapter;
    private ImageView shopImageView;
    private ShopInfo shopInfo;
    private TextView shopName;
    private ProgressBar spinner;

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
        shopViewModel.init(getActivity());

        shopImageView = root.findViewById(R.id.homeImage);
        shopName = root.findViewById(R.id.shopNameTitle);
        spinner = root.findViewById(R.id.spinner);
        spinner.setVisibility(View.VISIBLE);

        Picasso.get()
                .load(shopInfo.getImage().trim())
                .resize(1050, 800)
                .centerCrop()
                .into(shopImageView);
        shopName.setText(shopInfo.getName());

        shopViewModel.getGoodss().observe(this, new Observer<List<GoodInfo>>() {
            @Override
            public void onChanged(List<GoodInfo> goodInfos) {
                List<GoodInfo> newList = new ArrayList<>();
                for (GoodInfo goods : goodInfos){
                    if (goods.getShopInfo().equals(shopInfo.getId())){
                        Log.e("Used", goods.getName());
                        newList.add(goods);
                    }

                }
                recyclerView = root.findViewById(R.id.shopRecyclerView);
                GridLayoutManager layoutManager = new GridLayoutManager(root.getContext(), 1);
                recyclerView.setLayoutManager(layoutManager);
                adapter = new ShopAdapter(root.getContext(), newList);
                recyclerView.setAdapter(adapter);
                spinner.setVisibility(View.GONE);
            }
        });

        return root;
    }


}
