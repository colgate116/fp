package com.example.my_finances;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.text.DecimalFormat;
import java.util.ArrayList;

public class RecyclerAdapter_transactions extends RecyclerView.Adapter<RecyclerAdapter_transactions.MyViewHolder>{

    ArrayList<Transactions> transactionsList;
    Context context;

    public  RecyclerAdapter_transactions(ArrayList<Transactions> tl){
transactionsList=tl;
    }
    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
       View itemView=LayoutInflater.from(parent.getContext()).inflate(R.layout.row_list,parent,false);
        return new MyViewHolder(itemView);
    }


    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
       double spending =transactionsList.get(position).getSpending();
       String spd;
        if (spending%1==0){
            DecimalFormat decimalFormat=new DecimalFormat("#.###");
            spd=decimalFormat.format(spending);
        }
        else
            spd=String.format("%.2f",spending);
       holder.score.setText(spd);
String cat=transactionsList.get(position).getCategory();
holder.category.setText(cat);
boolean arr=transactionsList.get(position).isIncome();
if(arr){
    holder.imageView.setImageResource(R.drawable.ic_baseline_arrow_upward_24);
}
else {
    holder.imageView.setImageResource(R.drawable.ic_baseline_arrow_downward_24);
}


    }

    @Override
        public int getItemCount() {
    return transactionsList.size();
    }

    public class MyViewHolder extends  RecyclerView.ViewHolder{
        ImageView imageView;
        TextView score,category;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            imageView=itemView.findViewById(R.id.arrow);
            score=itemView.findViewById(R.id.listScore);
            category=itemView.findViewById(R.id.category);
        }
    }
}