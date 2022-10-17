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
import com.example.pantranafinal.StockDetail;
import com.example.pantranafinal.StockUpdate;

import java.util.ArrayList;

public class StockRecyclerAdapter extends RecyclerView.Adapter<StockRecyclerAdapter.ViewHolder>{

    Context context;
    ArrayList StockId, StockName, StockQuantity, StockExpired;

    public StockRecyclerAdapter(Context context, ArrayList StockId, ArrayList StockName,
                         ArrayList StockQuantity, ArrayList StockExpired){

        this.context = context;
        this.StockId = StockId;
        this.StockName = StockName;
        this.StockQuantity = StockQuantity;
        this.StockExpired = StockExpired;
    }

    @NonNull
    @Override
    public StockRecyclerAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.stock_list_card, parent, false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull StockRecyclerAdapter.ViewHolder holder, int position) {

        String temp = String.valueOf(StockId.get(position));
        String temp1 = String.valueOf(StockName.get(position));
        String temp2 = String.valueOf(StockQuantity.get(position));
        String temp3 = String.valueOf(StockExpired.get(position));

        holder.txvStockName.setText(temp1);
        holder.txvStockQuantity.setText(temp2 + " gr");
        holder.txvStockExpired.setText("Expired date: " + temp3);
        holder.cardStock.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent goToUpdateStock = new Intent(context, StockDetail.class);
                goToUpdateStock.putExtra("id", temp);
                goToUpdateStock.putExtra("name", temp1);
                goToUpdateStock.putExtra("quantity", temp2);
                goToUpdateStock.putExtra("expired", temp3);
                context.startActivity(goToUpdateStock);
            }
        });
    }

    @Override
    public int getItemCount() {
        return StockName.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView txvStockName, txvStockQuantity, txvStockExpired;
        CardView cardStock;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            txvStockName = itemView.findViewById(R.id.txvStockName);
            txvStockQuantity = itemView.findViewById(R.id.txvStockQuantity);
            txvStockExpired = itemView.findViewById(R.id.txvStockExpired);
            cardStock = itemView.findViewById(R.id.cardStock);
        }
    }
}
