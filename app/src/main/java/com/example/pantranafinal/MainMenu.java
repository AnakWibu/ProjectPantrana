package com.example.pantranafinal;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainMenu extends AppCompatActivity {

    private BottomNavigationView bottomNav;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_menu);

        bottomNav = findViewById(R.id.bottomNav);
        replaceFragment(new StockListFragment());

        bottomNav.setOnItemSelectedListener(item -> {

            switch(item.getItemId()){

                case R.id.menuStock:
                    replaceFragment(new StockListFragment());
                    break;
                case R.id.menuRecipe:
                    replaceFragment(new RecipeListFragment());
                    break;
                case R.id.menuShopping:
                    replaceFragment(new ShoppingListFragment());
                    break;
            }

            return true;
        });
    }

    private void replaceFragment(Fragment fragment){

        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.frameMain, fragment);
        fragmentTransaction.commit();
    }
}