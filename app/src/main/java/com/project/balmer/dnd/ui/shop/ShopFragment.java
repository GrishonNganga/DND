package com.project.balmer.dnd.ui.shop;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.project.balmer.dnd.Model.GoodInfo;
import com.project.balmer.dnd.R;

import java.util.List;


public class ShopFragment extends Fragment {

    private ShopViewModel shopViewModel;
    private RecyclerView recyclerView;
    private ShopAdapter adapter;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        shopViewModel =
                ViewModelProviders.of(this).get(ShopViewModel.class);
        final View root = inflater.inflate(R.layout.fragment_shop, container, false);
        shopViewModel.init();
        recyclerView = root.findViewById(R.id.shopRecyclerView);
        GridLayoutManager layoutManager = new GridLayoutManager(root.getContext(), 1);
        recyclerView.setLayoutManager(layoutManager);
        adapter = new ShopAdapter(root.getContext(), shopViewModel.getGoods().getValue());
        recyclerView.setAdapter(adapter);

        shopViewModel.getGoods().observe(this, new Observer<List<GoodInfo>>() {
            @Override
            public void onChanged(List<GoodInfo> goodInfos) {
                adapter.notifyDataSetChanged();
            }
        });

        return root;
    }


}
