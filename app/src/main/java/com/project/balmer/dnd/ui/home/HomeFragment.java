package com.project.balmer.dnd.ui.home;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.auth.FirebaseAuth;
import com.project.balmer.dnd.FoodActivity;
import com.project.balmer.dnd.Model.ShopInfo;
import com.project.balmer.dnd.R;

import java.util.List;

public class HomeFragment extends Fragment {


    private RecyclerView recyclerView;

    private HomeAdapter adapter;
    private HomeViewModel homeViewModel;

    @Override
    public void onResume() {
        super.onResume();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        homeViewModel = ViewModelProviders.of(this).get(HomeViewModel.class);
        homeViewModel.init(getActivity());
        Log.e("User status on fragment", FirebaseAuth.getInstance().getCurrentUser().getEmail());

    }

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        final View root = inflater.inflate(R.layout.fragment_home, container, false);


        homeViewModel.getShops().observe(this, new Observer<List<ShopInfo>>() {
            @Override
            public void onChanged(List<ShopInfo> shopInfos) {
                if (shopInfos == null){
                    //Log.e("Empty list", String.valueOf(shopInfos.size()));
                    Toast.makeText(root.getContext(), "Your list is empty", Toast.LENGTH_SHORT).show();
                }
                Log.e("Lets see", String.valueOf(shopInfos.size()));
                recyclerView = root.findViewById(R.id.homeRecyclerView);
                GridLayoutManager layoutManager = new GridLayoutManager(root.getContext(),1);
                recyclerView.setLayoutManager(layoutManager);
                adapter = new HomeAdapter(root.getContext(), shopInfos);
                recyclerView.setAdapter(adapter);
            }
        });
        return root;
    }
}