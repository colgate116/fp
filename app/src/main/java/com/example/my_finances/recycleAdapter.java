package com.example.my_finances;

import android.annotation.SuppressLint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.text.DecimalFormat;
import java.util.ArrayList;

public class recycleAdapter extends RecyclerView.Adapter<recycleAdapter.MyViewHolder> {
    ArrayList<Card> cardList;
    RecyclerViewClickListener listener;

    public recycleAdapter(ArrayList<Card> cardList,RecyclerViewClickListener listener){

        this.cardList=cardList;
        this.listener=listener;
    }



    public class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        TextView tittle;
        TextView annotation;
        TextView score;
        ImageButton star;
        boolean favouriteS;

        public MyViewHolder(final View view) {
            super(view);
            star=view.findViewById(R.id.favourite);
            tittle =view.findViewById(R.id.tittle_card);
            annotation=view.findViewById(R.id.annotation);
            score=view.findViewById(R.id.score);
            star.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(favouriteS){
                        favouriteS =false;
                        star.setImageResource(R.drawable.ic_star_border);
                    }
                    else {
                        favouriteS =true;
                        star.setImageResource(R.drawable.ic_star);
                    }
                    cardList.get(getBindingAdapterPosition()).setFavourite(favouriteS);
                }
            });
            view.setOnClickListener(this);

        }


        @Override
        public void onClick(View v) {
            listener.onClick(v,getBindingAdapterPosition());
        }
    }

    @NonNull
    @Override
    public recycleAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView= LayoutInflater.from(parent.getContext()).inflate(R.layout.card_design, parent, false);
        return new MyViewHolder(itemView);
    }

    @SuppressLint("DefaultLocale")
    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {

        String name=cardList.get(position).getTittle();
        String annotation=cardList.get(position).getAnnotation();
        Double score=cardList.get(position).getScore();

        holder.annotation.setText(annotation);
        String textScore;


        if (score%1==0){
            DecimalFormat decimalFormat=new DecimalFormat("#.###");
            textScore=decimalFormat.format(score);
        }
        else
            textScore=String.format("%.2f",score);
        boolean fav=cardList.get(position).isFavourite();
        if (fav){
            holder.star.setImageResource(R.drawable.ic_star);
        }
        else
            holder.star.setImageResource(R.drawable.ic_star_border);


        holder.score.setText(textScore);
        holder.tittle.setText(name);




    }


    @Override
    public int getItemCount() {
        return cardList.size();
    }
    public  interface RecyclerViewClickListener{
        void onClick(View v,int position);

    }
}


