package com.example.pantranafinal;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.pantranafinal.Database.DatabaseHelper;

public class ShoppingAddConfirmation extends AppCompatActivity {

    private Button btnConfirmAdd;

    public String ShoppingName, ShoppingExpired;
    public int ShoppingQuantity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shopping_add_confirmation);

        initUi();
        initControl();
    }

    private void initUi(){

        btnConfirmAdd = findViewById(R.id.btnConfirmAdd);

        Intent intent = this.getIntent();

        if(intent != null){

            ShoppingName = intent.getStringExtra("name");
            ShoppingExpired = intent.getStringExtra("expired");
            String temp = intent.getStringExtra("quantity");

            ShoppingQuantity = Integer.parseInt(temp);
        }
    }

    private void initControl(){

        btnConfirmAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                DatabaseHelper databaseHelper = new DatabaseHelper(ShoppingAddConfirmation.this);
                databaseHelper.addData(ShoppingName, ShoppingQuantity, ShoppingExpired);

                Intent goToMainMenu = new Intent(ShoppingAddConfirmation.this, MainMenu.class);
                startActivity(goToMainMenu);
            }
        });
    }
}