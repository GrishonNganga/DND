package com.project.balmer.dnd.ui.shop;


import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.snackbar.Snackbar;
import com.project.balmer.dnd.Model.CustomerInfo;
import com.project.balmer.dnd.R;

public class ShopOrder extends Fragment {

    private CustomerInfo customer;
    ShopViewModel shopViewModel;
    private TextView quantityText, add, remove;
    private Button checkOut;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        shopViewModel =
                ViewModelProviders.of(this).get(ShopViewModel.class);
        View view = inflater.inflate(R.layout.fragment_shop_order, container, false);

        //Add Views...
        quantityText = view.findViewById(R.id.quantity);
        add = view.findViewById(R.id.add);
        remove = view.findViewById(R.id.minus);
        checkOut = view.findViewById(R.id.checkOutBtn);

        //Make checkout Invisible
        checkOut.setVisibility(View.INVISIBLE);

        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                shopViewModel.addQuantity();
                Toast.makeText(view.getContext(), "Clicked add", Toast.LENGTH_SHORT).show();

            }
        });
        remove.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                shopViewModel.removeQuantity();
            }
        });

        checkOut.setOnClickListener(Navigation.createNavigateOnClickListener(R.id.action_shopOrder_to_nav_slideshow));
        //Start the observers
        shopViewModel.getCustomer().observe(this, new Observer<CustomerInfo>() {
            @Override
            public void onChanged(CustomerInfo customerInfo) {
                customer = customerInfo;
            }
        });
        shopViewModel.getQuantity().observe(this, new Observer<String>() {
            @Override
            public void onChanged(String s) {
                quantityText.setText(s);
                displayCheckOut(s);

            }
        });



        return view;
    }

    private void displayCheckOut(String quanti) {

        int check = Integer.parseInt(quanti);
        if (check > 0){
            checkOut.setVisibility(View.VISIBLE);
        }
        if (check == 0)
        checkOut.setVisibility(View.INVISIBLE);
    }

}
