package com.example.pantranafinal;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.pantranafinal.ShoppingDatabase.ShoppingArray;

import java.util.ArrayList;

public class ShoppingClearConfirmation extends AppCompatActivity {

    private Button btnConfirmClear;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shopping_clear_confirmation);

        initUi();
        initControl();
    }

    private void initUi(){

        btnConfirmClear = findViewById(R.id.btnConfirmClear);
    }

    private void initControl(){

        btnConfirmClear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                ShoppingArray.shoppingArrayList = new ArrayList<>();
                ShoppingArray.shoppingArrayList.clear();

                Intent goToMainMenu = new Intent(ShoppingClearConfirmation.this, MainMenu.class);
                startActivity(goToMainMenu);
            }
        });
    }
}