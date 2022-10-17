package com.example.pantranafinal;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.pantranafinal.Adapter.ShoppingRecyclerAdapter;
import com.example.pantranafinal.ShoppingDatabase.Shopping;
import com.example.pantranafinal.ShoppingDatabase.ShoppingArray;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class ShoppingListFragment extends Fragment {

    private RecyclerView rvShoppingList;
    private FloatingActionButton floatBtnAddShopping, floatBtnClearShopping;

    ArrayList<Shopping> shoppingArrayList = ShoppingArray.shoppingArrayList;
    ShoppingRecyclerAdapter shoppingRecyclerAdapter;

    public ShoppingListFragment() {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_shopping_list, container, false);

        rvShoppingList = view.findViewById(R.id.rvShoppingList);
        floatBtnAddShopping = view.findViewById(R.id.floatBtnAddShopping);
        floatBtnClearShopping = view.findViewById(R.id.floatBtnClearShopping);

        floatBtnAddShopping.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent goToShoppingAdd = new Intent(getContext(), ShoppingAdd.class);
                startActivity(goToShoppingAdd);
            }
        });

        floatBtnClearShopping.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent goToClearShopping = new Intent(getContext(), ShoppingClearConfirmation.class);
                startActivity(goToClearShopping);
            }
        });

        shoppingRecyclerAdapter = new ShoppingRecyclerAdapter(getContext(), shoppingArrayList);
        rvShoppingList.setAdapter(shoppingRecyclerAdapter);
        rvShoppingList.setLayoutManager(new LinearLayoutManager(getContext()));

        return view;
    }
}