package com.example.pantranafinal.Adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.pantranafinal.R;
import com.example.pantranafinal.RecipeDetail;
import com.example.pantranafinal.StockUpdate;

import java.util.ArrayList;

public class RecipeRecyclerAdapter extends RecyclerView.Adapter<RecipeRecyclerAdapter.ViewHolder>{

    Context context;
    ArrayList RecipeId, RecipeName, RecipeDesc;

    public RecipeRecyclerAdapter(Context context, ArrayList RecipeId, ArrayList RecipeName,
                                ArrayList RecipeDesc){

        this.context = context;
        this.RecipeId = RecipeId;
        this.RecipeName = RecipeName;
        this.RecipeDesc = RecipeDesc;
    }

    @NonNull
    @Override
    public RecipeRecyclerAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.recipe_list_card, parent, false);

        return new RecipeRecyclerAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecipeRecyclerAdapter.ViewHolder holder, int position) {

        String temp = String.valueOf(RecipeId.get(position));
        String temp1 = String.valueOf(RecipeName.get(position));
        String temp2 = String.valueOf(RecipeDesc.get(position));

        holder.txvRecipeName.setText(temp1);
        holder.txvRecipeShortDesc.setText(temp2);
        holder.cardRecipe.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent goToRecipeDetail = new Intent(context, RecipeDetail.class);
                goToRecipeDetail.putExtra("id", temp);
                goToRecipeDetail.putExtra("name", temp1);
                goToRecipeDetail.putExtra("desc", temp2);
                context.startActivity(goToRecipeDetail);
            }
        });
    }

    @Override
    public int getItemCount() {
        return RecipeId.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView txvRecipeName, txvRecipeShortDesc;
        CardView cardRecipe;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            txvRecipeName = itemView.findViewById(R.id.txvRecipeName);
            txvRecipeShortDesc = itemView.findViewById(R.id.txvRecipeShortDesc);
            cardRecipe = itemView.findViewById(R.id.cardRecipe);
        }
    }
}
