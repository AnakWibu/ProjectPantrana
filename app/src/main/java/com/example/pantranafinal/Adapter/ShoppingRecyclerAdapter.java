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
import com.example.pantranafinal.ShoppingAddConfirmation;
import com.example.pantranafinal.ShoppingDatabase.Shopping;
import com.example.pantranafinal.StockUpdate;

import java.util.ArrayList;

public class ShoppingRecyclerAdapter extends RecyclerView.Adapter<ShoppingRecyclerAdapter.ViewHolder>{

    Context context;
    ArrayList<Shopping> shoppingArrayList;

    public ShoppingRecyclerAdapter(Context context, ArrayList shoppingArrayList){

        this.context = context;
        this.shoppingArrayList = shoppingArrayList;
    }

    @NonNull
    @Override
    public ShoppingRecyclerAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.shopping_list_card, parent, false);

        return new ShoppingRecyclerAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ShoppingRecyclerAdapter.ViewHolder holder, int position) {

        String temp1 = shoppingArrayList.get(position).getShoppingName();
        int temp = shoppingArrayList.get(position).getShoppingQuantity();
        String temp3 = shoppingArrayList.get(position).getShoppingExpired();

        String temp2 = String.valueOf(temp);

        holder.txvShoppingName.setText(temp1);
        holder.txvShoppingQuantity.setText(temp2 + " gr");
        holder.cardShopping.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent goToRecipeDetail = new Intent(context, ShoppingAddConfirmation.class);
                goToRecipeDetail.putExtra("name", temp1);
                goToRecipeDetail.putExtra("quantity", temp2);
                goToRecipeDetail.putExtra("expired", temp3);
                context.startActivity(goToRecipeDetail);
            }
        });
    }

    @Override
    public int getItemCount() {
        return shoppingArrayList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView txvShoppingName, txvShoppingQuantity;
        CardView cardShopping;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            txvShoppingName = itemView.findViewById(R.id.txvShoppingName);
            txvShoppingQuantity = itemView.findViewById(R.id.txvShoppingQuantity);
            cardShopping = itemView.findViewById(R.id.cardShopping);
        }
    }
}
