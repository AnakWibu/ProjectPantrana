package com.example.pantranafinal;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.pantranafinal.Database.RecipeDatabaseHelper;

public class RecipeAdd extends AppCompatActivity {

    private EditText etxRecipeAddName, etxRecipeAddDesc;
    private Button btnStockAdd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recipe_add);

        etxRecipeAddName = findViewById(R.id.etxRecipeAddName);
        etxRecipeAddDesc = findViewById(R.id.etxRecipeAddDesc);
        btnStockAdd = findViewById(R.id.btnStockAdd);

        btnStockAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                RecipeDatabaseHelper recipeDatabaseHelper = new RecipeDatabaseHelper(RecipeAdd.this);

                String name = etxRecipeAddName.getText().toString();
                String desc = etxRecipeAddDesc.getText().toString();

                recipeDatabaseHelper.addData(name, desc);
                Intent goToMainMenu = new Intent(RecipeAdd.this, MainMenu.class);
                startActivity(goToMainMenu);
                finish();
            }
        });
    }
}