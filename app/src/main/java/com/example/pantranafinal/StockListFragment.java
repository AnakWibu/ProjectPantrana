package com.example.pantranafinal;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.pantranafinal.Adapter.StockRecyclerAdapter;
import com.example.pantranafinal.Database.DatabaseHelper;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class StockListFragment extends Fragment {

    private RecyclerView rvStockList;
    private FloatingActionButton floatBtnAddStock;

    public DatabaseHelper databaseHelper;
    ArrayList<String> StockId, StockName, StockQuantity, StockExpired;

    StockRecyclerAdapter stockRecyclerAdapter;

    public StockListFragment() {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_stock_list, container, false);

        rvStockList = view.findViewById(R.id.rvStockList);
        floatBtnAddStock = view.findViewById(R.id.floatBtnAddStock);

        floatBtnAddStock.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent goToStockAdd = new Intent(getContext(), StockAdd.class);
                startActivity(goToStockAdd);
            }
        });

        databaseHelper = new DatabaseHelper(getContext());
        StockId = new ArrayList<>();
        StockName = new ArrayList<>();
        StockQuantity = new ArrayList<>();
        StockExpired = new ArrayList<>();

        storeData();

        stockRecyclerAdapter= new StockRecyclerAdapter(getContext(), StockId, StockName, StockQuantity, StockExpired);
        rvStockList.setAdapter(stockRecyclerAdapter);
        rvStockList.setLayoutManager(new LinearLayoutManager(getContext()));

        return view;
    }
    void storeData(){

        Cursor cursor = databaseHelper.readAllData();

        if(cursor.getCount() == 0){

            Toast.makeText(getContext(), "No data", Toast.LENGTH_SHORT).show();
        }else{

            while(cursor.moveToNext()){

                StockId.add(cursor.getString(0));
                StockName.add(cursor.getString(1));
                StockQuantity.add(cursor.getString(2));
                StockExpired.add(cursor.getString(3));
            }
        }
    }
}