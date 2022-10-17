package com.example.pantranafinal;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;

import com.example.pantranafinal.Database.DatabaseHelper;

import java.util.Calendar;

public class StockDetail extends AppCompatActivity {

    private EditText etxStockDetailName, etxStockDetailQuantity;
    private TextView txvStockDetailExpired;
    private Button btnReduceStock, btnDeleteStock;

    private String StockId, StockName, StockQuantity, StockExpired;
    DatabaseHelper databaseHelper;

    private int selectedYear, selectedMonth, selectedDay;
    private DatePickerDialog datePickerDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_stock_detail);

        initDate();
        initUi();
        initControl();
    }

    private void initUi(){

        etxStockDetailName = findViewById(R.id.etxStockDetailName);
        etxStockDetailQuantity = findViewById(R.id.etxStockDetailQuantity);
        txvStockDetailExpired = findViewById(R.id.txvStockDetailExpired);
        btnReduceStock = findViewById(R.id.btnReduceStock);
        btnDeleteStock = findViewById(R.id.btnDeleteStock);

        databaseHelper = new DatabaseHelper(StockDetail.this);

        Intent intent = this.getIntent();

        if(intent != null){

            StockId = intent.getStringExtra("id");
            StockName = intent.getStringExtra("name");
            StockQuantity = intent.getStringExtra("quantity");
            StockExpired = intent.getStringExtra("expired");

            etxStockDetailName.setText(StockName);
            etxStockDetailQuantity.setText(StockQuantity);
            txvStockDetailExpired.setText(StockExpired);
        }
    }

    private void initControl(){

        txvStockDetailExpired.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                datePickerDialog.show();
            }
        });

        btnReduceStock.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String temp = txvStockDetailExpired.getText().toString();
                String temp1 = etxStockDetailName.getText().toString();
                String temp2 = etxStockDetailQuantity.getText().toString();

                databaseHelper.updateData(StockId, temp1, temp2, temp);
                Intent goToMainMenu = new Intent(StockDetail.this, MainMenu.class);
                startActivity(goToMainMenu);
                finish();
            }
        });

        btnDeleteStock.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                databaseHelper.deleteData(StockId);
                Intent goToMainMenu = new Intent(StockDetail.this, MainMenu.class);
                startActivity(goToMainMenu);
                finish();
            }
        });
    }

    private void initDate(){

        Calendar calendar = Calendar.getInstance();
        selectedYear = calendar.get(Calendar.YEAR);
        selectedMonth = calendar.get(Calendar.MONTH);
        selectedDay = calendar.get(Calendar.DAY_OF_MONTH);

        DatePickerDialog.OnDateSetListener dateSetListener = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int i, int i1, int i2) {

                selectedYear = i;
                selectedMonth = i1;
                selectedDay = i2;

                txvStockDetailExpired.setText(selectedDay + "/" + (selectedMonth+1) + "/" + selectedYear);
            }
        };

        datePickerDialog = new DatePickerDialog(this, dateSetListener, selectedYear, selectedMonth, selectedDay);
    }
}