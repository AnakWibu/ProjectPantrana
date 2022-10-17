package com.example.pantranafinal;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.pantranafinal.Database.DatabaseHelper;
import com.example.pantranafinal.Database.RecipeDatabaseHelper;

public class RecipeDetail extends AppCompatActivity {

    private TextView txvRecipeDetailName, txvRecipeDetailDesc;
    private Button btnDeleteRecipe;

    private String RecipeId, RecipeName, RecipeDesc;
    RecipeDatabaseHelper recipeDatabaseHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recipe_detail);

        initUi();
        initControl();
    }

    private void initUi(){

        txvRecipeDetailName = findViewById(R.id.txvRecipeDetailName);
        txvRecipeDetailDesc = findViewById(R.id.txvRecipeDetailDesc);
        btnDeleteRecipe = findViewById(R.id.btnDeleteRecipe);

        recipeDatabaseHelper = new RecipeDatabaseHelper(RecipeDetail.this);

        Intent intent = this.getIntent();

        if(intent != null){

            RecipeId = intent.getStringExtra("id");
            RecipeName = intent.getStringExtra("name");
            RecipeDesc = intent.getStringExtra("desc");

            txvRecipeDetailName.setText(RecipeName);
            txvRecipeDetailDesc.setText(RecipeDesc);
        }
    }

    private void initControl(){

        btnDeleteRecipe.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                recipeDatabaseHelper.deleteData(RecipeId);
                Intent goToMainMenu = new Intent(RecipeDetail.this, MainMenu.class);
                startActivity(goToMainMenu);
                finish();
            }
        });
    }
}