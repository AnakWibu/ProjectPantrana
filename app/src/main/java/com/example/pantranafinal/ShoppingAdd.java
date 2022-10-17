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

import com.example.pantranafinal.ShoppingDatabase.Shopping;
import com.example.pantranafinal.ShoppingDatabase.ShoppingArray;

import java.util.Calendar;

public class ShoppingAdd extends AppCompatActivity {

    private TextView txvShoppingAddExpired;
    private EditText etxShoppingAddName, etxShoppingAddQuantity;
    private Button btnShoppingAdd;

    private int selectedYear, selectedMonth, selectedDay;
    private DatePickerDialog datePickerDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shopping_add);

        initDate();
        initUi();
        initControl();
    }

    private void initUi(){

        txvShoppingAddExpired = findViewById(R.id.txvShoppingAddExpired);
        etxShoppingAddName = findViewById(R.id.etxShoppingAddName);
        etxShoppingAddQuantity = findViewById(R.id.etxShoppingAddQuantity);
        btnShoppingAdd = findViewById(R.id.btnShoppingAdd);
    }

    private void initControl(){

        txvShoppingAddExpired.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                datePickerDialog.show();
            }
        });

        btnShoppingAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String name = etxShoppingAddName.getText().toString();
                String expired = txvShoppingAddExpired.getText().toString();
                String temp = etxShoppingAddQuantity.getText().toString();
                int quantity = Integer.parseInt(temp);

                Shopping shopping = new Shopping(name, expired, quantity);
                ShoppingArray.shoppingArrayList.add(shopping);

                Intent goToMainMenu = new Intent(ShoppingAdd.this, MainMenu.class);
                startActivity(goToMainMenu);
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

                txvShoppingAddExpired.setText(selectedDay + "/" + (selectedMonth+1) + "/" + selectedYear);
            }
        };

        datePickerDialog = new DatePickerDialog(this, dateSetListener, selectedYear, selectedMonth, selectedDay);
    }
}