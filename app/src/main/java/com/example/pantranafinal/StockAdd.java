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

public class StockAdd extends AppCompatActivity {

    private EditText etxStockAddName, etxStockAddQuantity;
    private TextView txvStockAddExpired;
    private Button btnStockAdd;

    public String StockName, StockExpired;
    public int StockQuantity;

    private int selectedYear, selectedMonth, selectedDay;
    private DatePickerDialog datePickerDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_stock_add);

        initDate();
        initUi();
        initControl();
    }

    private void initUi(){

        etxStockAddName = findViewById(R.id.etxStockAddName);
        etxStockAddQuantity = findViewById(R.id.etxStockAddQuantity);
        txvStockAddExpired = findViewById(R.id.txvStockAddExpired);
        btnStockAdd = findViewById(R.id.btnStockAdd);
    }

    private void initControl(){

        txvStockAddExpired.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                datePickerDialog.show();
            }
        });

        btnStockAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                DatabaseHelper databaseHelper = new DatabaseHelper(StockAdd.this);

                StockName = etxStockAddName.getText().toString();
                StockExpired = txvStockAddExpired.getText().toString();

                String temp = etxStockAddQuantity.getText().toString();
                StockQuantity = Integer.parseInt(temp);

                databaseHelper.addData(StockName.trim(), StockQuantity, StockExpired);

                Intent goToStockList = new Intent(StockAdd.this, MainMenu.class);
                startActivity(goToStockList);
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

                txvStockAddExpired.setText(selectedDay + "/" + (selectedMonth+1) + "/" + selectedYear);
            }
        };

        datePickerDialog = new DatePickerDialog(this, dateSetListener, selectedYear, selectedMonth, selectedDay);
    }
}