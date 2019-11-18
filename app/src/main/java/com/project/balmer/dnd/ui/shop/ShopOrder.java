package com.project.balmer.dnd.ui.shop;


import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.snackbar.Snackbar;
import com.project.balmer.dnd.Model.CustomerInfo;
import com.project.balmer.dnd.Model.GoodInfo;
import com.project.balmer.dnd.Model.OrderInfo;
import com.project.balmer.dnd.R;
import com.project.balmer.dnd.ui.order.OrderViewModel;
import com.squareup.picasso.Picasso;

public class ShopOrder extends Fragment {

    private CustomerInfo customer;
    ShopViewModel shopViewModel;
    private TextView quantityText, add, remove;
    private Button checkOut, addToCartOnlyBtn;
    private GoodInfo good;
    private ImageView goodImage;
    private TextView goodTitle, goodPrice, goodDescription;
    private OrderViewModel orderViewModel;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        good = ShopOrderArgs.fromBundle(getArguments()).getGood();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        shopViewModel =
                ViewModelProviders.of(this).get(ShopViewModel.class);
        View view = inflater.inflate(R.layout.fragment_shop_order, container, false);
        orderViewModel = ViewModelProviders.of(this).get(OrderViewModel.class);

        //Add Views...
        addViews(view);

        //Set the Views
        setViews();

        //Add item to cart View onClickListener
        addItemToCart();

        //Remove item from cart View onClickListener
        removeItemFromCart();

        //Checkout cart Button onClickListener
        checkOutCart();

        //Add to Cart Button onClickListener
        addToCartOnly();

        //Start the observers
        //Start the customer observer. This will be changed soon
        shopViewModel.getCustomer().observe(this, new Observer<CustomerInfo>() {
            @Override
            public void onChanged(CustomerInfo customerInfo) {
                customer = customerInfo;
            }
        });
        //Start cart goods quantities. That increments or decrements the quantities views
        shopViewModel.getQuantity().observe(this, new Observer<String>() {
            @Override
            public void onChanged(String s) {
                quantityText.setText(s);
                displayCheckOut(s);

            }
        });
        return view;
    }

    private void addToCartOnly() {
        addToCartOnlyBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int howMany = Integer.parseInt(shopViewModel.getQuantity().getValue());
                if (howMany > 0){
                    OrderInfo order = new OrderInfo(null, null, good, String.valueOf(howMany));
                    //First initialize the Order but later will be pulling from Repo.
                    orderViewModel.init();
                    //Add Order
                    orderViewModel.addOrder(order);
                    checkOut.setVisibility(View.VISIBLE);
                }
            }
        });
    }

    private void checkOutCart() {
        checkOut.setOnClickListener(Navigation.createNavigateOnClickListener(R.id.action_shopOrder_to_nav_slideshow));
    }

    private void removeItemFromCart() {
        remove.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                shopViewModel.removeQuantity();
            }
        });
    }

    private void addItemToCart() {
        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                shopViewModel.addQuantity();
                Toast.makeText(view.getContext(), "Clicked add", Toast.LENGTH_SHORT).show();

            }
        });
    }

    private void setViews() {
        Picasso.get()
                .load(good.getImage().trim())
                .resize(1050, 800)
                .centerCrop()
                .into(goodImage);
        goodTitle.setText(good.getName());
        goodPrice.setText(good.getPrice());
        goodDescription.setText(good.getDescription());

        //Make checkout Invisible
        checkOut.setVisibility(View.INVISIBLE);

        shopViewModel.initQuantity();
    }

    private void addViews(View view) {
        quantityText = view.findViewById(R.id.quantity);
        add = view.findViewById(R.id.add);
        remove = view.findViewById(R.id.minus);
        checkOut = view.findViewById(R.id.checkOutBtn);
        goodImage = view.findViewById(R.id.goodImage);
        goodTitle = view.findViewById(R.id.goodNameTitle);
        goodPrice = view.findViewById(R.id.goodPriceTxt);
        goodDescription = view.findViewById(R.id.goodDescription);
        addToCartOnlyBtn = view.findViewById(R.id.addToCartBtn);
    }

    private void displayCheckOut(String quanti) {

        int check = Integer.parseInt(quanti);
        if (check == 0)
        checkOut.setVisibility(View.INVISIBLE);
    }

}
