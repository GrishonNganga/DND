package com.project.balmer.dnd.ui.home;

import android.os.Bundle;
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

import com.project.balmer.dnd.Model.ShopInfo;
import com.project.balmer.dnd.R;

import java.util.List;

public class HomeFragment extends Fragment {


    private RecyclerView recyclerView;
    private HomeViewModel homeViewModel;
    private HomeAdapter adapter;
    NavController controller = null;


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        controller = Navigation.findNavController(view);
    }

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        homeViewModel =
                ViewModelProviders.of(this).get(HomeViewModel.class);
        final View root = inflater.inflate(R.layout.fragment_home, container, false);
        homeViewModel.init();

        recyclerView = root.findViewById(R.id.homeRecyclerView);
        GridLayoutManager layoutManager = new GridLayoutManager(root.getContext(),1);
        recyclerView.setLayoutManager(layoutManager);
        adapter = new HomeAdapter(root.getContext(), homeViewModel.getShops().getValue());
        recyclerView.setAdapter(adapter);

        homeViewModel.getShops().observe(this, new Observer<List<ShopInfo>>() {
            @Override
            public void onChanged(List<ShopInfo> shopInfos) {

               if (shopInfos == null){
                   Toast.makeText(root.getContext(), "Your list is empty", Toast.LENGTH_SHORT).show();
               }
                adapter.notifyDataSetChanged();
            }
        });

        return root;
    }
}