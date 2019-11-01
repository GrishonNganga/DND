package com.project.balmer.dnd.ui.order;

import android.os.Bundle;
import android.os.TestLooperManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.project.balmer.dnd.Model.OrderInfo;
import com.project.balmer.dnd.R;

import java.util.List;

public class OrderFragment extends Fragment {

    private OrderViewModel orderViewModel;
    RecyclerView recyclerView;
    private OrderAdapter orderAdapter;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        orderViewModel =
                ViewModelProviders.of(this).get(OrderViewModel.class);
        final View root = inflater.inflate(R.layout.fragment_checkout, container, false);
        //Set UIs
        Button button = root.findViewById(R.id.buyNow);
        button.setOnClickListener(Navigation.createNavigateOnClickListener(R.id.action_nav_slideshow_to_nav_home));
        orderViewModel.init();
        //Set up recyclerView.
        recyclerView = root.findViewById(R.id.orderRecyclerView);
        GridLayoutManager layoutManager = new GridLayoutManager(root.getContext(), 1 );
        recyclerView.setLayoutManager(layoutManager);
        orderAdapter = new OrderAdapter(root.getContext(), orderViewModel.getOrders().getValue());
        recyclerView.setAdapter(orderAdapter);

        orderViewModel.getOrders().observe(this, new Observer<List<OrderInfo>>() {
            @Override
            public void onChanged(List<OrderInfo> orderInfos) {
                orderAdapter.notifyDataSetChanged();
            }
        });


        return root;
    }
}