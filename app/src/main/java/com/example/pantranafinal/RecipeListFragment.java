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

import com.example.pantranafinal.Adapter.RecipeRecyclerAdapter;
import com.example.pantranafinal.Database.RecipeDatabaseHelper;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;


public class RecipeListFragment extends Fragment {

    private RecyclerView rvRecipeList;
    private FloatingActionButton floatBtnAddRecipe;

    public RecipeDatabaseHelper recipeDatabaseHelper;
    ArrayList<String> RecipeId, RecipeName, RecipeDesc;

    RecipeRecyclerAdapter recipeRecyclerAdapter;

    public RecipeListFragment() {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_recipe_list, container, false);

        rvRecipeList = view.findViewById(R.id.rvRecipeList);
        floatBtnAddRecipe = view.findViewById(R.id.floatBtnAddRecipe);

        floatBtnAddRecipe.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent goToRecipeAdd = new Intent(getContext(), RecipeAdd.class);
                startActivity(goToRecipeAdd);
            }
        });

        recipeDatabaseHelper = new RecipeDatabaseHelper(getContext());
        RecipeId = new ArrayList<>();
        RecipeName = new ArrayList<>();
        RecipeDesc = new ArrayList<>();

        storeData();

        recipeRecyclerAdapter = new RecipeRecyclerAdapter(getContext(), RecipeId, RecipeName,RecipeDesc);
        rvRecipeList.setAdapter(recipeRecyclerAdapter);
        rvRecipeList.setLayoutManager(new LinearLayoutManager(getContext()));

        return view;
    }

    void storeData(){

        Cursor cursor = recipeDatabaseHelper.readAllData();

        if(cursor.getCount() == 0){

            Toast.makeText(getContext(), "No data", Toast.LENGTH_SHORT).show();
        }else{

            while(cursor.moveToNext()){

                RecipeId.add(cursor.getString(0));
                RecipeName.add(cursor.getString(1));
                RecipeDesc.add(cursor.getString(2));
            }
        }
    }
}