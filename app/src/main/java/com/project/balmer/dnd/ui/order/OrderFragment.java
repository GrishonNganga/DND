package com.project.balmer.dnd.ui.order;

import android.graphics.Color;
import android.os.Bundle;
import android.os.TestLooperManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.navigation.NavigationView;
import com.project.balmer.dnd.Model.OrderInfo;
import com.project.balmer.dnd.R;

import java.util.List;

public class OrderFragment extends Fragment {

    private OrderViewModel orderViewModel;
    RecyclerView recyclerView;
    private OrderAdapter orderAdapter;
    private ProgressBar spinner;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        orderViewModel =
                ViewModelProviders.of(this).get(OrderViewModel.class);
        final View root = inflater.inflate(R.layout.fragment_checkout, container, false);

        //Set UIs
        spinner = root.findViewById(R.id.spinner);
        spinner.setVisibility(View.VISIBLE);
        Button button = root.findViewById(R.id.buyNow);
        TextView totalAmount = root.findViewById(R.id.totalAmount);
        orderViewModel.totalAmount().observe(this, new Observer<String>() {
            @Override
            public void onChanged(String s) {
                totalAmount.setText(s);
                orderAdapter.notifyDataSetChanged();
                
                if (Integer.parseInt(s) > 0){
                    button.setBackgroundColor(Color.rgb(161,18,56));
                }
                else {
                    button.setBackgroundColor(android.R.drawable.btn_default);
                }

            }
        });
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                orderViewModel.makeOrder();

                OrderFragmentDirections.ActionNavSlideshowToNavHome action = OrderFragmentDirections.actionNavSlideshowToNavHome();
                Navigation.findNavController(view).navigate(action);

                orderViewModel.removeAllOrders();
            }
        });

        //Set up recyclerView.
        recyclerView = root.findViewById(R.id.orderRecyclerView);
        GridLayoutManager layoutManager = new GridLayoutManager(root.getContext(), 1 );
        recyclerView.setLayoutManager(layoutManager);
        orderAdapter = new OrderAdapter(root.getContext(), orderViewModel.getOrders().getValue(), this);
        recyclerView.setAdapter(orderAdapter);
        spinner.setVisibility(View.GONE);

        orderViewModel.getOrders().observe(this, new Observer<List<OrderInfo>>() {
            @Override
            public void onChanged(List<OrderInfo> orderInfos) {
                orderAdapter.notifyDataSetChanged();
            }
        });


        return root;
    }
}