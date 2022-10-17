package com.example.pantranafinal;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class StockUpdate extends AppCompatActivity {

    private TextView txvStockUpdateName;
    private EditText etxStockUpdateQuantity;
    private Button btnConfirmStockUpdate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_stock_update);


    }
}